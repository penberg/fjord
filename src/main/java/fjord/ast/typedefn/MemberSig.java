package fjord.ast.typedefn;

import java.util.Set;

import java.util.Optional;
import com.google.common.collect.Sets;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;
import fjord.ast.typar.TyparDefns;

public class MemberSig implements Node {

  public static enum Property {

    None,

    Get,

    Set

  }

  private final String ident;

  private final Optional<TyparDefns> typarDefns;

  private final CurriedSig curriedSig;

  private final Set<Property> properties;

  public MemberSig(String ident, TyparDefns typarDefns, CurriedSig curriedSig, Property... properties) {
    this.ident = ident;
    this.typarDefns = Optional.ofNullable(typarDefns);
    this.curriedSig = curriedSig;
    this.properties  = Sets.newHashSet(properties);
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public String getIdent() {
    return ident;
  }

  public Optional<TyparDefns> getTyparDefns() {
    return typarDefns;
  }

  public CurriedSig getCurriedSig() {
    return curriedSig;
  }

  public Set<Property> getProperties() {
    return properties;
  }

}
