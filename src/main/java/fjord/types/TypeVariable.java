package fjord.types;

import java.util.Optional;

public class TypeVariable implements Type {
  public final int id;
  public Optional<Type> instance = Optional.empty();

  public TypeVariable(int id) {
    this.id = id;
  }
}
