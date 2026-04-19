package controlador;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import data.DAOUsuario;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    public static final long serialversionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String nombreUsuario = correo.split("@")[0]; // nombre siempre se va a estraer del correo, nomas por pracricidad en este caso
        String contrasenia = req.getParameter("password");

        Boolean pass = DAOUsuario.registrarUsuario(correo, contrasenia);

        if (!pass) return;

        req.getSession().setAttribute("usuarioLoggeado", nombreUsuario);

        resp.sendRedirect(req.getContextPath() + "/login"); // despues del registro regresar al inicio
    }
}
