package fjord.ast;

import java.util.List;

public class NamespaceDeclGroup implements Node {

  private final String namespace;
  
  private final List<ModuleElem> moduleElems;
  
  public NamespaceDeclGroup(String namespace, List<ModuleElem> moduleElems) {
    this.namespace = namespace;
    this.moduleElems = moduleElems;
  }

  public List<ModuleElem> getModuleElems() {
    return moduleElems;
  }

  public String getNamespace() {
    return namespace;
  }

  @Override
  public void accept(NodeVisitor visitor) {

  }
  
}
