import java.util.*;

class Borrower {
    String name;
    int key;
    int priority;
    int order;

    Borrower(String name, int key, int priority, int order) {
        this.name = name;
        this.key = key;
        this.priority = priority;
        this.order = order;
    }
}

public class Nomor3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) sc.nextInt();

        sc.nextLine();

        String[] data = sc.nextLine().split(" ");

        int[] priority = new int[n];
        for (int i = 0; i < n; i++) {
            priority[i] = sc.nextInt();
        }

        List<Borrower> list = new ArrayList<>();

        int idx = 0;
        for (int i = 0; i < n; i++) {
            String name = data[idx++];
            int key = Integer.parseInt(data[idx++]);

            list.add(new Borrower(name, key, priority[i], i));
        }

        Collections.sort(list, (a, b) -> {
            if (a.key != b.key)
                return 0; 
            if (a.priority != b.priority)
                return a.priority - b.priority;
            return a.order - b.order;
        });

        for (Borrower b : list) {
            System.out.println(b.name + " | " + b.key);
        }
    }
}