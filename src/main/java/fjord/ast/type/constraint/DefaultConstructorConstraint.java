package fjord.ast.type.constraint;

import fjord.ast.typar.Typar;

public class DefaultConstructorConstraint implements Constraint {

  private final Typar typar;

  public DefaultConstructorConstraint(Typar typar) {
    this.typar = typar;
  }
  
  public Typar getTypar() {
    return typar;
  }
  
}
