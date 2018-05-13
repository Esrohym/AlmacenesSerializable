package Almacenfiles;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ModeloArrayListFile extends ModeloArrayList {
	
	
	public ModeloArrayListFile(String str) {
		
		super();
		loadFile(str);
	}
	
	public void loadFile(String str) {
	    
    	try{
    		
    		FileInputStream fis=new FileInputStream(str);
    		ObjectInputStream ois=new ObjectInputStream(fis);
    		
    		try{
    			Producto aux=(Producto)ois.readObject();
    			
    			while(true) {	
    				super.insertarProducto(aux);
    				aux=(Producto)ois.readObject();
    			}
    		}
    		catch(EOFException eofe) {
    			
    			fis.close();
    			ois.close();
    		}
    	}
    	catch(IOException ioe) {
    		
    		System.err.println(" Error en E/S sobre fichero "+str+ " "+ioe);
    	}
    	catch(ClassNotFoundException cce) {
    		
    		System.err.println(" El fichero no tiene objetos ");
    	}
    	
    }
	
	public void toFile(String str) {
    	
        try {	
        	FileOutputStream fos=new FileOutputStream(str);
        	ObjectOutputStream oos=new ObjectOutputStream(fos);
        	
        	for(Producto p:super.getLista()) {
        		oos.writeObject(p);
        	}
        	fos.close();
        	oos.close();
        }
        catch(IOException ioe) {
        	
        	System.err.println(" Error en E/S sobre fichero "+str+ " "+ioe);
        }
        	
    }
	
	
	
	

}
