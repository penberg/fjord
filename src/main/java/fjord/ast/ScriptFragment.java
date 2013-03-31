package fjord.ast;

import java.util.List;

public class ScriptFragment implements Node {

  public ScriptFragment(List<Node> moduleElems) {
    this.moduleElems = moduleElems;
  }

  @Override public void accept(NodeVisitor visitor) {
    visitor.visit(this);

    for (Node moduleElem : moduleElems) {
      if (moduleElem != null)
        moduleElem.accept(visitor);
    }
  }

  private List<Node> moduleElems;
}
