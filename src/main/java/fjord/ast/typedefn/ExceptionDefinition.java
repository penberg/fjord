package fjord.ast.typedefn;

import java.util.Collections;
import java.util.List;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;
import fjord.ast.attribute.Attribute;

public class ExceptionDefinition implements Node {

  private final List<Attribute> attributes;

  private final UnionTypeCaseData unionTypeCaseData;

  public ExceptionDefinition(List<Attribute> attributes, UnionTypeCaseData unionTypeCaseData) {
    this.attributes = attributes != null ? attributes : Collections.<Attribute>emptyList();
    this.unionTypeCaseData = unionTypeCaseData;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public List<Attribute> getAttributes() {
    return attributes;
  }

  public UnionTypeCaseData getUnionTypeCaseData() {
    return unionTypeCaseData;
  }

}
