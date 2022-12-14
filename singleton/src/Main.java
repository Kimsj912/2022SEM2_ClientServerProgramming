public class Main {
    public static void main(String[] args){
        Singleton.getInstance();
        Singleton.getInstance();
        Singleton.getInstance();
        Singleton.getInstance();
        Singleton.getInstance();
        Singleton.getInstance();
        System.out.println("TEST");
        // 종료되는걸 main에게 알려줄 수 있도록 따로 처리를 해야한다.
    }
}