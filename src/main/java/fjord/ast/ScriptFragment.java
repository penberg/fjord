package fjord.ast;

import java.util.List;

public class ScriptFragment implements Node {

  public ScriptFragment(List<ModuleElem> moduleElems) {
    this.moduleElems = moduleElems;
  }

  @Override public void accept(NodeVisitor visitor) {
    visitor.visit(this);

    for (ModuleElem moduleElem : moduleElems) {
      moduleElem.accept(visitor);
    }
  }

  private List<ModuleElem> moduleElems;
}
