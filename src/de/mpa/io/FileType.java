package de.mpa.io;

import java.io.File;
import java.io.IOException;

import de.mpa.io.MascotGenericFileReader.LoadMode;

public enum FileType {
	
	MGF_FILE {
		@Override
		public InputFileReader createInputFileReader(File file) throws IOException {
			return new MascotGenericFileReader(file, LoadMode.NONE);
		}
	};
	
	public abstract InputFileReader createInputFileReader(File file) throws IOException;
	
}
