package spring.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UploadController {
	private Logger log=LoggerFactory.getLogger(getClass());
	//단일파일업로드
	@RequestMapping("/photoUpload")
	 public String file_uploader(HttpServletRequest request, HttpServletResponse response, Editor editor){
		 return null;
}



	 
	
	@RequestMapping("/MultiPhotoUpload")
//	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response){
		@ResponseBody
		  public String multiplePhotoUpload(HttpServletRequest request) {

		    // 파일정보
		    StringBuffer sb = new StringBuffer();
		    try {
		      // 파일명을 받는다 - 일반 원본파일명
		      String oldName = request.getHeader("file-name");
		      // 파일 기본경로 _ 상세경로
		      String filePath = "C:/Users/IT202-04/git/itbank0829/project/src/main/webapp/resource/photo_upload/";
		      String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss")
		                          .format(System.currentTimeMillis()))
		                          .append(UUID.randomUUID().toString())
		                          .append(oldName.substring(oldName.lastIndexOf("."))).toString();
		      InputStream is = request.getInputStream();
		      OutputStream os = new FileOutputStream(filePath + saveName);
		      int numRead;
		      byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
		      while ((numRead = is.read(b, 0, b.length)) != -1) {
		        os.write(b, 0, numRead);
		      }
		      os.flush();
		      os.close();
		      // 정보 출력
		      sb = new StringBuffer();
		      sb.append("&bNewLine=true")
		        .append("&sFileName=").append(oldName)
		        .append("&sFileURL=").append("http://localhost:8080/project/resource/photo_upload/")
		        .append(saveName);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return sb.toString();
		  }




}
