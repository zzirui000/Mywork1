package Task1;




import java.awt.image.ImagingOpException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

abstract class Cat{
    protected String name;
    protected int age;
    protected String sex;
    protected static double price;
    public Cat(String name,int age,String sex,double price){
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.price=price;
    }
    public String toString(){
        return "name:"+name+",age:"+age+",sex:"+sex+",price:"+price;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public static double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static void setPrice(double price) {
        Cat.price = price;
    }
}
class OrangeCat extends Cat{
     private boolean isfat;

    public OrangeCat(String name, int age, String sex, double price, boolean isfat) {
        super(name, age, sex, price);
        this.isfat = isfat;
    }


}
class BlackCat extends Cat{

    public BlackCat(String name, int age, String sex, double price) {
        super(name, age, sex, price);
    }
}

class Customer {
    String name;
    int rua;
    LocalDate localDate;
    public Customer(){}
    public Customer(String name,int rua){
        this.name=name;
        this.rua=rua;
        this.localDate=LocalDate.now();
    }
    public String toString(){
        return "name:"+name+",rua:"+rua+",time:"+localDate;
    }
}
interface CatCafe{
    void BuyCat(String name,int age,String sex,boolean isFat,String lei);
    void TotalCostomer(String name,int rua);
    void Close(LocalDate LocalDate);
}
  class MyCatCafe implements CatCafe{
      private double money;
      private ArrayList<String> cat_list=new ArrayList<>();
      private ArrayList<Customer> cs_list=new ArrayList<>();
      private double rua_get;
      public int count=0;
      public MyCatCafe(double money) {
          this.money = money;
      }

      private void getCatMsg(int number){
          System.out.println(cat_list.get(number));
      }

      @Override
      public void BuyCat(String name, int age, String sex, boolean isFat, String lei) {
          if (lei=="orange"){
              try{
                  OrangeCat orangeCat=new OrangeCat(name,age,sex,200,isFat);
                  double or_money=orangeCat.getPrice();
                  double temp=money-or_money;
                  if (temp<0){
                      throw new ImagingOpException("店铺没钱了");

                  }else {
                      cat_list.add(count,orangeCat.toString());
                      count++;
                      money-=or_money;
                  }
              }catch (CatNotFoundException e){
                  e.printStackTrace();
              }
          }
          else {
              try{
                  BlackCat blackCat=new BlackCat(name,age,sex,350);
                  double bl_money=blackCat.getPrice();
                  double temp=money-bl_money;
                  if (temp<0){
                      throw new ImagingOpException("店铺没钱了");

                  }else {
                      cat_list.add(count,blackCat.toString());
                      count++;
                      money-=bl_money;
                  }
              }catch (CatNotFoundException e){
                  e.printStackTrace();
              }
          }
      }

      @Override
      public void TotalCostomer(String name, int rua) {
          Random rm=new Random();
          int rm_number=rm.nextInt(5);
          try {
              Customer customer=new Customer(name,rua);
              if (cat_list.isEmpty()){
                  throw new CatNotFoundException("没有猫");
              }
              else {
                  money+=(15*rua);
                  rua_get=rua_get+rua*15;
                  cs_list.add(customer);
                  getCatMsg(rm_number);
              }

          }catch (InsufficientBalanceException e){
              e.printStackTrace();
          }

      }

      @Override
      public void Close(LocalDate localDate) {

              for (Customer i:cs_list) {
                  System.out.println(i);
              }
              System.out.print("今日的收入为"+rua_get);


      }
  }

class CatNotFoundException extends RuntimeException{
    public CatNotFoundException(String message) {
        super(message);
    }
}
class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

//Test
public class task1 {
    public static void main(String[] args) {
        MyCatCafe a= new MyCatCafe(10000);
        a.BuyCat("tom",2,"公",false,"orange");
        a.BuyCat("tim",2,"母",true,"orange");
        a.BuyCat("dsf",3,"母",false,"black");
        a.BuyCat("ppf",3,"公",false,"black");
        a.BuyCat("dsdaf",2,"母",false,"black");
        a.TotalCostomer("weiwei",5);
        a.TotalCostomer("dada",3);
        a.Close(LocalDate.now());

    }
}
