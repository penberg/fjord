package fjord.ast.typar;

import java.util.Collections;
import java.util.List;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;
import fjord.ast.type.Type;
import fjord.ast.type.constraint.Constraint;

public class TyparDefns extends NodeWithChildren<TyparDefn> implements Type {

  private List<Constraint> typarConstraints = Collections.emptyList();

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public void setTyparConstraints(List<Constraint> constraints) {
    this.typarConstraints = constraints != null ? constraints : Collections.<Constraint>emptyList();
  }

  public List<Constraint> getTyparConstraints() {
    return typarConstraints;
  }

}
