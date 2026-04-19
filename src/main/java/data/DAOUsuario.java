package data;

import modelo.usuario.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUsuario {
    public static Boolean registrarUsuario(String correo, String contrasenia) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setContrasenia(contrasenia);

        String insert = "insert into Usuarios(correo_usuario, contrasenia_usuario, juegos_usuario) values(?, ?, 0)";

        Connection conn = null;
        PreparedStatement ps = null;

        Boolean result;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(insert);
            ps.setString(1, correo);
            ps.setString(2, contrasenia);

            int id_usuario = ps.executeUpdate();
            usuario.setId(id_usuario);
            result = true;
        } catch (
                SQLException ex) {
            ex.printStackTrace(System.out);
            result = false;
        } finally {
            DBConnection.close(ps);
            DBConnection.close(conn);
        }

        return result;
    }

    public static Usuario obtenerUsuario(String correo, String contrasenia) {
        String query = "select * from Usuarios " +
                "where correo_usuario like '" + correo + "' and " +
                "contrasenia_usuario like '" + contrasenia + "';";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Usuario usuario = new Usuario();

        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setCorreo(rs.getString("correo_usuario"));
                usuario.setNombre(usuario.getCorreo().split("@")[0]);
                usuario.setJuegos(rs.getInt("juegos_usuario"));
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        } finally {
            DBConnection.close(rs);
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
        if (usuario.getCorreo() != null)
            return usuario;
        else return null;
    }
}
