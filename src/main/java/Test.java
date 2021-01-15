import java.util.Scanner;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        while (true) {
            System.out.println(containSpecial(sca.nextLine()));
        }
    }

    private static boolean containSpecial(String str) {
        String pattern = "^[ㄱ-ㅎ가-힣a-zA-Z0-9@.]*$";
        return !Pattern.matches(pattern, str);
    }

}
