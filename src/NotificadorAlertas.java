import java.util.ArrayList;
import java.util.List;

public class NotificadorAlertas implements SujetoAlerta {
    private static NotificadorAlertas instancia;
    private List<ObservadorAlerta> observadores;
    private EstrategiaAnalisis estrategia;

    private NotificadorAlertas() {
        this.observadores = new ArrayList<>();
        this.estrategia = new EstrategiaAnalisisBasica(); // estrategia por defecto
        System.out.println("🔔 Notificador de Alertas inicializado");
    }

    public static NotificadorAlertas obtenerInstancia() {
        if (instancia == null) {
            synchronized (NotificadorAlertas.class) {
                if (instancia == null) {
                    instancia = new NotificadorAlertas();
                }
            }
        }
        return instancia;
    }

    public void verificarYNotificar(Sensor sensor) {
        Alerta alerta = estrategia.analizar(sensor);
        if (alerta != null) {
            notificarObservadores(alerta);
        }
    }

//    @Override
//    public void registrarObservador(ObservadorAlerta observador) {
//        observadores.add(observador);
//        System.out.println("👀 Observador registrado: " + observador.obtenerTipoObservador());
//    }

    @Override
    public void eliminarObservador(ObservadorAlerta observador) {
        observadores.remove(observador);
        System.out.println("👋 Observador eliminado: " + observador.obtenerTipoObservador());
    }

    @Override
    public void notificarObservadores(Alerta alerta) {
        System.out.println("\n🚨 Notificando " + observadores.size() + " observadores...");
        for (ObservadorAlerta observador : observadores) {
            observador.actualizar(alerta);
        }
    }

    public void establecerEstrategiaAnalisis(EstrategiaAnalisis estrategia) {
        this.estrategia = estrategia;
        System.out.println("🔄 Estrategia de análisis cambiada: " + estrategia.getClass().getSimpleName());
    }

    //Nuevo código que reemplaza al registrarObservador del ejemplo del profe para evitar repetidos y añade el metodo
    //de obtener la cantidad de observadores y dichos observadores.
    public int obtenerCantidadObservadores() {
        return observadores.size();
    }

    @Override
    public void registrarObservador(ObservadorAlerta observador) {
        if (!observadores.contains(observador)) {//De esta forma podemos evitar la aparición de duplicados.
            observadores.add(observador);
            System.out.println("👀 Observador registrado: " + observador.obtenerTipoObservador());
        } else {
            System.out.println("⚠️ Observador ya registrado: " + observador.obtenerTipoObservador());
        }
    }

    public List<ObservadorAlerta> obtenerObservadores() {
        return new ArrayList<>(observadores);
    }
}