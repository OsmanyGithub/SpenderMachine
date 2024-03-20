public class Main {
    public static void main(String[] args) {
        Product chips = new Product("Chips", 2.54);
        Product ricola = new Product("Ri-Cola", 1.05);
        Product iceCream = new Product("Ice Cream", 3.35);
        Product[] products = new Product[10];
        products[0] = chips;
        products[1] = ricola;
        products[2] = iceCream;

        Store store = new Store();
        VendingMachine machine = new VendingMachine(products);
        machine.showProducts();

        Product fanta = new Product("Fanta", 2.35);
        System.out.println(".............Executing machine.addProduct(fanta)...........");
        machine.addProduct(fanta);
        machine.showProducts();


        Client pepe = new Client("Pepe", 2);
        Client lui = new Client("Lui", 40);

        System.out.println(".....Clients..........");
        System.out.println(pepe);
        System.out.println(lui);
        System.out.println("");


        System.out.println(".....Pepe is buying iceCream.......");

        machine.buyItem(pepe, iceCream); // Check what is the problem in this line.
        machine.showProducts();

        System.out.println(".....Lui is buying chips.......");
        machine.buyItem(lui, chips);
        System.out.println("");
        System.out.println(".......After lui bought......");
        machine.showProducts();
        System.out.println("..After machine were refill.....");
        machine.supply();

        machine.showProducts();
        System.out.println(".....New Client..........");
        Client mark = new Client("Mark", 50);
        System.out.println(mark);
        System.out.println("");
        System.out.println(".....Mark in buying........");
        machine.buySeveralItems(mark, "Doritos", "Cheetos", "Chips", "Sprite");
        machine.showProducts();
        System.out.println("....Call machine.supply......");
        machine.supply();
        System.out.println("");
        store.inventory();
        System.out.println("");
        System.out.println("");
        System.out.println("......New Client Susi.....");
        Client susi = new Client("Susi", 25);
        System.out.println(susi);
        System.out.println("");











    }
}
