import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

public class TwoC {
  public static List<Integer> rods = new ArrayList<Integer>();
  public static int d;
  public static Knapsack solution;
  public static Set<List<Integer>> seen = new HashSet<List<Integer>>();
  public static void main (String [] args) throws Exception
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    d = Integer.parseInt(br.readLine());
    String[] inputs;
    while(true) {
      inputs = br.readLine().split(" ");
      int val = Integer.parseInt(inputs[0]);
      int num = Integer.parseInt(inputs[1]);
      if (val == 0) {
        break;
      }

      for(int i = 0; i < num; i++){
        rods.add(val);
      }
    }

    Collections.sort(rods);
    recurse(new Knapsack());
    if (solution != null){
      System.out.println(solution.values.size() - 1);
    } else {
      System.out.println("No solution possible");
    }
  }


  public static void recurse(Knapsack k){
    if (seen.contains(k.values)){
      return;
    } else {
      seen.add(k.values);
    }
    for (int i = k.lastIndex + 1; i < rods.size(); i++) {
      Knapsack newK = k.clone();
      int total = newK.add(i, rods.get(i));
      if(seen.contains(newK.values)) {
        continue;
      }
      if (total == d) {
        if (solution == null ||  solution.values.size() > newK.values.size()){
          solution = newK;
        }
      } else if (total > d) {
        return;
      } else {
        recurse(newK);
      }
    }
  }
        




  public static class Knapsack implements Comparable<Knapsack>{
    public int lastIndex = -1;
    public List<Integer> values = new ArrayList<Integer>();
    public int total;
    public int add(int index, int value) {
      this.lastIndex = index;
      values.add(value);
      total += value;
      return total;
    }

    public Knapsack clone() {
      Knapsack k = new Knapsack();
      k.lastIndex = this.lastIndex;
      k.values = new ArrayList<Integer>(values);
      k.total = this.total;
      return k;
    }

    public String toString() {
      return "Knapsack: " + this.values + ", " + this.total;
    }
    public boolean equals(Object o){
      if(o.getClass() != Knapsack.class) {
        return false;
      } else if (values.equals( ((Knapsack) o).values)){
        return true;
      } else {
        return false;
      }
    }

    public int compareTo(Knapsack k){
      return this.values.size() - k.values.size();
    }
  }
}
