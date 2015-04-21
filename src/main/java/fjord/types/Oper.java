package fjord.types;

public class Oper implements Type {
  private final String name;

  public Oper(String name) {
    this.name = name;
  }

  @Override public String toString() {
    return name;
  }
}
