package fjord.ast.typedefn;

import java.util.Collections;
import java.util.List;

import fjord.ast.Access;
import fjord.ast.attribute.Attribute;
import fjord.ast.type.Type;

public class RecordField {

  private final List<Attribute> attributes;
  
  private final boolean mutable;
  
  private final Access access;
  
  private final String ident;
  
  private final Type type;
  
  public RecordField(List<Attribute> attributes, boolean mutable, Access access, String ident, Type type) {
    this.attributes = attributes != null ? attributes : Collections.<Attribute>emptyList();
    this.mutable = mutable;
    this.access = access != null ? access : Access.Public;
    this.ident = ident;
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  public String getIdent() {
    return ident;
  }

  public boolean isMutable() {
    return mutable;
  }

  public Access getAccess() {
    return access;
  }

  public List<Attribute> getAttributes() {
    return attributes;
  }

}
