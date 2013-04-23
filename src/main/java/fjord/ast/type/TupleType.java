package fjord.ast.type;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;

public class TupleType extends NodeWithChildren<Type> implements Type {

  public TupleType(Type firstElem) {
    super(firstElem);
  }

  @Override
  public void accept(NodeVisitor visitor) { }

}
