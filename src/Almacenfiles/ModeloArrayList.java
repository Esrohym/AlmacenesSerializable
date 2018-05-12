package Almacenfiles;
/**
 * Implementa la parte de Modelo de Datow
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Map;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class ModeloArrayList extends ModeloAbs implements Serializable{
    /**
	 * 
	 */
	/**
	 * 
	 */
	private ArrayList <Producto> lista;
    
    public ModeloArrayList()
    {
       lista=new ArrayList <Producto>();
    }
    
    public ModeloArrayList(String str) {
    	
    	this();
    	loadFile(str);
    }

    public boolean insertarProducto ( Producto p){
      assert ( p != null ); // No permito productos nulos  
      return lista.add(p);     
    }
 
    public boolean borrarProducto ( int codigo ){
      Producto p = buscarProducto (codigo);
      if ( p != null ){
          // Remove null 
          return lista.remove(p);
        }
      return false;
    }
    
    public Producto buscarProducto ( int codigo) {
       for ( Producto p: lista ){
           if ( p.getCodigo() == codigo ){
               return p;
            }
        }
        return null;
    }
    
    public void listarProductos (){
        int i = 1;
        for ( Producto p: lista){
            System.out.println(" Nº "+i+" "+p);
            i++;
        }
    }
    
    public boolean modificarProducto (Producto nuevo){
       Producto p;
       int i =   lista.indexOf(nuevo);
       // Si no esta devuelvo false
       return ( i != -1);
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
        	
        	for(Producto p:lista) {
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
