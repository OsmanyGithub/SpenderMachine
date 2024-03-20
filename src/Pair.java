public class Pair {
    private Product product;
    private int amount;
    public Pair(Product product, int amount){
        this.product = product;
        this.amount =  amount;
    }

    public Product product() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int amount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
