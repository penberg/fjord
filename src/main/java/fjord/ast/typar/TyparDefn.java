package fjord.ast.typar;

import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class TyparDefn implements Type {

  private final Typar typar;
  
  public TyparDefn(Typar typar) {
    this.typar = typar;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
  }

  public Typar getTypar() {
    return typar;
  }

}
