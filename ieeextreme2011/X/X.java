import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Arrays;
import java.util.BitSet;

public class X {
  public static List<StringContents> words = new ArrayList<StringContents>();
  public static void main (String [] args) throws Exception
  {
    int n;
    String s;
    int t = 0;
    String inputs[];
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    
    inputs = br.readLine().split(" ");
    for(int i = 0; i < n; i++){
        words.add(new StringContents(inputs[i]));
    }
    s = br.readLine();
    t = Integer.parseInt(s);
    int i = 0;
    while(i < t){
      inputs = br.readLine().split(" ");
      for(String l : inputs){
        StringBuilder sb = new StringBuilder();
        StringContents letters = new StringContents(l);
        for( StringContents word : words) {
          if(word.contains(letters)){
            sb.append(word.word).append(" ");
          }
        }
        String f = sb.toString();
        if("".equals(f)){
          System.out.println("NONE");
        } else {
          System.out.println(f);
        }
        i++;
      }
      
    }
  }

  public static class StringContents implements Comparable<StringContents> {
    public BitSet bs = new BitSet(26);
    public String word;
    public StringContents(String word) {
      char[] ca = word.toCharArray();
      for(char c : ca){
        bs.set(c - 'a');
      }
      this.word = word;
    }
    public boolean contains(StringContents otherWord) {
      return this.bs.equals(otherWord.bs);
    }

    public int compareTo(StringContents sc) {
      return this.word.compareTo(sc.word);
    }
  }
}
