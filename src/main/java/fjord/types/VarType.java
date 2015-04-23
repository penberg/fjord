package fjord.types;

import java.util.Optional;

public class VarType implements TypeClass {
  public final int id;
  public Optional<TypeClass> instance = Optional.empty();

  public VarType(int id) {
    this.id = id;
  }
}
