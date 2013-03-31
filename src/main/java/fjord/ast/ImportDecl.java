package fjord.ast;

public class ImportDecl implements Node {

  public ImportDecl(String ident) {
    this.ident = ident;
  }

  public String getIdent() {
    return ident;
  }

  @Override public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  @Override public String toString() {
    return "open " + ident;
  }

  private String ident;
}
