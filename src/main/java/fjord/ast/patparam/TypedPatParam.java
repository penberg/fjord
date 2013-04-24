package fjord.ast.patparam;

import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class TypedPatParam implements PatParam {

  private final PatParam patParam;
  
  private final Type type;

  public TypedPatParam(PatParam patParam, Type type) {
    this.patParam = patParam;
    this.type = type;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public PatParam getPatParam() {
    return patParam;
  }

  public Type getType() {
    return type;
  }
  
}
