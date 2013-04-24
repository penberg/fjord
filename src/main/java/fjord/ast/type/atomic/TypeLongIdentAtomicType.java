package fjord.ast.type.atomic;

import java.util.Collections;
import java.util.List;

import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class TypeLongIdentAtomicType implements AtomicType {

  private final Type type;
  
  private final String ident;

  private final List<Type> types;
  
  public TypeLongIdentAtomicType(Type type, String ident) {
    this(type, ident, Collections.<Type>emptyList());
  }
  
  public TypeLongIdentAtomicType(Type type, String ident, List<Type> types) {
    this.type = type;
    this.ident = ident;
    this.types = types;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Type getType() {
    return type;
  }

  public String getIdent() {
    return ident;
  }

  public List<Type> getTypes() {
    return types;
  }

}
