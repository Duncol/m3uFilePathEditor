package com.duncol.utility;

import java.util.ArrayList;

import com.duncol.client.Client;

public abstract class EditOption {
	ArrayList<String> songPathsCopy = Client.songPaths;
    char[] alphabet = new char[26];
    public abstract void change();
}
