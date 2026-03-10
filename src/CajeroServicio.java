import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Error.ErrorBanca.ErrorBanca;
import Error.ErrorBanca.ErrorInicioSesion;
import Error.ErrorBanca.UsuarioNoEncontrado;
import Error.ErroresBD.ErrorBD;

public class CajeroServicio {

    private ConexionBD conexionbD = new ConexionBD();

    public boolean existeUsuario(String user) throws UsuarioNoEncontrado, ErrorBD {
        String sql = "SELECT saldo from usuarios WHERE username = ?";
        ConexionBD conexionbD = new ConexionBD();

        try (Connection con = conexionbD.conectar();
                PreparedStatement smts = con.prepareStatement(sql)) {

            smts.setString(1, user);
            ResultSet rs = smts.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                throw new UsuarioNoEncontrado();
            }

        } catch (SQLException e) {
            throw new ErrorBD(e.getMessage());
        }
    }

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

    public void mostrarReporteAdmin() throws ErrorBD {
        String busqueda = "SELECT username, saldo FROM usuarios";
        ConexionBD conexionbD = new ConexionBD();

        try (Connection con = conexionbD.conectar();
                PreparedStatement stms = con.prepareStatement(busqueda);
                ResultSet rs = stms.executeQuery();) {

            System.out.println("|-- Reporte de Usuario --|");
            while (rs.next()) {
                System.out.println("Usuario: " + rs.getString("username") + " |saldo: " + rs.getDouble("saldo"));
            }

        } catch (SQLException e) {
            throw new ErrorBD(e.getMessage());
        }

    }

}
