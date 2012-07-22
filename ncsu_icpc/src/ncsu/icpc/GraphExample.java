package ncsu.icpc;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

/* I stuck this in graph example since in the ICPC the classes will all have
 * to be static classes, and I wanted to make sure our code followed that */
public class GraphExample {

  public static class Graph {
    public Set<Edge> edges = new HashSet<Edge>();
    public Set<Node> nodes = new HashSet<Node>();
    public Map<String, Node> nodeMap = new HashMap<String, Node>();
    public Node addNode(Node n) {
      nodes.add(n);
      if(!nodeMap.containsKey(n.id)){
        nodeMap.put(n.id, n);
        return n;
      } else {
        return nodeMap.get(n.id);
      }
    }

    public void addEdge(Edge e) {
      edges.add(e);
      Node n1 = this.addNode(e.nodeA);
      Node n2 = this.addNode(e.nodeB);
      n1.addEdge(e);
      n2.addEdge(e);
    }
  }

  public static class Edge implements Comparable<Edge>{
    public Node nodeA;
    public Node nodeB;
    public int weight;
    
    public Edge(Node nodeA, Node nodeB) {
      this.nodeA = nodeA;
      this.nodeB = nodeB;
      this.weight = 1;
    }
    public Edge(Node nodeA, Node nodeB, int weight) {
      this.nodeA = nodeA;
      this.nodeB = nodeB;
      this.weight = weight;
    }

    public int compareTo(Edge e) {
      return this.weight - e.weight;
    }

    /* This is just useful for debugging */
    public String toString(){
      return nodeA.id + " - " + nodeB.id + " : " + weight;
    }
  }

  public static class Node implements Comparable<Node> {
    public List<Edge> edges = new ArrayList<Edge>();
    public String id;
    public Set<Node> adjacent = new HashSet<Node>();
    /* A map from the adjacent node to the edge that connects it and this node */
    public Map<Node, Edge> edgeMap = new HashMap<Node, Edge>();
    /* Variables needed for Prim's and Djikstra's */
    public Node parent;
    public int key = Integer.MAX_VALUE;

    public Node(String id) {
      this.id = id;
    }

    public void addEdge(Edge e){
      edges.add(e);
      Node toAdd;
      if(this.equals(e.nodeA)){
        toAdd = e.nodeB;
      } else {
        toAdd = e.nodeA;
      }
      adjacent.add(toAdd);
      if(edgeMap.containsKey(toAdd)){
        if(edgeMap.get(toAdd).weight < e.weight){
          edgeMap.put(toAdd, e);
        }
      } else {
        edgeMap.put(toAdd, e);
      }
    }

    public int getAdjacentWeight(Node n){
      if(edgeMap.containsKey(n)){
        return edgeMap.get(n).weight;
      } else {
        return Integer.MAX_VALUE;
      }
    }

    public boolean equals(Object obj) {
      if(obj == null || obj.getClass() != Node.class) return false;
      if(this.id.equals(((Node) obj).id)){
        return true;
      }
      return false;
    }

    public int hashCode() {
      return this.id.hashCode();
    }

    public int compareTo(Node n) {
      if((this.key - n.key) == 0){
        return this.id.compareTo(n.id);
      } else {
        return  this.key - n.key;
      }
    }

    public String toString() {
      return id;
    }
  }
}
