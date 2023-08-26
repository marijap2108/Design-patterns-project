package strategy;

import java.io.IOException;

public class FileManager implements IFileOperator {
	
	private IFileOperator fileOperator;
	
	public FileManager(IFileOperator fileOperator) {
		this.fileOperator = fileOperator;
	}

	@Override
	public void writeInFile(String path) throws IOException {
		fileOperator.writeInFile(path);
	}

	@Override
	public void readFromFile(String path){
		fileOperator.readFromFile(path);
	}
}
