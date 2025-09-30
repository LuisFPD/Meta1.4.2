import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GestorSensores {
    private static GestorSensores instancia;
    private Map<String, Sensor> sensores;

    private GestorSensores() {
        this.sensores = new ConcurrentHashMap<>();
        System.out.println("🔧 Gestor de Sensores inicializado");
    }

    public static GestorSensores obtenerInstancia() {
        if (instancia == null) {
            synchronized (GestorSensores.class) {
                if (instancia == null) {
                    instancia = new GestorSensores();
                }
            }
        }
        return instancia;
    }

    public void registrarSensor(Sensor sensor) {
        sensores.put(sensor.getId(), sensor);
        System.out.println("✅ Sensor registrado: " + sensor.getId() + " en " + sensor.getUbicacion());
    }

    public void actualizarValorSensor(String idSensor, double nuevoValor) {
        Sensor sensor = sensores.get(idSensor);
        if (sensor != null) {
            sensor.setValor(nuevoValor);
            sensor.setUltimaActualizacion(new Date());
            System.out.println("📊 Sensor " + idSensor + " actualizado: " + nuevoValor);

            // Notificar a los observadores
            NotificadorAlertas.obtenerInstancia().verificarYNotificar(sensor);
        } else {
            System.out.println("❌ Sensor no encontrado: " + idSensor);
        }
    }

    public Sensor obtenerSensor(String idSensor) {
        return sensores.get(idSensor);
    }

    public void eliminarSensor(String idSensor) {
        sensores.remove(idSensor);
        System.out.println("🗑️ Sensor eliminado: " + idSensor);
    }

    public List<Sensor> obtenerTodosSensores() {
        return new ArrayList<>(sensores.values());
    }

    public int obtenerCantidadSensores() {
        return sensores.size();
    }

    //Nuevos métodos solicitados en la meta para obtener sensores filtrados por tipo y obtener estadisticas:

    public List<Sensor> obtenerSensoresPorTipo(String tipo) {
        return sensores.values().stream()
                .filter(s -> s.getTipo().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }

    public Map<String, Object> obtenerEstadisticas() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalSensores", sensores.size());
        Map<String, Long> conteoPorTipo = sensores.values().stream()
                .collect(Collectors.groupingBy(Sensor::getTipo, Collectors.counting()));
        stats.put("sensoresPorTipo", conteoPorTipo);
        Map<String, Double> promedioPorTipo = sensores.values().stream()
                .collect(Collectors.groupingBy(
                        Sensor::getTipo,
                        Collectors.averagingDouble(Sensor::getValor)
                ));
        stats.put("promediosPorTipo", promedioPorTipo);

        return stats;
    }
}