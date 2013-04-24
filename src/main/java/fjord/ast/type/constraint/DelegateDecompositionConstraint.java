package fjord.ast.type.constraint;

import fjord.ast.typar.Typar;
import fjord.ast.type.Type;

public class DelegateDecompositionConstraint implements Constraint {

  private final Typar typar;
  
  private final Type type1;
  
  private final Type type2;

  public DelegateDecompositionConstraint(Typar typar, Type type1, Type type2) {
    this.typar = typar;
    this.type1 = type1;
    this.type2 = type2;
  }
  
  public Typar getTypar() {
    return typar;
  }

  public Type getType1() {
    return type1;
  }

  public Type getType2() {
    return type2;
  }
  
}
