package fjord.ast.typedefn;

import java.util.Collections;
import java.util.List;

import java.util.Optional;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;
import fjord.ast.attribute.Attribute;
import fjord.ast.type.Type;

public class ArgSpec implements Node {

  private final List<Attribute> attributes;

  private final Optional<ArgNameSpec> argNameSpec;

  private final Type type;

  public ArgSpec(List<Attribute> attributes, ArgNameSpec argNameSpec, Type type) {
    this.attributes = attributes != null ? attributes : Collections.<Attribute>emptyList();
    this.argNameSpec = Optional.ofNullable(argNameSpec);
    this.type = type;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public List<Attribute> getAttributes() {
    return attributes;
  }

  public Optional<ArgNameSpec> getArgNameSpec() {
    return argNameSpec;
  }

  public Type getType() {
    return type;
  }

}
