package fjord.ast.expr;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Optional;

import fjord.ast.NodeVisitor;

public class IfExpression implements Expr {

  private final Expr condition;
  
  private final Expr thenExpr;
  
  private final List<ElifBranch> elifBranches;
  
  private final Optional<Expr> elseExpr;
  
  public IfExpression(Expr condition, Expr thenExpr, List<ElifBranch> elifBranches, Expr elseExpr) {
    this.condition = condition;
    this.thenExpr = thenExpr;
    this.elifBranches = elifBranches != null ? elifBranches : Collections.<ElifBranch>emptyList();
    this.elseExpr = Optional.of(elseExpr);
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    // TODO Auto-generated method stub
    
  }

  public Expr getCondition() {
    return condition;
  }

  public Expr getThenExpr() {
    return thenExpr;
  }

  public List<ElifBranch> getElifBranches() {
    return elifBranches;
  }

  public Optional<Expr> getElseExpr() {
    return elseExpr;
  }

}
