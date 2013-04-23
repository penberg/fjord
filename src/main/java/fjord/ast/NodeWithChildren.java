package fjord.ast;


import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public abstract class NodeWithChildren<T extends Node> implements Node {
  
  protected final List<T> children;
  
  public NodeWithChildren() {
    this(new ArrayList<T>());
  }
  
  public NodeWithChildren(T... children) {
    this(Arrays.asList(children));
  }
  
  public NodeWithChildren(List<T> children) {
    this.children = children;
  }
  
  public void addChild(T child) {
    children.add(child);
  }
  
} 