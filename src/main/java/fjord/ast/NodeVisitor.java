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
  void visit(ApplicationExpression applicationExpression);
  void visit(ArrayExpression arrayExpression);
  void visit(AssertExpression assertExpression);
  void visit(AssignmentExpression assignmentExpression);
  void visit(BaseCall baseCall);
  void visit(ConstantExpression constantExpression);
  void visit(DeterministicDisposalExpression deterministicDisposalExpression);
  void visit(DotLookupExpression dotLookupExpression);
  void visit(DowncastExpression downcastExpression);
  void visit(ElifBranch elifBranch);
  void visit(FieldInitializer fieldInitializer);
  void visit(FunctionDefinitionExpression functionDefinitionExpression);
  void visit(IfExpression ifExpression);
  void visit(LazyExpression lazyExpression);
  void visit(ListExpression listExpression);
  void visit(MatchExpression matchExpression);
  void visit(MatchingFunctionExpression matchingFunctionExpression);
  void visit(NullExpression nullExpression);
  void visit(ObjectConstruction objectConstruction);
  void visit(RecordCloningExpression recordCloningExpression);
  void visit(RecordExpression recordExpression);
  void visit(SimpleForLoop simpleForLoop);
  void visit(SimpleObjectExpression simpleObjectExpression);
  void visit(TryFinallyExpression tryFinallyExpression);
  void visit(TryWithExpression tryWithExpression);
  void visit(TupleExpression tupleExpression);
  void visit(TypeAnnotationExpression typeAnnotationExpression);
  void visit(TypeApplicationExpression typeApplicationExpression);
  void visit(UpcastExpression upcastExpression);
  void visit(ValueDefinitionExpression valueDefinitionExpression);
  void visit(WhileExpression whileExpression);
  
  void visit(ArrayPattern arrayPattern);
  void visit(AsPattern asPattern);
  void visit(AttributedPattern attributedPattern);
  void visit(ConjunctivePattern conjunctivePattern);
  void visit(ConsPattern consPattern);
  void visit(ConstantPattern constantPattern);
  void visit(DisjunctivePattern disjunctivePattern);
  void visit(DynamicTypeTestPattern dynamicTypeTestPattern);
  void visit(FieldPattern fieldPattern);
  void visit(ListPattern listPattern);
  void visit(NamedPattern namedPattern);
  void visit(NullTestPattern nullTestPattern);
  void visit(RecordPattern recordPattern);
  void visit(TuplePattern tuplePattern);
  void visit(TypeConstrainedPattern typeConstrainedPattern);
  void visit(WildcardPattern wildcardPattern);
  
  void visit(ConstantPatParam constantPatParam);
  void visit(IdentParamPatParam identParamPatParam);
  void visit(IdentPatParam identPatParam);
  void visit(ListPatParam listPatParam);
  void visit(NullPatParam nullPatParam);
  void visit(TuplePatParam tuplePatParam);
  void visit(TypedPatParam typedPatParam);
  
  void visit(TypeVariable typeVariable);
  void visit(StaticHeadTypeVariable staticHeadTypeVariable);
  void visit(AnonymousTypeVariable anonymousTypeVariable);
  
  void visit(TyparDefn typarDefn);
  void visit(TyparDefns typarDefns);
  void visit(
      AnoymousWithSubtypeConstraintAtomicType anoymousWithSubtypeConstraintAtomicType);
  void visit(TypeLongIdentAtomicType typeLongIdentAtomicType);
  void visit(TypeTypeAtomicType typeTypeAtomicType);
  
  void visit(CoercionConstraint coercionConstraint);
  void visit(ComparisonConstraint comparisonConstraint);
  void visit(DefaultConstructorConstraint defaultConstructorConstraint);
  void visit(DelegateDecompositionConstraint delegateDecompositionConstraint);
  void visit(EnumDecompositionConstraint enumDecompositionConstraint);
  void visit(EqualityConstraint equalityConstraint);
  void visit(NullnessConstraint nullnessConstraint);
  void visit(ReferenceTypeConstraint referenceTypeConstraint);
  void visit(StructConstraint structConstraint);
  void visit(UnmanagedConstraint unmanagedConstraint);
  
  void visit(
      AnonymousTypeWithSubtypeConstraint anonymousTypeWithSubtypeConstraint);
  void visit(ArrayType arrayType);
  void visit(ConstrainedType constrainedType);
  void visit(FunctionType functionType);
  void visit(NamedType namedType);
  void visit(TupleType tupleType);
  
  void visit(AbbrevTypeDefn abbrevTypeDefn);
  void visit(ArgSpec argSpec);
  void visit(ClassInheritsDecl classInheritsDecl);
  void visit(CurriedSig curriedSig);
  void visit(DelegateTypeDefn delegateTypeDefn);
  void visit(EnumTypeCase enumTypeCase);
  void visit(EnumTypeDefn enumTypeDefn);
  void visit(ExceptionAbbreviation exceptionAbbreviation);
  void visit(ExceptionDefinition exceptionDefinition);
  void visit(FunctionDefn functionDefn);
  void visit(InterfaceSpec interfaceSpec);
  void visit(InterfaceTypeDefn interfaceTypeDefn);
  void visit(MemberSig memberSig);
  void visit(NAryUnionCase nAryUnionCase);
  void visit(NullaryUnionCase nullaryUnionCase);
  void visit(RecordTypeDefn recordTypeDefn);
  void visit(TypeExtension typeExtension);
  void visit(UncurriedSig uncurriedSig);
  void visit(UncurriedSigUnionCase uncurriedSigUnionCase);
  void visit(UnionTypeCase unionTypeCase);
  void visit(UnionTypeDefn unionTypeDefn);
  
  void visit(AnonymousModule anonymousModule);
  void visit(ModuleAbbrev moduleAbbrev);
  
  void visit(ModuleFunctionDefinition moduleFunctionDefinition);
  
}
