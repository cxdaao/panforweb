package com.bkd.edu.util;

import javax.servlet.http.HttpServlet;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

/**
  * 
  * 二维码生成工具类
  */
public class MakeQRCode extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     * 
     * @Title: getCode   
     * @Description: 生成二维码 
     * @param: @param mCode 二维码的url地址
     * @param: @param pathName 二维码全路径名称唯一,否则会覆盖
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
    public static void getCode(String mCode,String pathName) throws Exception{
        File qrCode = QRCode.from(mCode).withCharset("UTF-8").to(ImageType.PNG).withSize(250, 250).file();
        FileInputStream inputStream = new FileInputStream(qrCode);
        FileOutputStream outputStream = new FileOutputStream(new File(pathName+".png"));
        byte[] buffer = new byte[1024];
           int i = -1;
           while ((i = inputStream.read(buffer)) != -1) {
              outputStream.write(buffer, 0, i);
           }
           outputStream.flush();
           outputStream.close();
           inputStream.close();
    }
    
    public static File getCode2(String mCode,String pathName) throws Exception{
        File qrCode = QRCode.from(mCode).withCharset("UTF-8").to(ImageType.PNG).withSize(250, 250).file();
        return qrCode;
    }
    
}
