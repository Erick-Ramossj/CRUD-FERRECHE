package vallegrade.edu.pe;

import vallegrade.edu.pe.controller.ClienteController;
import vallegrade.edu.pe.service.ClienteService;
import vallegrade.edu.pe.view.FrmCliente;

/**
 * Lanzador principal de la aplicación.
 * Esta clase es el único punto de entrada (main).
 * Se encarga de instanciar el patrón MVC (Modelo-Vista-Controlador).
 */
public class AppLauncher {

    public static void main(String[] args) {
        // Usamos EventQueue.invokeLater para asegurar que la UI se cree
        // en el hilo de despacho de eventos de Swing (Event Dispatch Thread).
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // 1. Crear la Vista
                FrmCliente vista = new FrmCliente();

                // 2. Crear el Servicio (que a su vez crea el DAO)
                ClienteService servicio = new ClienteService();

                // 3. Crear el Controlador (y pasarle la Vista y el Servicio)
                ClienteController controlador = new ClienteController(vista, servicio);

                // 4. Iniciar el controlador (esto hace visible la vista y carga los datos)
                controlador.iniciar();
            }
        });
    }
}