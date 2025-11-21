package vallegrade.edu.pe.model;

public class Producto {

    private int id;
    private String name;
    private double price;
    private double discount_price;
    private String image;
    private String genero;
    private String color;
    private String rubro;

    public Producto() {
    }

    public Producto(int id, String name, double price, double discount_price, String image,
                    String genero, String color, String rubro) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount_price = discount_price;
        this.image = image;
        this.genero = genero;
        this.color = color;
        this.rubro = rubro;
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
    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
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
}
