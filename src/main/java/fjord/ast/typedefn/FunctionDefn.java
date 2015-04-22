package fjord.ast.typedefn;

import java.util.Collections;
import java.util.List;

import java.util.Optional;

import fjord.ast.Access;
import fjord.ast.Node;
import fjord.ast.NodeVisitor;
import fjord.ast.expr.Expr;
import fjord.ast.pat.AtomicPattern;
import fjord.ast.typar.TyparDefns;
import fjord.ast.type.Type;

public class FunctionDefn implements Node {

  private final boolean inline;

  private final Access access;

  private final String ident;

  private final Optional<TyparDefns> typarDefns;

  private final List<AtomicPattern> argumentPats;

  private final Optional<Type> returnType;

  private final Expr expr;

  public FunctionDefn(boolean inline, Access access, String ident, TyparDefns typarDefns, List<AtomicPattern> argumentPats, Type returnType, Expr expr) {
    this.inline = inline;
    this.access = access != null ? access : Access.Public;
    this.ident = ident;
    this.typarDefns = Optional.ofNullable(typarDefns);
    this.argumentPats = argumentPats != null ? argumentPats : Collections.<AtomicPattern>emptyList();
    this.returnType = Optional.ofNullable(returnType);
    this.expr = expr;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public boolean isInline() {
    return inline;
  }

  public Access getAccess() {
    return access;
  }

  public List<AtomicPattern> getArgumentPats() {
    return argumentPats;
  }

  public Optional<TyparDefns> getTyparDefns() {
    return typarDefns;
  }

  public Optional<Type> getReturnType() {
    return returnType;
  }

  public Expr getExpr() {
    return expr;
  }

  public String getIdent() {
    return ident;
  }

}
