package data;

import modelo.juego.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

public class DAOGrafo {
    private static List<Nodo> nodos;

    private static List<Nodo> obtenerEntidades() {
        String query = "select * from Entidades";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                NodoEntidad NoEnt = new NodoEntidad();

                NoEnt.setId_nodo(rs.getInt("id_nodo"));

                Entidad ent = new Entidad();
                ent.setId_entidad(rs.getInt("id_entidad"));
                ent.setNombre_entidad(rs.getString("nombre_entidad"));
                NoEnt.setEntidad(ent);

                nodos.add(NoEnt);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
        return nodos;
    }

    private static List<Atributo> obtenerAtributos() {
        List<Atributo> atributos = new ArrayList<>();
        String query = "select * from Atributos";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Atributo atr = new Atributo();
                atr.setId_atributo(rs.getInt("id_atributo"));
                atr.setNombre_atributo(rs.getString("nombre_atributo"));

                atributos.add(atr);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
        return atributos;
    }

    private static List<Nodo> obtenerNodosAtributos() {
        String query = "select * from NodoAtributo order by id_nodo asc";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Atributo> listaAtributos = obtenerAtributos();

        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                NodoAtributo NoAtr = new NodoAtributo();
                int fk_atributo = rs.getInt("fk_atributo");

                Atributo atr = listaAtributos.stream()
                        .filter(atributo -> fk_atributo == atributo.getId_atributo())
                        .findFirst()
                        .orElse(null);

                NoAtr.setId_nodo(rs.getInt("id_nodo"));
                NoAtr.setAtributo(atr);

                int id_nodo_der = rs.getInt("nodo_der");
                Nodo nodo_der = nodos.stream()
                                .filter(nodo->id_nodo_der == nodo.getId_nodo())
                                        .findFirst()
                                                .orElse(null);
                NoAtr.setNodo_der(nodo_der);
                int id_nodo_izq = rs.getInt("nodo_izq");
                Nodo nodo_izq = nodos.stream()
                        .filter(nodo->id_nodo_izq == nodo.getId_nodo())
                        .findFirst()
                        .orElse(null);
                NoAtr.setNodo_izq(nodo_izq);

                nodos.add(NoAtr);
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(ps);
            DBConnection.close(conn);
        }
        return nodos;
    }

    public static Nodo obtenerGrafo() {
        nodos = new ArrayList<>();

        obtenerEntidades();
        obtenerNodosAtributos();

        if (nodos.isEmpty()) {
            return null;
        }
        else {
            return nodos.get(nodos.size() - 1);
        }
    }
}
