package fjord.ast;

public class Const implements Node {
  public Const(String value) {
    this.value = value;
  }

  @Override public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  @Override public String toString() {
    return value;
  }

  private final String value;
}
