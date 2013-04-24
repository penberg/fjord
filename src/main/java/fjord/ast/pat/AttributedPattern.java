package fjord.ast.pat;

import fjord.ast.NodeVisitor;

public class AttributedPattern implements Pat {

  private final Pat pattern;
  
  public AttributedPattern(Pat pattern) {
    this.pattern = pattern;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    
  }

  public Pat getPattern() {
    return pattern;
  }
  
}
