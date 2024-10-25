import javax.swing.*;
import java.sql.Statement;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Clase de Conección a la base de datos con métodos crud
 * @author Susana
 */
public class ConectarBD {

    Connection conectar = null;
    String driver = "jdbc:postgresql:";
    String host = "//localhost:";
    String porto = "5432";
    String sid = "postgres";
    String usuario = "postgres";
    String password = "postgres";
    String url = driver + host+ porto + "/" + sid;

    /**
     * Metodo para conectar con la base de datos PostgreSQL
     * @return conectar de tipo Connection
     */
    public Connection conexion(){

        try{
            conectar = DriverManager.getConnection(url,usuario,password);

        }catch(SQLException ex){
            System.out.println("Error al conectar a la base de datos"+ ex.getMessage());
        }
        return conectar;
    }

    /**
     * Metodo para insetar porductos a la base de datos
     * @param codigo de tipo String
     * @param descricion de tipo String
     * @param prezo de tipo int
     * @param data de tipo String
     */
    public void insireProduto(String codigo, String descricion, int prezo, String data){

        // Consulta para insertar productos en la tabla
        String consulta = "INSERT INTO produtos(codigo,descricion,prezo,datac) values (?,?,?,?)";

        try {

            // Conectamos a la BD
            Connection conectar = conexion();

            // Pasamos parametros a la BD
            PreparedStatement ps = conectar.prepareStatement(consulta);

            //Asignamos los valores
            ps.setString(1,codigo);
            ps.setString(2,descricion);
            ps.setInt(3,prezo);
            ps.setDate(4,Date.valueOf(data)); // Convertimos la data en tipp Date para la base de datos

            // Ejecutamos y agregamos a la BD
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Producto insertado correctamente");

            // Cerramos el PreparedStatement
            ps.close();

        } catch (SQLException e) {
            System.out.println("No se puede ingresar productos a la tabla."+e.getMessage());
        }

    }

    /**
     * Metodo para listar los productos de la base de datos
     */
    public void listaProdutos(){
        // Consulta para listar productos en la tabla
        String consulta = "SELECT * FROM produtos";

        try{
            // Conectamos a la BD
            Connection conectar = conexion();

            //Ejecutamos la orden
            PreparedStatement ps = conectar.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                System.out.println("Código: "+rs.getString("codigo"));
                System.out.println("Descricion: "+rs.getString("descricion"));
                System.out.println("Prezo: "+rs.getInt("prezo"));
                System.out.println("Data: "+rs.getString("datac"));
            }

        }catch (SQLException e){
            System.out.println("No se puede ver los productos."+e.getMessage());
        }
    }

    /**
     * Metodo para buscar un producto por su código
     * @param codigo de tipo String
     */
    public void listaProdutoPorCodigo(String codigo){

        // Consulta para listar producto por código
        String consulta = "SELECT *  FROM produtos WHERE codigo=?";

        try{
            // Conectamos con la base de datos
            Connection conectar = conexion();

            // Ejecutamos la orden
            PreparedStatement ps = conectar.prepareStatement(consulta);
            ps.setString(1,codigo);
            ResultSet rs = ps.executeQuery();

            // Mientras se recorre el resulset, se lee los elementos
            while (rs.next()){
                String codigoProd = rs.getString("codigo");
                String descricion = rs.getString("descricion");
                int prezo = rs.getInt("prezo");
                Date datac = Date.valueOf(rs.getString("datac"));

                JOptionPane.showMessageDialog(null, "Codigo: "+ codigoProd+"\nNombre: "+ descricion
                + "\nPrecio: "+prezo+ "\nFecha: "+datac);

            }

        }catch(SQLException e){
            System.out.println("No se puede ver el producto."+e.getMessage());
        }
    }

    /**
     * Metodo que actualiza el precio de un producto por su código
     * @param codigo String
     * @param novoPrezo de tipo int
     */
    public void actualizaPre(String codigo, int novoPrezo){

        // Consulta para actualizar productos
        String consulta = "UPDATE produtos SET prezo=? WHERE codigo=?";

        try{
            // Conectamos a la BD
            Connection conectar = conexion();

            // Ejecutamos la orden
            PreparedStatement ps = conectar.prepareStatement(consulta);
            ps.setInt(1, novoPrezo);
            ps.setString(2, codigo);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null,"Se actualizó el producto correctamente");


        }catch(SQLException e){
            System.out.println("No se ah actualizado el producto."+e.getMessage());
        }
    }

    /**
     * Metodo para eliminar un producto buscado por su código
     * @param codigo de tipo String
     */
    public void eliminaProduto(String codigo){

        // Consulta para eliminar producto
        String consulta = "DELETE FROM produtos WHERE codigo=?";

        try{
            // Conectamos a la BD
            Connection conectar = conexion();
            //Ejecutamos la orden
            PreparedStatement ps = conectar.prepareStatement(consulta);
            ps.setString(1,codigo);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null,"Producto eliminado");

        }catch(SQLException e){
            System.out.println("No se pudo eliminar produto. "+e.getMessage());
        }
    }
}//Cerramos la clase con sus métodos
