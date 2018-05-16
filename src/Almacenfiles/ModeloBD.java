package Almacenfiles;

import java.sql.*;
import java.util.Iterator;

public class ModeloBD extends ModeloAbs{
	
	private String maquina = "192.168.7.200";
	private String usuario = "alumno";
	private String clave = "alumno";
	private int puerto = 3306;
	private String baseDeDatos="ProductosDB";
	private String servidor = "";
	private Connection conexion = null;
	private Statement stmt = null;
	private PreparedStatement pstmt=null;
	private ResultSet rset=null;
	
	
	public ModeloBD(){
		
		servidor="jdbc:mysql://"+maquina+":"+puerto+"/"+baseDeDatos+"?useSSL=false&serverTimezone=GMT";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion=DriverManager.getConnection(servidor,usuario,clave);
			stmt=conexion.createStatement();
		}
		catch(SQLException sqle) {
			System.out.println("Error al conectarse al servidor");
			sqle.printStackTrace();
		}
		catch(ClassNotFoundException cnfe) {
			System.out.println("Error al registrar el driver");
		}
		
	}
	
	public boolean insertarProducto(Producto p) {
		
		try{
			pstmt=conexion.prepareStatement("insert into Productos values(?,?,?,?,?)");
			pstmt.setInt(1,p.getCodigo());
			pstmt.setString(2, p.getNombre());
			pstmt.setInt(3, p.getStock());
			pstmt.setInt(4, p.getStock_min());
			pstmt.setFloat(5, p.getPrecio());
			
			return (pstmt.executeUpdate()>0);
			
			/*String aux="insert into productos values("+p.getCodigo()+","+p.getNombre()+","+p.getStock()+","+p.getStock_min()+","+p.getPrecio()+")";
			System.out.println(aux);
			return(stmt.executeUpdate(aux)>0);*/
		}
		catch(SQLException sqle) {}
		return false;
	}
	
	public boolean borrarProducto(int c) {
		try{
			
			return (stmt.executeUpdate("delete from Productos where codigo="+c)>0);
		}
		catch(SQLException sqle) {}
		
		return false;
	}
	
	public Producto buscarProducto(int c) {
		
		Producto rquery=null;
		try{
			
			rset=stmt.executeQuery("select * from Productos where codigo="+c);
			
			if(rset.next()) {
				
				rquery=new Producto(rset.getInt(1),rset.getString(2),
						rset.getInt(3),rset.getInt(4),rset.getFloat(5));
			}
		}
		catch(SQLException sqle) {
			System.out.println("error");
			
		}
			
		return rquery;
	}
	
	public void listarProductos() {
		
		try{
			rset=stmt.executeQuery("select codigo,nombre,precio from Productos");
		
			while (rset.next()) {
				System.out.printf("|%4s |%-15s | %-5s|\n", rset.getString(1), rset.getString(2),
						rset.getString(3));

			}
		}
		catch(SQLException sqle) {
			
			System.out.println("Error al realizar el query");
			sqle.printStackTrace();
		}
	}
	
	public boolean modificarProducto (Producto nuevo) {
		
		try{
			pstmt=conexion.prepareStatement("Update Productos"
					+ "set stock=?"
					+ "set stock_min=?"
					+ "set precio=?"
					+ "where codigo=?");
			pstmt.setInt(1, nuevo.getStock());
			pstmt.setInt(2, nuevo.getStock_min());
			pstmt.setFloat(3, nuevo.getPrecio());
			pstmt.setInt(4, nuevo.getCodigo());
			
			return (pstmt.executeUpdate()>0);
		}
		catch(SQLException sqle) {}
		
		return false;
	}
	
	public Iterator <Producto> getIterator(){
		
		ModeloArrayList aux=new ModeloArrayList();
		try{
			rset=stmt.executeQuery("Select * from productos");
			
			while(rset.next()) {
				
				aux.insertarProducto(new Producto(rset.getInt(1),rset.getString(2),rset.getInt(3),rset.getInt(4),rset.getFloat(5)));
			}
		}
		catch(SQLException sqle){
			
			System.out.println("Error en el query");
		}
		return aux.getIterator();
		
	}
	
	

	
	
	
	

	
	
	

}
