package controlador;

import data.DAOGrafo;
import modelo.juego.Entidad;
import modelo.juego.Nodo;
import modelo.juego.NodoAtributo;
import modelo.juego.NodoEntidad;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/game")
public class GameServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Nodo nodoActual = DAOGrafo.obtenerGrafo();
        session.setAttribute("nodoActual", nodoActual);

        session.setAttribute("numP", 1);

        NodoAtributo noAtr = (NodoAtributo) nodoActual;
        session.setAttribute("pregunta", noAtr.getAtributo().getNombre_atributo());

        session.setAttribute("fin", false);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/game.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Nodo nodoActual = (Nodo) session.getAttribute("nodoActual");

        int numP = (int) session.getAttribute("numP");
        session.setAttribute("numP", numP+1);

        String respuesta = (String) req.getParameter("respuesta");
        System.out.println(respuesta);

        NodoAtributo noAtr;

        // Para saber si ya se acabo el juego y se ha seleccionado la opcion de reiniciarlo
        Boolean fin = (Boolean) session.getAttribute("fin");
        String restartFlag = (String) req.getParameter("reiniciar");

        if (fin && "reiniciar".equals(restartFlag)) {
            reiniciar(req, resp);
            return;
        }

        // recorrido del arbol
        if ("si".equals(respuesta)) {
            nodoActual = nodoActual.getNodo_der();

            if (nodoActual.esAtributo()) {
                noAtr = (NodoAtributo) nodoActual;
                session.setAttribute("pregunta", noAtr.getAtributo().getNombre_atributo());
                session.setAttribute("fin", false);
            }
            else {
                session.setAttribute("pregunta", "No mas preguntas!!!, tengo tu respuesta");

                NodoEntidad noEntidad = (NodoEntidad) nodoActual;
                Entidad entidad = noEntidad.getEntidad();
                session.setAttribute("personaje", entidad.getNombre_entidad());
                session.setAttribute("fin", true);
            }

        }
        else {
            nodoActual = nodoActual.getNodo_izq();

            if (nodoActual.esAtributo()) {
                noAtr = (NodoAtributo) nodoActual;
                session.setAttribute("pregunta", noAtr.getAtributo().getNombre_atributo());
                session.setAttribute("fin", false);
            }
            else {
                session.setAttribute("pregunta", "No mas preguntas!!!, tengo tu respuesta");

                NodoEntidad noEntidad = (NodoEntidad) nodoActual;
                Entidad entidad = noEntidad.getEntidad();
                session.setAttribute("personaje", entidad.getNombre_entidad());
                session.setAttribute("fin", true);
            }
        }
        session.setAttribute("nodoActual", nodoActual);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/game.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void reiniciar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // funcion de apoyo para reiniciar el juego
        doGet(req, resp);
    }
}
