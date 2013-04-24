package fjord.ast.pat;

import fjord.ast.NodeVisitor;
import fjord.ast.type.atomic.AtomicType;

public class DynamicTypeTestPattern implements Pat {

  private final AtomicType atomicType;
  
  public DynamicTypeTestPattern(AtomicType atomicType) {
    this.atomicType = atomicType;
  }

  public AtomicType getAtomicType() {
    return atomicType;
  }

  @Override
  public void accept(NodeVisitor visitor) {

  }
  
}
