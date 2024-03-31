import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class VendingMachine {
    private Bank bank = new Bank();
    protected Product[] products;
    private final int CAPACITIY = 10;
    protected Set<String> listOfProductsInTheMachine;
    
    public VendingMachine(){
        products = new Product[CAPACITIY];
        listOfProductsInTheMachine = new HashSet<>();
    }

    // Method to keep the name list of products in the machine updated.
    public void updateListOfProductsInTheMachine(){
        listOfProductsInTheMachine.clear(); // Clear the previous list
        for (Product p : products) {
            if (p != null){
                listOfProductsInTheMachine.add(p.name());
            }
        }
    }

    public void addProduct(Product product) {
        if (availableSpace() > 0 ) {
            System.out.println("Adding " + product.name() + " to the vending machine.");
            for (int i = 0; i < products.length; i++) {
                if (products[i] == null) {
                    products[i] = product;
                    listOfProductsInTheMachine.add(products[i].name());
                    return; // Exit the loop after adding the product
                }
            }
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
        int num = 0; // The variable num counts the amount of products in the Machine
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < products.length; i ++){
            if (products[i] != null){
                num ++;
                sb.append("Product " + (i+1) + ": " + products[i] + "\n");
            }
            else{
                break;
            }
        }
        System.out.println("Products available in the machine: " + num);
        System.out.println(sb.toString());
    }


    public void buyItem(Client client, String... productList) {
        updateListOfProductsInTheMachine(); // Call method to update the list of products in the machine;

        if (productList.length == 1) {
            String productName = productList[0];
            if (!listOfProductsInTheMachine.contains(productName)) {
                System.out.println("Sorry " + productName + " out of stock");
            }
            else {
                for (Product product : this.products) {
                    if (product != null && productName.equals(product.name())) {
                        buyItem(client, product); // Call the buyItem method with Product object
                        return; // No need to continue searching once the product is found and bought
                    }
                }
            }
        }

        else {
            for (String productName : productList) {
                if (!listOfProductsInTheMachine.contains(productName)) {
                    System.out.println("Sorry " + productName + " out of stock");
                }
                else {
                    for (Product product : this.products) {
                        if (product != null && productName.equals(product.name())) {
                            buyItem(client, product); // Call the buyItem method with Product object
                            break; // No need to continue searching once the product is found and bought
                        }
                    }
                }
            }
        }
    }

    private void buyItem(Client client, Product product) {
        for (int i = 0; i < this.products.length; i++) {
            if (product.equals(this.products[i])) {
                double clientBalance = bank.validatePurchase(client, product);

                if (clientBalance >= 0) {
                    processSuccessfulPurchase(client, product, i);
                    updateListOfProductsInTheMachine(); // Call method to update the list of products in the machine
                } else {
                    handleInsufficientFunds(client, product);
                }
                return; // Once the product is found and processed, exit the loop
            }
        }
    }

    private void processSuccessfulPurchase(Client client, Product product, int index) {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Operation approved \n" +
                "You have bought: " + product.name() + ".\n " +
                "Your new card Balance is: $" + df.format(client.getMoney()));

        removeProductFromMachine(index, products);

        updateListOfProductsInTheMachine();
        if (!listOfProductsInTheMachine.contains(product)) {
            System.out.println(product + " " + "Out of stock");
        }
    }

    private void handleInsufficientFunds(Client client, Product product) {
        System.out.println("Operation denied. Insufficient balance to buy: " + product.name() + "\n" +
                "Your actual balance is: " + client.getMoney() + " and " + product.name() + " price is " +
                product.price());
    }

    // Product removal Method
    public void removeProductFromMachine(int num, Product[] prodsArray) {
        if (prodsArray[num] != null) {
            // Remove product name from the set
            System.out.println("Removing product " + prodsArray[num].name() + " from the machine's product list.");
            listOfProductsInTheMachine.remove(prodsArray[num].name());

            // Remove product from Array
            for (int i = num; i < prodsArray.length - 1; i++) {
                prodsArray[i] = prodsArray[i + 1];
            }
            prodsArray[prodsArray.length - 1] = null;
        }
    }

    public void supply(Supplier supplier) {
        if (supplier != null) {
            supplier.supply();
        } else {
            System.out.println("Supplier is not set. Cannot supply products.");
        }
    }

}
