package com.duncol.utility.changers;

import java.util.ArrayList;

import com.duncol.utility.file.FileUtils;
import com.duncol.utility.input.FileInputReader;

public abstract class PathChanger {
	FileUtils fileUtils;
	FileInputReader fileInputReader;
	String filePath;

	PathChanger(FileUtils fileUtils, FileInputReader fileinputReader) {
		this.fileUtils = fileUtils;
		this.fileInputReader = fileinputReader;
	}

	public final void run() {
		save(parse(specifyAndReadFile()));
	}

	ArrayList<String> specifyAndReadFile() {
		ArrayList<String> songPaths;
		do {
			filePath = fileInputReader.read();
			songPaths = fileUtils.loadFile(filePath);
		} while (songPaths.isEmpty());
		return songPaths;
	}

	abstract ArrayList<String> parse(ArrayList<String> songPaths);

	final void save(ArrayList<String> newSongPaths) {
		fileUtils.saveFile(newSongPaths, this.filePath);
	}

	public abstract static class Builder<T extends Builder<T>> {
		FileUtils fileUtils;
		FileInputReader fileInputReader;

		Builder() {

		}

		public abstract T getThis();

		public T fileUtils(FileUtils fileUtils) {
			this.fileUtils = fileUtils;
			return getThis();
		}

		public T fileInputReader(FileInputReader fileinputReader) {
			this.fileInputReader = fileinputReader;
			return getThis();
		}

		public abstract PathChanger build();
	}
}
