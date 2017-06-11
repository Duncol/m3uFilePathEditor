package com.duncol.utility;

import java.util.ArrayList;

public class WholePathChange implements PathChanger{
	private FileUtils fu;
	private FilePathInput fpi;
	
	WholePathChange(FileUtils fu, FilePathInput fpi) {
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
        String shortestCommon = shortestCommon(songPaths);
        String newCommonPath = getReplacePathFor(shortestCommon);
        for (String line : songPaths){
            newSongPaths.add(newCommonPath + line.substring(shortestCommon.length()));
        }
        fu.saveM3u(newSongPaths, filePath);
    }
    
    public String getReplacePathFor(String shortestCommon) {
        String path;
        System.out.print("The current shortest common part of filepath is:\n'" + shortestCommon +
                "'\n\nIt will replace this part only, since the rest is singular for specific song/album/band" +
                "\n\nPlease specify new common part of path (starting with disc letter): ");

        do {
        	path = InputUtils.next();
        } while (!path.matches("[A-Za-z]:(\\\\\\w+)+\\\\?"));
          
        return path.endsWith("\\") ? path : path.concat("\\");
    }
    
    public String shortestCommon(ArrayList<String> songPaths){
        int count = Math.abs(Integer.MAX_VALUE);
        for (int i=0; i<songPaths.size()-1; i++){
            char[] line1 = songPaths.get(i).toCharArray();
            char[] line2 = songPaths.get(i+1).toCharArray();
            for (int j=0; j<line1.length && j<line2.length; j++){
                if (line1[j] == line2[j]) {
                    continue;
                }
                count = Math.min(count, j);
            }
        }
        String common = songPaths.get(0).substring(0, count);
        // Cuts the unnecessary remains after last backslash
        while (!common.endsWith("\\")){
            common = common.substring(0, common.length()-1);
        }
        return common;
    }
}