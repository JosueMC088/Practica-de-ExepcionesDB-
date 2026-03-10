import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Error.ErrorBanca.ErrorBanca;
import Error.ErrorBanca.ErrorInicioSesion;
import Error.ErrorBanca.UsuarioNoEncontrado;
import Error.ErrorBanca.usuarioExistenteError;
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

    public double consultarSaldo(String username) throws ErrorBD {
        String sql = "SELECT saldo from usuarios WHERE username = ?";

        try (Connection con = conexionbD.conectar();
                PreparedStatement stmts = con.prepareStatement(sql)) {

            stmts.setString(1, username);
            ResultSet rs = stmts.executeQuery();

            if (rs.next()) {
                return rs.getDouble("saldo");
            }

        } catch (SQLException e) {
            throw new ErrorBD(e.getMessage());
        }
        return 0;

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
                return rs.getBoolean("admin");
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

                System.out.printf("User: %-8S | Saldo: %8.2f%n", rs.getString("username"), rs.getDouble("saldo"));
            }

        } catch (SQLException e) {
            throw new ErrorBD(e.getMessage());
        }

    }

    public void AgregarUsuario(String user, String password, double saldo)
            throws ErrorBD, usuarioExistenteError {
        String busqueda = "SELECT saldo from usuarios WHERE username = ?";
        String insercion = "INSERT INTO usuarios (username, contrasena,saldo) VALUES (?,?,?)";
        int afecto;

        // "INSERT INTO usuarios (username, contrasena) VALUES (?,?)";
        ConexionBD conexionDB = new ConexionBD();

        try (Connection con = conexionDB.conectar();
                PreparedStatement stms = con.prepareStatement(busqueda);) {

            stms.setString(1, user);

            if (!(stms.executeQuery().next())) {
                try (PreparedStatement stms2 = con.prepareStatement(insercion)) {
                    stms2.setString(1, user);
                    stms2.setString(2, password);
                    stms2.setDouble(3, saldo);

                    afecto = stms2.executeUpdate();
                    if (afecto > 0) {
                        System.out.println("Agregacion Correcta.. ");
                    }

                }

            } else {
                throw new usuarioExistenteError();

            }

        } catch (SQLException e) {
            throw new ErrorBD(e.getMessage());
        }
    }

}
