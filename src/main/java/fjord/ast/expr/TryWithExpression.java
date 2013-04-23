package fjord.ast.expr;

import java.util.List;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;

public class TryWithExpression implements Expr {

  private final Expr tryExpr;
  
  private final List<Node> rules;
  
  public TryWithExpression(Expr tryExpr, List<Node> rules) {
    this.tryExpr = tryExpr;
    this.rules = rules;
  }

  @Override
  public void accept(NodeVisitor visitor) {

  }

  public Expr getTryExpr() {
    return tryExpr;
  }

  public List<Node> getRules() {
    return rules;
  }

}
