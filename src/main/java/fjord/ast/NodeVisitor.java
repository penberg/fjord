package fjord.ast;

public interface NodeVisitor {
  void visit(CompilerDirectiveDecl node);
  void visit(Const node);
}
