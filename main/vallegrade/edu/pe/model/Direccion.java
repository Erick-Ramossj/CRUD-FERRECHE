package vallegrade.edu.pe.model;

/**
 * Clase POJO (Plain Old Java Object) que representa la entidad Direccion.
 * Corresponde a la tabla 'direcciones' de la base de datos.
 */
public class Direccion {

    private int id;
    private int idCliente; // Clave foránea (Foreign Key)
    private String calle;
    private String numero;
    private String distrito;
    private String provincia;
    private String departamento;
    private String referencia;
    private boolean esPrincipal;

    // Constructor vacío
    public Direccion() {
    }

    // Constructor completo (Opcional, pero recomendado)
    public Direccion(int id, int idCliente, String calle, String numero, String distrito, String provincia, String departamento, String referencia, boolean esPrincipal) {
        this.id = id;
        this.idCliente = idCliente;
        this.calle = calle;
        this.numero = numero;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        this.referencia = referencia;
        this.esPrincipal = esPrincipal;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Usamos 'idCliente' en camelCase en Java, pero en el DAO se mapea a 'id_cliente'
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public boolean isEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    // Método toString() para facilitar la depuración
    @Override
    public String toString() {
        return "Direccion{" + "id=" + id + ", idCliente=" + idCliente + ", calle=" + calle + ", distrito=" + distrito + '}';
    }
}
