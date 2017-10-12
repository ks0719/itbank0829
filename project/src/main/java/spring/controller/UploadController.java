package spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UploadController {
	
	private String getNick(HttpServletRequest req) throws Exception {
		Cookie[] c=req.getCookies();
		if(c != null){
	        for(int i=0; i < c.length; i++){
	            Cookie ck = c[i] ;
	            // 저장된 쿠키 이름을 가져온다
	            String cName = ck.getName();
	            // 쿠키값을 가져온다
	            String cValue =  URLDecoder.decode(ck.getValue(),"utf-8");
//	            log.debug("쿠키값  :"+cValue);
	            if(cName.equals("mynick")) {
	            	return cValue;
	            }
	        }
		}
		return "";
	}
	
	private Logger log=LoggerFactory.getLogger(getClass());
	//단일파일업로드
	@RequestMapping("/photoUpload")
	public String photoUpload(HttpServletRequest request, PhotoVo vo){
	    String callback = vo.getCallback();
	    String callback_func = vo.getCallback_func();
	    String file_result = "";
	    try {
	        if(vo.getFiledata() != null && vo.getFiledata().getOriginalFilename() != null && !vo.getFiledata().getOriginalFilename().equals("")){
	            //파일이 존재하면
	            String original_name = vo.getFiledata().getOriginalFilename();
	            String ext = original_name.substring(original_name.lastIndexOf(".")+1);
	            //파일 기본경로
	           // String defaultPath = request.getSession().getServletContext().getRealPath("/");
	           // System.out.println("파일 기본경로 = "+defaultPath);
	            //파일 기본경로 _ 상세경로
	            //String path = "C:/Users/IT202-04/git/itbank0829/project/src/main/webapp/resource/photo_upload/";   
	            //String path = "D:/SW-1/5.Spring/sts-work/maven.1505120132372/project/src/main/webapp/resource/photo_upload/";
	            String path=request.getServletContext().getRealPath("/resource/photo_upload/");
	            File file = new File(path);
	            //System.out.println("path:"+path);
	            //디렉토리 존재하지 않을경우 디렉토리 생성
	            if(!file.exists()) {
	                file.mkdirs();
	            }
	            //서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
	            String realname = UUID.randomUUID().toString() + "." + ext;
	        ///////////////// 서버에 파일쓰기 ///////////////// 
	            vo.getFiledata().transferTo(new File(path+realname));
	            file_result += "&bNewLine=true&sFileName="+original_name+"&sFileURL="+request.getServletContext().getContextPath()+"/resource/photo_upload"+realname;
	           // System.out.println("file="+file_result);
	        } else {
	            file_result += "&errstr=error";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "redirect:" + callback + "?callback_func="+callback_func+file_result;
	}

	@RequestMapping("/MultiPhotoUpload")
		@ResponseBody
		  public String multiplePhotoUpload(HttpServletRequest request) {

		    // 파일정보
			StringBuffer img=new StringBuffer();
		    StringBuffer sb = new StringBuffer();
		    try {
		      // 파일명을 받는다 - 일반 원본파일명
		      String oldName = request.getHeader("file-name");
		      // 파일 기본경로 _ 상세경로
//		      String filePath = "C:/Users/IT202-04/git/itbank0829/project/src/main/webapp/resource/photo_upload/";
		      String filePath = request.getServletContext().getRealPath("/resource/photo_upload/");   
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
		        .append("&sFileURL=").append(request.getServletContext().getContextPath())
		        .append("/resource/photo_upload/"+saveName);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return sb.toString();
		  }
}
