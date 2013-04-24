package fjord.ast.expr;

import java.util.List;

import fjord.ast.NodeVisitor;
import fjord.ast.pat.Rule;

public class MatchingFunctionExpression implements Expr {

  private final List<Rule> rules;
  
  public MatchingFunctionExpression(List<Rule> rules) {
    this.rules = rules;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public List<Rule> getRules() {
    return rules;
  }

}
