package fjord.ast.expr;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;

public class TupleExpression extends NodeWithChildren<Expr> implements Expr {

  public TupleExpression(Expr firstElem) {
    addChild(firstElem);
  }
  
  @Override
  public void accept(NodeVisitor visitor) {

  }

}
