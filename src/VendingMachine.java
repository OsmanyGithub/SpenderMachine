public class VenderMachine {
    private Product[] products;
    
    public VenderMachine(Product[] products){
        this.products = products;
    }
    
    public VenderMachine(Product product){
        this.products = new Product[50];
        products[0] = product;
    }
    
    public VenderMachine(Product product, int amount){
        this.products = new Product[amount];
        products[0] = product;
    }
    
    public VenderMachine(int amount){
        this.products = new Product[amount];
    }

    public void addProduct(Product product){
        if (availableSpace() > 0){
            System.out.println("Machine have " + availableSpace()+"(s) free space");
        }

        else{
            System.out.println("Machine is full. You can't add products right now");
        }

    }

    public int availableSpace(){
        int freeSpace = 0;
        for (int i = 0; i < products.length; i++){
            if (products[i] == null){
                freeSpace ++;
            }
        }
        return freeSpace;
    }
}
