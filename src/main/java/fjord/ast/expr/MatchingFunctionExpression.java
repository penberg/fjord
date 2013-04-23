package fjord.ast.expr;

import java.util.List;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;

public class MatchingFunctionExpression implements Expr {

  private final List<Node> rules;
  
  public MatchingFunctionExpression(List<Node> rules) {
    this.rules = rules;
  }

  @Override
  public void accept(NodeVisitor visitor) {

  }

  public List<Node> getRules() {
    return rules;
  }

}
