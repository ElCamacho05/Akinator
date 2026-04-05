package modelo.juego;

public class Nodo {
    public int id_nodo;
    Nodo nodo_izq;
    Nodo nodo_der;

    public Nodo() {
    }

    public int getId_nodo() {
        return id_nodo;
    }

    public void setId_nodo(int id_nodo) {
        this.id_nodo = id_nodo;
    }

    public Nodo getNodo_izq() {
        return nodo_izq;
    }

    public void setNodo_izq(Nodo nodo_izq) {
        this.nodo_izq = nodo_izq;
    }

    public Nodo getNodo_der() {
        return nodo_der;
    }

    public void setNodo_der(Nodo nodo_der) {
        this.nodo_der = nodo_der;
    }

    public Nodo getSi() {
        return this.nodo_der;
    }

    public Nodo getNo() {
        return this.nodo_izq;
    }
}
