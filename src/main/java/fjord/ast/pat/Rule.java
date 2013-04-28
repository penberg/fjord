package fjord.ast.pat;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;
import fjord.ast.expr.Expr;

import com.google.common.base.Optional;
  
public class Rule implements Node {
  
  private final Pat pattern;
  
  private final Optional<Expr> guardExpr;
  
  private final Expr ruleExpr;
  
  public Rule(Pat pattern, Expr guardExpr, Expr ruleExpr) {
    this.pattern = pattern;
    this.guardExpr = Optional.fromNullable(guardExpr);
    this.ruleExpr = ruleExpr;
  }
  
  public Pat getPattern() {
    return pattern;
  }
  
  public Optional<Expr> getGuardExpr() {
    return guardExpr;
  }
  
  public Expr getRuleExpr() {
    return ruleExpr;
  }

  @Override
  public void accept(NodeVisitor visitor) {
  }
  
}