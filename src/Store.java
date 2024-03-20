import java.util.ArrayList;
import java.util.List;

public class Store {
    protected List<Pair> stock;
    private Product cheetos = new Product("Cheetos", 1.35);
    private Product sprite = new Product("Sprite", 2.99);
    private Product cocaCola = new Product("Coca Cola", 1.99);
    private Product doritos = new Product("Doritos", 2.08);
    private Product pringles = new Product("Pringles", 3.75);
    private Product beefJerky = new Product("Beef Jerky", 7.37);
    private Product nutellaBiscuits = new Product("Nutella Biscuits", 4.48);
    private Product wonderfulPistachios = new Product("Wonderful Pistachios", 7.48);
    private Product oreoMini = new Product("Oreo mini", 3.17);

    public Store(){
        stock = new ArrayList<Pair>();

        stock.add(new Pair(cheetos, 3));
       /* stock.add(new Pair(sprite, 3));
        stock.add(new Pair(cocaCola, 3));
        stock.add(new Pair(doritos, 3));
        stock.add(new Pair(pringles, 3));
        stock.add(new Pair(beefJerky, 3));
        stock.add(new Pair(nutellaBiscuits, 3));
        stock.add(new Pair(wonderfulPistachios, 3));
        stock.add(new Pair(oreoMini, 3));

        */


    }

    public void inventory(){
        for (int i = 0; i < stock.size(); i++){
           Pair pair = stock.get(i);
           Product product = pair.product();
           int quantity = pair.amount();
           System.out.println(product + " (" + quantity +" units)");
        }

    }

}
