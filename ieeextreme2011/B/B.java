import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

public class B {
  public static Map<Character, Boolean> bloom = new HashMap<Character, Boolean>();
  public static void main (String [] args) throws Exception
  {
    for(char i = 0; i < 26; i++) {
      bloom.put(i, false);
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String inputs[] = br.readLine().split("[\\p{P} \\t\\n\\r]");
    for(String s : inputs ){
      if(s.equals("")) continue;
      for(char c : s.toCharArray()){
        bloom.put((char)(Character.toLowerCase(c) - 'a'), true);
      }
    }
    inputs = br.readLine().split("[\\p{P} \\t\\n\\r]");
    int total = 0;
    for(String s : inputs){
      if(s.equals("")) continue;
      boolean inFilter = true;
      for(char c : s.toLowerCase().toCharArray()){
        if(!(bloom.get((char)(c - 'a')))){
          inFilter = false;
        }
      }
      if (inFilter) {
        total++;
      }
    }
    System.out.print(total);
  }
}
