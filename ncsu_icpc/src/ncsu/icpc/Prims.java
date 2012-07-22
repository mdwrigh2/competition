package ncsu.icpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;
import java.util.Set;
import java.util.Comparator;
import static ncsu.icpc.GraphExample.*;
public class Prims {
  public static void main (String [] args) throws Exception
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());;
    while(n != 0){
      Graph g = new Graph();
      for(int i = 0; i < n; i++){
        String[] inputs = br.readLine().split("\\s");
        Node a = new Node(inputs[0]);
        Node b = new Node(inputs[1]);
        int weight = Integer.parseInt(inputs[2]);
        Edge e = new Edge(a, b, weight);
        g.addEdge(e);
      }
      Graph mst = runPrims(g);
      for(Edge ed : mst.edges) {
        System.out.println(ed);
      }
      System.out.println();
      n = Integer.parseInt(br.readLine());
    }

  }

  public static Graph runPrims(Graph g){
    /* Set the root nodes key to 0 */
    List<Node> nodes = new ArrayList<Node>(g.nodes);
    /* this isn't necessary, but it makes things easier */ 
    Collections.sort(nodes);
    nodes.get(0).key = 0;
    Node u;
    while(!nodes.isEmpty()){
      /* this could probably be improved by using a heap */
      Collections.sort(nodes);
      u = nodes.remove(0);
      System.out.println(u);
      for(Node v : u.adjacent){
        if(nodes.contains(v) && v.getAdjacentWeight(u) < v.key){
          v.parent = u;
          v.key = v.getAdjacentWeight(u);
        }
      }
    }
    /* I clone the nodes a third time. You could modify in place if you wanted though */
    Graph mst = new Graph();
    mst.nodes = new HashSet<Node>(g.nodes);
    for(Node n : mst.nodes){
      if(n.parent != null) {
        mst.addEdge(n.edgeMap.get(n.parent));
      }
    }
    return mst;
  }

}
