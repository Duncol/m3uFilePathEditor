package com.duncol.utility;

import java.util.Objects;

import com.duncol.exceptions.InvalidChoiceException;

public class PathChangeFactory {
	private static PathChangeFactory instance;
	private final FileUtils fu;
	private final FilePathInput fpi;
	
//	private PathChangeFactory(Builder builder) {
//		this.fu = builder.fu;
//		this.fpi = builder.fpi;
//	}
	
	private PathChangeFactory(String fileExt) {
		this.fu = new FileUtils();
		this.fpi = new SystemIn(fileExt);
	}
	
	public static PathChangeFactory getInstance(String fileExt) {
		if (instance == null) {
			instance = new PathChangeFactory(fileExt);
//			instance = new PathChangeFactory.Builder()
//						.fu(new FileUtils())
//						.fpi(new SystemIn(fileExt))
//						.build();
		}
		return instance;
	}
	
	public PathChanger getPathChanger(String id) throws InvalidChoiceException {
		if (Objects.equals(id, "1")) {
			return new WholePathChange(fu, fpi);
		}
		else if (Objects.equals(id, "2")) {
			return new DiscLetterChange(fu, fpi);
		}
		else { throw new InvalidChoiceException("Cannot match any PathChanger object"); }
	}
	
//	class Builder {
//		private FileUtils fu;
//		private FilePathInput fpi;
//		
//		Builder() {
//			
//		}
//		
//		public Builder fu(FileUtils fu) {
//			this.fu(fu);
//			return this;
//		}
//		
//		public Builder fpi(FilePathInput fpi) {
//			this.fpi(fpi);
//			return this;
//		}
//		
//		public PathChangeFactory build() {
//			return new PathChangeFactory(this);
//		}
//	}
}
