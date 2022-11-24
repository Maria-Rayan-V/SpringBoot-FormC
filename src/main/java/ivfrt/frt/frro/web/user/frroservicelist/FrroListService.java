package ivfrt.frt.frro.web.user.frroservicelist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class FrroListService implements FrroListRepository {

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbcEservices;

	@Override
	public Map<String, Object> fetchFrroListByStateAndCityDist(String nationality, String state, String cityDist) {
		List<FrroList> frroListFinal = new ArrayList<FrroList>();
		List<FrroList> frroListFinal1 = new ArrayList<FrroList>();
		List<FrroList> frroListFinal2 = new ArrayList<FrroList>();
		Map<String, Object> serviceResponse = new HashMap<String, Object>();
		Map<String, String> serviceList = fetchServiceList(nationality);
		int countnationality = 0;
		boolean finalselctflag = true;
		List<FrroList> frroList = fetchListOfFrro(nationality, state, cityDist);

		for (FrroList frroList2 : frroList) {

			if (frroList2.getNatinality().equals("")) {
				frroListFinal1.add(new FrroList(frroList2.getFrroCode(), frroList2.getFrroName()));
			} else {
				countnationality = frroNationality(frroList2.getFrroCode(), nationality);
				if (countnationality == 1) {

					frroListFinal2.add(new FrroList(frroList2.getFrroCode(), frroList2.getFrroName()));
					finalselctflag = false;
				}
			}
		}

		if (finalselctflag) {
			frroListFinal = frroListFinal1;
		} else {
			frroListFinal = frroListFinal2;
		}
		Map<String, String> mapFrroListFinal = new HashMap<>(); 
		for (FrroList itrList : frroListFinal) { 
			mapFrroListFinal.put(itrList.getFrroCode(), itrList.getFrroName()); 
        }
		serviceResponse.put("countFrro",mapFrroListFinal.size());
		serviceResponse.put("frroDescription", mapFrroListFinal);
		serviceResponse.put("services",serviceList );
		return serviceResponse;
	}

	private Map<String, String> fetchServiceList(String nationality) {
		Map<String, String> service = new HashMap<String, String>();
		String query = "SELECT form_type_code,form_type_desc FROM fr_online_form_type order by form_type_desc";

		service = jdbcEservices.query(query, new ResultSetExtractor<Map<String, String>>() {
			@Override
			public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				HashMap<String, String> mapRet = new HashMap<String, String>();
				while (rs.next()) {
					mapRet.put(rs.getString("form_type_code"), rs.getString("form_type_desc"));
				}
				return mapRet;
			}
		});

		return service;
	}

	private int frroNationality(String frroCode, String natinality) {
		String query = " select count(*) from fr_frro_fro_nationality_mapping where frro_fro_code=? and nationality=? and active='Y' ";
		return jdbcEservices.queryForObject(query, new Object[] { frroCode, natinality }, Integer.class);
	}

	private List<FrroList> fetchListOfFrro(String nationality, String state, String cityDist) {

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

		 return jdbcEservices.query(frro_fro_query, new Object[] { cityDist, state }, (rs, rowNum) -> new FrroList(

				rs.getString("frro_fro_code"), rs.getString("frro_fro_desc"),
				rs.getString("nationality") == null ? "" : rs.getString("nationality"))

		);
	}

}
