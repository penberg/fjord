package fjord.types;

public abstract class OperType implements TypeClass {
  private final String name;
  private final Class<?> refType;
  private final Class<?> primType;

  public OperType(String name, Class<?> refType, Class<?> primType) {
    this.name = name;
    this.refType = refType;
    this.primType = primType;
  }

  @Override
  public Class<?> getRefType() {
    return refType;
  }

  @Override
  public Class<?> getPrimType() {
    return primType;
  }

  @Override
  public String toString() {
    return name;
  }
}