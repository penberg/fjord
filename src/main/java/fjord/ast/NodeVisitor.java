package fjord.ast;

public interface NodeVisitor {
  void visit(CompilerDirectiveDecl node);
  void visit(Const node);
  void visit(Ident node);
  void visit(ImportDecl node);
  void visit(ScriptFragment node);
  void visit(ValueDefn node);
}
