package com.duncol.utility;

import java.util.ArrayList;
import com.duncol.client.Client;

public class WholePathChange extends EditOption{
	
    public void change() {
        String shortestCommon = shortestCommon();
        String newPath = getReplacePath(shortestCommon);
        ArrayList<String> newSongPaths = new ArrayList<String>();
        ArrayList<String> songPathsCopy = Client.songPaths;
        for (String line : songPathsCopy){
            newSongPaths.add(newPath + line.substring(shortestCommon.length()));
        }
        Client.songPaths = newSongPaths;
        System.out.println("Conversion complete!");
    }
    
    public String getReplacePath(String shortestCommon){
        System.out.print("The current shortest common part of filepath is:\n'" + shortestCommon +
                "'\n\nIt will replace this part only, since the rest is singular for specific song/album/band" +
                "\n\nPlease specify new common part of path (starting with disc letter): ");
        String path = "";
        while (path.isEmpty()){
            path = Client.input.nextLine();
        }
        return path.endsWith("\\") ? path : path.concat("\\");
    }
    
    public String shortestCommon(){
        int count = Math.abs(Integer.MAX_VALUE);
        for (int i=0; i<songPathsCopy.size()-1; i++){
            char[] line1 = songPathsCopy.get(i).toCharArray();
            char[] line2 = songPathsCopy.get(i+1).toCharArray();
            for (int j=0; j<line1.length && j<line2.length; j++){
                if (line1[j] == line2[j]) {
                    continue;
                }
                count = Math.min(count, j);
            }
        }
        String common = songPathsCopy.get(0).substring(0, count);
        while (!common.endsWith("\\")){
            common = common.substring(0, common.length()-1);
        }
        return common;
    }
}