import java.util.*;

class Person {
    int time, index;

    Person(int time, int index) {
        this.time = time;
        this.index = index;
    }
}

public class Nomor1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int T = sc.nextInt();

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            people.add(new Person(t, i + 1)); 
        }

        solve(people, T);
    }

    public static void solve(List<Person> people, int T) {

        people.sort(Comparator.comparingInt(p -> p.time));

        List<String> actions = new ArrayList<>();
        List<Person> left = new ArrayList<>(people);

        int totalTime = 0;

        while (left.size() > 3) {
            Person p1 = left.get(0);
            Person p2 = left.get(1);
            Person pn = left.get(left.size() - 1);
            Person pn1 = left.get(left.size() - 2);

            int cost1 = p1.time + 2 * p2.time + pn.time;
            int cost2 = 2 * p1.time + pn1.time + pn.time;

            if (totalTime + Math.min(cost1, cost2) > T) break;

            if (cost1 < cost2) {
                actions.add(p1.index + " " + p2.index + " ->");
                actions.add(p1.index + " <-");
                actions.add(pn1.index + " " + pn.index + " ->");
                actions.add(p2.index + " <-");

                totalTime += cost1;
            } else {
                actions.add(p1.index + " " + pn.index + " ->");
                actions.add(p1.index + " <-");
                actions.add(p1.index + " " + pn1.index + " ->");
                actions.add(p1.index + " <-");

                totalTime += cost2;
            }

            left.remove(left.size() - 1);
            left.remove(left.size() - 1);
        }

        if (left.size() == 3) {
            Person p1 = left.get(0);
            Person p2 = left.get(1);
            Person p3 = left.get(2);

            if (totalTime + p1.time + p2.time + p3.time <= T) {
                actions.add(p1.index + " " + p2.index + " ->");
                actions.add(p1.index + " <-");
                actions.add(p1.index + " " + p3.index + " ->");

                left.clear();
            }
        }

        else if (left.size() == 2) {
            Person p1 = left.get(0);
            Person p2 = left.get(1);

            if (totalTime + p2.time <= T) {
                actions.add(p1.index + " " + p2.index + " ->");
                left.clear();
            }
        }

        else if (left.size() == 1) {
            Person p1 = left.get(0);

            if (totalTime + p1.time <= T) {
                actions.add(p1.index + " ->");
                left.clear();
            }
        }

        for (String act : actions) {
            System.out.println(act);
        }

        if (!left.isEmpty()) {
            List<Integer> dead = new ArrayList<>();
            for (Person p : left) dead.add(p.index);

            System.out.println("Non-survivors: " + dead);
        }
    }
}