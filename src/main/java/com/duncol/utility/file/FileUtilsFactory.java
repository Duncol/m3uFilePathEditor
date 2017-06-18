package com.duncol.utility.file;

public class FileUtilsFactory {
	private static FileUtilsFactory instance;
	
	public static FileUtilsFactory getInstance() {
		if (instance == null) {
			instance = new FileUtilsFactory();
		}
		return instance;
	}
	
	public BasicFileUtils getBasicInputUtils() {
		return new BasicFileUtils();
	}
}
