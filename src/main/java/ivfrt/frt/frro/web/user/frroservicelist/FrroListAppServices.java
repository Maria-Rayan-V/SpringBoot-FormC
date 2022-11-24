package ivfrt.frt.frro.web.user.frroservicelist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class FrroListAppServices implements FrroListAppRepository {

	 @Autowired
	 @Qualifier("jdbcEservices")
	 private JdbcTemplate jdbcEservices;
	@Override
	public List<FrroListApp> fetchFrroListByStateAndCityDistAPP(String nationality, String state, String cityDist) {

		List<FrroListApp> frroListFinal = new ArrayList<FrroListApp>();
		List<FrroListApp> frroListFinal1 = new ArrayList<FrroListApp>();
		List<FrroListApp> frroListFinal2 = new ArrayList<FrroListApp>();
	
		int countnationality = 0;
		boolean finalselctflag = true;
		List<FrroListApp> frroList = fetchListOfFrro(nationality, state, cityDist);

		for (FrroListApp frroList2 : frroList) {

			if (frroList2.getNatinality().equals("")) {
				frroListFinal1.add(new FrroListApp(frroList2.getFrroCode(), frroList2.getFrroName()));
			} else {
				countnationality = frroNationality(frroList2.getFrroCode(), nationality);
				if (countnationality == 1) {

					frroListFinal2.add(new FrroListApp(frroList2.getFrroCode(), frroList2.getFrroName()));
					finalselctflag = false;
				}
			}
		}

		if (finalselctflag) {
			frroListFinal = frroListFinal1;
		} else {
			frroListFinal = frroListFinal2;
		}
		
		
	//	Map<String, String> mapFrroListFinal = new HashMap<>(); 
		/*
		 * for (FrroList itrList : frroListFinal) {
		 * mapFrroListFinal.put(itrList.getFrroCode(), itrList.getFrroName()); }
		 * serviceResponse.put("countFrro",mapFrroListFinal.size());
		 * serviceResponse.put("frroDescription", mapFrroListFinal);
		 * //serviceResponse.put("services",serviceList );
		 */		
		return frroListFinal;
	
	}

	private int frroNationality(String frroCode, String natinality) {
		String query = " select count(*) from fr_frro_fro_nationality_mapping where frro_fro_code=? and nationality=? and active='Y' ";
		return jdbcEservices.queryForObject(query, new Object[] { frroCode, natinality }, Integer.class);
	}

	private List<FrroListApp> fetchListOfFrro(String nationality, String state, String cityDist) {

		String frro_fro_query = "";
		

		if (state.equalsIgnoreCase("13") && cityDist.equalsIgnoreCase("14D")) {
			if (nationality.equals("TBT")) {
				frro_fro_query = "select a.frro_fro_code,a.frro_fro_desc,a.nationality  from fr_frro_fro_list a,fr_frro_fro_city_district_mapping b where b.city_district_code =? and b.state_code=?  and a.frro_fro_code=b.frro_fro_code and a.active='Y' and b.active='Y'";
			} else {
				frro_fro_query = "select a.frro_fro_code,a.frro_fro_desc,a.nationality  from fr_frro_fro_list a,fr_frro_fro_city_district_mapping b where b.city_district_code =? and b.state_code=?  and a.frro_fro_code=b.frro_fro_code and a.active='Y' and b.active='Y' and a.office_sub_type_code='CI' ";
			}
		} else {
			frro_fro_query = "select a.frro_fro_code,a.frro_fro_desc,a.nationality  from fr_frro_fro_list a,fr_frro_fro_city_district_mapping b where b.city_district_code =? and b.state_code=?  and a.frro_fro_code=b.frro_fro_code and a.active='Y' and b.active='Y'";
		}

		 return jdbcEservices.query(frro_fro_query, new Object[] { cityDist, state }, (rs, rowNum) -> new FrroListApp(

				rs.getString("frro_fro_code"), rs.getString("frro_fro_desc"),
				rs.getString("nationality") == null ? "" : rs.getString("nationality"))

		);
	}
	
}
