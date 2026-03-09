import java.util.Scanner;

import Error.ErrorBD;
import Error.ErrorBanca;
import Error.ErrorInicioSesion;

public class App {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        CajeroServicio cajero = new CajeroServicio();

        System.out.println("Ingrese su nombre: ");
        String user = entrada.nextLine();

        System.out.println("Ingrese su Contraseña: ");
        String contraseña = entrada.nextLine();
        try {

            if (cajero.IniciarSion(user, contraseña)) {
                double saldo = cajero.consultarSaldo(user);
                System.out.println("Hola " + user + " tu saldo es de : $" + saldo);

            } else {
                System.out.println("Usuario no Encontrado");
            }

        } catch (ErrorBanca e) {
            if (e instanceof ErrorInicioSesion) {
                System.out.println(e.getMessage());
            }
            System.out.println("Banner Amarillo: " + e.getMessage());

        } catch (ErrorBD e) {
            System.out.println(e.getMessage());
        } finally {
            entrada.close();
        }

    }
}
