package ivfrt.frt.frro.web.service.imagesave;


import java.util.HashMap;

import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageSaveController {

	@Autowired
	ImageSaveRepository imagesaverepo;
	
	@PostMapping("/formc/uploadimage")
	public ResponseEntity<?> saveImageDataTemp(@RequestBody @Valid ImageSave imgdata) {
		HashMap<String, String> resultMap = new HashMap<>();
		try {			
			int status = imagesaverepo.saveImage(imgdata.getApplication_id(),
					Base64.decodeBase64(imgdata.getImg().getBytes())
					,imgdata.getEntered_by());
			if (status == 1) {
				resultMap.put("status", "success");
				resultMap.put("message", "Image Data saved successfully");
			} else if (status == 2) {
				resultMap.put("status", "success");
				resultMap.put("message", "Image Data updated successfully");
			} else {
				resultMap.put("status", "error");
				resultMap.put("message", "Error in uploading image.");
				return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<HashMap<String, String>>(resultMap, HttpStatus.OK);
	}
	
	
	@GetMapping("/formc/downloadimage/{appId}")
	public  ResponseEntity<?> downloadImage(@PathVariable String appId){
		HashMap<String, String> resultMap = new HashMap<>();
		ImageSave imageData=imagesaverepo.downLoadImage(appId);
		if(imageData!=null) {
			resultMap.put("application_id", imageData.getApplication_id());
			resultMap.put("img",Base64.encodeBase64String(imageData.getImgData()) );
			
		}else {

			resultMap.put("status", "error");
			resultMap.put("message", "Error in uploading document.");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		
		}
		return new ResponseEntity<HashMap<String, String>>(resultMap, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/formc/downloadimage-temp/{appId}")
	public  ResponseEntity<?> downloadImageTemp(@PathVariable String appId){
		HashMap<String, String> resultMap = new HashMap<>();
		ImageSave imageData=imagesaverepo.downLoadImageFromTemp(appId);
		if(imageData!=null) {
			resultMap.put("application_id", imageData.getApplication_id());
			resultMap.put("img",Base64.encodeBase64String(imageData.getImgData()) );
			
		}else {
			resultMap.put("status", "error");
			resultMap.put("message", "Error in uploading document.");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		
		}
		return new ResponseEntity<HashMap<String, String>>(resultMap, HttpStatus.OK);
	}
	
}
