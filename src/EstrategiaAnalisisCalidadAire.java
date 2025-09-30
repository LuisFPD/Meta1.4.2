//Meta 1.4.2 - Luis Fernando Prieto Duarte
import java.util.Date;

public class EstrategiaAnalisisCalidadAire implements EstrategiaAnalisis {
    @Override
    public Alerta analizar(Sensor sensor) {
        if (!"calidad_aire".equals(sensor.getTipo())) return null;

        double valor = sensor.getValor();
        if (valor >= 150) {
            return new Alerta(sensor.getId(),
                    "CALIDAD DEL AIRE MUY MALA: " + valor + " µg/m³ en " + sensor.getUbicacion(),
                    NivelAlerta.CRITICO, new Date());
        } else if (valor < 75) {
            return new Alerta(sensor.getId(),
                    "Calidad del aire regular: " + valor + " µg/m³ en " + sensor.getUbicacion(),
                    NivelAlerta.ADVERTENCIA, new Date());
        } else {
            return new Alerta(sensor.getId(),
                    "Calidad del aire buena: " + valor + " µg/m³ en " + sensor.getUbicacion(),
                    NivelAlerta.INFORMATIVO, new Date());
        }
    }
}
