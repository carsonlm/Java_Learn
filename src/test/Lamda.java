package test;

public class Lamda {
    public static void main(String[] args) {
       new Thread( () -> System.out.println("s")).start();
    }


}
