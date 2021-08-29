package com.alkbackend.util;

public class FileNameUtil {

	  public static String getSuffix(String fileName){
	        return fileName.substring(fileName.lastIndexOf("."));
	    }
	  public static String getFileName(String fileOriginName){
	        return UUIDUtils.getUUID() + FileNameUtil.getSuffix(fileOriginName);
	    }
}
