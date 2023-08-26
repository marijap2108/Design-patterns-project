package strategy;

import java.io.IOException;

public interface IFileOperator {
	void writeInFile(String path) throws IOException;
	void readFromFile(String path);
}
