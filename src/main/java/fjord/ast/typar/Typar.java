package fjord.ast.typar;

import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public abstract class Typar implements Type {

  private final String ident;
  
  private Typar(String ident) {
    this.ident = ident;
  }

  public String getIdent() {
    return ident;
  }
  
  public static Typar typeVariable(String ident) {
    return new TypeVariable(ident);
  }
  
  public static Typar staticHeadTypeVariable(String ident) {
    return new StaticHeadTypeVariable(ident);
  }
  
  public static Typar anonymousTypeVariable() {
    return new AnonymousTypeVariable();
  }
  
  private static class TypeVariable extends Typar {

    public TypeVariable(String ident) {
      super(ident);
    }
    
    @Override
    public void accept(NodeVisitor visitor) {}
    
  }
  
  private static class StaticHeadTypeVariable extends Typar {
    
    public StaticHeadTypeVariable(String ident) {
      super(ident);
    }

    @Override
    public void accept(NodeVisitor visitor) {}
    
  }

  private static class AnonymousTypeVariable extends Typar {

    public AnonymousTypeVariable() {
      super("_");
    }
    
    @Override
    public void accept(NodeVisitor visitor) { }
    
  }
  
}
