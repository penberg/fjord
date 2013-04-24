package fjord.ast;

public class ModuleAbbrev implements ModuleElem {
  
  private final String moduleAlias;
  
  private final String moduleName;
  
  public ModuleAbbrev(String moduleAlias, String moduleName) {
    this.moduleAlias = moduleAlias;
    this.moduleName = moduleName;
  }
  
  public String getModuleAlias() {
    return moduleAlias;
  }
  
  public String getModuleName() {
    return moduleName;
  }
  
  @Override 
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }
  
}