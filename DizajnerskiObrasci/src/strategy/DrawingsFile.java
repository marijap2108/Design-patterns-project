package strategy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.DrawingModel;
import model.shape.Shape;

public class DrawingsFile implements IFileOperator {
	DrawingModel model;
	
	public DrawingsFile(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void writeInFile(String path) throws IOException  {
		try {
			FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);
            
            out.writeObject(model.getShapes());
            
            out.close();
            file.close();
		} catch (IOException ex) {
            System.out.println("IOException is caught");
            ex.printStackTrace();
        }
	}

	@Override
	public void readFromFile(String path) {
		try {   
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(file);
              
			@SuppressWarnings("unchecked")
			ArrayList<Shape> drawings = (ArrayList<Shape>) in.readObject();
              
            in.close();
            file.close();
            
            model.setShapes(drawings);
            
        } catch(IOException ex) {
            System.out.println("IOException is caught");
        } catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
	}
}
