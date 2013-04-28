package fjord.ast.pat;

import fjord.ast.NodeVisitor;

public class DisjunctivePattern implements Pat {

  private final Pat left;
  
  private final Pat right;
  
  public DisjunctivePattern(Pat left, Pat right) {
    this.left = left;
    this.right = right;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Pat getLeft() {
    return left;
  }
  
  public Pat getRight() {
    return right;
  }

}
