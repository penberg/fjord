package fjord.types;

public class TypeOperator implements Type {
  private final String name;

  public TypeOperator(String name) {
    this.name = name;
  }

  @Override public String toString() {
    return name;
  }
}
