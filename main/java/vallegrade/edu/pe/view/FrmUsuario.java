package vallegrade.edu.pe.view;

import vallegrade.edu.pe.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmUsuario extends JFrame {

    public JTable tabla;
    public JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar;
    public JTextField txtId, txtUsername, txtPassword, txtRol, txtBuscar; // Usamos nombres de Usuario
    private DefaultTableModel modelo;

    public FrmUsuario() {
        setTitle("Gestión de Usuarios");
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ... (Inicialización de paneles, campos, botones y tabla, como se vio antes)

        // Simplemente definimos los campos públicos para evitar errores
        txtId = new JTextField(); txtId.setEditable(false);
        txtUsername = new JTextField();
        txtPassword = new JTextField();
        txtRol = new JTextField();
        txtBuscar = new JTextField();

        modelo = new DefaultTableModel(new String[]{"ID", "Usuario", "Contraseña", "Rol"}, 0);
        tabla = new JTable(modelo);

        // Los botones se inicializan aquí (aunque no los añadas a un panel para esta vista)
        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        // ... (Código de layout simple omitido por brevedad, pero necesario para compilar)

        // ======= Iniciar controlador =======
        // new UsuarioController(this); // Comentado para evitar errores si UsuarioController no está completo
    }

    public void mostrarUsuarios(List<Usuario> lista) {
        // ... Lógica para llenar la tabla ...
    }

    public void limpiarCampos() {
        txtId.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtRol.setText("");
        txtBuscar.setText("");
        tabla.clearSelection();
    }

    // ... otros métodos
    public Usuario obtenerDatosFormulario() { return new Usuario(); }
    public int obtenerIdSeleccionado() { return 0; }
    public String getTextoBusqueda() { return ""; }
}