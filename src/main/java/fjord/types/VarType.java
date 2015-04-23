package fjord.types;

import java.util.Optional;

public class VarType implements TypeClass {
  public final int id;
  public Optional<TypeClass> instance = Optional.empty();

  public VarType(int id) {
    this.id = id;
  }

  @Override public Class<?> getRefType() {
    throw new UnsupportedOperationException("Type variable do not have a reference type");
  }

  @Override public Class<?> getPrimType() {
    throw new UnsupportedOperationException("Type variables do not have a primitive type");
  }

  @Override public Object parseValue(String s) {
    throw new UnsupportedOperationException("Type variables do not support value parsing");
  }
}
