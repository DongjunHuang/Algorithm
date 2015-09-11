package GRAPH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Graph {
  private Set<GraphNode> mAllNodes;
  private Set<GraphNode> mReverseAllNodes;
  private List<GraphNode> mDFSequence;
  private List<List<GraphNode>> mStrongConnectedComponent;
  private Stack<GraphNode> mStackSCC;
  private Map<GraphNode, GraphNode> correlateNodes;

  /**
   * Base Constructor
   */
  public Graph() {
    mAllNodes = new HashSet<GraphNode>();
    mDFSequence = new ArrayList<GraphNode>();
  }

  /**
   * Clear sequence
   */
  public void clearDFSSequence() {
    mDFSequence.clear();
  }

  /**
   * Getter
   * 
   * @return
   */
  public List<GraphNode> getDFSList() {
    return mDFSequence;
  }

  /**
   * Getter
   * 
   * @return
   */
  public Set<GraphNode> getNodeList() {
    return mAllNodes;
  }

  /**
   * Add node into Graph
   * 
   * @param node
   */
  public void addNode(GraphNode node) {
    if (!mAllNodes.contains(node)) {
      mAllNodes.add(node);
    }
  }

  /**
   * Clear history after doing DFS
   */
  public void clearVisitedHistory() {
    for (GraphNode n : mAllNodes) {
      n.isVisited = false;
    }
  }

  /**
   * Add edge
   */
  public void addEdge(GraphNode source, GraphNode target) {
    if (!mAllNodes.contains(source))
      mAllNodes.add(source);
    if (!mAllNodes.contains(target))
      mAllNodes.add(target);
    source.neib.add(target);
  }

  /**
   * Depth first search the whole graph
   * 
   * @return
   */
  public List<GraphNode> dfs() {
    for (GraphNode n : mAllNodes)
      dfsHelp(n);
    return mDFSequence;
  }

  /**
   * Depth first search helper
   * 
   * @param node
   */
  private void dfsHelp(GraphNode node) {
    if (!node.isVisited) {
      node.isVisited = true;
      mDFSequence.add(node);
      for (GraphNode n : node.neib) {
        dfsHelp(n);
      }
    }
  }

  /**
   * Depth first search the whole graph Internal Use Only
   * 
   * @return
   */
  private List<GraphNode> dfsSCC() {
    for (GraphNode n : mAllNodes)
      dfsSCCHelp(n);
    return mDFSequence;
  }

  /**
   * Depth first search helper for scc
   * 
   * @param node
   */
  private void dfsSCCHelp(GraphNode node) {
    if (!node.isVisited) {
      node.isVisited = true;
      for (GraphNode n : node.neib)
        dfsSCCHelp(n);
      mStackSCC.push(node);
    }
  }

  /**
   * Strong Connected Component
   * 
   * @return
   */
  public List<List<GraphNode>> scc() {
    // step 1: dfs to create stack
    mStrongConnectedComponent = new ArrayList<List<GraphNode>>();
    mStackSCC = new Stack<GraphNode>();
    dfsSCC();

    // step 2: create reversed graph
    getReversedGraph();

    // step 3: do reverse dfs from stack
    while (!mStackSCC.isEmpty()) {
      GraphNode p = mStackSCC.pop();
      GraphNode rp = correlateNodes.get(p);

      if (rp.isVisited)
        continue;
      dfsHelp(rp);
      mStrongConnectedComponent.add(new ArrayList<GraphNode>(mDFSequence));
      clearDFSSequence();
    }
    return mStrongConnectedComponent;
  }

  /**
   * Reverse current graph
   * 
   * @return
   */
  private void getReversedGraph() {
    correlateNodes = new HashMap<GraphNode, GraphNode>();
    mReverseAllNodes = new HashSet<GraphNode>();
    for (GraphNode old : mAllNodes) {
      if (!correlateNodes.containsKey(old)) {
        GraphNode neww = new GraphNode(old.val);
        correlateNodes.put(old, neww);
      }
      for (GraphNode o : old.neib) {
        if (!correlateNodes.containsKey(o)) {
          GraphNode n = new GraphNode(o.val);
          correlateNodes.put(o, n);
        }
        mReverseAllNodes.add(correlateNodes.get(o));
        correlateNodes.get(o).neib.add(correlateNodes.get(old));
      }
    }
  }

}
