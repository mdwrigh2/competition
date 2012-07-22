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
    generateNextSet(new HashSet<Knapsack>(), 1);
    if (solution != null){
      System.out.println(solution.values.size() - 1);
    } else {
      System.out.println("No solution possible");
    }
  }

  public static void generateNextSet(Set<Knapsack> sk, int n){
    Set<Knapsack> newSk = new HashSet<Knapsack>();
    if( sk.isEmpty() ){
      for(int i = 0; i < rods.size() ; i++){
        Knapsack k = new Knapsack();
        int total = k.add(i, rods.get(i));
        if (total == d) {
          solution = k;
        }
        newSk.add(k);
      }
    } else {
      for(Knapsack k : sk) {
        for(int i = 0; i < rods.size(); i++) {
          Knapsack newK = k.clone();
          if(!newK.indices.contains(i)){
            if(seen.contains(newK.values)){
              continue;
            } else {
              seen.add(newK.values);
              int total = newK.add(i, rods.get(i));
              newSk.add(newK);
              if(total == d) {
                solution = newK;
              }
            }
          }
        }
      }
    }
    if (solution != null || n  == rods.size()){
      return;
    G
    System.out.println(n);
    generateNextSet(newSk, n+1);
  }


  public static class Knapsack implements Comparable<Knapsack>{
    public List<Integer> indices = new ArrayList<Integer>();
    public List<Integer> values = new ArrayList<Integer>();
    public int total;
    public int add(int index, int value) {
      indices.add(index);
      values.add(value);
      total += value;
      return total;
    }

    public Knapsack clone() {
      Knapsack k = new Knapsack();
      k.values = new ArrayList<Integer>(values);
      k.indices = new ArrayList<Integer>(indices);
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
