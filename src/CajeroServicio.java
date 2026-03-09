import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Error.*;

public class CajeroServicio {

    private ConexionBD conexionbD = new ConexionBD();

    public double consultarSaldo(String username) throws ErrorBanca, ErrorBD {
        String sql = "SELECT saldo from usuarios WHERE username = ?";

        try (Connection con = conexionbD.conectar();
                PreparedStatement stmts = con.prepareStatement(sql)) {

            stmts.setString(1, username);
            ResultSet rs = stmts.executeQuery();

            if (rs.next()) {
                return rs.getDouble("saldo");
            } else {
                throw new UsuarioNoEncontrado();
            }

        } catch (SQLException e) {
            throw new ErrorBD(e.getMessage());
        }

    };

    public boolean IniciarSion(String user, String password) throws ErrorBanca, ErrorBD {

        ConexionBD conexionbD = new ConexionBD();
        String busqueda = "SELECT * FROM usuarios WHERE username = ? AND contrasena = ?";

        try (Connection con = conexionbD.conectar();
                PreparedStatement stmst = con.prepareStatement(busqueda)) {

            stmst.setString(1, user);
            stmst.setString(2, password);

            ResultSet rs = stmst.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                throw new ErrorInicioSesion();
            }

        } catch (SQLException e) {
            throw new ErrorBD(e.getMessage());
        }

    }

}
