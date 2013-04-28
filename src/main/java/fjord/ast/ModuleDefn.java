package fjord.ast;

import java.util.Collections;
import java.util.List;

import fjord.ast.attribute.Attribute;

public class ModuleDefn implements ModuleElem {

  private final List<Attribute> attributes;
  
  private final Access access;
  
  private final String ident;
  
  private final List<ModuleElem> moduleElems;
  
  public ModuleDefn(List<Attribute> attributes, Access access, String ident, List<ModuleElem> modulesElems) {
    this.attributes = attributes != null ? attributes : Collections.<Attribute>emptyList();
    this.access = access != null ? access : Access.Public;
    this.ident = ident;
    this.moduleElems = modulesElems;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
  }

  public List<Attribute> getAttributes() {
    return attributes;
  }

  public Access getAccess() {
    return access;
  }

  public String getIdent() {
    return ident;
  }

  public List<ModuleElem> getModuleElems() {
    return moduleElems;
  }

}
