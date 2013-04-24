package fjord.ast.expr;

import com.google.common.base.Optional;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class ObjectConstruction implements Node {

  private final Type type;
  
  private final Optional<Expr> expr;
  
  public ObjectConstruction(Type type) {
    this(type, null);
  }

  public ObjectConstruction(Type type, Expr expr) {
    this.type = type;
    this.expr = Optional.fromNullable(expr);
  }
  
  
  @Override
  public void accept(NodeVisitor visitor) {
  }

  public Type getType() {
    return type;
  }

  public Optional<Expr> getExpr() {
    return expr;
  }

}
