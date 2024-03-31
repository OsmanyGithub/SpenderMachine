public class Main {
    public static void main(String[] args) {
        System.out.println("");
        // Creating Store, Vending Machine, Supplier and a product
        Store store = new Store();
        VendingMachine machine = new VendingMachine();
        Supplier supplier = new Supplier(machine, store);
        Product fanta = new Product("Fanta", 2.35);

        System.out.println(".Executing machine.addProduct(fanta)." +
                " It have to show one Fanta in the machine.");
        System.out.println("");
        machine.addProduct(fanta);
        machine.showProducts();

        // Creating clients
        Client pepe = new Client("Pepe", 2);
        Client lui = new Client("Lui", 40);

        System.out.println(".....Clients created.........");
        System.out.println(pepe);
        System.out.println(lui);
        System.out.println("");


        System.out.println(".....Pepe is buying Fanta..But he haven't enough founds.....");

        machine.buyItem(pepe, "Fanta");
        machine.showProducts();
        System.out.println("");


        System.out.println(".Lui is buying chips and Fanta. .Machine has Fanta but not Chips..");
        machine.buyItem(lui, "chips", "Fanta");
        System.out.println("");
        System.out.println(".......After lui bought......");
        machine.showProducts();
        System.out.println("..After machine were refill.....");
        machine.supply(supplier);

        System.out.println(".....New Client.. Mark.........");
        Client mark = new Client("Mark", 50);
        System.out.println(mark);
        System.out.println("");
        System.out.println(".....Mark in buying..2 Doritos, Cheetos, Sprite......");
        machine.buyItem(mark, "Doritos", "Doritos","Cheetos", "Sprite");
        System.out.println("");

        System.out.println("..Show what is left in the machine");
        machine.showProducts();

        System.out.println("....Call machine.supply......");
        machine.supply(supplier);
        System.out.println("");

        System.out.println("");
        System.out.println("..What products store have now?");
        store.inventory();
        System.out.println("");











    }
}
