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

public interface NodeVisitor {
  void visit(CompilerDirectiveDecl node);
  void visit(Const node);
  void visit(Ident node);
  void visit(ImportDecl node);
  void visit(ScriptFragment node);
  void visit(ValueDefn node);
  
  void visit(TypeApplicationExpression typeApplicationExpression);
  void visit(AssignmentExpression assignmentExpression);
  void visit(ConstantExpression constantExpression);
  void visit(DotLookupExpression dotLookupExpression);
  void visit(IndexedLookupExpression indexedLookupExpression);
  void visit(InfixApplicationExpression infixApplicationExpression);
  void visit(LookupExpression lookupExpression);
  void visit(PrefixApplicationExpression prefixApplicationExpression);
  void visit(SimpleObjectExpression simpleObjectExpression);
  void visit(TupleExpression tupleExpression);
  

}
