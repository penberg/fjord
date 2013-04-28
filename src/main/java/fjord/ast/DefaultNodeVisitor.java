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

  @Override
  public void visit(CompilerDirectiveDecl node) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(Const node) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(Ident node) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ImportDecl node) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ScriptFragment node) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ValueDefn node) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ApplicationExpression applicationExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ArrayExpression arrayExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(AssertExpression assertExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(AssignmentExpression assignmentExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(BaseCall baseCall) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ConstantExpression constantExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(
      DeterministicDisposalExpression deterministicDisposalExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(DotLookupExpression dotLookupExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(DowncastExpression downcastExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ElifBranch elifBranch) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(FieldInitializer fieldInitializer) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(FunctionDefinitionExpression functionDefinitionExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(IfExpression ifExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(LazyExpression lazyExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ListExpression listExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(MatchExpression matchExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(MatchingFunctionExpression matchingFunctionExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(NullExpression nullExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ObjectConstruction objectConstruction) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(RecordCloningExpression recordCloningExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(RecordExpression recordExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(SimpleForLoop simpleForLoop) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(SimpleObjectExpression simpleObjectExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TryFinallyExpression tryFinallyExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TryWithExpression tryWithExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TupleExpression tupleExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TypeAnnotationExpression typeAnnotationExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TypeApplicationExpression typeApplicationExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(UpcastExpression upcastExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ValueDefinitionExpression valueDefinitionExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(WhileExpression whileExpression) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ArrayPattern arrayPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(AsPattern asPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(AttributedPattern attributedPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ConjunctivePattern conjunctivePattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ConsPattern consPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ConstantPattern constantPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(DisjunctivePattern disjunctivePattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(DynamicTypeTestPattern dynamicTypeTestPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(FieldPattern fieldPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ListPattern listPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(NamedPattern namedPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(NullTestPattern nullTestPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(RecordPattern recordPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TuplePattern tuplePattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TypeConstrainedPattern typeConstrainedPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(WildcardPattern wildcardPattern) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ConstantPatParam constantPatParam) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(IdentParamPatParam identParamPatParam) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(IdentPatParam identPatParam) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ListPatParam listPatParam) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(NullPatParam nullPatParam) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TuplePatParam tuplePatParam) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TypedPatParam typedPatParam) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TypeVariable typeVariable) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(StaticHeadTypeVariable staticHeadTypeVariable) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(AnonymousTypeVariable anonymousTypeVariable) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TyparDefn typarDefn) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TyparDefns typarDefns) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(
      AnoymousWithSubtypeConstraintAtomicType anoymousWithSubtypeConstraintAtomicType) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TypeLongIdentAtomicType typeLongIdentAtomicType) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TypeTypeAtomicType typeTypeAtomicType) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(CoercionConstraint coercionConstraint) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ComparisonConstraint comparisonConstraint) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(DefaultConstructorConstraint defaultConstructorConstraint) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(
      DelegateDecompositionConstraint delegateDecompositionConstraint) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(EnumDecompositionConstraint enumDecompositionConstraint) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(EqualityConstraint equalityConstraint) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(NullnessConstraint nullnessConstraint) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ReferenceTypeConstraint referenceTypeConstraint) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(StructConstraint structConstraint) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(UnmanagedConstraint unmanagedConstraint) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(
      AnonymousTypeWithSubtypeConstraint anonymousTypeWithSubtypeConstraint) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ArrayType arrayType) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ConstrainedType constrainedType) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(FunctionType functionType) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(NamedType namedType) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TupleType tupleType) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(AbbrevTypeDefn abbrevTypeDefn) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ArgSpec argSpec) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ClassInheritsDecl classInheritsDecl) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(CurriedSig curriedSig) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(DelegateTypeDefn delegateTypeDefn) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(EnumTypeCase enumTypeCase) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(EnumTypeDefn enumTypeDefn) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ExceptionAbbreviation exceptionAbbreviation) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ExceptionDefinition exceptionDefinition) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(FunctionDefn functionDefn) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(InterfaceSpec interfaceSpec) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(InterfaceTypeDefn interfaceTypeDefn) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(MemberSig memberSig) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(NAryUnionCase nAryUnionCase) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(NullaryUnionCase nullaryUnionCase) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(RecordTypeDefn recordTypeDefn) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(TypeExtension typeExtension) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(UncurriedSig uncurriedSig) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(UncurriedSigUnionCase uncurriedSigUnionCase) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(UnionTypeCase unionTypeCase) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(UnionTypeDefn unionTypeDefn) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(AnonymousModule anonymousModule) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ModuleAbbrev moduleAbbrev) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(ModuleFunctionDefinition moduleFunctionDefinition) {
    // TODO Auto-generated method stub
    
  }


}
