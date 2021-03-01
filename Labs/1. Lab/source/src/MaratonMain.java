import java.util.Scanner;

class Atleticar{
    private String name;
    private String sex;
    private int age;
    private double runTime;
    private String origin;

    public Atleticar(){}
    public Atleticar(String name, String sex, int age, double runTime, String origin){
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.runTime = runTime;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public double getRunTime() {
        return runTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRunTime(double runTime) {
        this.runTime = runTime;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString(){
        return (this.name + "\n" + this.age + "\n" + this.origin + "\n" + this.runTime + "\n");
    }
}

interface IMaraton{
    public Atleticar najdobroVreme();
    public int atleticariOd(String s);
}

class Maraton implements IMaraton {
    private String place;
    private int year;
    private Atleticar [] at;

    public Maraton(){}
    public Maraton(String place, int year, Atleticar [] at){
        this.place = place;
        this.year = year;
        this.at = at;
    }

    public String getPlace() {
        return place;
    }

    public int getYear() {
        return year;
    }

    public Atleticar[] getAt() {
        return at;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAt(Atleticar[] at) {
        this.at = at;
    }

    @Override
    public String toString(){
        String s = this.place + "\n" + this.year + "\n";
        for(int i=0; i<this.at.length; i++)
            s += this.at[i].toString();
        return s;
    }

    @Override
    public Atleticar najdobroVreme(){
        double min=this.at[0].getRunTime();
        int minIndex = 0;
        for(int i=0; i<this.at.length; i++){
            if(this.at[i].getRunTime() < min) {
                min = this.at[i].getRunTime();
                minIndex = i;
            }
        }
        return this.at[minIndex];
    }

    @Override
    public int atleticariOd(String s){
        int counter = 0;
        for(int i=0; i<this.at.length; i++) {
            if (this.at[i].getOrigin().equals(s))
                counter++;
        }
        return counter;
    }
}

public class MaratonMain {

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        Atleticar[] atleticari = new Atleticar[n];

        String ime;
        String pol;
        int vozrast;
        double vreme;
        String zemja;

        input.nextLine();

        for(int i=0;i<n;i++)
        {
            ime = input.nextLine();
            pol = input.nextLine();
            vozrast = input.nextInt();
            vreme = input.nextDouble();
            input.nextLine();
            zemja = input.nextLine();
            atleticari[i]=new Atleticar(ime,pol,vozrast,vreme,zemja);
        }

        String mesto;
        int godina;
        String zemjaP;
        mesto = input.nextLine();
        godina = input.nextInt();
        input.nextLine();

        Maraton m1 = new Maraton(mesto, godina, atleticari);
        System.out.print(m1.toString());

        zemjaP = input.nextLine();
        System.out.println("Prvo mesto: " + m1.najdobroVreme().toString());
        System.out.println("Ima vkupno " + m1.atleticariOd(zemjaP) + " atleticar/i od " + zemjaP);
    }
}
