package modelo.juego;

public class NodoEntidad extends Nodo{
    public Entidad entidad;

    public NodoEntidad() {
        this.nodo_der = null;
        this.nodo_izq = null;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
}
