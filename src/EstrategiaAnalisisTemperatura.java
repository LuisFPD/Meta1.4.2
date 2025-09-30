//Meta 1.4.2 - Luis Fernando Prieto Duarte
import java.util.Date;

public class EstrategiaAnalisisTemperatura implements EstrategiaAnalisis {
    @Override
    public Alerta analizar(Sensor sensor) {
        if (!"temperatura".equals(sensor.getTipo())) return null;

        double valor = sensor.getValor();
        if (valor > 80.0) {
            return new Alerta(sensor.getId(),
                    "❌ TEMPERATURA CRÍTICA: " + valor + "°C en " + sensor.getUbicacion(),
                    NivelAlerta.CRITICO, new Date());
        } else if (valor > 40.0) {
            return new Alerta(sensor.getId(),
                    "⚠️ Temperatura alta: " + valor + "°C en " + sensor.getUbicacion(),
                    NivelAlerta.ADVERTENCIA, new Date());
        } else {
            return new Alerta(sensor.getId(),
                    "   Temperatura Normal: " + valor + "°C en " + sensor.getUbicacion(),
                    NivelAlerta.INFORMATIVO, new Date());
        }
    }
}