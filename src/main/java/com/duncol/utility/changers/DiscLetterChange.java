package com.duncol.utility.changers;

import java.util.ArrayList;

import com.duncol.utility.input.ConsoleGeneralInputReader;

public class DiscLetterChange extends PathChanger {

    public DiscLetterChange(Builder builder) {
    	super(builder.fileUtils, builder.fileInputReader);
	}
    
    @Override
	ArrayList<String> parse(ArrayList<String> songPaths) {
    	ArrayList<String> newSongPaths = new ArrayList<String>();
        String letter = getReplaceLetter();
        for (String songPath : songPaths) {
            newSongPaths.add(songPath.replace(songPath.substring(0,1), letter));
        }
        return newSongPaths;
    }
    
    public String getReplaceLetter(){
        System.out.print("Please specify the disc letter you would like to implement as new: ");
        String letter;
        do {
        	letter = ConsoleGeneralInputReader.read();
        } while (!letter.matches("[a-zA-Z]{1}"));
        return letter;
    }
    
    public static class Builder extends PathChanger.Builder<Builder> {
    	public Builder getThis() {
    		return this;
    	}
    	
    	public PathChanger build() {
    		return new DiscLetterChange(this);
    	}
    }
}
