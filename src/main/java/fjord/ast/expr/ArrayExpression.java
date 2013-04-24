package fjord.ast.expr;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;

public class ArrayExpression extends NodeWithChildren<Expr> implements Expr {
  
  public void addExpr(Expr expr) {
    addChild(expr);
  }
  
  @Override
  public void accept(NodeVisitor visitor) { 
    visitor.visit(this);
  }
  
}