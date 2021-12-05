package Task3;
class MyThread1 extends Thread{
    private int arr[];
    public MyThread1(int arr[]){
        this.arr=arr;
    }
    public void run(){
       for(int i=0;i<arr.length;i++){
           System.out.print(arr[i]);
           try {
               Thread.sleep(4000);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }
}
public class Task3 {

    public static void main(String[] args) {
        int []arr1={1,3,5,7,9};
        int []arr2={2,4,6,8,10};
        MyThread1 t1=new MyThread1(arr1);
        MyThread1 t2=new MyThread1(arr2);
        t1.start();
        t2.start();
        ppx("123456789@qq.com");
    }
    public static Boolean ppx(String email){
        if(email.matches("[0,9]{10,11}"+"@qq.com")==true){
          return true;
        }
        else {
            return false;
        }
    }
}
