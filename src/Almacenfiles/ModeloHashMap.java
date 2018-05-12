package Almacenfiles;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * Write a description of class ModeloHaspMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import java.util.Map;

public class ModeloHashMap extends ModeloAbs implements Serializable
{
    private HashMap <Integer,Producto> lista;
    
    public ModeloHashMap()
    {
       lista=new HashMap  <Integer,Producto>();
    }
    public ModeloHashMap(String fichero) {
    	this();
    	loadFile(fichero);
    }
    	

    public boolean insertarProducto ( Producto p){
      assert ( p != null ); // No permito productos nulos  
      lista.put(p.getCodigo(),p);
      return true;
    }
 
    public boolean borrarProducto ( int codigo ){
      // Si es null es que no estaba
      return ( lista.remove(codigo) != null);
    }
    
    public Producto buscarProducto ( int codigo) {
        return lista.get(codigo);
    }
    
    public void listarProductos (){
        int i = 1;
        for (Map.Entry<Integer,Producto> valor: lista.entrySet()) {
            System.out.println(" Nº "+i+" "+valor.getValue());
            i++;
        }
    }
    
    public boolean modificarProducto (Producto nuevo){
       // Si no esta devuelvo false
       return (lista.containsValue( nuevo));
    }
    
    public void loadFile(String str) {
    
    	try{
    		
    		FileInputStream fis=new FileInputStream(str);
    		
    		ObjectInputStream ois=new ObjectInputStream(fis);
    		
    		try{
    			Producto aux=(Producto)ois.readObject();
    			
    			while(true) {	
    				insertarProducto(aux);
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
    	System.out.println("Archivo cargado");
    }
    
    public void toFile(String str) {
    	
        try {	
        	FileOutputStream fos=new FileOutputStream(str);
        	ObjectOutputStream oos=new ObjectOutputStream(fos);
        	
        	for(Map.Entry<Integer, Producto> p:lista.entrySet()) {
        		oos.writeObject(p.getValue());
        	}
        	fos.close();
        	oos.close();
        }
        catch(IOException ioe) {
        	
        	System.err.println(" Error en E/S sobre fichero "+str+ " "+ioe);
        }
        	
    }
}