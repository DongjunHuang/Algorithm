package GRAPH;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    Graph graph = new Graph();
    GraphNode a = new GraphNode('a');
    GraphNode b = new GraphNode('b');
    GraphNode c = new GraphNode('c');
    GraphNode d = new GraphNode('d');
    GraphNode e = new GraphNode('e');
    GraphNode f = new GraphNode('f');
    GraphNode g = new GraphNode('g');
    GraphNode h = new GraphNode('h');
    graph.addNode(a);
    graph.addNode(b);
    graph.addNode(c);
    graph.addNode(d);
    graph.addNode(e);
    graph.addNode(f);
    graph.addNode(g);
    graph.addNode(h);

    graph.addEdge(a, b);
    graph.addEdge(b, e);
    graph.addEdge(e, a);
    graph.addEdge(e, f);
    graph.addEdge(b, f);
    graph.addEdge(b, c);
    graph.addEdge(f, g);
    graph.addEdge(g, f);
    graph.addEdge(c, g);
    graph.addEdge(c, d);
    graph.addEdge(d, c);
    graph.addEdge(g, h);
    graph.addEdge(d, h);

    List<List<GraphNode>> scc = graph.scc();
    for (int i = 0; i < scc.size(); i++) {
      for (int j = 0; j < scc.get(i).size(); j++) {
        System.out.print(scc.get(i).get(j).val);
      }
      System.out.println();
    }
  }
}
