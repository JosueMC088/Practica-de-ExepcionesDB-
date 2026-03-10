import java.util.Scanner;

import Error.ErrorBanca.ErrorBanca;
import Error.ErrorBanca.ErrorInicioSesion;
import Error.ErroresBD.ErrorBD;

public class App {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        CajeroServicio cajero = new CajeroServicio();
        String pantalla = "Login";

        System.out.println("|-- Bienvenido a la AppBanco --|" + pantalla);
        System.out.println("Ingrese su nombre: ");
        String user = entrada.nextLine();

        System.out.println("Ingrese su Contraseña: ");
        String contraseña = entrada.nextLine();
        try {

            if (cajero.existeUsuario(user)) {
                if (cajero.IniciarSion(user, contraseña)) {
                    System.out.println("Eres admin");
                } else {
                    System.out.println("No eres Admin");
                }
            }

        } catch (ErrorBanca e) {
            if (e instanceof ErrorInicioSesion) {
                System.out.println(e.getMessage());
            }
            System.out.println("Banner: " + e.getMessage());

        } catch (ErrorBD e) {
            System.out.println(e.getMessage());
        } finally {
            entrada.close();
        }

    }
}
