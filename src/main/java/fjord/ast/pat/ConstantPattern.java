package fjord.ast.pat;

import fjord.ast.Const;
import fjord.ast.NodeVisitor;

public class ConstantPattern implements Pat {

  private final Const constant;

  public ConstantPattern(Const co) {
    this.constant = co;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Const getConstant() {
    return constant;
  }

}
