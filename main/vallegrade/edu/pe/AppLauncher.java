package vallegrade.edu.pe;

import vallegrade.edu.pe.view.MainMenuView;

public class AppLauncher {
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Lanzar el menú principal
                new MainMenuView().setVisible(true);
            }
        });
    }
}
