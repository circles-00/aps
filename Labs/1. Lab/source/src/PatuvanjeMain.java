import java.util.Scanner;

class cusException extends Exception{
    private String msg;

    public cusException(String msg){
        this.msg = msg;
    }

    public void printMsg(){
        System.out.println(this.msg);
    }
}

abstract class Patuvanje{
    private String name;
    private int price;

    public Patuvanje(){}

    public Patuvanje(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public abstract int vratiVremeVoDenovi();

    public static int vratiMinCena(Patuvanje [] niza, int n, Patuvanje zaSporedba){
        int min=0, flag = 0;
        for(int i=0; i<n; i++){
            if(niza[i].vratiVremeVoDenovi() > zaSporedba.vratiVremeVoDenovi()){
                if(flag == 0){
                    flag = 1;
                    min = niza[i].getPrice();
                }
                if(niza[i].getPrice() < min)
                    min = niza[i].getPrice();
            }
        }
        return min;
    }
}

class PraznicnoPatuvanje extends Patuvanje{
    private int startDay;
    private int startMonth;
    private int endDay;
    private int endMonth;

    public PraznicnoPatuvanje(String name, int price, int startDay, int startMonth, int endDay, int endMonth){
        super(name, price);
        try {
            if (startMonth > endMonth || (startMonth == endMonth && startDay > endDay)) {
                throw new cusException("Iskluchok");
            }
            this.startDay = startDay;
            this.startMonth = startMonth;
            this.endDay = endDay;
            this.endMonth = endMonth;
        }
        catch (cusException e) {
            e.printMsg();

            this.startMonth = endMonth;
            this.endMonth = startMonth;

            this.startDay = endDay;
            this.endDay = startDay;
        }
    }


    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    @Override
    public int vratiVremeVoDenovi(){
        return (30 * ((this.endMonth - this.startMonth) - 1) + (30 - this.startDay) + this.endDay);
    }

}

class GodishenOdmor extends Patuvanje{
    private int travelTime;

    public GodishenOdmor(String name, int price, int travelTime) {
        super(name, price);
        this.travelTime = travelTime;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    @Override
    public int vratiVremeVoDenovi(){
        return this.travelTime;
    }

    @Override
    public int getPrice() {
        return super.getPrice() - 1000;
    }
}

public class PatuvanjeMain {
    public static void main(String[] args) {
        int n;
        Scanner in=new Scanner(System.in);
        n=in.nextInt();

        Patuvanje nizaPatuvanje[]=new Patuvanje[n];

        for (int i=0;i<n;i++){
            int tip=in.nextInt();
            if (tip==0){
                String ime=in.next();
                int cena =in.nextInt();
                int vreme=in.nextInt();
                nizaPatuvanje[i]=new GodishenOdmor(ime,cena,vreme);
            }
            else {
                String ime=in.next();
                int cena =in.nextInt();
                int pocD=in.nextInt();
                int pocM=in.nextInt();
                int krajD=in.nextInt();
                int krajM=in.nextInt();
                nizaPatuvanje[i]=new PraznicnoPatuvanje(ime,cena,pocD,pocM, krajD,krajM);
            }
        }

        //решение на барање 1
        for(int i=0; i<nizaPatuvanje.length; i++){
            if(nizaPatuvanje[i] instanceof  PraznicnoPatuvanje){
                if(((PraznicnoPatuvanje) nizaPatuvanje[i]).getStartMonth() == 6){
                    System.out.print(nizaPatuvanje[i].getName() + " ");
                }
            }
        }
        //решение на барање 2
        double totalTravelTime = 0.0;
        for(int i=0; i<nizaPatuvanje.length; i++)
            totalTravelTime += nizaPatuvanje[i].vratiVremeVoDenovi();
        double avgTravelTime = totalTravelTime / nizaPatuvanje.length;
        System.out.println("\n" + avgTravelTime);

        //решение на барање 3
        String threeName = in.next();
        int threePrice = in.nextInt();
        int threeTravelTime = in.nextInt();

        GodishenOdmor odmor = new GodishenOdmor(threeName, threePrice, threeTravelTime);

        //решение на барање 4
        int getMin = Patuvanje.vratiMinCena(nizaPatuvanje, nizaPatuvanje.length, odmor);
        System.out.println(getMin);

    }
}