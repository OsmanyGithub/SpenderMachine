import java.util.HashMap;
import java.util.Map;

public class Store{
    private Map<Product,Integer> stock; // Use map to keep track of stock
    private Map<Product, Double> catalogue; // Use map to keep track of product-price values

    // Constructor to initialize stock and catalogue
    public Store(){
        stock = new HashMap<>();
        catalogue = new HashMap<>();
        initializeStore();


    }

    public void initializeStore(){
        Product[] products = {
                new Product("Cheetos", 1.35),
                new Product("Sprite", 2.99),
                new Product("Coca Cola", 1.99),
                new Product("Doritos", 2.08),
                new Product("Pringles", 3.75),
                new Product("Beef Jerky", 7.37),
                new Product("Nutella Biscuits", 4.48),
                new Product("Wonderful Pistachios", 7.48),
                new Product("Oreo mini", 3.17)
        };

        // Initial quantity for each product is 3
        for (Product product: products){
            stock.put(product, 3);
        }
    }

    // Method to populate catalogue with product-price values
    private void populateCatalogue(){
        for (Map.Entry<Product, Integer> entry: stock.entrySet()){
            catalogue.put(entry.getKey(), entry.getKey().price());
        }
    }

    // Method to add a product to stock
    public void addProduct(Product product, int quantity){
        if (stock.containsKey(product)){
            int currentQuantity = stock.get(product);
            stock.put(product, currentQuantity + quantity);
        }
        else{
            stock.put(product, quantity);
            catalogue.put(product, product.price()); // Add to catalogue if not already present
        }
    }

    // Method to remove product from stock
    public void removeProduct(Product product, int quantity){
        if (stock.containsKey(product)){
            int currentQuantity = stock.get(product);
            if (currentQuantity <= quantity){
                stock.remove(product);
                catalogue.remove(product); // Remove from catalogue if not more in stock
            }
            else{
                stock.put(product, currentQuantity - quantity);
            }
        }
    }

    // Method to display the current stock
    public void inventory(){
        System.out.println("Current stock: ");
        for (Map.Entry<Product, Integer> entry : stock.entrySet()){
            System.out.println(entry.getKey() + " (" + entry.getValue() + " units)");
        }
    }

    // Method to display the catalogue
    public void showCatalogue(){
        System.out.println("Catalogue: ");
        for (Map.Entry<Product, Double> entry : catalogue.entrySet()){
            System.out.println(entry.getKey() + " Price: $" + entry.getValue());
        }
    }

    // Method to return stock
    public Map<Product, Integer> getStock() {
        return stock;
    }

    // Method to return the catalogue
    public Map<Product, Double> getCatalogue() {
        return catalogue;
    }
}