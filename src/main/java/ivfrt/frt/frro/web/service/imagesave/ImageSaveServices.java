package ivfrt.frt.frro.web.service.imagesave;

import java.io.ByteArrayInputStream;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImageSaveServices  implements ImageSaveRepository{

	@Autowired
	@Qualifier("jdbcFormC")
	JdbcTemplate jdbcFormC;
	
	@Override
	public int saveImage(String imageid, byte[] img, String enteredBy) throws Exception {
	
	/*	if (formc.getImg().contains("<") || formc.getImg().contains(">") || formc.getImg().contains(".exe")
				|| formc.getImg().contains(".bat") || formc.getImg().contains(".com") || formc.getImg().contains(".cmd")
				|| formc.getImg().contains(".run")) {
			throw new Exception("Invalid Image.");
		}
		*/
			String mimeType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(img));
			System.out.println("----contentType---------"+mimeType);
			if(mimeType.equalsIgnoreCase("image/jpeg") || mimeType.equalsIgnoreCase("image/jpg")) {
				
			}else {
				throw new Exception("Invalid Image! only JPEG/JPG allowed.");
			}
			
		
		
		int status=0;
		
		String query="select count(1) from fr_formc_photo_temp where applicant_id=?";
		int found=jdbcFormC.queryForObject(query,new Object[] {imageid }, Integer.class);
		
		if(found==0) {
			String query1="INSERT INTO fr_formc_photo_temp(" + 
					"	applicant_id, photo, entered_by, entered_on) " + 
					"	VALUES (?, ?, ?, clock_timestamp()) ";
			jdbcFormC.update(query1,
					imageid,
					img,
					enteredBy
					);
			status=1;
		}else {
			String query2="UPDATE fr_formc_photo_temp " + 
					"	SET  photo=?, entered_by=?, entered_on=clock_timestamp() " + 
					"	WHERE applicant_id=?";
			
			jdbcFormC.update(query2,
					img,
					enteredBy,
					imageid
					);
			status=2;
		}
		
		return status;
	}

	@Override
	public ImageSave downLoadImage(String appId) {
		ImageSave res=null;
		String query="SELECT applicant_id, photo, entered_by, entered_on " + 
				" FROM  fr_formc_photo where applicant_id=? ";
		try {
			res= jdbcFormC.queryForObject(query,new Object[] {appId },
				(rs, rowNum)->new ImageSave(rs.getString("applicant_id"),
						rs.getString("entered_by"),rs.getBytes("photo")));
		}catch(Exception e) {
			 
		}
		return res;
	  }
	
	@Override
	public ImageSave downLoadImageEDIT(String appId) {
		ImageSave res=null;
		String query="SELECT applicant_id, photo, entered_by, entered_on " + 
				" FROM  fr_formc_photo_temp where applicant_id=? ";
		try {
			res= jdbcFormC.queryForObject(query,new Object[] {appId },
				(rs, rowNum)->new ImageSave(rs.getString("applicant_id"),
						rs.getString("entered_by"),rs.getBytes("photo")));
		}catch(Exception e) {
			 
		}
		return res;
	  }

	@Override
	public ImageSave downLoadImageFromTemp(String appId) {
		ImageSave res=null;
		String query="SELECT applicant_id, photo, entered_by, entered_on " + 
				" FROM  fr_formc_photo_temp where applicant_id=? ";
		try {
			res= jdbcFormC.queryForObject(query,new Object[] {appId },
				(rs, rowNum)->new ImageSave(rs.getString("applicant_id"),
						rs.getString("entered_by"),rs.getBytes("photo")));
		}catch(Exception e) {
			 
		}
		return res;
	  }
}
