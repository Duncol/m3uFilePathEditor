package com.duncol.utility.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.duncol.client.ConsoleClient;

public class BasicFileUtils implements FileUtils{
	
	public ArrayList<String> loadFile(String filePath)  {
		ArrayList<String> paths = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				paths.add(line);
			}
		} catch (IOException ex) {
			ConsoleClient.log.error("File not found.");
		}
		return paths;
	}
	
	public void saveFile(ArrayList<String> songPaths, String filePath) {
		String newFilePath = filePath.substring(0, filePath.lastIndexOf("\\"));
		try (PrintWriter out = new PrintWriter(new FileWriter(newFilePath + "\\newPlaylist.m3u"))) {
			for (String path : songPaths) {
				out.println(path);
			}
			System.out.println("Operation complete, converted playlist was saved in the same directory, as previous one. Enjoy!");
		} catch (IOException ex) {
			ConsoleClient.log.fatal("Unable to save file");
		}
	}
}
