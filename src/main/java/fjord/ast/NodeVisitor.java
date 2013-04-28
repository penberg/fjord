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

public interface NodeVisitor {

  void visit(CompilerDirectiveDecl node);
  void visit(Const node);
  void visit(Ident node);
  void visit(ImportDecl node);
  void visit(ScriptFragment node);
  void visit(ValueDefn node);
  void visit(ApplicationExpression node);
  void visit(ArrayExpression node);
  void visit(AssertExpression node);
  void visit(AssignmentExpression node);
  void visit(BaseCall node);
  void visit(ConstantExpression node);
  void visit(DeterministicDisposalExpression node);
  void visit(DotLookupExpression node);
  void visit(DowncastExpression node);
  void visit(ElifBranch node);
  void visit(FieldInitializer node);
  void visit(FunctionDefinitionExpression node);
  void visit(IfExpression node);
  void visit(LazyExpression node);
  void visit(ListExpression node);
  void visit(MatchExpression node);
  void visit(MatchingFunctionExpression node);
  void visit(NullExpression node);
  void visit(ObjectConstruction node);
  void visit(RecordCloningExpression node);
  void visit(RecordExpression node);
  void visit(SimpleForLoop node);
  void visit(SimpleObjectExpression node);
  void visit(TryFinallyExpression node);
  void visit(TryWithExpression node);
  void visit(TupleExpression node);
  void visit(TypeAnnotationExpression node);
  void visit(TypeApplicationExpression node);
  void visit(UpcastExpression node);
  void visit(ValueDefinitionExpression node);
  void visit(WhileExpression node);
  
  void visit(ArrayPattern node);
  void visit(AsPattern node);
  void visit(AttributedPattern node);
  void visit(ConjunctivePattern node);
  void visit(ConsPattern node);
  void visit(ConstantPattern node);
  void visit(DisjunctivePattern node);
  void visit(DynamicTypeTestPattern node);
  void visit(FieldPattern node);
  void visit(ListPattern node);
  void visit(NamedPattern node);
  void visit(NullTestPattern node);
  void visit(RecordPattern node);
  void visit(TuplePattern node);
  void visit(TypeConstrainedPattern node);
  void visit(WildcardPattern node);
  
  void visit(ConstantPatParam node);
  void visit(IdentParamPatParam node);
  void visit(IdentPatParam node);
  void visit(ListPatParam node);
  void visit(NullPatParam node);
  void visit(TuplePatParam node);
  void visit(TypedPatParam node);
  
  void visit(TypeVariable node);
  void visit(StaticHeadTypeVariable node);
  void visit(AnonymousTypeVariable node);
  
  void visit(TyparDefn node);
  void visit(TyparDefns node);
  void visit(AnoymousWithSubtypeConstraintAtomicType node);
  void visit(TypeLongIdentAtomicType node);
  void visit(TypeTypeAtomicType node);
  
  void visit(CoercionConstraint node);
  void visit(ComparisonConstraint node);
  void visit(DefaultConstructorConstraint node);
  void visit(DelegateDecompositionConstraint node);
  void visit(EnumDecompositionConstraint node);
  void visit(EqualityConstraint node);
  void visit(NullnessConstraint node);
  void visit(ReferenceTypeConstraint node);
  void visit(StructConstraint node);
  void visit(UnmanagedConstraint node);
  
  void visit(AnonymousTypeWithSubtypeConstraint node);
  void visit(ArrayType node);
  void visit(ConstrainedType node);
  void visit(FunctionType node);
  void visit(NamedType node);
  void visit(TupleType node);
  
  void visit(AbbrevTypeDefn node);
  void visit(ArgSpec node);
  void visit(ClassInheritsDecl node);
  void visit(CurriedSig node);
  void visit(DelegateTypeDefn node);
  void visit(EnumTypeCase node);
  void visit(EnumTypeDefn node);
  void visit(ExceptionAbbreviation node);
  void visit(ExceptionDefinition node);
  void visit(FunctionDefn node);
  void visit(InterfaceSpec node);
  void visit(InterfaceTypeDefn node);
  void visit(MemberSig node);
  void visit(NAryUnionCase node);
  void visit(NullaryUnionCase node);
  void visit(RecordTypeDefn node);
  void visit(TypeExtension node);
  void visit(UncurriedSig node);
  void visit(UncurriedSigUnionCase node);
  void visit(UnionTypeCase node);
  void visit(UnionTypeDefn node);
  
  void visit(AnonymousModule node);
  void visit(ModuleAbbrev node);
  
  void visit(ModuleFunctionDefinition node);
  
}
