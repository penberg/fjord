package fjord.ast;

import fjord.ast.expr.AssignmentExpression;
import fjord.ast.expr.ConstantExpression;
import fjord.ast.expr.DotLookupExpression;
import fjord.ast.expr.IndexedLookupExpression;
import fjord.ast.expr.InfixApplicationExpression;
import fjord.ast.expr.LookupExpression;
import fjord.ast.expr.PrefixApplicationExpression;
import fjord.ast.expr.SimpleObjectExpression;
import fjord.ast.expr.TupleExpression;
import fjord.ast.expr.TypeApplicationExpression;

public class DefaultNodeVisitor implements NodeVisitor {

  @Override public void visit(CompilerDirectiveDecl node) { }
  @Override public void visit(Const node) { }
  @Override public void visit(Ident node) { }
  @Override public void visit(ImportDecl node) { }
  @Override public void visit(ScriptFragment node) { }
  @Override public void visit(ValueDefn node) { }

  @Override public void visit(TypeApplicationExpression typeApplicationExpression) { }
  @Override public void visit(AssignmentExpression assignmentExpression) { }
  @Override public void visit(ConstantExpression constantExpression) { }
  @Override public void visit(DotLookupExpression dotLookupExpression) { }
  @Override public void visit(IndexedLookupExpression indexedLookupExpression) { }
  @Override public void visit(InfixApplicationExpression infixApplicationExpression) { }
  @Override public void visit(LookupExpression lookupExpression) { }
  @Override public void visit(PrefixApplicationExpression prefixApplicationExpression) { }
  @Override public void visit(SimpleObjectExpression simpleObjectExpression) { }
  @Override public void visit(TupleExpression tupleExpression) { }
  
}
