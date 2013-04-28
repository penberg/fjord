package fjord.ast;

import java.util.List;

public class AnonymousModule implements Node {

  private final List<ModuleElem> moduleElems;

  public AnonymousModule(List<ModuleElem> moduleElems) {
    this.moduleElems = moduleElems;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public List<ModuleElem> getModuleElems() {
    return moduleElems;
  }

}
