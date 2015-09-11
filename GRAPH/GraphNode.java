package GRAPH;

import java.util.ArrayList;
import java.util.List;

class GraphNode {
  public char val;
  public List<GraphNode> neib;
  public boolean isVisited;

  public GraphNode(char val) {
    this.val = val;
    neib = new ArrayList<GraphNode>();
  }
}
