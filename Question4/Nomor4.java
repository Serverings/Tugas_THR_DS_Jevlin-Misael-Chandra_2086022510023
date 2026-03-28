import java.util.*;

public class Nomor4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) break;
            if (sb.length() > 0) sb.append(" ");
            sb.append(line);
        }
        
        String[] input = sb.toString().split(" ");
        
        List<List<String>> stacks = new ArrayList<>();
        
        for (String card : input) {
            if (card.isEmpty()) continue;
            
            boolean placed = false;
            
            for (int i = 0; i < stacks.size(); i++) {
                if (!stacks.get(i).contains(card)) {
                    stacks.get(i).add(card);
                    placed = true;
                    break; 
                }
            }
            
            if (!placed) {
                List<String> newStack = new ArrayList<>();
                newStack.add(card);
                stacks.add(newStack);
            }
        }
        
        for (List<String> st : stacks) {
            StringBuilder out = new StringBuilder();
            for (String c : st) {
                if (out.length() > 0) out.append(" ");
                out.append(c);
            }
            System.out.println(out.toString());
        }
    }
}