package com.tttn.ptithcm.controller.common;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tttn.ptithcm.bean.CodeMsg;
import com.tttn.ptithcm.bean.Result;
import com.tttn.ptithcm.util.StringUtil;


@RequestMapping("/upload")
@Controller
public class UploadController {

	@Value("${file.upload.photo.sufix}")
	private String uploadPhotoSufix;
	
	@Value("${file.upload.photo.maxsize}")
	private long uploadPhotoMaxSize;   //Kích thước 1024kb
	
	@Value("${file.upload.photo.path}")
	private String uploadPhotoPath;//Vị trí lưu trữ tập tin
	
	private Logger log = LoggerFactory.getLogger(UploadController.class);
	
	
	@RequestMapping(value="/upload_photo",method=RequestMethod.POST)
	@ResponseBody
	public Result<String> uploadPhoto(@RequestParam(name="photo",required=true)MultipartFile photo){
		//Xác định xem loại tệp có phải là hình ảnh không
		String originalFilename = photo.getOriginalFilename();
		//Nhận tập tin
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
		if(!uploadPhotoSufix.contains(suffix.toLowerCase())){
			return Result.error(CodeMsg.UPLOAD_PHOTO_SUFFIX_ERROR);
		}
		//photo.getSize()Đơn vị
		if(photo.getSize()/1024 > uploadPhotoMaxSize){
			CodeMsg codeMsg = CodeMsg.UPLOAD_PHOTO_ERROR;
			codeMsg.setMsg("Kích thước hình ảnh không thể vượt quá" + (uploadPhotoMaxSize/1024) + "M");
			return Result.error(codeMsg);
		}
		//Chuẩn bị để lưu tệp
		File filePath = new File(uploadPhotoPath);
		if(!filePath.exists()){
			//Nếu không có thư mục, hãy tạo một thư mục
			filePath.mkdir();
		}
		filePath = new File(uploadPhotoPath + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
		//Xác định xem thư mục tồn tại vào ngày trong ngày, nếu nó không tồn tại, hãy tạo ra
		if(!filePath.exists()){
			//Nếu không có thư mục, hãy tạo một thư mục
			filePath.mkdir();
		}
		String filename = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffix;
		try {
			photo.transferTo(new File(uploadPhotoPath+"/"+filename));   //Tải lên tệp
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Hình ảnh tải lên thành công, lưu vị trí：" + uploadPhotoPath +filename);
		return Result.success(filename);
		
	}
}
