package ivfrt.frt.frro.web.formc.mainform;

public class FormC_UploadPhoto {

	private String form_c_appl_id;
	private String img;
	private byte[] imgData;

	public FormC_UploadPhoto() {
		super();
	}

	public FormC_UploadPhoto(String form_c_appl_id, String img, byte[] imgData) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.img = img;
		this.imgData = imgData;
	}

	public String getForm_c_appl_id() {
		return form_c_appl_id;
	}

	public void setForm_c_appl_id(String form_c_appl_id) {
		this.form_c_appl_id = form_c_appl_id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public byte[] getImgData() {
		return imgData;
	}

	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
	}

}
