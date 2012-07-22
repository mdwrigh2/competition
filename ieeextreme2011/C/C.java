import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class C {

  public static int i = 0;
  public static int d;
  /*
   * The first line of the input D is the distance between the two air bases to be connected in
   * kilometers. (0 < D < 5000)
   *
   * Each subsequent line contains a pair of numbers Li Ni, indicating the length of the cable in
   * kilometers and the quantity available in the inventory. (0 < i <= 20, 1 <= Li <= 200, 1 <= Ni
   * <= 100)
   *
   * The input is terminated by 0 0.
   */
  public static void main(String [] args) throws Exception 
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    d = Integer.parseInt(br.readLine());
    int totalSum = 0;
    String[] inputs;
    Set<Pipe> rods = new HashSet<Pipe>();
    while(true) {
      inputs = br.readLine().split(" ");
      int val = Integer.parseInt(inputs[0]);
      int num = Integer.parseInt(inputs[1]);
      if (val == 0) {
        break;
      }
      for(int i = 0; i < num; i++){
        rods.add(new Pipe(val));
        totalSum += val;
      }
    }
    Set<Set<Pipe>> ps = powerSet(rods);
    System.out.println(ps);
    Set<Set<Pipe>> poss = new HashSet<Set<Pipe>>();
    //for(Set<Pipe> p : ps){
      //if (sumNodes(p) == d){
        //poss.add(p);
      //}
    //}

    //System.out.println(poss);
  }

  public static class Pipe {
    public int val;
    public Pipe(int val){
      this.val = val;
    }

    public String toString(){
      return val+"";
    }
  }

  public static int sumNodes(Set<Pipe> vals) {
    int sum = 0;
    for (Pipe p : vals){
      sum += p.val;
    }
    return sum;
  }

  public static  Set<Set<Pipe>> powerSet(Set<Pipe> originalSet) {
    System.out.println(i++);
    Set<Set<Pipe>> sets = new HashSet<Set<Pipe>>();
    if (originalSet.isEmpty()) {
      sets.add(new HashSet<Pipe>());
      return sets;
    }
    List<Pipe> list = new ArrayList<Pipe>(originalSet);
    Pipe head = list.get(0);
    Set<Pipe> rest = new HashSet<Pipe>(list.subList(1, list.size())); 
    for (Set<Pipe> set : powerSet(rest)) {
      Set<Pipe> newSet = new HashSet<Pipe>();
      newSet.add(head);
      newSet.addAll(set);
      if(sumNodes(newSet) <=  d) {
        sets.add(newSet);
      }
      if(sumNodes(set) <=  d) {
        sets.add(set);
      }
    }           
    return sets;
  }
}
