package fjord.ast.typedefn;

import java.util.Collections;
import java.util.List;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;
import fjord.ast.attribute.Attribute;

public class ExceptionAbbreviation implements Node {

  private List<Attribute> attributes;

  private final String alias;

  private final String exceptionName;

  public ExceptionAbbreviation(List<Attribute> attributes, String alias, String exceptionName) {
    this.setAttributes(attributes != null ? attributes : Collections.<Attribute>emptyList());
    this.alias = alias;
    this.exceptionName = exceptionName;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public String getExceptionName() {
    return exceptionName;
  }

  public String getAlias() {
    return alias;
  }

  public List<Attribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<Attribute> attributes) {
    this.attributes = attributes;
  }

}
