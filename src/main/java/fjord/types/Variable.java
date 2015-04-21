package fjord.types;

import java.util.Optional;

public class Variable implements Type {
  public final int id;
  public Optional<Type> instance = Optional.empty();

  public Variable(int id) {
    this.id = id;
  }
}
