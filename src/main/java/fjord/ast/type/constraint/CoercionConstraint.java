package fjord.ast.type.constraint;

import fjord.ast.typar.Typar;
import fjord.ast.type.Type;

public class CoercionConstraint implements Constraint {

  private final Typar typar;
  
  private final Type type;
  
  public CoercionConstraint(Typar typar, Type type) {
    this.typar = typar;
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  public Typar getTypar() {
    return typar;
  }
  
}
