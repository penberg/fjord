package fjord.ast.type.constraint;

import fjord.ast.typar.Typar;
import fjord.ast.type.Type;

public class EnumDecompositionConstraint implements Constraint {

  private final Typar typar;
  
  private final Type type;
  
  public EnumDecompositionConstraint(Typar typar, Type type) {
    this.typar = typar;
    this.type = type;
  }

  public Typar getTypar() {
    return typar;
  }

  public Type getType() {
    return type;
  }
  
}
