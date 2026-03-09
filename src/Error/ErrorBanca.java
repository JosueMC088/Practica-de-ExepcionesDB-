package Error;

public class ErrorBanca extends Exception {

    private int codigo;

    public ErrorBanca(String mensaje, int codigo) {
        super(mensaje);
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
