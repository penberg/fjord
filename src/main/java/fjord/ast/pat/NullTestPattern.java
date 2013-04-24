package fjord.ast.pat;

import fjord.ast.NodeVisitor;

public class NullTestPattern implements Pat {

  @Override
  public void accept(NodeVisitor visitor) {    
    visitor.visit(this);
  }

}
