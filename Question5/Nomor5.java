import java.util.*;

class Card {
    int value, category;

    Card(int v, int c) {
        value = v;
        category = c;
    }

    public String toString() {
        return value + "," + category;
    }
}

public class Nomor5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<List<Card>> players = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            String[] input = sc.nextLine().split(" ");
            List<Card> hand = new ArrayList<>();

            for (String s : input) {
                String[] parts = s.split(",");
                hand.add(new Card(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1])
                ));
            }

            hand.sort((a, b) -> {
                if (a.category != b.category)
                    return a.category - b.category;
                return a.value - b.value;
            });

            players.add(hand);
        }

        int turn = sc.nextInt() - 1;

        Stack<Card> stack = new Stack<>();

        int lastPlayer = -1;
        int skipCount = 0;

        while (true) {
            List<Card> hand = players.get(turn);

            boolean played = false;

            if (stack.isEmpty() || turn == lastPlayer) {
                Card c = hand.remove(0); 
                stack.push(c);
                lastPlayer = turn;
                played = true;
            } else {
                Card top = stack.peek();

                for (int i = 0; i < hand.size(); i++) {
                    Card c = hand.get(i);
                    if (c.category == top.category && c.value > top.value) {
                        stack.push(c);
                        hand.remove(i);
                        lastPlayer = turn;
                        played = true;
                        break;
                    }
                }
            }

            if (played) {
                skipCount = 0;
            } else {
                skipCount++;
            }

            if (hand.isEmpty()) {
                System.out.println(turn + 1);

                while (!stack.isEmpty()) {
                    System.out.println(stack.pop());
                }
                break;
            }

            if (skipCount == 4) {
                lastPlayer = -1;
                skipCount = 0;
            }

            turn = (turn + 1) % 4;
        }
    }
}