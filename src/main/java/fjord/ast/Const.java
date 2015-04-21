package fjord.ast;

public class Const implements Node {
  public Const(String value) {
    this.value = value;
    this.type = null;
  }

  public Const(String value, fjord.types.Type type) {
    this.value = value;
    this.type = type;
  }

  public fjord.types.Type getType() {
    return type;
  }

  @Override public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  @Override public String toString() {
    return value;
  }

  private final String value;
  private final fjord.types.Type type;
}
