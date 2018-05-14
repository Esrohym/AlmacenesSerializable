package Almacenfiles;
/**
 * Implementa la parte de Modelo de Datos con un array que se salva a fichero
 * cada vez que se cambian los datos ( Solucion poco eficiente pues siempre graba todo 
 * el contenido no solo lo que ha cambiado )
 * Formato del fichero
 *  int codigo;    // Código del producto, se utiliza para buscar
    String nombre; // Nombre un texto
    int stock;    // existencia actuales
    int stock_min; // Número mínimo de existencias recomedadas
    float precio;
    codigo, nombre, stock, stock_min, precio
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class ModeloArrayListFile extends ModeloArrayList
{
    static final String nombrefichero = "productos.objetos";
    
    public ModeloArrayListFile()
    {
       super();
       cargarDesdeFichero();
       
    }
    
    private void salvarAfichero(){
        
    	try {
    		
    		FileOutputStream fos=new FileOutputStream(nombrefichero);
    		ObjectOutputStream oos=new ObjectOutputStream(fos);
    		
    		Iterator<Producto> aux=super.getIterator();
    		while(aux.hasNext()) {
    			Object e=aux.next();
    			oos.writeObject(e);
    		}
    		fos.close();
    		oos.close();
        }
    	catch(IOException ioe) {
    		
    		System.out.println("Error de escritura");
    	}
    }
    
    private void cargarDesdeFichero() {
        
         
    	try {
    		
    		FileInputStream fis=new FileInputStream(nombrefichero);
    		ObjectInputStream ois=new ObjectInputStream(fis);
    	
    		try {
    			Producto aux=(Producto)ois.readObject();
    			
    			while(true) {
    				super.insertarProducto(aux);
    				aux=(Producto)ois.readObject();
    			}
    		}catch(EOFException eofe) {
    			
    			fis.close();
    			ois.close();
    			
    		}
    	}
    	catch(IOException ioe) {
    		
    		System.err.println("Error de lectura");
    		
    	}
    	catch(ClassNotFoundException cnfe) {
    		
    		System.err.println("El fichero no tiene objetos");
    	}
    }

    public boolean insertarProducto ( Producto p){
      boolean resu = super.insertarProducto(p);
      if (resu){
          salvarAfichero();
      }
      return resu;
    }
 
    public boolean borrarProducto ( int codigo ){
      boolean resu = super.borrarProducto(codigo);
      if ( resu ){
          salvarAfichero();
        }
      return resu;
    }
    
    
    public boolean modificarProducto (Producto nuevo){
       boolean resu = super.modificarProducto(nuevo);
       if ( resu ){
           salvarAfichero();
        }
       return (resu);
    }
    
        
}    