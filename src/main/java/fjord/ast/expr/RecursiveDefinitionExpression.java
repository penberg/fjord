package fjord.ast.expr;

import java.util.List;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;


public class RecursiveDefinitionExpression implements Expr {

  private final List<Node> functionOrValueDefns;

  private final Expr expr;

  public RecursiveDefinitionExpression(List<Node> functionOrValueDefns, Expr expr) {
    this.functionOrValueDefns = functionOrValueDefns;
    this.expr = expr;
  }

  public List<Node> getFunctionOrValueDefns() {
    return functionOrValueDefns;
  }

  public Expr getExpr() {
    return expr;
  }

  @Override
  public void accept(NodeVisitor visitor) {
  }

}
