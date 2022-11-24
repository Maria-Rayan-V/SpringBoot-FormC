package ivfrt.frt.frro.web.service.imagesave;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class ImageSave {
    
	@NotBlank(message = "Applicant ID is Requered")
	private String application_id;
	@NotBlank(message = "Image-Base64 is Requered")
	private  String img;
	
	private String entered_by;
	
	@JsonIgnore
	 private byte[] imgData;
	
	
	
	public ImageSave(
			@NotBlank(message = "Image ID is Requered") @Size(min = 15, max = 15, message = "Invalid length") String imageid,
			@NotBlank(message = "Base64 is Requered") String img,
			String entered_by) {
		super();
		this.application_id = imageid;
		this.img = img;
		this.entered_by = entered_by;
	}
	


	public ImageSave() {
		super();
	}


	public ImageSave(@NotBlank(message = "Applicant ID is Requered") String application_id, String entered_by,
			byte[] imgData) {
		super();
		this.application_id = application_id;
		this.entered_by = entered_by;
		this.imgData = imgData;
	}












	public String getApplication_id() {
		return application_id;
	}




	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}




	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	public String getEntered_by() {
		return entered_by;
	}
	public void setEntered_by(String entered_by) {
		this.entered_by = entered_by;
	}












	public byte[] getImgData() {
		return imgData;
	}












	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
	}






	
	
	
	

	
	
}
