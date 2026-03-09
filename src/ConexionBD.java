import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private String url = "jdbc:postgresql://localhost:5432/sistema_bancario";
    private String user = "postgres";
    private String password = "josuefran";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
