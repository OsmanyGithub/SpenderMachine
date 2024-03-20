import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class VendingMachine {
    private Store store = new Store();
    private Product[] products;
    private int capacity = 10;
    Operations op = new Operations();
    
    public VendingMachine(Product[] products){
        this.products = products;
    }
    
    public VendingMachine(Product product){
        this.products = new Product[capacity];
        products[0] = product;
    }

    public VendingMachine(Product product, int amount){
        this.products = new Product[amount];
        products[0] = product;
    }


    public void addProduct(Product product) {
        if (availableSpace() > 0 ) {
            System.out.println("Adding " + product.name() + " to the vending machine.");
            for (int i = 0; i < products.length; i++) {
                if (products[i] == null) {
                    products[i] = product;
                    return; // Exit the loop after adding the product
                }
            }
        } else {
            System.out.println("Cannot add " + product.name() + "Vending machine is full.");
        }
    }

    // Check available space in the machine for put more products.
    public int availableSpace(){
        int freeSpace = 0;
        for (int i = 0; i < products.length; i++){
            if (products[i] == null){
                freeSpace ++;
            }
        }
        return freeSpace;
    }


    // Method to show products in the machine
    public void showProducts(){
        int curr = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < products.length; i ++){
            if (products[i] != null){
                curr ++;
                sb.append("Product " + (i+1) + ": " + products[i] + "\n");
            }
            else{
                break;
            }

        }
        System.out.println("Products available in the machine: " + curr);
        System.out.println(sb.toString());
    }

    public void buyItem(Client client, Product[] products){
        for(Product p: products){
            if (p != null){
                buyItem(client, p);
            }
        }
    }

    public void buySeveralItems(Client client, String... productList) {
        Set<String> productsInMachine = new HashSet<>();
       for (String productName: productList){
           for (Product product: this.products){
               if (product != null && productName.equals(product.name())){
                   productsInMachine.add(productName);
                   buyItem(client, product);
               }
           }
           if (!productsInMachine.contains(productName)){
               System.out.println("Sorry " + productName + " out of stock");
           }

       }
    }

    public void buyItem(Client client, Product product){
        String currProduct = product.name();
        for (int i = 0; i < this.products.length; i++){
            // Handle null elements if the Array is not initialized
            // with all products to avoid the NullPointerException
            if (this.products[i] != null && currProduct.equals(this.products[i].name())){
                double value = op.check(client.getMoney(), product.price());
                if (value >= 0){
                    client.setMoney(value);
                    System.out.println("Operation approved \n" +
                            "You have bought: " + currProduct + ".\n " +
                            "Your new card Balance is: $" + client.getMoney());

                    // Remove product from Array
                    if (i < (this.products.length -1) && (this.products[i+1] != null)){
                        while(i < (this.products.length -1)){
                            this.products[i] = this.products[i+1];
                            i++;
                        }
                        this.products[i] = null;
                    }
                    // Notify if product is out of stock
                    Set<String> actualProductList = new HashSet<>();
                    for (Product prod: this.products){
                        if (prod != null){
                            actualProductList.add(prod.name());
                        }

                    }

                    if (!actualProductList.contains(currProduct)){
                        System.out.println(product.name() + " " + "Out of stock");
                    }


                }
                else{
                    System.out.println("Operation denied. Insufficient balance to by: " + product.name());
                }
            }

        }

    }

    public void supply(){
        int curr = 0;
        int finalProducts = 0;
        for (int i = 0; i < products.length; i ++) {
            if (products[i] != null) {
                curr++;
            }
        }

        if (curr < products.length) {
            Set<String> existingProducts = new HashSet<>();
            for (Product p: products){
                if (p != null){
                    existingProducts.add(p.name());
                }
            }

            // Use iterator to safely remove elements from the list
            Iterator<Pair> iterator = store.stock.iterator();
            while (iterator.hasNext() && curr < products.length){
                Pair pair = iterator.next();
                Product product = pair.product();
                int quantity = pair.amount();

                if (curr >= products.length){
                    System.out.println("Vending machine is full  1");
                    break; // Stop adding if vending machine is full;
                }

                // Prioritize adding different products before considering same products
                if (!existingProducts.contains(product.name())) {
                    products[curr++] = product;  // Add one unit of the different product
                    finalProducts ++;
                    int remainingQuantity = quantity - 1; // Decrease the remaining quantity by 1
                    pair.setAmount(remainingQuantity); // Update amount in the stock

                    // Remove the product from the store if the stock is exhausted
                    if (remainingQuantity == 0) {
                        iterator.remove();
                    }
                    // Add the product to the set of existing products in the vending machine
                    existingProducts.add(product.name());
                }
            }
            // If the code has arrived here,
            // there is still free space in the vending machine and no more different products.
            // Refill with the same product
            iterator = store.stock.iterator(); // Reset iterator
            while(iterator.hasNext() && curr < products.length){
                System.out.println("...Line 188...");
                Pair pair = iterator.next();
                Product product = pair.product();
                int quantity = pair.amount();

                // Add products to the vending machine until
                // the stock in the store is exhausted or the machine is full
                while(quantity > 0 && (curr < products.length)){
                    products[curr++] = product;
                    finalProducts ++;
                    quantity--;
                }

                // Update the stock amount in store
                pair.setAmount(quantity);
                if (quantity == 0) {
                    iterator.remove(); // Remove the product from the store if the amount becomes 0
                }
                if (curr >= products.length){
                    System.out.println("Vending machine is full 2"); // Stop adding if vending machine is full;
                }
            }

        }
        // Check if the store is empty after supplying the vending machine
        if (store.stock.isEmpty()) {
            System.out.println("Total added: " + finalProducts +". Store out of stock.");
        }
    }
}
