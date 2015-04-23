package fjord.types;

public class OperType implements TypeClass {
  private final String name;

  public OperType(String name) {
    this.name = name;
  }

  @Override public String toString() {
    return name;
  }
}
