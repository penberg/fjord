package fjord.ast.typedefn;

import java.util.Set;

import com.google.common.base.Optional;
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
    this.typarDefns = Optional.fromNullable(typarDefns);
    this.curriedSig = curriedSig;
    this.properties  = Sets.newHashSet(properties);
  }
  
  @Override
  public void accept(NodeVisitor visitor) {

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
