package vallegrade.edu.pe.model;

public class Producto {

    private int id;
    private String name;
    private double price;
    private Double discount_price; // <-- Wrapper para permitir NULL
    private String image;
    private String color;
    private String genero;
    private String rubro;
    private String description;  // <-- CORREGIDO para coincidir con la BD

    public Producto() {
    }

    public Producto(int id, String name, double price, Double discount_price, String image,
                    String genero, String color, String rubro, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount_price = discount_price;
        this.image = image;
        this.genero = genero;
        this.color = color;
        this.rubro = rubro;
        this.description = description;
    }

    // -------- ID --------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // -------- NAME --------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // -------- PRICE --------
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // -------- DISCOUNT PRICE --------
    public Double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(Double discount_price) {
        this.discount_price = discount_price;
    }

    // -------- IMAGE --------
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // -------- GENERO --------
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // -------- COLOR --------
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // -------- RUBRO --------
    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    // -------- DESCRIPTION --------
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
