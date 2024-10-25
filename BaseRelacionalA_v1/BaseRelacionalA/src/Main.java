import javax.swing.*;
import java.util.Scanner;

/**
 * Clase main donde el usuario interactua con el programa
 * @author Susana
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {

        // Instanciamos la clase Conectar
        ConectarBD conec = new ConectarBD();

        String codigo;
        int precio;
        int opcion = 0;

        do{

            try{
                // Menú de opciones del programa
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, """
                        *** REGISTRO EN LA BASE DE DATOS ***
                        
                        Escoja la opción que quiere realizar:
                        1) Ingresar producto nuevo.
                        2) Ver todos los productos.
                        3) Buscar producto por su código.
                        4) Actualizar el precio de un producto.
                        5) Eliminar producto.
                        6) Salir.
                        """));

                switch (opcion){
                    case 1:
                        codigo = JOptionPane.showInputDialog(null,"Ingrese el código del producto:");
                        String desc = JOptionPane.showInputDialog(null, "Ingrese la descripcion del producto:");
                        String data = JOptionPane.showInputDialog(null,"Ingrese la fecha (YYYY-MM-DD):");
                        precio = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el precio del producto:"));
                        conec.insireProduto(codigo,desc,precio, data);
                        break;

                    case 2:
                        conec.listaProdutos();
                        break;

                    case 3:
                        codigo = JOptionPane.showInputDialog(null,"Ingrese el código del producto:");
                        conec.listaProdutoPorCodigo(codigo);
                        break;

                    case 4:
                        codigo = JOptionPane.showInputDialog(null,"Ingrese el código del producto:");
                        precio = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el precio del producto:"));
                        conec.actualizaPre(codigo, precio);
                        break;

                    case 5:
                        codigo = JOptionPane.showInputDialog(null,"Ingrese el código del producto:");
                        conec.eliminaProduto(codigo);
                        break;

                    case 6:

                        JOptionPane.showMessageDialog(null,"Saliendo del programa...");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null,"Solo los números de las opciones");
                }

            }catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(null,"Debes insertar un número como opción");
                e.printStackTrace();
            }

        }while(opcion!=6);
            System.exit(0);

    }
}