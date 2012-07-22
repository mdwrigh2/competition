import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;


public class V {
  /*
   * First line is the number of Test Cases, N.
   * Next line contains Number of students X (X <= 100000), Number of Revision operations R,
   * Number of Group values to calculate G.(#R + #G <= 100000)
   * Next line will be the initial scores for each of the students. Comma separated s1..sX. Each
   * student can be awarded a maximum score of 100.
   * Next line will continue with operations for score revision. Line starts with R, next value is
   * the index of student to revise, and next value is the extra points awarded.
   * Further down will be the operations for calculating group totals. Group start student index,
   * and end student index. (Both students inclusive)
   */
  public static List<List<Integer>> groups = new ArrayList<List<Integer>>();
  public static void main (String [] args) throws Exception
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    for(int i = 0; i < n ; i++) {
      String input[] = br.readLine().split("[, ]+");
      int X = Integer.parseInt(input[0]);
      int R = Integer.parseInt(input[1]);
      int G = Integer.parseInt(input[2]);
      int students[] = new int[X];
      input = br.readLine().split("[, ]+");
      for(int j = 0; j < X ; j++){
        int initialScore = Integer.parseInt(input[j]);
        if ( initialScore < 0){
          students[j] = 0;
        }else if (initialScore > 100) {
          students[j] = 100;
        } else {
          students[j] = initialScore;
        }
      }
      for(int j = 0; j<G+R; j++){
        input = br.readLine().split("[, ]+");
        if(input[0].equals("R")){
          int index = Integer.parseInt(input[1])-1;
          int extra = Integer.parseInt(input[2]);
          students[index] += extra;
          if( students[index] < 0) {
            students[index] = 0;
          }
        }
        if(input[0].equals("G")) {
          int startIndex = Integer.parseInt(input[1]);
          int endIndex = Integer.parseInt(input[2]);
          List<Integer> g = new ArrayList<Integer>();
          g.add(startIndex);
          g.add(endIndex);
          groups.add(g);
        }
      }
      int max = 0;
      for(List<Integer> g : groups) {
        int lower = g.get(0);
        int upper = g.get(1);
        int total = 0;
        for(int j = lower; j <= upper; j++){
          total += students[j-1];
        }
        if(total > max) {
          max = total;
        }
      }

      if (i+1 >= n){
        System.out.print(max);
      } else {
        System.out.println(max);
      }
    }

  }
}
