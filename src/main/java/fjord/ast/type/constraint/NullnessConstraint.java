package fjord.ast.type.constraint;

import fjord.ast.typar.Typar;

public class NullnessConstraint implements Constraint {

  private final Typar typar;

  public NullnessConstraint(Typar typar) {
    this.typar = typar;
  }
  
  public Typar getTypar() {
    return typar;
  }
  
}
