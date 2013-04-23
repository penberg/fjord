package fjord.ast.pat;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;

public class RecordPattern extends NodeWithChildren<FieldPattern> implements Pat {

  @Override
  public void accept(NodeVisitor visitor) { }

}
