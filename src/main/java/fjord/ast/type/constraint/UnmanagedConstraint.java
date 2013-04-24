package fjord.ast.type.constraint;

import fjord.ast.typar.Typar;

public class UnmanagedConstraint implements Constraint {

  private final Typar typar;

  public UnmanagedConstraint(Typar typar) {
    this.typar = typar;
  }
  
  public Typar getTypar() {
    return typar;
  }
  
}
