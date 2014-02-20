package fjord.ast;

import fjord.ast.expr.*;
import fjord.ast.pat.*;
import fjord.ast.patparam.*;
import fjord.ast.typar.*;
import fjord.ast.typar.Typar.StaticHeadTypeVariable;
import fjord.ast.typar.Typar.AnonymousTypeVariable;
import fjord.ast.typar.Typar.TypeVariable;
import fjord.ast.type.*;
import fjord.ast.type.atomic.*;
import fjord.ast.type.constraint.*;
import fjord.ast.typedefn.*;

public class DefaultNodeVisitor implements NodeVisitor {

  @Override public void visit(CompilerDirectiveDecl node) { }
  @Override public void visit(Const node) { }
  @Override public void visit(Ident node) { }
  @Override public void visit(ImportDecl node) { }
  @Override public void visit(ScriptFragment node) { }
  @Override public void visitBefore(ValueDefn node) { }
  @Override public void visitAfter(ValueDefn node) { }
  @Override public void visitBefore(ApplicationExpression node) { }
  @Override public void visitAfter(ApplicationExpression node) { }
  @Override public void visit(ArrayExpression node) { }
  @Override public void visit(AssertExpression node) { }
  @Override public void visit(AssignmentExpression node) { }
  @Override public void visit(BaseCall node) { }
  @Override public void visit(ConstantExpression node) { }
  @Override public void visit(DeterministicDisposalExpression node) { }
  @Override public void visit(DotLookupExpression node) { }
  @Override public void visit(DowncastExpression node) { }
  @Override public void visit(ElifBranch node) { }
  @Override public void visit(FieldInitializer node) { }
  @Override public void visit(FunctionDefinitionExpression node) { }
  @Override public void visit(IfExpression node) { }
  @Override public void visit(LazyExpression node) { }
  @Override public void visit(ListExpression node) { }
  @Override public void visit(MatchExpression node) { }
  @Override public void visit(MatchingFunctionExpression node) { }
  @Override public void visit(NullExpression node) { }
  @Override public void visit(ObjectConstruction node) { }
  @Override public void visit(RecordCloningExpression node) { }
  @Override public void visit(RecordExpression node) { }
  @Override public void visit(SimpleForLoop node) { }
  @Override public void visit(SimpleObjectExpression node) { }
  @Override public void visit(TryFinallyExpression node) { }
  @Override public void visit(TryWithExpression node) { }
  @Override public void visit(TupleExpression node) { }
  @Override public void visit(TypeAnnotationExpression node) { }
  @Override public void visit(TypeApplicationExpression node) { }
  @Override public void visit(UpcastExpression node) { }
  @Override public void visit(ValueDefinitionExpression node) { }
  @Override public void visit(WhileExpression node) { }

  @Override public void visit(ArrayPattern node) { }
  @Override public void visit(AsPattern node) { }
  @Override public void visit(AttributedPattern node) { }
  @Override public void visit(ConjunctivePattern node) { }
  @Override public void visit(ConsPattern node) { }
  @Override public void visit(ConstantPattern node) { }
  @Override public void visit(DisjunctivePattern node) { }
  @Override public void visit(DynamicTypeTestPattern node) { }
  @Override public void visit(FieldPattern node) { }
  @Override public void visit(ListPattern node) { }
  @Override public void visit(NamedPattern node) { }
  @Override public void visit(NullTestPattern node) { }
  @Override public void visit(RecordPattern node) { }
  @Override public void visit(TuplePattern node) { }
  @Override public void visit(TypeConstrainedPattern node) { }
  @Override public void visit(WildcardPattern node) { }

  @Override public void visit(ConstantPatParam node) { }
  @Override public void visit(IdentParamPatParam node) { }
  @Override public void visit(IdentPatParam node) { }
  @Override public void visit(ListPatParam node) { }
  @Override public void visit(NullPatParam node) { }
  @Override public void visit(TuplePatParam node) { }
  @Override public void visit(TypedPatParam node) { }

  @Override public void visit(TypeVariable node) { }
  @Override public void visit(StaticHeadTypeVariable node) { }
  @Override public void visit(AnonymousTypeVariable node) { }

  @Override public void visit(TyparDefn node) { }
  @Override public void visit(TyparDefns node) { }
  @Override public void visit(AnoymousWithSubtypeConstraintAtomicType node) { }
  @Override public void visit(TypeLongIdentAtomicType node) { }
  @Override public void visit(TypeTypeAtomicType node) { }

  @Override public void visit(CoercionConstraint node) { }
  @Override public void visit(ComparisonConstraint node) { }
  @Override public void visit(DefaultConstructorConstraint node) { }
  @Override public void visit(DelegateDecompositionConstraint node) { }
  @Override public void visit(EnumDecompositionConstraint node) { }
  @Override public void visit(EqualityConstraint node) { }
  @Override public void visit(NullnessConstraint node) { }
  @Override public void visit(ReferenceTypeConstraint node) { }
  @Override public void visit(StructConstraint node) { }
  @Override public void visit(UnmanagedConstraint node) { }

  @Override public void visit(AnonymousTypeWithSubtypeConstraint node) { }
  @Override public void visit(ArrayType node) { }
  @Override public void visit(ConstrainedType node) { }
  @Override public void visit(FunctionType node) { }
  @Override public void visit(NamedType node) { }
  @Override public void visit(TupleType node) { }

  @Override public void visit(AbbrevTypeDefn node) { }
  @Override public void visit(ArgSpec node) { }
  @Override public void visit(ClassInheritsDecl node) { }
  @Override public void visit(CurriedSig node) { }
  @Override public void visit(DelegateTypeDefn node) { }
  @Override public void visit(EnumTypeCase node) { }
  @Override public void visit(EnumTypeDefn node) { }
  @Override public void visit(ExceptionAbbreviation node) { }
  @Override public void visit(ExceptionDefinition node) { }
  @Override public void visit(FunctionDefn node) { }
  @Override public void visit(InterfaceSpec node) { }
  @Override public void visit(InterfaceTypeDefn node) { }
  @Override public void visit(MemberSig node) { }
  @Override public void visit(NAryUnionCase node) { }
  @Override public void visit(NullaryUnionCase node) { }
  @Override public void visit(RecordTypeDefn node) { }
  @Override public void visit(TypeExtension node) { }
  @Override public void visit(UncurriedSig node) { }
  @Override public void visit(UncurriedSigUnionCase node) { }
  @Override public void visit(UnionTypeCase node) { }
  @Override public void visit(UnionTypeDefn node) { }

  @Override public void visit(AnonymousModule node) { }
  @Override public void visit(ModuleAbbrev node) { }

  @Override public void visit(ModuleFunctionDefinition node) { }

}
