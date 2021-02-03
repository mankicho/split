import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Date().getDay());
        System.out.println(new Date(new Date().getTime() - 1000 * 60 * 60 * 24 * 2).getDay());
        System.out.println(new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * 2).getDay());
    }
}
