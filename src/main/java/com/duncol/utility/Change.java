package com.duncol.utility;

import java.util.ArrayList;
import com.duncol.client.Client;


public abstract class Change {
    ArrayList<String> internalSongPaths = Client.songPaths;
    char[] alphabet = new char[26];
    public abstract void change();
}
