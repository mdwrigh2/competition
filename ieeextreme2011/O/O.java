import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;

public class O {

  public static int D;
  public static int L;
  public static Map<Byte, Node> nodeMap = new HashMap<Byte,Node>();
  public static void main (String [] args) throws Exception
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    D = Integer.parseInt(br.readLine());
    L = Integer.parseInt(br.readLine());
    int F = Integer.parseInt(br.readLine());
    List<Byte> bytes = new ArrayList<Byte>();
    for(int i = 0; i < F; i++){
      bytes.add(Byte.parseByte(br.readLine()));
    }
    createTrie(bytes);
    Set<CompleteNode> nodes = getNodes(nodeMap, new ArrayList<Byte>());
    int sum = 0;
    for(CompleteNode n : nodes) {
      sum += n.num;
    }
    System.out.print(sum);
  }

  public static Set<CompleteNode> getNodes(Map<Byte, Node> map, List<Byte> prev) { 
    Collection<Node> values = map.values();
    Set<CompleteNode> s = new HashSet<CompleteNode>();
    for(Node n : values) {
      if(prev.size() + 1 >= D && n.num >= L){
        List<Byte> nL = new ArrayList<Byte>(prev);
        nL.add(n.val);
        CompleteNode cn = new CompleteNode(nL, n.num);
        s.add(cn);
      }
      List<Byte> next = new ArrayList<Byte>(prev);
      next.add(n.val);
      s.addAll(getNodes(n.map, next));
    }
    return s;
  }

  public static void createTrie(List<Byte> chs){
    Map<Byte, Node> aMap = nodeMap;
    for(byte ch : chs) {
      if(aMap.containsKey(ch)){
        Node node = aMap.get(ch);
        node.num += 1;
        aMap = node.map;
      } else {
        Node n = new Node(ch);
        aMap.put(ch, n);
        aMap = n.map;
      }
    }
    if(chs.size() > 1){
      createTrie(chs.subList(1, chs.size()));
    }
  }


  public static class CompleteNode {
    public List<Byte> vals;
    public int num;
    public CompleteNode(List<Byte> vals, int num){
      this.vals = vals;
      this.num = num;
    }
    public String toString() {
      return "(" + vals + ", " + num + ")";
    }
  }
      

  public static class Node {
    public byte val;
    public int num;
    public Map<Byte, Node> map = new HashMap<Byte, Node>();
    public Node(byte val) {
      this.val = val;
      this.num = 1;
    }
    public String toString(){
      return "(" + val + ", " + num + ")";
    }
  }
}
