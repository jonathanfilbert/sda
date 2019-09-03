
/**
 * reverse
 */
import java.util.Scanner;

public class reverse {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        String answer = "";

        for (int i = input.length() - 1; i >= 0; i--) {
            answer += input.charAt(i);
        }
        System.out.println(answer);
    }
}