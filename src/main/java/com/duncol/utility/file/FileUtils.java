package com.duncol.utility.file;

import java.util.ArrayList;

public interface FileUtils {
	ArrayList<String> loadFile(String path);
	void saveFile(ArrayList<String> data, String path);
}
