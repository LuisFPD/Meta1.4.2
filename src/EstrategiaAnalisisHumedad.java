import java.util.Date;

public class EstrategiaAnalisisHumedad implements EstrategiaAnalisis {
    @Override
    public Alerta analizar(Sensor sensor) {
        if (!"humedad".equals(sensor.getTipo())) return null;

        double valor = sensor.getValor();
        if (valor < 40) {
            return new Alerta(sensor.getId(),
                    "💧 HUMEDAD MUY BAJA: " + valor + "% en " + sensor.getUbicacion(),
                    NivelAlerta.ADVERTENCIA, new Date());
        } else if (valor >= 80) {
            return new Alerta(sensor.getId(),
                    "💧 HUMEDAD MUY ALTA: " + valor + "% en " + sensor.getUbicacion(),
                    NivelAlerta.ADVERTENCIA, new Date());
        } else {
            return new Alerta(sensor.getId(),
                    "💧 HUMEDAD NORMAL: " + valor + "% en " + sensor.getUbicacion(),
                    NivelAlerta.INFORMATIVO, new Date());
        }
    }
}