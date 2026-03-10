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

                    String op = "";
                    while (!(op == "/")) {
                        System.out.println("\n|-- Vista Admin --|");
                        System.out.println("1.- Ver registros");
                        System.out.println("2.- Agregar usuario");
                        System.out.println("3.- Eliminar Usuario");
                        System.out.println("4.- Salir");
                        op = entrada.nextLine();

                        switch (op) {
                            case "1":
                                cajero.mostrarReporteAdmin();
                                entrada.nextLine();
                                System.out.println("\n");
                                break;

                            case "2":
                                System.out.println("Ingrese nombre y contraseña de nueva cuenta");
                                System.out.println("Nombre:");
                                String usuario = entrada.nextLine();

                                System.out.println("Contraseña: ");
                                String passsword = entrada.next();

                                System.out.println("Saldo: ");
                                double saldo = entrada.nextDouble();

                                cajero.AgregarUsuario(usuario, passsword, saldo);
                                break;

                            case "3":
                                System.out.println("|-- Elimnar Cliente --|");
                                System.out.println("Cliente a Eliminar: ");
                                usuario = entrada.nextLine();
                                cajero.eliminarUsuario(usuario);

                                break;

                            case "4":
                                op = "/";
                                break;

                            default:
                                System.out.println("Opcion invorrecta");
                                break;

                        }

                    }
                } else {
                    System.out.println("No eres admin");
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
