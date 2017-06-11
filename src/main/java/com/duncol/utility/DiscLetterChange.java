package com.duncol.utility;

import java.util.ArrayList;

public class DiscLetterChange implements PathChanger {
	private FileUtils fu;
	private FilePathInput fpi;
	
    public DiscLetterChange(FileUtils fu, FilePathInput fpi) {
		this.fu = fu;
		this.fpi = fpi;
	}
    
    @Override
    public void run() {
    	// Do metody szablonowej
    	String filePath;
		ArrayList<String> songPaths;
		do {
			filePath = fpi.read();
			songPaths = fu.loadM3u(filePath);
		} while (songPaths.isEmpty());
		
    	ArrayList<String> newSongPaths = new ArrayList<String>();
        String letter = getReplaceLetter();
        for (String songPath : songPaths) {
            newSongPaths.add(songPath.replace(songPath.substring(0,1), letter));
        }
        fu.saveM3u(newSongPaths, filePath);

    }
    public String getReplaceLetter(){
        System.out.print("Please specify the disc letter you would like to implement as new: ");
        String letter;
        do {
        	letter = InputUtils.next();
        } while (!letter.matches("[a-zA-Z]{1}"));
        return letter;
    }
}
