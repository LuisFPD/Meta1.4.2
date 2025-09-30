//Meta 1.4.2 - Luis Fernando Prieto Duarte
public interface SujetoAlerta {
    void registrarObservador(ObservadorAlerta observador);
    void eliminarObservador(ObservadorAlerta observador);
    void notificarObservadores(Alerta alerta);
}