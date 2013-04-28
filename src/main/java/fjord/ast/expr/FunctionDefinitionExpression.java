package fjord.ast.expr;

import fjord.ast.NodeVisitor;
import fjord.ast.typedefn.FunctionDefn;

public class FunctionDefinitionExpression implements Expr {

  private final FunctionDefn functionDefn;
  
  private final Expr expr;
  
  public FunctionDefinitionExpression(FunctionDefn functionDefn, Expr expr) {
    this.functionDefn = functionDefn;
    this.expr = expr;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }


  public Expr getExpr() {
    return expr;
  }


  public FunctionDefn getFunctionDefn() {
    return functionDefn;
  }

}
