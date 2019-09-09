package com.bkd.edu.util;

import java.util.UUID;

/**
* @ClassName: FileUpload
* @Description:文件重命名，防止文件重名
* @author:cxdaao
* @date:2019.5.24
*
*/ 
public class FileUpload{

    /**
    * @Method: makeFileName
    * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
    * @Anthor:cxdaao
    * @param filename 文件的原始名称
    * @return uuid+"_"+文件的原始名称
    */ 
	static public String makeFileName(String filename){  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + filename;
    }
}