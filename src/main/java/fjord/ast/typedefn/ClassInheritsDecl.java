package fjord.ast.typedefn;

import java.util.Optional;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;
import fjord.ast.expr.Expr;
import fjord.ast.type.Type;

public class ClassInheritsDecl implements Node {

  private final Type type;

  private final Optional<Expr> expr;

  public ClassInheritsDecl(Type type, Expr expr) {
    this.type = type;
    this.expr = Optional.ofNullable(expr);
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Optional<Expr> getExpr() {
    return expr;
  }

  public Type getType() {
    return type;
  }

}
