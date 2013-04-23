package fjord.ast.expr;

import java.util.List;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;

public class MatchExpression implements Expr {

  private final Expr matchExpr;
  
  private final List<Node> rules;
  
  public MatchExpression(Expr matchExpr, List<Node> rules) {
    this.matchExpr = matchExpr; 
    this.rules = rules;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    
  }

  public Expr getMatchExpr() {
    return matchExpr;
  }

  public List<Node> getRules() {
    return rules;
  }

}
