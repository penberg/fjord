package fjord.ast;

public class ImportDecl implements Node {

  public ImportDecl(Ident ident) {
    this.ident = ident;
  }

  @Override public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  @Override public String toString() {
    return String.format("open %s", ident);
  }

  private Ident ident;
}
