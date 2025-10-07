package vallegrade.edu.pe;


import vallegrade.edu.pe.view.FrmProducto;
import vallegrade.edu.pe.controller.ProductoController;
import vallegrade.edu.pe.service.ProductoService;

public class AppLauncher {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            // El resto del código no cambia
            FrmProducto vista = new FrmProducto();
            ProductoService servicio = new ProductoService();
            ProductoController controlador = new ProductoController(vista, servicio);

            controlador.iniciar();
        });
    }
}