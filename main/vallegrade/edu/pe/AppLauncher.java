package vallegrade.edu.pe;

import vallegrade.edu.pe.view.LoginView; // Importar el Login

public class AppLauncher {
    public static void main(String[] args) {
        // Ejecutar la interfaz en el hilo de eventos de Swing (Buenas prácticas)
        javax.swing.SwingUtilities.invokeLater(() -> {

            // ANTES:
            // MainMenuView menu = new MainMenuView();
            // menu.setVisible(true);

            // AHORA:
            LoginView login = new LoginView();
            login.setVisible(true);

        });
    }
}
