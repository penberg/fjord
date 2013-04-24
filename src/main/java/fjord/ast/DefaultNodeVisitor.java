package fjord.ast;

import fjord.ast.expr.ApplicationExpression;
import fjord.ast.expr.ArrayExpression;
import fjord.ast.expr.AssertExpression;
import fjord.ast.expr.AssignmentExpression;
import fjord.ast.expr.BaseCall;
import fjord.ast.expr.ConstantExpression;
import fjord.ast.expr.DeterministicDisposalExpression;
import fjord.ast.expr.DotLookupExpression;
import fjord.ast.expr.DowncastExpression;
import fjord.ast.expr.ElifBranch;
import fjord.ast.expr.FieldInitializer;
import fjord.ast.expr.FunctionDefinitionExpression;
import fjord.ast.expr.IfExpression;
import fjord.ast.expr.LazyExpression;
import fjord.ast.expr.ListExpression;
import fjord.ast.expr.MatchExpression;
import fjord.ast.expr.MatchingFunctionExpression;
import fjord.ast.expr.NullExpression;
import fjord.ast.expr.ObjectConstruction;
import fjord.ast.expr.RecordCloningExpression;
import fjord.ast.expr.RecordExpression;
import fjord.ast.expr.SimpleForLoop;
import fjord.ast.expr.SimpleObjectExpression;
import fjord.ast.expr.TryFinallyExpression;
import fjord.ast.expr.TryWithExpression;
import fjord.ast.expr.TupleExpression;
import fjord.ast.expr.TypeAnnotationExpression;
import fjord.ast.expr.TypeApplicationExpression;
import fjord.ast.expr.UpcastExpression;
import fjord.ast.expr.ValueDefinitionExpression;
import fjord.ast.expr.WhileExpression;
import fjord.ast.pat.ArrayPattern;
import fjord.ast.pat.AsPattern;
import fjord.ast.pat.AttributedPattern;
import fjord.ast.pat.ConjunctivePattern;
import fjord.ast.pat.ConsPattern;
import fjord.ast.pat.ConstantPattern;
import fjord.ast.pat.DisjunctivePattern;
import fjord.ast.pat.DynamicTypeTestPattern;
import fjord.ast.pat.FieldPattern;
import fjord.ast.pat.ListPattern;
import fjord.ast.pat.NamedPattern;
import fjord.ast.pat.NullTestPattern;
import fjord.ast.pat.RecordPattern;
import fjord.ast.pat.TuplePattern;
import fjord.ast.pat.TypeConstrainedPattern;
import fjord.ast.pat.WildcardPattern;
import fjord.ast.patparam.ConstantPatParam;
import fjord.ast.patparam.IdentParamPatParam;
import fjord.ast.patparam.IdentPatParam;
import fjord.ast.patparam.ListPatParam;
import fjord.ast.patparam.NullPatParam;
import fjord.ast.patparam.TuplePatParam;
import fjord.ast.patparam.TypedPatParam;
import fjord.ast.typar.Typar.AnonymousTypeVariable;
import fjord.ast.typar.Typar.StaticHeadTypeVariable;
import fjord.ast.typar.Typar.TypeVariable;
import fjord.ast.typar.TyparDefn;
import fjord.ast.typar.TyparDefns;
import fjord.ast.type.AnonymousTypeWithSubtypeConstraint;
import fjord.ast.type.ArrayType;
import fjord.ast.type.ConstrainedType;
import fjord.ast.type.FunctionType;
import fjord.ast.type.NamedType;
import fjord.ast.type.TupleType;
import fjord.ast.type.atomic.AnoymousWithSubtypeConstraintAtomicType;
import fjord.ast.type.atomic.TypeLongIdentAtomicType;
import fjord.ast.type.atomic.TypeTypeAtomicType;
import fjord.ast.type.constraint.CoercionConstraint;
import fjord.ast.type.constraint.ComparisonConstraint;
import fjord.ast.type.constraint.DefaultConstructorConstraint;
import fjord.ast.type.constraint.DelegateDecompositionConstraint;
import fjord.ast.type.constraint.EnumDecompositionConstraint;
import fjord.ast.type.constraint.EqualityConstraint;
import fjord.ast.type.constraint.NullnessConstraint;
import fjord.ast.type.constraint.ReferenceTypeConstraint;
import fjord.ast.type.constraint.StructConstraint;
import fjord.ast.type.constraint.UnmanagedConstraint;
import fjord.ast.typedefn.AbbrevTypeDefn;
import fjord.ast.typedefn.ArgSpec;
import fjord.ast.typedefn.ClassInheritsDecl;
import fjord.ast.typedefn.CurriedSig;
import fjord.ast.typedefn.DelegateTypeDefn;
import fjord.ast.typedefn.EnumTypeCase;
import fjord.ast.typedefn.EnumTypeDefn;
import fjord.ast.typedefn.ExceptionAbbreviation;
import fjord.ast.typedefn.ExceptionDefinition;
import fjord.ast.typedefn.FunctionDefn;
import fjord.ast.typedefn.InterfaceSpec;
import fjord.ast.typedefn.InterfaceTypeDefn;
import fjord.ast.typedefn.MemberSig;
import fjord.ast.typedefn.NAryUnionCase;
import fjord.ast.typedefn.NullaryUnionCase;
import fjord.ast.typedefn.RecordTypeDefn;
import fjord.ast.typedefn.TypeExtension;
import fjord.ast.typedefn.UncurriedSig;
import fjord.ast.typedefn.UncurriedSigUnionCase;
import fjord.ast.typedefn.UnionTypeCase;
import fjord.ast.typedefn.UnionTypeDefn;


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


}
