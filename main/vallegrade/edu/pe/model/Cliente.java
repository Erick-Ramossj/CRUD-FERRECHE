package vallegrade.edu.pe.model;

import java.sql.Timestamp;

/**
 * Clase POJO (Plain Old Java Object) que representa la entidad Cliente.
 * Corresponde a la tabla 'clientes' de la base de datos.
 */
public class Cliente {

    private int id;
    private String tipo_cliente; // "persona" o "empresa"
    private String correo;
    private String celular;
    private String contrasena; // Considerar encriptar esto en una app real
    private String nombre;
    private String apellidos;
    private String direccion;
    private String tipo_documento;
    private String numero_documento;
    private String profesion;
    private String razon_social;
    private String tipo_empresa;
    private String ruc;
    private Timestamp fecha_registro;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con todos los campos (útil para crear objetos desde el DAO)
    public Cliente(int id, String tipo_cliente, String correo, String celular, String contrasena, String nombre, String apellidos, String direccion, String tipo_documento, String numero_documento, String profesion, String razon_social, String tipo_empresa, String ruc, Timestamp fecha_registro) {
        this.id = id;
        this.tipo_cliente = tipo_cliente;
        this.correo = correo;
        this.celular = celular;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
        this.profesion = profesion;
        this.razon_social = razon_social;
        this.tipo_empresa = tipo_empresa;
        this.ruc = ruc;
        this.fecha_registro = fecha_registro;
    }

    // Getters y Setters para todos los campos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getTipo_empresa() {
        return tipo_empresa;
    }

    public void setTipo_empresa(String tipo_empresa) {
        this.tipo_empresa = tipo_empresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Timestamp getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Timestamp fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    // Método toString() para facilitar la depuración
    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", tipo_cliente=" + tipo_cliente + ", correo=" + correo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", razon_social=" + razon_social + ", ruc=" + ruc + '}';
    }
}



