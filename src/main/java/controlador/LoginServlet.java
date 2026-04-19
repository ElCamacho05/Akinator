package controlador;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import data.DAOUsuario;
import modelo.usuario.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    // Realiza las llamadas a la base de datos correspondientes y
    // gestiona loggeos exitosos o fallidos
    public static final long serialversionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String nombreUsuario = correo.split("@")[0];
        String contrasenia = req.getParameter("password");

        Usuario usuario = DAOUsuario.obtenerUsuario(correo, contrasenia);
        System.out.println(usuario);

        if (usuario != null) { // inicio exitoso
            req.getSession().setAttribute("usuarioLoggeado", usuario.getNombre());
            resp.sendRedirect(req.getContextPath() + "/game");
        }
        else { // inicio fallido
            req.getSession().setAttribute("usuarioLoggeado", false); // flag de fallo en acceso
            resp.sendRedirect(req.getContextPath() + "/login");
        }

    }
}
