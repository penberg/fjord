package fjord.ast;

public class ValueDefn implements Node {

  public ValueDefn(Node pattern, Node expr) {
    this.pattern = pattern;
    this.expr    = expr;
  }

  @Override public void accept(NodeVisitor visitor) {
    visitor.visit(this);

    if (pattern != null)
      pattern.accept(visitor);

    if (expr != null)
      expr.accept(visitor);
  }

  @Override public String toString() {
    return String.format("let %s = %s", pattern, expr);
  }

  private final Node pattern;
  private final Node expr;
}
