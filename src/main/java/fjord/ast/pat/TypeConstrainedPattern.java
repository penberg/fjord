package fjord.ast.pat;

import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class TypeConstrainedPattern implements Pat {

  private final Pat pattern;
  
  private final Type type;
  
  public TypeConstrainedPattern(Pat pattern, Type type) {
    this.pattern = pattern;
    this.type = type;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Type getType() {
    return type;
  }
  
  public Pat getPattern() {
    return pattern;
  }
  
}
