public class Client {
    private String name;
    private double money;
    public Client(String name, double money){
        this.name = name;
        this.money = money;
    }

    public double getMoney(){
        return money;
    }

    public void setMoney(double money){
        this.money = money;
    }

    public String toString(){
        return name + " has $" + money;
    }
}
