package Error;

public class ErrorBD extends Exception {

    public ErrorBD(String mensaje) {
        super("Error tecnico en la Base de Datos" + mensaje);
    }

}
