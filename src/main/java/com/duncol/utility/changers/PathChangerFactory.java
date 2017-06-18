package com.duncol.utility.changers;

import java.util.Objects;

import com.duncol.exceptions.InvalidChoiceException;
import com.duncol.utility.file.BasicFileUtils;
import com.duncol.utility.input.FileInputReader;
import com.duncol.utility.input.InputReadersFactory;

public class PathChangerFactory {
	private static PathChangerFactory instance;
	
	private final BasicFileUtils basicFileUtils;
	private final FileInputReader inputReader;
	
	private PathChangerFactory(String fileExtension) {
		this.basicFileUtils = new BasicFileUtils();
		this.inputReader = InputReadersFactory.getInstance()
										.consoleInputFilePathReader(fileExtension);
	}
	
	public static PathChangerFactory getInstance(String fileExt) {
		if (instance == null) {
			instance = new PathChangerFactory(fileExt);
		}
		return instance;
	}
	
	public PathChanger getPathChanger(String id) throws InvalidChoiceException {
		if (Objects.equals(id, "1")) {
			return new WholePathChange.Builder()
					.fileUtils(basicFileUtils)
					.fileInputReader(inputReader)
					.build();
		}
		else if (Objects.equals(id, "2")) {
			return new DiscLetterChange.Builder()
					.fileUtils(basicFileUtils)
					.fileInputReader(inputReader)
					.build();
		}
		else { throw new InvalidChoiceException("Cannot match any PathChanger object"); }
	}

}
