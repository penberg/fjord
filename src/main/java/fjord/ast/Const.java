package fjord.ast;

public class Const implements Node {

  @Override public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }
}
