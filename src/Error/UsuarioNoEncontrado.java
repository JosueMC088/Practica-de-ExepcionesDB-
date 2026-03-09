package Error;

public class UsuarioNoEncontrado extends ErrorBanca {

    public UsuarioNoEncontrado() {
        super("Usuario no existe", 404);
    }

}
