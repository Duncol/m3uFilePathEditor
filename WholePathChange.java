import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lenovo on 2016-11-25.
 */
public class WholePathChange extends Change{
    public void change() {
        String shortestCommon = shortestCommon();
        String newPath = getReplacePath(shortestCommon);
        List<String> newSongPaths = new ArrayList<>();
        for (String line : internalSongPaths){
            newSongPaths.add(newPath + line.substring(shortestCommon.length()));
        }
        Client.songPaths = (ArrayList) newSongPaths;

    }
    public String getReplacePath(String shortestCommon){
        Scanner scan = new Scanner(System.in);
        System.out.print("The current shortest common part of filepath is:\n'" + shortestCommon +
                "'\n\nIt will replace this part only, since the rest is singular for specific song/album/band" +
                "\n\nPlease specify new common part of path (starting with disc letter): ");
        String path = "";
        while (path.isEmpty()){
            path = scan.nextLine();
        }
        return path.endsWith("\\") ? path : path.concat("\\");
    }
    public String shortestCommon(){
        int count = Math.abs(Integer.MAX_VALUE);
        for (int i=0; i<internalSongPaths.size()-1; i++){
            char[] line1 = internalSongPaths.get(i).toCharArray();
            char[] line2 = internalSongPaths.get(i+1).toCharArray();
            for (int j=0; j<line1.length && j<line2.length; j++){
                if (line1[j] == line2[j]) {
                    continue;
                }
                count = Math.min(count, j);
            }
        }
        String common = internalSongPaths.get(0).substring(0, count);
        while (!common.endsWith("\\")){
            common = common.substring(0, common.length()-1);
        }
        return common;
    }

}
