package com.duncol.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileUtils {
	
	ArrayList<String> loadM3u(String filePath)  {
		ArrayList<String> paths = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				paths.add(line);
			}
		} catch (IOException ex) {
			System.out.println("File not found.");
		}
		return paths;
	}
	
	void saveM3u(ArrayList<String> songPaths, String filePath) {
		String newFilePath = filePath.substring(0, filePath.lastIndexOf("\\"));
		try (PrintWriter out = new PrintWriter(new FileWriter(newFilePath + "\\newPlaylist.m3u"))) {
			for (String path : songPaths) {
				out.println(path);
			}
			System.out.println("Operation complete, converted playlist was saved in the same directory, as previous one. Enjoy!");
		} catch (IOException ex) {
			System.out.println("Operation failed.");
		}
	}
}
