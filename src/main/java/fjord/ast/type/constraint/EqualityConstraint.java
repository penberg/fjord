package fjord.ast.type.constraint;

import fjord.ast.NodeVisitor;
import fjord.ast.typar.Typar;

public class EqualityConstraint implements Constraint {

  private final Typar typar;

  public EqualityConstraint(Typar typar) {
    this.typar = typar;
  }

  public Typar getTypar() {
    return typar;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

}
