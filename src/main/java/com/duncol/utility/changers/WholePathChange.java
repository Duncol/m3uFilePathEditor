package com.duncol.utility.changers;

import java.util.ArrayList;

import com.duncol.utility.input.ConsoleGeneralInputReader;

public class WholePathChange extends PathChanger{

    public WholePathChange(Builder builder) {
    	super(builder.fileUtils, builder.fileInputReader);
	}
	
	@Override
	ArrayList<String> parse(ArrayList<String> songPaths) {
		ArrayList<String> newSongPaths = new ArrayList<String>();
        String shortestCommon = shortestCommon(songPaths);
        String newCommonPath = getReplacePathFor(shortestCommon);
        for (String line : songPaths){
            newSongPaths.add(newCommonPath + line.substring(shortestCommon.length()));
        }
        return newSongPaths;
	}
	
    public String getReplacePathFor(String shortestCommon) {
        String path;
        System.out.print("The current shortest common part of filepath is:\n'" + shortestCommon +
                "'\n\nIt will replace this part only, since the rest is singular for specific song/album/band" +
                "\n\nPlease specify new common part of path (starting with disc letter): ");
        do {
        	path = ConsoleGeneralInputReader.read();
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
        return cutRemains(common);
    }
    
    private String cutRemains(String commonRaw) {
    	while (!commonRaw.endsWith("\\")){
    		commonRaw = commonRaw.substring(0, commonRaw.length()-1);
        }
        return commonRaw;
    }
    
    public static class Builder extends PathChanger.Builder<Builder> {
    	public Builder getThis() {
    		return this;
    	}
    	
    	public PathChanger build() {
    		return new WholePathChange(this);
    	}
    }
}