package Almacenfiles;
/**
 * Write a description of class ModeloHaspMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class ModeloHashMap extends ModeloAbs
{
    private HashMap <Integer,Producto> lista;
    
    public ModeloHashMap()
    {
       lista=new HashMap  <Integer,Producto>();
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
    
    public Iterator <Producto> getIterator(){
        return lista.values().iterator();
    } 
    
}
