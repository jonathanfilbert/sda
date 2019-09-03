/**
 * FACTORIALS This program uses recursion to find factorials. The program takes
 * in a number, and if the number is less than 2, returns 1 (base case) However,
 * if the number is greater or equal than 2, it will call the number times the
 * recursion of its previous number
 */
public class factorials {

    public static int factorial(int number) {
        if (number < 2) {
            return 1;
        } else {
            return (number * factorial(number - 1));
        }
    }

    public static void main(String[] args) {
        System.out.println(factorial(3));
    }
}