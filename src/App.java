import java.util.Scanner;

import Error.ErrorBanca.ErrorBanca;
import Error.ErrorBanca.ErrorInicioSesion;
import Error.ErroresBD.ErrorBD;

public class App {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        CajeroServicio cajero = new CajeroServicio();

        System.out.println("|-- Bienvenido a la AppBanco --|");
        System.out.println("Ingrese su nombre: ");
        String user = entrada.nextLine();

        System.out.println("Ingrese su Contraseña: ");
        String contraseña = entrada.nextLine();
        try {

            if (cajero.existeUsuario(user)) {
                System.out.println("Usuario existe");
            } else {
                System.out.println("Usuario no Existe");
            }

        } catch (ErrorBanca e) {
            if (e instanceof ErrorInicioSesion) {
                System.out.println(e.getMessage());
            }
            System.out.println("Banner" + e.getMessage());

        } catch (ErrorBD e) {
            System.out.println(e.getMessage());
        } finally {
            entrada.close();
        }

    }
}
