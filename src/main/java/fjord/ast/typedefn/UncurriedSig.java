package fjord.ast.typedefn;

import java.util.List;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class UncurriedSig implements Node {

  private final List<ArgSpec> argsSpec;

  private final Type type;

  public UncurriedSig(List<ArgSpec> argsSpec, Type type) {
    this.argsSpec = argsSpec;
    this.type = type;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Type getType() {
    return type;
  }

  public List<ArgSpec> getArgsSpec() {
    return argsSpec;
  }

}
