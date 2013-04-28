package fjord.ast.type;

import fjord.ast.NodeVisitor;

public class FunctionType implements Type {

  private Type left;

  private Type right;

  public FunctionType(Type left, Type right) {
    this.left = left;
    this.right = right;
  }

  public Type getLeft() {
    return left;
  }

  public Type getRight() {
    return right;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

}
