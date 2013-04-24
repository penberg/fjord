package fjord.ast.type.constraint;

import fjord.ast.typar.Typar;

public class ReferenceTypeConstraint implements Constraint {

  private final Typar typar;

  public ReferenceTypeConstraint(Typar typar) {
    this.typar = typar;
  }
  
  public Typar getTypar() {
    return typar;
  }
  
}
