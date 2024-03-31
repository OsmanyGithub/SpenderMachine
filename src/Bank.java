public class Bank {
    public double validatePurchase(Client client, Product product) {
        double result = 0;
        result = client.getMoney() - product.price();

        if (result >= 0) {
            client.setMoney(result);

        }

        else {
            result = -1;
        }

        return result;
    }
}
