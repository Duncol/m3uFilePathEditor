

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2016-11-25.
 */
public abstract class Change {
    List<String> internalSongPaths = Client.songPaths;
    char[] alphabet = new char[26];
    abstract void change();
}
