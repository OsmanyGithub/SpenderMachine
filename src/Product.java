public class Product {
    private String name;
    private double price;
    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }
    public Product(String name){
        this.name = name;
    }

    public String name() {
        return name;
    }

    public double price() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return name + "- price: $" + price;
    }
}
