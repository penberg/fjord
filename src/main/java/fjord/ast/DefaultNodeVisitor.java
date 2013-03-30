package fjord.ast;

public class DefaultNodeVisitor implements NodeVisitor {

  @Override public void visit(CompilerDirectiveDecl node) { }
  @Override public void visit(Const node) { }
  @Override public void visit(ScriptFragment node) { }
}
