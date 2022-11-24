package ivfrt.frt.frro.web.service.imagesave;

public interface ImageSaveRepository {
	int saveImage(String imageid, byte[] img,String enteredBy) throws Exception;
	
	ImageSave downLoadImage(String appId);
	ImageSave downLoadImageEDIT(String appId);
	ImageSave downLoadImageFromTemp(String appId);
}
