import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;

public class TwoC {
  public static List<Pipe> rods = new ArrayList<Pipe>();
  public static int d;
  public static List<Knapsack> solutions = new ArrayList<Knapsack>();
  public static void main (String [] args) throws Exception
  public static Set<Set<Pipe>> memo = new Set<Set<Pipe>>();
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

      rods.add(new Pipe(num, val));
    }

    Collections.sort(rods);
    System.out.println(rods);
    recurse(new Knapsack());
    System.out.println(solutions);
  }


  public static void recurse(Knapsack k){
  }
        
  public static class Pipe implements Comparable<Pipe> {
    public int count;
    public int size;
    public Pipe(int count, int size){
      this.count = count;
      this.size = size;
    }
    public int compareTo(Pipe p){
      return this.size - p.size;
    }

    public String toString() {
      return "Pipe: " + size + ", " + count;
    }
  }


  public static class Knapsack {
    public List<Integer> values = new ArrayList<Integer>();
    public int total;
    public int add(int index, int value) {
      this.lastIndex = index;
      values.add(value);
      total += value;
      return total;
    }

    public String toString(){
      return "Knapsack: " + this.values + ", " + this.total;
    }

    public Knapsack clone() {
      Knapsack k = new Knapsack();
      k.lastIndex = this.lastIndex;
      k.values = new ArrayList<Integer>(values);
      k.total = this.total;
      return k;
    }
  }
}
