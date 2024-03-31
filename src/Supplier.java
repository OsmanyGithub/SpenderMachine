import java.util.*;

public class Supplier {
    private VendingMachine vendingMachine;
    private Store store;
    private boolean showProduct;

    public Supplier(VendingMachine vendingMachine, Store store) {
        this.vendingMachine = vendingMachine;
        this.store = store;
    }

    public void supply() {
        showProduct = true;
        addDifferentProducts();
        refillWithSameProduct();
        if (showProduct) {
            System.out.println();
            System.out.println("........Product in the machine......");
            vendingMachine.showProducts();
            showProduct = false;
        }
    }

    public void addDifferentProducts() {
        Map<Product, Integer> stock = store.getStock();
        List<Product> productsToRemove = new ArrayList<>(); // List to hold products to remove

        // Iterate using a copy to safely remove product from stock without error
        for (Map.Entry<Product, Integer> entry : new HashSet<>(stock.entrySet())) {
            Product product = entry.getKey();

            if (!vendingMachine.listOfProductsInTheMachine.contains(product) &&
                    vendingMachine.availableSpace() > 0) {
                vendingMachine.addProduct(product);
                int quantity = entry.getValue();
                quantity--;

                // Update the stock amount in store
                store.removeProduct(product, 1);

                // Remove the product from the stock if the amount becomes 0;
                if (quantity == 0) {
                    productsToRemove.add(product); // Add product to remove list
                } else {
                    stock.put(product, quantity); // Update the quantity in the stock
                }
            }
        }
        if (vendingMachine.availableSpace() == 0){
            System.out.println("Vending Machine is full");
        }

        // Remove products from stock after iteration
        for (Product productToRemove : productsToRemove) {
            stock.remove(productToRemove);
        }
    }



    public void refillWithSameProduct() {
        Map<Product, Integer> stock = store.getStock();
        Iterator<Map.Entry<Product, Integer>> iterator = stock.entrySet().iterator();
        while (iterator.hasNext() && vendingMachine.availableSpace() > 0) {
            Map.Entry<Product, Integer> entry = iterator.next();
            Product product = entry.getKey();
            int quantity = entry.getValue();

            while (quantity > 0 && vendingMachine.availableSpace() > 0) {
                vendingMachine.addProduct(product);
                store.removeProduct(product, 1);
                quantity--;
            }

            if (quantity == 0) {
                iterator.remove();
            }
        }
    }
}
