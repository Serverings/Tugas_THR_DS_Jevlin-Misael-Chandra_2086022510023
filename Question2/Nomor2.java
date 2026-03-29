import java.util.*;

public class Nomor2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        Stack<Integer> stack = new Stack<>();

        String[] g1 = sc.nextLine().split(" ");

        for (String s : g1) {
            if (isOperator(s)) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(calculate(a, b, s));
            } else {
                stack.push(Integer.parseInt(s));
            }
        }

        Queue<String> queue = new LinkedList<>();
        String[] g2 = sc.nextLine().split(" ");

        for (String s : g2) {
            queue.offer(s);
        }

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            if (isOperator(curr)) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(calculate(a, b, curr));
            } else {
                stack.push(Integer.parseInt(curr));
            }
        }

        System.out.println(stack.pop());
    }

    public static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    public static int calculate(int a, int b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
        }
        return 0;
    }
}