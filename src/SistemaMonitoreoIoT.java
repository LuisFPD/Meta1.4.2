//Meta 1.4.2 - Luis Fernando Prieto Duarte
public class SistemaMonitoreoIoT {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===  INICIO PRUEBA SISTEMA IoT ===\n");

        //1. Obtener instancia del GestorSensores y Notificador
        GestorSensores gestor = GestorSensores.obtenerInstancia();
        NotificadorAlertas notificador = NotificadorAlertas.obtenerInstancia();

        //2. Registrar sensores de diferentes tipos
        Sensor sensorTemp = new Sensor("TEMP-001", "temperatura", 0, "Sala de Máquinas A");
        Sensor sensorHum  = new Sensor("HUM-001", "humedad", 0, "Almacén B");
        Sensor sensorAir  = new Sensor("AIR-001", "calidad_aire", 0, "Exteriores");

        gestor.registrarSensor(sensorTemp);
        gestor.registrarSensor(sensorHum);
        gestor.registrarSensor(sensorAir);

        //3. Configurar observadores
        notificador.registrarObservador(new NotificadorEmail());
        notificador.registrarObservador(new NotificadorDashboard());
        notificador.registrarObservador(new NotificadorSMS());
        notificador.registrarObservador(new RegistradorLogs());

        //4. Probar diferentes estrategias de análisis
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisTemperatura());

        System.out.println("\n=== 🔬 SIMULANDO LECTURAS DE SENSORES ===\n");

        //5. Simular actualizaciones de sensores
        System.out.println("--- Lecturas Normales ---");
        gestor.actualizarValorSensor("TEMP-001", 25.0);//INFORMATIVO
        Thread.sleep(1000);

        System.out.println("--- Lecturas Normales ---");
        gestor.actualizarValorSensor("TEMP-001", 45.0);//ADVERTENCIA
        Thread.sleep(1000);

        System.out.println("\n--- Temperatura Crítica ---");
        gestor.actualizarValorSensor("TEMP-001", 85.0);//CRÍTICO
        Thread.sleep(1000);

        //Cambiar estrategia a Humedad
        System.out.println("\n--- Humedad Baja ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisHumedad());
        gestor.actualizarValorSensor("HUM-001", 30.0);//ADVERTENCIA
        Thread.sleep(1000);

        System.out.println("\n--- Humedad Normal ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisHumedad());
        gestor.actualizarValorSensor("HUM-001", 60.0);//INFORMATIVO
        Thread.sleep(1000);

        System.out.println("\n--- Humedad Alta ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisHumedad());
        gestor.actualizarValorSensor("HUM-001", 80.0);//ADVERTENCIA
        Thread.sleep(1000);

        //Cambiar estrategia a Calidad de Aire
        System.out.println("\n--- Calidad de Aire ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisCalidadAire());
        gestor.actualizarValorSensor("AIR-001", 150.0);//CRÍTICO

        System.out.println("\n--- Calidad de Aire ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisCalidadAire());
        gestor.actualizarValorSensor("AIR-001", 75.0);//ADVERTENCIA

        System.out.println("\n--- Calidad de Aire ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisCalidadAire());
        gestor.actualizarValorSensor("AIR-001", 30.0);//INFORMATIVO

        System.out.println("\n===  FIN SISTEMA MONITOREO IoT ===");
    }
}
