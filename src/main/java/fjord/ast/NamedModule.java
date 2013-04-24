package fjord.ast;

import java.util.List;

public class NamedModule implements Node {

  private final String moduleName;
  
  private final List<ModuleElem> moduleElems; 
  
  public NamedModule(String moduleName, List<ModuleElem> moduleElems) {
    this.moduleName = moduleName;
    this.moduleElems = moduleElems;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {

  }

  public List<ModuleElem> getModuleElems() {
    return moduleElems;
  }

  public String getModuleName() {
    return moduleName;
  }

}
