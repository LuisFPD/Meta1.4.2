//Meta 1.4.2 - Luis Fernando Prieto Duarte
public class EstrategiaAnalisisBasica implements EstrategiaAnalisis {
    @Override
    public Alerta analizar(Sensor sensor) {
        // Estrategia básica que no genera alertas
        return null;
    }
}