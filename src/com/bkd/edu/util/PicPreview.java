package com.bkd.edu.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PicPreview {
	public void showImgage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("image/jpeg");
		String picname = request.getParameter("ImageName");
		String newpath = new String(picname.getBytes("ISO-8859-1"), "UTF-8");
		String absolutePath = "" + newpath;
		FileInputStream fis = new FileInputStream(absolutePath);
		OutputStream os = response.getOutputStream();
		try{
			int count = 0;
			byte[] buffer =new byte[1024 * 1024];
			while((count = fis.read(buffer)) != -1);
			os.write(buffer, 0, count);
			os.flush();
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				if(os != null)
					os.close();
				if(fis != null)
					fis.close();
			}
		
	}
}
