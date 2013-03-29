package fjord.ast;

public interface Node {
  void accept(NodeVisitor visitor);
}
