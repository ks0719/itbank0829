package spring.controller;

import java.io.File;
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
		 String return1=request.getParameter("callback");
		 String return2="?callback_func=" + request.getParameter("callback_func");
		 String return3="";
		 String name = "";
		 try {
			if(editor.getFiledata() != null && editor.getFiledata().getOriginalFilename() != null && !editor.getFiledata().getOriginalFilename().equals("")) {
	             // 기존 상단 코드를 막고 하단코드를 이용
	            name = editor.getFiledata().getOriginalFilename().substring(editor.getFiledata().getOriginalFilename().lastIndexOf(File.separator)+1);
				String filename_ext = name.substring(name.lastIndexOf(".")+1);
				filename_ext = filename_ext.toLowerCase();
			   	String[] allow_file = {"jpg","png","bmp","gif"};
			   	int cnt = 0;
			   	for(int i=0; i<allow_file.length; i++) {
			   		if(filename_ext.equals(allow_file[i])){
			   			cnt++;
			   		}
			   	}
			   	if(cnt == 0) {
			   		return3 = "&errstr="+name;
			   	} else {
			   		//파일 기본경로
		    		String dftFilePath = request.getSession().getServletContext().getRealPath("/");
		    		log.debug("기본경로 = "+dftFilePath);
		    		//파일 기본경로 _ 상세경로
		    		String filePath = dftFilePath + "resource"+ File.separator + "photo_upload"+File.separator;
		    		log.debug("상세경로 = "+filePath);
		    		File file = new File(filePath);
		    		if(!file.exists()) {
		    			file.mkdirs();
		    		}
		    		String realFileNm = "";
		    		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
					String today= formatter.format(new java.util.Date());
					realFileNm = today+UUID.randomUUID().toString() + name.substring(name.lastIndexOf("."));
					String rlFileNm = filePath + realFileNm;
					///////////////// 서버에 파일쓰기 /////////////////
					editor.getFiledata().transferTo(new File(rlFileNm));
					///////////////// 서버에 파일쓰기 /////////////////
		    		return3 += "&bNewLine=true";
		    		return3 += "&sFileName="+ name;
		    		return3 += "&sFileURL=/resource/photo_upload/"+realFileNm;
			   	}
			}else {
				  return3 += "&errstr=error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:"+return1+return2+return3;
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
