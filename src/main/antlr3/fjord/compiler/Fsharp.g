/*
 * F# grammar as defined in Appendix A ("F# Grammar Summary") of the F# 3.0
 * Language Specification.
 */

grammar Fsharp;

options {
  language  = Java;
  backtrack = true;
  memoize   = true;
}

@header {
  package fjord.compiler;

  import java.util.Arrays;

  import fjord.ast.*;
  import fjord.ast.typar.*;
  import fjord.ast.pat.*;
  import fjord.ast.expr.*;
  import fjord.ast.type.*;
  import fjord.ast.type.constraint.*;
}

@lexer::header {
  package fjord.compiler;

  import fjord.ast.*;
}

/*****************************************************************************
 * A.2 Syntactic grammar
 *****************************************************************************/

/*
 * A.2.1 Program format
 */

implementationFile
  : namespaceDeclGroup+
  | namedModule
  | anonymousModule
  ;

scriptFile
  : implementationFile
  ;

signatureFile
  : namespaceDeclGroupSignature+
  | anonymousModuleSignature
  | namedModuleSignature
  ;

namedModule
  : Module longIdent moduleElems
  ;

anonymousModule
  : moduleElems
  ;

namedModuleSignature
  : Module longIdent moduleSignatureElements
  ;

anonymousModuleSignature
  : moduleSignatureElements
  ;

scriptFragment returns [ScriptFragment n]
  :  moduleElems { $n = new ScriptFragment($moduleElems.n); }
  ;

/*
 * A.2.1.1 Namespaces and modules
 */

namespaceDeclGroup
  : Namespace longIdent moduleElems
  | Namespace Global moduleElems
  ;

moduleDefn
  : attributes? Module access? Ident Equals Begin? moduleDefnBody End?
  ;

moduleDefnBody
  : Begin moduleElems? End
  ;

moduleElem returns [Node n]
  : moduleFunctionOrValueDefn { $n = $moduleFunctionOrValueDefn.n; }
  | typeDefns
  | exceptionDefn
  | moduleDefn
  | moduleAbbrev              { $n = $moduleAbbrev.n; }
  | importDecl                { $n = $importDecl.n; }
  | compilerDirectiveDecl     { $n = $compilerDirectiveDecl.n; }
  ;

moduleFunctionOrValueDefn returns [Node n]
  : attributes? Let functionDefn
  | attributes? Let valueDefn { $n = $valueDefn.n; }
  | attributes? Let Rec? functionOrValueDefns
  | attributes? Do expr
  ;

importDecl returns [ImportDecl n]
  : Open longIdent { $n = new ImportDecl($longIdent.n); }
  ;

moduleAbbrev returns [ModuleAbbrev n]
  : Module Ident Equals longIdent { $n = new ModuleAbbrev($Ident.text, $longIdent.n); }
  ;

compilerDirectiveDecl returns [CompilerDirectiveDecl n]
  : Hash Ident { $n = new CompilerDirectiveDecl($Ident.text); }
  ;

moduleElems returns [List n]
  : { $n = new ArrayList(); }
    (moduleElem { $n.add($moduleElem.n); } )+
  ;

access returns [Access n]
  : Private { $n = Access.Private;}
  | Internal { $n = Access.Internal; }
  | Public { $n = Access.Public; }
  ;

/*
 * A.2.1.2 NamespaceandModuleSignatures
 */

namespaceDeclGroupSignature
  : Namespace longIdent moduleSignatureElements
  ;

moduleSignature
  : Module Ident Equals Begin? moduleSignatureBody End?
  ;

moduleSignatureElement
  : Val Mutable? curriedSig
  | Val valueDefn
  | Type typeSignatures
/*FIXME: | Exception exceptionSignature */
  | moduleSignature
  | moduleAbbrev
  | importDecl
  ;

moduleSignatureElements
  : Begin? moduleSignatureElement+ End?
  ;

moduleSignatureBody
  : Begin moduleSignatureElements End
  ;

typeSignature
  : abbrevTypeSignature
  | recordTypeSignature
  | unionTypeSignature
  | anonTypeSignature
  | classTypeSignature
  | structTypeSignature
  | interfaceTypeSignature
  | enumTypeSignature
  | delegateTypeSignature
  | typeExtensionSignature
  ;

typeSignatures
  : typeSignature (And typeSignature)*
  ;

typeSignatureElement
  : attributes? access? New Colon uncurriedSig
  | attributes? Member access? memberSig
  | attributes? Abstract access? memberSig
  | attributes? Override memberSig
  | attributes? Default memberSig
  | attributes? Static Member access? memberSig
  | Interface type
  ;

abbrevTypeSignature
  : typeName Equals type
  ;

unionTypeSignature
  : typeName Equals unionTypeCases typeExtensionElementsSignature?
  ;

recordTypeSignature
  : typeName Equals LBrace recordFields RBrace typeExtensionElementsSignature?
  ;

anonTypeSignature
  : typeName Equals Begin typeSignatureElement End
  ;

classTypeSignature
  : typeName Equals Class typeSignatureElement End
  ;

structTypeSignature
  : typeName Equals Struct typeSignatureElement End
  ;

interfaceTypeSignature
  : typeName Equals Interface typeSignatureElement End
  ;

enumTypeSignature
  : typeName Equals enumTypeCases
  ;

delegateTypeSignature
  : typeName Equals delegateSig
  ;

typeExtensionSignature
  : typeName typeExtensionElementsSignature
  ;

typeExtensionElementsSignature
  : With typeSignatureElement End
  ;

/*
 * A.2.2 Types and type constraints
 */

type returns [Type n]
  : Hash t1=type { $n = new AnonymousTypeWithSubtypeConstraint($t1.n); }
  | LParen t1=type RParen { $n = $t1.n; }
  | ty1=typar { $n = $ty1.n; }
  | l1=longIdent { $n = new NamedType($l1.n); }
  | l1=longIdent '<' ty=types '>' { $n = new NamedType($l1.n, $ty.n); }
  | l1=longIdent '<' '>' { $n = new NamedType($l1.n); }
  ( RArrow t2=type { $n = new FunctionType($n, $t2.n); }
  | { $n = new TupleType($n); } ('*' (t2=type { ((TupleType)$n).addChild($t2.n); }))+
  | l1=longIdent { $n = new NamedType($l1.n, Arrays.asList($n)); }
  | LBrack dims=(','*) RBrack { $n = new ArrayType($n, ($dims.text != null ? $dims.text : "").length() + 1); }
  | typarDefns { $n = new ConstrainedType($n, $typarDefns.n); }
  )?
  ;

types returns [List n]
  : { $n = new ArrayList(); } (t1=type { $n.add($t1.n); }) (',' (t2=type { $n.add($t2.n); }))*
  ;

atomicType
  : type Colon (Hash type | LParen type RParen | longIdent | longIdent '<' types '>')
  ;

typar returns [Typar n]
  : '_' { $n = Typar.anonymousTypeVariable(); }
  | '\'' Ident { $n = Typar.typeVariable($Ident.text); }
  | '^' Ident { $n = Typar.staticHeadTypeVariable($Ident.text); }
  ;

constraint returns [Constraint n]
  : ty1=typar ColonGreater t1=type { $n = new CoercionConstraint($ty1.n, $t1.n); }
  | ty1=typar Colon 'null' { $n = new NullnessConstraint($ty1.n); }
  | staticTypars Colon LParen memberSig RParen
  | ty1=typar Colon LParen New Colon Unit RArrow '\'T' RParen { $n = new DefaultConstructorConstraint($ty1.n); }
  | ty1=typar Colon Struct { $n = new StructConstraint($ty1.n); }
  | ty1=typar Colon 'not' Struct { $n = new ReferenceTypeConstraint($ty1.n); }
  | typar Colon 'enum' '<' type '>'
  | typar Colon 'unmanaged'
  | typar Colon Delegate '<' type ',' type '>'
  ;

typarDefn returns [TyparDefn n]
  : attributes? typar { $n = new TyparDefn($typar.n); }
  ;

typarDefns returns [TyparDefns n]
  : { $n = new TyparDefns(); } '<' (t1=typarDefn { $n.addChild($t1.n); }) (',' (t2=typarDefn { $n.addChild($t2.n); }))* typarConstraints? '>'
  ;

typarConstraints returns [List n]
  : { $n = new ArrayList(); } When (c1=constraint { $n.add($c1.n); }) (And (c2=constraint { $n.add($c2.n); }))*
  ;

staticTypars returns [List n]
  : '^' Ident { $n = Arrays.asList($Ident.text); }
  | { $n = new ArrayList(); } LParen '^'(id1=Ident { $n.add($id1.text); }) (Or '^'(id2=Ident { $n.add($id2.text); }))* RParen
  ;

/*
 * A.2.3 Expressions
 */

expr returns [Expr n]
  : ( constant { $n = new ConstantExpression($constant.n); }
    | LParen e1=expr RParen
    | Begin expr End
    | longIdentOrOp
    | prefixOp expr
    | New type expr
    | LBrace New baseCall objectMembers interfaceImpl RBrace
    | LBrace fis=fieldInitializers RBrace { $n = new RecordExpression($fis.n); }
    | LBrace e1=expr With fis=fieldInitializers RBrace { $n = new RecordCloningExpression($e1.n, $fis.n); }
    | { $n = new ListExpression(); } LBrack (e1=expr { ((ListExpression)$n).addExpr($e1.n); }) (Semicolon (e2=expr { ((ListExpression)$n).addExpr($e2.n); }))* RBrack
    | { $n = new ArrayExpression(); } LBrackBar (e1=expr { ((ArrayExpression)$n).addExpr($e1.n); }) (Semicolon (e2=expr { ((ArrayExpression)$n).addExpr($e2.n); }))* BarRBrack
    | LBrack compOrRangeExpr RBrack
    | LBrackBar compOrRangeExpr BarRBrack
    | Lazy e1=expr { $n = new LazyExpression($e1.n); }
    | Null { $n = new NullExpression(); }
    | Upcast e1=expr { $n = new UpcastExpression($e1.n); } 
    | Downcast e1=expr { $n = new DowncastExpression($e1.n); } 
    | Let functionDefn In expr
    | Let valueDefn In expr
    | Let Rec functionOrValueDefns In expr
    | Use i1=Ident Equals e1=expr In e2=expr { $n = new DeterministicDisposalExpression($i1.text, $e1.n, $e2.n); }
    | Fun argumentPats RArrow expr
    | Function r1=rules { $n = new MatchingFunctionExpression($r1.n); }
    | Match e1=expr With r1=rules { $n = new MatchExpression($e1.n, $r1.n); }
    | Try e1=expr With r1=rules { $n = new TryWithExpression($e1.n, $r1.n); } 
    | Try e1=expr Finally e2=expr { $n = new TryFinallyExpression($e1.n, $e2.n); }
    | If e1=expr Then e2=expr elifBranches? e4=elseBranch? { $n = new IfExpression($e1.n, $e2.n, $elifBranches.n, $e4.n); }
    | While e1=expr Do e2=expr Done { $n = new WhileExpression($e1.n, $e2.n); } 
    | For i1=Ident Equals e1=expr To e2=expr Do e3=expr Done { $n = new SimpleForLoop($i1.text, $e1.n, $e2.n, $e3.n); }
    | For pat In exprOrRangeExpr Do expr Done
    | Assert e1=expr { $n = new AssertExpression($e1.n); }
    | LQuote expr RQuote
    | LQuoteUntyped expr RQuoteUntyped
    | '%' expr
    | '%%' expr
    | LParen staticTypars Colon LParen memberSig RParen expr RParen
    )
    ( Dot longIdentOrOp
    | expr
    | LParen expr RParen
    | '<' types '>'
    | infixOp expr
    | Dot LBrack expr RBrack
    | Dot LBrack sliceRange RBrack
    | Dot LBrack sliceRange ',' sliceRange RBrack
    | LArrow expr
    | (',' expr)+
    | LBrace compOrRangeExpr RBrace
    | Colon type
    | ColonGreater type
    | ColonQMark type
    | ColonQMarkGreater type
    | Semicolon expr
    )?
  ;

exprs
  : expr (',' expr)*
  ;

exprOrRangeExpr
  : expr
  | rangeExpr
  ;

elifBranches returns [List n]
  : { $n = new ArrayList(); } (elifBranch {$n.add($elifBranch.n); })+
  ;

elifBranch returns [ElifBranch n]
  : Elif e1=expr Then e2=expr { $n = new ElifBranch($e1.n, $e2.n); }
  ;

elseBranch returns [Expr n]
  : Else expr { $n = $expr.n; }
  ;

functionOrValueDefn
  : functionDefn
  | valueDefn
  ;

functionDefn
  : Inline? access? identOrOp typarDefns? argumentPats returnType? Equals expr
  ;

valueDefn returns [Node n]
  : Mutable? access? pat typarDefns? returnType? Equals expr { $n = new ValueDefn($pat.n, $expr.n); }
  ;

returnType returns [Type n]
  : Colon type { $n = $type.n; }
  ;

functionOrValueDefns
  : functionOrValueDefn (And functionOrValueDefn)+
  ;

argumentPats returns [List n]
  : { $n = new ArrayList(); } (atomicPat { $n.add($atomicPat.n); })+
  ;

fieldInitializer returns [FieldInitializer n]
  : longIdent Equals expr { $n = new FieldInitializer($longIdent.n, $expr.n); }
  ;

fieldInitializers returns [List n]
  : { $n = new ArrayList(); } (f1=fieldInitializer { $n.add($f1.n); }) (Semicolon (f2=fieldInitializer { $n.add($f2.n); }))*
  ;

objectConstruction
  : type expr
  | type
  ;

baseCall
  : objectConstruction
  | objectConstruction As Ident
  ;

interfaceImpls
  : interfaceImpl+
  ;

interfaceImpl
  : Interface type objectMembers?
  ;

objectMembers
  : With memberDefns End
  ;

memberDefns
  : memberDefn+
  ;

/*
 * A.2.3.1 Computation and Range Expressions
 */

compOrRangeExpr
  : compExpr
  | shortCompExpr
  | rangeExpr
  ;

compExpr
  :( LetE pat Equals expr In compExpr
  | Let pat Equals expr In compExpr
  | DoE expr In compExpr
  | Do expr In compExpr
  | UseE pat Equals expr In compExpr
  | Use pat Equals expr In compExpr
  | YieldE expr
  | Yield expr
  | ReturnE expr
  | Return expr
  | If expr Then compExpr
  | If expr Then compExpr Else compExpr
  | Match expr With compRules
  | Try compExpr With compRules
  | Try compExpr Finally expr
  | While expr Do expr Done?
  | For Ident Equals expr To expr Do compExpr Done?
  | For pat In exprOrRangeExpr Do compExpr Done?
  | expr
  )
  (
    | Semicolon compExpr
  )?
  ;

compRule returns [Node n]
  : pat patternGuard? RArrow compExpr
  ;

compRules returns [List n]
  : { $n = new ArrayList(); } Bar? (c1=compRule { $n.add($c1.n); }) (Bar c2=compRule { $n.add($c2.n); })*
  ;

shortCompExpr
  : For pat In exprOrRangeExpr RArrow expr
  ;

rangeExpr
  : expr DotDot expr
  | expr DotDot expr DotDot expr
  ;

sliceRange
  : expr DotDot
  | DotDot expr
  | expr DotDot expr
  | '*'
  ;

/*
 * A.2.4 Patterns
 */

rule returns [Rule n]
  : pat patternGuard? RArrow expr { $n = new Rule($pat.n, $patternGuard.n, $expr.n); }
  ;

patternGuard returns [Expr n]
  : When expr { $n = $expr.n; }
  ;

pat returns [Pat n]
  : (constant { $n = new ConstantPattern($constant.n); }
  | longIdent patParam? pat? { $n = new NamedPattern($longIdent.n); }
  | Underscore { $n = new WildcardPattern(); }
  | LParen p1=pat RParen { $n = $p1.n; }
  | listPat { $n = $listPat.n; }
  | arrayPat { $n = $arrayPat.n; }
  | recordPat { $n = $recordPat.n; }
  | ColonQMark atomicType
  | ColonQMark atomicType As Ident
  | Null
  | attributes pat
  )
  (
    | As i1=Ident { $n = new AsPattern($n, $i1.text); }
    | Bar p1=pat { $n = new DisjunctivePattern($n, $p1.n); }
    | '&' p1=pat { $n = new ConjunctivePattern($n, $p1.n); }
    | ColonColon p1=pat { $n = new ConsPattern($n, $p1.n); }
    | Colon ty=type { $n = new TypeConstrainedPattern($n, $ty.n); }
    | { $n = new TuplePattern($n); } (',' (p2=pat { ((TuplePattern)$n).addChild($p2.n); }))+
  )?

  ;

listPat returns [ListPattern n]
  : LBrack RBrack { $n = new ListPattern(); }
  | {$n = new ListPattern(); } LBrack (p1=pat { $n.addChild($p1.n); }) (Semicolon (p2=pat { $n.addChild($p2.n); }))* RBrack 
  ;

arrayPat returns [ArrayPattern n]
  : LBrackBar BarRBrack { $n = new ArrayPattern(); }
  | { $n = new ArrayPattern(); } LBrackBar (p1=pat { $n.addChild($p1.n); }) (Semicolon (p2=pat { $n.addChild($p2.n); }))* BarRBrack
  ;

recordPat returns [RecordPattern n]
  : { $n = new RecordPattern(); } LBrace (f1=fieldPat { $n.addChild($f1.n); }) (Semicolon (f2=fieldPat { $n.addChild($f2.n); }))* RBrace
  ;

atomicPat returns [Node n]
  : pat Colon (constant | longIdent | listPat | recordPat | arrayPat | LParen pat RParen | ColonQMark atomicType | Null | Underscore)
  ;

fieldPat returns [FieldPattern n]
  : longIdent Equals pat { $n = new FieldPattern($longIdent.n, $pat.n); }
  ;

patParam
  :( constant
  | longIdent
  | LBrack patParam (Semicolon patParam) RBrack
  | LParen patParam (',' patParam) RParen
  | longIdent patParam
/*
  | '<@' expr '@>'
  | '<@@' expr '@@>'
*/
  | Null
  )
  (
  | patParam Colon type
  )?
  ;

pats
  : pat (',' pat)*
  ;

fieldPats returns [List n]
  : { $n = new ArrayList(); } (f1=fieldPat { $n.add($f1.n); }) (Semicolon (f2=fieldPat { $n.add($f2.n); }))*
  ;

rules returns [List n]
  : { $n = new ArrayList(); }Bar? (r1=rule { $n.add($r1.n); }) (Bar (r2=rule { $n.add($r2.n); }))*
  ;


/*
 * A.2.5 Type Definitions
 */

typeDefns
  : typeDefn+
  ;

typeDefn
  : abbrevTypeDefn
  | recordTypeDefn
  | unionTypeDefn
  | anonTypeDefn
  | classTypeDefn
  | structTypeDefn
  | interfaceTypeDefn
  | enumTypeDefn
  | delegateTypeDefn
  | typeExtension
  ;

typeName
  : attributes? access? Ident typarDefns?
  ;

abbrevTypeDefn
  : typeName Equals type
  ;

unionTypeDefn
  : typeName Equals unionTypeCases typeExtensionElements?
  ;

unionTypeCases
  : Bar? unionTypeCase (Bar unionTypeCase)*
  ;

unionTypeCase
  : attributes? unionTypeCaseData
  ;

unionTypeCaseData
  : Ident
  | Ident Of type ('*' type)*
  | Ident Colon uncurriedSig
  ;

anonTypeDefn
  :  typeName primaryConstrArgs? valueDefn? Equals Begin classTypeBody End
  ;

recordTypeDefn
  : typeName Equals LBrace recordFields RBrace typeExtensionElements?
  ;

recordFields returns [List n]
  : { $n = new ArrayList(); } (r1=recordField { $n.add($r1.n); }) (Semicolon (r2=recordField { $n.add($r2.n); }))* Semicolon?
  ;

recordField returns [Node n]
  : attributes? Mutable? access? Ident Colon type
  ;

classTypeDefn
  :  typeName primaryConstrArgs? valueDefn? Equals Class classTypeBody End
  ;

asDefn
  : As Ident
  ;

classTypeBody
  : Begin? classInheritsDecl? classFunctionOrValueDefns? typeDefnElements? End?
  ;

classInheritsDecl
  : Inherit type expr?
  ;

/* This rule is mentioned in spec but it's not declared anywhere :S */
classFunctionOrValueDefns
  : classFunctionOrValueDefn+
  ;

classFunctionOrValueDefn
  : attributes? Static? Let Rec? functionOrValueDefns
  | attributes? Static? Do expr
  ;

structTypeDefn
  : typeName primaryConstrArgs? asDefn? Equals Struct structTypeBody End
  ;

structTypeBody
  : typeDefnElements
  ;

interfaceTypeDefn
  : typeName Equals Interface interfaceTypeBody End
  ;

interfaceTypeBody
  : typeDefnElements
  ;

exceptionDefn
  : attributes? Exception unionTypeCaseData
  | attributes? Exception Ident Equals longIdent
  ;

enumTypeDefn
  : typeName Equals enumTypeCases
  ;

enumTypeCases
  : Bar? enumTypeCase (Bar enumTypeCase)*
  ;

enumTypeCase
  : Ident Equals constant
  ;

delegateTypeDefn
  : typeName Equals delegateSig
  ;

delegateSig
  : Delegate Of uncurriedSig
  ;

typeExtension
  : typeName typeExtensionElements
  ;

typeExtensionElements
  : With typeDefnElements End
  ;

typeDefnElement
  : memberDefn
  | interfaceImpl
  /*FIXME: |  interfaceSignature */
  ;

typeDefnElements
  : typeDefnElement+
  ;

primaryConstrArgs
  : attributes? access? LParen simplePat (',' simplePat)* RParen
  ;

simplePat
  : Ident
  (| simplePat Colon Type)?
  ;

additionalConstrDefn
  : attributes? access? New pat asDefn Equals additionalConstrExpr
  ;

additionalConstrExpr
  : (If expr Then additionalConstrExpr Else additionalConstrExpr
  | Let valueDefn In additionalConstrExpr
  | additionalConstrInitExpr
  )
  (
    Semicolon additionalConstrExpr
  | Then expr
  )?
  ;

additionalConstrInitExpr
  : LBrace classInheritsDecl fieldInitializers RBrace
  | New type expr
  ;

memberDefn
  : attributes? Static? Member access? methodOrPropDefn
  | attributes? Abstract Member? access? memberSig
  | attributes? Override access? methodOrPropDefn
  | attributes? Default access? methodOrPropDefn
  | attributes? Static? Val Mutable? access? Ident Colon type
  | additionalConstrDefn
  ;

methodOrPropDefn
  : Ident? functionDefn
  | Ident? valueDefn
  | Ident? Ident With functionOrValueDefns
  | Member Ident Equals expr
  | Member Ident Equals expr With Get
  | Member Ident Equals expr With Set
  | Member Ident Equals expr With Get ',' Set
  | Member Ident Equals expr With Set ',' Get
  ;

memberSig
  : Ident typarDefns? Colon curriedSig
  | Ident typarDefns? Colon curriedSig With Get
  | Ident typarDefns? Colon curriedSig With Set
  | Ident typarDefns? Colon curriedSig With Get ',' Set
  | Ident typarDefns? Colon curriedSig With Set ',' Get
  ;

curriedSig
  : argsSpec RArrow (argsSpec RArrow)* type
  ;

uncurriedSig
  : argsSpec RArrow type
  ;

argsSpec returns [List n]
  : { $n = new ArrayList(); } (a1=argSpec { $n.add($a1.n); }) ('*' (a2=argSpec { $n.add($a2.n); }))*
  ;

argSpec returns [Node n]
  : attributes? argNameSpec? type
  ;

argNameSpec
  : Qmark? Ident Colon
  ;

interfaceSpec
  : Interface type
  ;

/*
 * A.2.6 Units Of Measure
 */

measureLiteralAtom
  : longIdent
  | LParen measureLiteralSimp RParen
  ;

measureLiteralPower
  : measureLiteralAtom ('^' Int32)?
  ;

measureLiteralSeq
  : measureLiteralPower+
  ;

measureLiteralSimp
  : (measureLiteralSeq
  | '/' measureLiteralSimp
  | One
  )
  (
  | '*' measureLiteralSimp
  | '/' measureLiteralSimp
  )?
  ;

measureLiteral
  : Underscore
  | measureLiteralSimp
  ;

measureAtom
  : typar
  | longIdent
  | LParen measureSimp RParen
  ;

measurePower
  : measureAtom ('^' Int32)?
  ;

measureSeq
  : measurePower+
  ;

measureSimp
  : (measureSeq
  | '/' measureSimp
  | One
  )
  (
  | '*' measureSimp
  | '/' measureSimp
  )?
  ;

measure
  : Underscore
  | measureSimp
  ;

/*
 * A.2.7 Custom attributes and reflection
 */

attribute
  : (attributeTarget Colon)? objectConstruction
  ;

attributeSet
  : LBrackLess attribute (Semicolon attribute)* GreaterRBrack
  ;

attributes
  : attributeSet+
  ;

attributeTarget
  : Module
  | Type
  | Ident
  ;

/*****************************************************************************
 * A.1 Lexical grammar
 *****************************************************************************/

/*
 * A.1.1 Whitespace
 */

fragment
Whitespace
  : ' '+
  ;

fragment
NewLine
  : '\n'
  | '\r' '\n'
  ;

WhitespaceOrNewline
  : (Whitespace | NewLine) { skip(); }
  ;

/*
 * A.1.2 Comments
 */

BlockComment
  : '(*' (options { greedy = false; } : . )* '*)' { skip(); }
  ;

EndOfLineComment
  : '//' ~('\n' | '\r')* { skip(); }
  ;

/*
 * A.1.3 Conditional Compilation
 */

IfDirective
  : '#if' Whitespace IdentText
  ;

ElseDirective
  : '#else'
  ;

EndifDirective
  : '#endif'
  ;

/*
 * A.1.4 Identifiers and Keywords
 */

/*
 * A.1.4.2 Long identifiers
 */

longIdent returns [String n]
  : LongIdentWithDots { $n = $LongIdentWithDots.text; }
  | Ident             { $n = $Ident.text;             }
  ;

LongIdentWithDots
  : Ident (Dot Ident)+
  ;

longIdentOrOp returns [Node n]
  : longIdent Dot identOrOp
  | identOrOp
  ;

/*
 * A.1.4.3 Keywords
 */

Abstract
  : 'abstract'
  ;

And
  : 'and'
  ;

As
  : 'as'
  ;

Assert
  : 'assert'
  ;

Base
  : 'base'
  ;

Begin
  : 'begin'
  ;

Class
  : 'class'
  ;

Default
  : 'default'
  ;

Delegate
  : 'delegate'
  ;

Do
  : 'do'
  ;

Done
  : 'done'
  ;

Downcast
  : 'downcast'
  ;

Downto
  : 'downto'
  ;

Elif
  : 'elif'
  ;

Else
  : 'else'
  ;

End
  : 'end'
  ;

Exception
  : 'exception'
  ;

Extern
  : 'extern'
  ;

False
  : 'false'
  ;

Finally
  : 'finally'
  ;

For
  : 'for'
  ;

Fun
  : 'fun'
  ;

Function
  : 'function'
  ;

Global
  : 'global'
  ;

If
  : 'if'
  ;

In
  : 'in'
  ;

Inherit
  : 'inherit'
  ;

Inline
  : 'inline'
  ;

Interface
  : 'interface'
  ;

Internal
  : 'internal'
  ;

Lazy
  : 'lazy'
  ;

Let
  : 'let'
  ;

Match
  : 'match'
  ;

Member
  : 'member'
  ;

Module
  : 'module'
  ;

Mutable
  : 'mutable'
  ;

Namespace
  : 'namespace'
  ;

New
  : 'new'
  ;

Null
  : 'null'
  ;

Of
  : 'of'
  ;

Open
  : 'open'
  ;

Or
  : 'or'
  ;

Override
  : 'override'
  ;

Private
  : 'private'
  ;

Public
  : 'public'
  ;

Rec
  : 'rec'
  ;

Return
  : 'return'
  ;

Sig
  : 'sig'
  ;

Static
  : 'static'
  ;

Struct
  : 'struct'
  ;

Then
  : 'then'
  ;

To
  : 'to'
  ;

True
  : 'true'
  ;

Try
  : 'try'
  ;

Type
  : 'type'
  ;

Upcast
  : 'upcast'
  ;

Use
  : 'use'
  ;

Val
  : 'val'
  ;

Void
  : 'void'
  ;

When
  : 'when'
  ;

While
  : 'while'
  ;

With
  : 'with'
  ;

Yield
  : 'yield'
  ;

Atomic
  : 'atomic'
  ;

Break
  : 'break'
  ;

Checked
  : 'checked'
  ;

Component
  : 'component'
  ;

Const
  : 'const'
  ;

Constraint
  : 'constraint'
  ;

Constructor
  : 'constructor'
  ;

Continue
  : 'continue'
  ;

Eager
  : 'eager'
  ;

Fixed
  : 'fixed'
  ;

Fori
  : 'fori'
  ;

Functor
  : 'functor'
  ;

Get
  : 'get'
  ;

Include
  : 'include'
  ;

Measure
  : 'measure'
  ;

Method
  : 'method'
  ;

Mixin
  : 'mixin'
  ;

Object
  : 'object'
  ;

Parallel
  : 'parallel'
  ;

Params
  : 'params'
  ;

Process
  : 'process'
  ;

Protected
  : 'protected'
  ;

Pure
  : 'pure'
  ;

Recursive
  : 'recursive'
  ;

Sealed
  : 'sealed'
  ;

Set
  : 'set'
  ;

Tailcall
  : 'tailcall'
  ;

Trait
  : 'trait'
  ;

Unit
  : 'unit'
  ;

Virtual
  : 'virtual'
  ;

Volatile
  : 'volatile'
  ;

/*
 * A.1.4.3 Symbolic Keywords
 */

LetE
  : 'let!'
  ;

UseE
  : 'use!'
  ;

DoE
  : 'do!'
  ;

YieldE
  : 'yield!'
  ;

ReturnE
  : 'return!'
  ;

Bar
  : '|'
  ;

RArrow
  : '->'
  ;

LArrow
  : '<-'
  ;

Dot
  : '.'
  ;

Colon
  : ':'
  ;

LParen
  : '('
  ;

RParen
  : ')'
  ;

LBrack
  : '['
  ;

RBrack
  : ']'
  ;

LBrackLess
  : '[<'
  ;

GreaterRBrack
  : '>]'
  ;

LBrackBar
  : '[|'
  ;

BarRBrack
  : '|]'
  ;

LBrace
  : '{'
  ;

RBrace
  : '}'
  ;

Quote
  : '\''
  ;

Hash
  : '#'
  ;

ColonQMarkGreater
  : ':?>'
  ;

ColonQMark
  : ':?'
  ;

ColonGreater
  : ':>'
  ;

DotDot
  : '..'
  ;

ColonColon
  : '::'
  ;

ColonEquals
  : ':='
  ;

SemicolonSemicolon
  : ';;'
  ;

Semicolon
  : ';'
  ;

Equals
  : '='
  ;

Underscore
  : '_'
  ;

Qmark
  : '?'
  ;

QmarkQmark
  : '??'
  ;

LParenStarRParen
  : '(*)'
  ;

LQuote
  : '<@'
  ;

RQuote
  : '@>'
  ;

LQuoteUntyped
  : '<@@'
  ;

RQuoteUntyped
  : '@@>'
  ;

Tilde
  : '~'
  ;

Backtick
  : '`'
  ;

/*
 * A.1.4.1 Identifiers
 */

fragment
One
  : '1'
  ;

fragment
DigitChar
  : '0'..'9'
  ;

fragment
LetterChar
  : '\u0041'..'\u005a'
  | '\u0061'..'\u007a'
  | '\u00aa'..'\u00aa'
  | '\u00b5'..'\u00b5'
  | '\u00ba'..'\u00ba'
  | '\u00c0'..'\u00d6'
  | '\u00d8'..'\u00f6'
  | '\u00f8'..'\u02c1'
  | '\u02c6'..'\u02d1'
  | '\u02e0'..'\u02e4'
  | '\u02ec'..'\u02ec'
  | '\u02ee'..'\u02ee'
  | '\u0370'..'\u0374'
  | '\u0376'..'\u0377'
  | '\u037a'..'\u037d'
  | '\u0386'..'\u0386'
  | '\u0388'..'\u038a'
  | '\u038c'..'\u038c'
  | '\u038e'..'\u03a1'
  | '\u03a3'..'\u03f5'
  | '\u03f7'..'\u0481'
  | '\u048a'..'\u0527'
  | '\u0531'..'\u0556'
  | '\u0559'..'\u0559'
  | '\u0561'..'\u0587'
  | '\u05d0'..'\u05ea'
  | '\u05f0'..'\u05f2'
  | '\u0620'..'\u064a'
  | '\u066e'..'\u066f'
  | '\u0671'..'\u06d3'
  | '\u06d5'..'\u06d5'
  | '\u06e5'..'\u06e6'
  | '\u06ee'..'\u06ef'
  | '\u06fa'..'\u06fc'
  | '\u06ff'..'\u06ff'
  | '\u0710'..'\u0710'
  | '\u0712'..'\u072f'
  | '\u074d'..'\u07a5'
  | '\u07b1'..'\u07b1'
  | '\u07ca'..'\u07ea'
  | '\u07f4'..'\u07f5'
  | '\u07fa'..'\u07fa'
  | '\u0800'..'\u0815'
  | '\u081a'..'\u081a'
  | '\u0824'..'\u0824'
  | '\u0828'..'\u0828'
  | '\u0840'..'\u0858'
  | '\u08a0'..'\u08a0'
  | '\u08a2'..'\u08ac'
  | '\u0904'..'\u0939'
  | '\u093d'..'\u093d'
  | '\u0950'..'\u0950'
  | '\u0958'..'\u0961'
  | '\u0971'..'\u0977'
  | '\u0979'..'\u097f'
  | '\u0985'..'\u098c'
  | '\u098f'..'\u0990'
  | '\u0993'..'\u09a8'
  | '\u09aa'..'\u09b0'
  | '\u09b2'..'\u09b2'
  | '\u09b6'..'\u09b9'
  | '\u09bd'..'\u09bd'
  | '\u09ce'..'\u09ce'
  | '\u09dc'..'\u09dd'
  | '\u09df'..'\u09e1'
  | '\u09f0'..'\u09f1'
  | '\u0a05'..'\u0a0a'
  | '\u0a0f'..'\u0a10'
  | '\u0a13'..'\u0a28'
  | '\u0a2a'..'\u0a30'
  | '\u0a32'..'\u0a33'
  | '\u0a35'..'\u0a36'
  | '\u0a38'..'\u0a39'
  | '\u0a59'..'\u0a5c'
  | '\u0a5e'..'\u0a5e'
  | '\u0a72'..'\u0a74'
  | '\u0a85'..'\u0a8d'
  | '\u0a8f'..'\u0a91'
  | '\u0a93'..'\u0aa8'
  | '\u0aaa'..'\u0ab0'
  | '\u0ab2'..'\u0ab3'
  | '\u0ab5'..'\u0ab9'
  | '\u0abd'..'\u0abd'
  | '\u0ad0'..'\u0ad0'
  | '\u0ae0'..'\u0ae1'
  | '\u0b05'..'\u0b0c'
  | '\u0b0f'..'\u0b10'
  | '\u0b13'..'\u0b28'
  | '\u0b2a'..'\u0b30'
  | '\u0b32'..'\u0b33'
  | '\u0b35'..'\u0b39'
  | '\u0b3d'..'\u0b3d'
  | '\u0b5c'..'\u0b5d'
  | '\u0b5f'..'\u0b61'
  | '\u0b71'..'\u0b71'
  | '\u0b83'..'\u0b83'
  | '\u0b85'..'\u0b8a'
  | '\u0b8e'..'\u0b90'
  | '\u0b92'..'\u0b95'
  | '\u0b99'..'\u0b9a'
  | '\u0b9c'..'\u0b9c'
  | '\u0b9e'..'\u0b9f'
  | '\u0ba3'..'\u0ba4'
  | '\u0ba8'..'\u0baa'
  | '\u0bae'..'\u0bb9'
  | '\u0bd0'..'\u0bd0'
  | '\u0c05'..'\u0c0c'
  | '\u0c0e'..'\u0c10'
  | '\u0c12'..'\u0c28'
  | '\u0c2a'..'\u0c33'
  | '\u0c35'..'\u0c39'
  | '\u0c3d'..'\u0c3d'
  | '\u0c58'..'\u0c59'
  | '\u0c60'..'\u0c61'
  | '\u0c85'..'\u0c8c'
  | '\u0c8e'..'\u0c90'
  | '\u0c92'..'\u0ca8'
  | '\u0caa'..'\u0cb3'
  | '\u0cb5'..'\u0cb9'
  | '\u0cbd'..'\u0cbd'
  | '\u0cde'..'\u0cde'
  | '\u0ce0'..'\u0ce1'
  | '\u0cf1'..'\u0cf2'
  | '\u0d05'..'\u0d0c'
  | '\u0d0e'..'\u0d10'
  | '\u0d12'..'\u0d3a'
  | '\u0d3d'..'\u0d3d'
  | '\u0d4e'..'\u0d4e'
  | '\u0d60'..'\u0d61'
  | '\u0d7a'..'\u0d7f'
  | '\u0d85'..'\u0d96'
  | '\u0d9a'..'\u0db1'
  | '\u0db3'..'\u0dbb'
  | '\u0dbd'..'\u0dbd'
  | '\u0dc0'..'\u0dc6'
  | '\u0e01'..'\u0e30'
  | '\u0e32'..'\u0e33'
  | '\u0e40'..'\u0e46'
  | '\u0e81'..'\u0e82'
  | '\u0e84'..'\u0e84'
  | '\u0e87'..'\u0e88'
  | '\u0e8a'..'\u0e8a'
  | '\u0e8d'..'\u0e8d'
  | '\u0e94'..'\u0e97'
  | '\u0e99'..'\u0e9f'
  | '\u0ea1'..'\u0ea3'
  | '\u0ea5'..'\u0ea5'
  | '\u0ea7'..'\u0ea7'
  | '\u0eaa'..'\u0eab'
  | '\u0ead'..'\u0eb0'
  | '\u0eb2'..'\u0eb3'
  | '\u0ebd'..'\u0ebd'
  | '\u0ec0'..'\u0ec4'
  | '\u0ec6'..'\u0ec6'
  | '\u0edc'..'\u0edf'
  | '\u0f00'..'\u0f00'
  | '\u0f40'..'\u0f47'
  | '\u0f49'..'\u0f6c'
  | '\u0f88'..'\u0f8c'
  | '\u1000'..'\u102a'
  | '\u103f'..'\u103f'
  | '\u1050'..'\u1055'
  | '\u105a'..'\u105d'
  | '\u1061'..'\u1061'
  | '\u1065'..'\u1066'
  | '\u106e'..'\u1070'
  | '\u1075'..'\u1081'
  | '\u108e'..'\u108e'
  | '\u10a0'..'\u10c5'
  | '\u10c7'..'\u10c7'
  | '\u10cd'..'\u10cd'
  | '\u10d0'..'\u10fa'
  | '\u10fc'..'\u1248'
  | '\u124a'..'\u124d'
  | '\u1250'..'\u1256'
  | '\u1258'..'\u1258'
  | '\u125a'..'\u125d'
  | '\u1260'..'\u1288'
  | '\u128a'..'\u128d'
  | '\u1290'..'\u12b0'
  | '\u12b2'..'\u12b5'
  | '\u12b8'..'\u12be'
  | '\u12c0'..'\u12c0'
  | '\u12c2'..'\u12c5'
  | '\u12c8'..'\u12d6'
  | '\u12d8'..'\u1310'
  | '\u1312'..'\u1315'
  | '\u1318'..'\u135a'
  | '\u1380'..'\u138f'
  | '\u13a0'..'\u13f4'
  | '\u1401'..'\u166c'
  | '\u166f'..'\u167f'
  | '\u1681'..'\u169a'
  | '\u16a0'..'\u16ea'
  | '\u16ee'..'\u16f0'
  | '\u1700'..'\u170c'
  | '\u170e'..'\u1711'
  | '\u1720'..'\u1731'
  | '\u1740'..'\u1751'
  | '\u1760'..'\u176c'
  | '\u176e'..'\u1770'
  | '\u1780'..'\u17b3'
  | '\u17d7'..'\u17d7'
  | '\u17dc'..'\u17dc'
  | '\u1820'..'\u1877'
  | '\u1880'..'\u18a8'
  | '\u18aa'..'\u18aa'
  | '\u18b0'..'\u18f5'
  | '\u1900'..'\u191c'
  | '\u1950'..'\u196d'
  | '\u1970'..'\u1974'
  | '\u1980'..'\u19ab'
  | '\u19c1'..'\u19c7'
  | '\u1a00'..'\u1a16'
  | '\u1a20'..'\u1a54'
  | '\u1aa7'..'\u1aa7'
  | '\u1b05'..'\u1b33'
  | '\u1b45'..'\u1b4b'
  | '\u1b83'..'\u1ba0'
  | '\u1bae'..'\u1baf'
  | '\u1bba'..'\u1be5'
  | '\u1c00'..'\u1c23'
  | '\u1c4d'..'\u1c4f'
  | '\u1c5a'..'\u1c7d'
  | '\u1ce9'..'\u1cec'
  | '\u1cee'..'\u1cf1'
  | '\u1cf5'..'\u1cf6'
  | '\u1d00'..'\u1dbf'
  | '\u1e00'..'\u1f15'
  | '\u1f18'..'\u1f1d'
  | '\u1f20'..'\u1f45'
  | '\u1f48'..'\u1f4d'
  | '\u1f50'..'\u1f57'
  | '\u1f59'..'\u1f59'
  | '\u1f5b'..'\u1f5b'
  | '\u1f5d'..'\u1f5d'
  | '\u1f5f'..'\u1f7d'
  | '\u1f80'..'\u1fb4'
  | '\u1fb6'..'\u1fbc'
  | '\u1fbe'..'\u1fbe'
  | '\u1fc2'..'\u1fc4'
  | '\u1fc6'..'\u1fcc'
  | '\u1fd0'..'\u1fd3'
  | '\u1fd6'..'\u1fdb'
  | '\u1fe0'..'\u1fec'
  | '\u1ff2'..'\u1ff4'
  | '\u1ff6'..'\u1ffc'
  | '\u2071'..'\u2071'
  | '\u207f'..'\u207f'
  | '\u2090'..'\u209c'
  | '\u2102'..'\u2102'
  | '\u2107'..'\u2107'
  | '\u210a'..'\u2113'
  | '\u2115'..'\u2115'
  | '\u2119'..'\u211d'
  | '\u2124'..'\u2124'
  | '\u2126'..'\u2126'
  | '\u2128'..'\u2128'
  | '\u212a'..'\u212d'
  | '\u212f'..'\u2139'
  | '\u213c'..'\u213f'
  | '\u2145'..'\u2149'
  | '\u214e'..'\u214e'
  | '\u2160'..'\u2188'
  | '\u2c00'..'\u2c2e'
  | '\u2c30'..'\u2c5e'
  | '\u2c60'..'\u2ce4'
  | '\u2ceb'..'\u2cee'
  | '\u2cf2'..'\u2cf3'
  | '\u2d00'..'\u2d25'
  | '\u2d27'..'\u2d27'
  | '\u2d2d'..'\u2d2d'
  | '\u2d30'..'\u2d67'
  | '\u2d6f'..'\u2d6f'
  | '\u2d80'..'\u2d96'
  | '\u2da0'..'\u2da6'
  | '\u2da8'..'\u2dae'
  | '\u2db0'..'\u2db6'
  | '\u2db8'..'\u2dbe'
  | '\u2dc0'..'\u2dc6'
  | '\u2dc8'..'\u2dce'
  | '\u2dd0'..'\u2dd6'
  | '\u2dd8'..'\u2dde'
  | '\u2e2f'..'\u2e2f'
  | '\u3005'..'\u3007'
  | '\u3021'..'\u3029'
  | '\u3031'..'\u3035'
  | '\u3038'..'\u303c'
  | '\u3041'..'\u3096'
  | '\u309d'..'\u309f'
  | '\u30a1'..'\u30fa'
  | '\u30fc'..'\u30ff'
  | '\u3105'..'\u312d'
  | '\u3131'..'\u318e'
  | '\u31a0'..'\u31ba'
  | '\u31f0'..'\u31ff'
  | '\ua000'..'\ua48c'
  | '\ua4d0'..'\ua4fd'
  | '\ua500'..'\ua60c'
  | '\ua610'..'\ua61f'
  | '\ua62a'..'\ua62b'
  | '\ua640'..'\ua66e'
  | '\ua67f'..'\ua697'
  | '\ua6a0'..'\ua6ef'
  | '\ua717'..'\ua71f'
  | '\ua722'..'\ua788'
  | '\ua78b'..'\ua78e'
  | '\ua790'..'\ua793'
  | '\ua7a0'..'\ua7aa'
  | '\ua7f8'..'\ua801'
  | '\ua803'..'\ua805'
  | '\ua807'..'\ua80a'
  | '\ua80c'..'\ua822'
  | '\ua840'..'\ua873'
  | '\ua882'..'\ua8b3'
  | '\ua8f2'..'\ua8f7'
  | '\ua8fb'..'\ua8fb'
  | '\ua90a'..'\ua925'
  | '\ua930'..'\ua946'
  | '\ua960'..'\ua97c'
  | '\ua984'..'\ua9b2'
  | '\ua9cf'..'\ua9cf'
  | '\uaa00'..'\uaa28'
  | '\uaa40'..'\uaa42'
  | '\uaa44'..'\uaa4b'
  | '\uaa60'..'\uaa76'
  | '\uaa7a'..'\uaa7a'
  | '\uaa80'..'\uaaaf'
  | '\uaab1'..'\uaab1'
  | '\uaab5'..'\uaab6'
  | '\uaab9'..'\uaabd'
  | '\uaac0'..'\uaac0'
  | '\uaac2'..'\uaac2'
  | '\uaadb'..'\uaadd'
  | '\uaae0'..'\uaaea'
  | '\uaaf2'..'\uaaf4'
  | '\uab01'..'\uab06'
  | '\uab09'..'\uab0e'
  | '\uab11'..'\uab16'
  | '\uab20'..'\uab26'
  | '\uab28'..'\uab2e'
  | '\uabc0'..'\uabe2'
  | '\ud7b0'..'\ud7c6'
  | '\ud7cb'..'\ud7fb'
  | '\uf900'..'\ufa6d'
  | '\ufa70'..'\ufad9'
  | '\ufb00'..'\ufb06'
  | '\ufb13'..'\ufb17'
  | '\ufb1d'..'\ufb1d'
  | '\ufb1f'..'\ufb28'
  | '\ufb2a'..'\ufb36'
  | '\ufb38'..'\ufb3c'
  | '\ufb3e'..'\ufb3e'
  | '\ufb40'..'\ufb41'
  | '\ufb43'..'\ufb44'
  | '\ufb46'..'\ufbb1'
  | '\ufbd3'..'\ufd3d'
  | '\ufd50'..'\ufd8f'
  | '\ufd92'..'\ufdc7'
  | '\ufdf0'..'\ufdfb'
  | '\ufe70'..'\ufe74'
  | '\ufe76'..'\ufefc'
  | '\uff21'..'\uff3a'
  | '\uff41'..'\uff5a'
  | '\uff66'..'\uffbe'
  | '\uffc2'..'\uffc7'
  | '\uffca'..'\uffcf'
  | '\uffd2'..'\uffd7'
  | '\uffda'..'\uffdc'
/*
  | '\u10000'..'\u1000b'
  | '\u1000d'..'\u10026'
  | '\u10028'..'\u1003a'
  | '\u1003c'..'\u1003d'
  | '\u1003f'..'\u1004d'
  | '\u10050'..'\u1005d'
  | '\u10080'..'\u100fa'
  | '\u10140'..'\u10174'
  | '\u10280'..'\u1029c'
  | '\u102a0'..'\u102d0'
  | '\u10300'..'\u1031e'
  | '\u10330'..'\u1034a'
  | '\u10380'..'\u1039d'
  | '\u103a0'..'\u103c3'
  | '\u103c8'..'\u103cf'
  | '\u103d1'..'\u103d5'
  | '\u10400'..'\u1049d'
  | '\u10800'..'\u10805'
  | '\u10808'..'\u10808'
  | '\u1080a'..'\u10835'
  | '\u10837'..'\u10838'
  | '\u1083c'..'\u1083c'
  | '\u1083f'..'\u10855'
  | '\u10900'..'\u10915'
  | '\u10920'..'\u10939'
  | '\u10980'..'\u109b7'
  | '\u109be'..'\u109bf'
  | '\u10a00'..'\u10a00'
  | '\u10a10'..'\u10a13'
  | '\u10a15'..'\u10a17'
  | '\u10a19'..'\u10a33'
  | '\u10a60'..'\u10a7c'
  | '\u10b00'..'\u10b35'
  | '\u10b40'..'\u10b55'
  | '\u10b60'..'\u10b72'
  | '\u10c00'..'\u10c48'
  | '\u11003'..'\u11037'
  | '\u11083'..'\u110af'
  | '\u110d0'..'\u110e8'
  | '\u11103'..'\u11126'
  | '\u11183'..'\u111b2'
  | '\u111c1'..'\u111c4'
  | '\u11680'..'\u116aa'
  | '\u12000'..'\u1236e'
  | '\u12400'..'\u12462'
  | '\u13000'..'\u1342e'
  | '\u16800'..'\u16a38'
  | '\u16f00'..'\u16f44'
  | '\u16f50'..'\u16f50'
  | '\u16f93'..'\u16f9f'
  | '\u1b000'..'\u1b001'
  | '\u1d400'..'\u1d454'
  | '\u1d456'..'\u1d49c'
  | '\u1d49e'..'\u1d49f'
  | '\u1d4a2'..'\u1d4a2'
  | '\u1d4a5'..'\u1d4a6'
  | '\u1d4a9'..'\u1d4ac'
  | '\u1d4ae'..'\u1d4b9'
  | '\u1d4bb'..'\u1d4bb'
  | '\u1d4bd'..'\u1d4c3'
  | '\u1d4c5'..'\u1d505'
  | '\u1d507'..'\u1d50a'
  | '\u1d50d'..'\u1d514'
  | '\u1d516'..'\u1d51c'
  | '\u1d51e'..'\u1d539'
  | '\u1d53b'..'\u1d53e'
  | '\u1d540'..'\u1d544'
  | '\u1d546'..'\u1d546'
  | '\u1d54a'..'\u1d550'
  | '\u1d552'..'\u1d6a5'
  | '\u1d6a8'..'\u1d6c0'
  | '\u1d6c2'..'\u1d6da'
  | '\u1d6dc'..'\u1d6fa'
  | '\u1d6fc'..'\u1d714'
  | '\u1d716'..'\u1d734'
  | '\u1d736'..'\u1d74e'
  | '\u1d750'..'\u1d76e'
  | '\u1d770'..'\u1d788'
  | '\u1d78a'..'\u1d7a8'
  | '\u1d7aa'..'\u1d7c2'
  | '\u1d7c4'..'\u1d7cb'
  | '\u1ee00'..'\u1ee03'
  | '\u1ee05'..'\u1ee1f'
  | '\u1ee21'..'\u1ee22'
  | '\u1ee24'..'\u1ee24'
  | '\u1ee27'..'\u1ee27'
  | '\u1ee29'..'\u1ee32'
  | '\u1ee34'..'\u1ee37'
  | '\u1ee39'..'\u1ee39'
  | '\u1ee3b'..'\u1ee3b'
  | '\u1ee42'..'\u1ee42'
  | '\u1ee47'..'\u1ee47'
  | '\u1ee49'..'\u1ee49'
  | '\u1ee4b'..'\u1ee4b'
  | '\u1ee4d'..'\u1ee4f'
  | '\u1ee51'..'\u1ee52'
  | '\u1ee54'..'\u1ee54'
  | '\u1ee57'..'\u1ee57'
  | '\u1ee59'..'\u1ee59'
  | '\u1ee5b'..'\u1ee5b'
  | '\u1ee5d'..'\u1ee5d'
  | '\u1ee5f'..'\u1ee5f'
  | '\u1ee61'..'\u1ee62'
  | '\u1ee64'..'\u1ee64'
  | '\u1ee67'..'\u1ee6a'
  | '\u1ee6c'..'\u1ee72'
  | '\u1ee74'..'\u1ee77'
  | '\u1ee79'..'\u1ee7c'
  | '\u1ee7e'..'\u1ee7e'
  | '\u1ee80'..'\u1ee89'
  | '\u1ee8b'..'\u1ee9b'
  | '\u1eea1'..'\u1eea3'
  | '\u1eea5'..'\u1eea9'
  | '\u1eeab'..'\u1eebb'
*/
  ;

fragment
ConnectingChar
  : '\u005f'
  | '\u203f'..'\u2040'
  | '\u2054'
  | '\ufe33'..'\ufe34'
  | '\ufe4d'..'\ufe4f'
  ;

fragment
CombiningChar
  : '\u0300'..'\u036f'
  | '\u0483'..'\u0487'
  | '\u0591'..'\u05bd'
  | '\u05bf'..'\u05bf'
  | '\u05c1'..'\u05c2'
  | '\u05c4'..'\u05c5'
  | '\u05c7'..'\u05c7'
  | '\u0610'..'\u061a'
  | '\u064b'..'\u065f'
  | '\u0670'..'\u0670'
  | '\u06d6'..'\u06dc'
  | '\u06df'..'\u06e4'
  | '\u06e7'..'\u06e8'
  | '\u06ea'..'\u06ed'
  | '\u0711'..'\u0711'
  | '\u0730'..'\u074a'
  | '\u07a6'..'\u07b0'
  | '\u07eb'..'\u07f3'
  | '\u0816'..'\u0819'
  | '\u081b'..'\u0823'
  | '\u0825'..'\u0827'
  | '\u0829'..'\u082d'
  | '\u0859'..'\u085b'
  | '\u08e4'..'\u08fe'
  | '\u0900'..'\u0903'
  | '\u093a'..'\u093c'
  | '\u093e'..'\u094f'
  | '\u0951'..'\u0957'
  | '\u0962'..'\u0963'
  | '\u0981'..'\u0983'
  | '\u09bc'..'\u09bc'
  | '\u09be'..'\u09c4'
  | '\u09c7'..'\u09c8'
  | '\u09cb'..'\u09cd'
  | '\u09d7'..'\u09d7'
  | '\u09e2'..'\u09e3'
  | '\u0a01'..'\u0a03'
  | '\u0a3c'..'\u0a3c'
  | '\u0a3e'..'\u0a42'
  | '\u0a47'..'\u0a48'
  | '\u0a4b'..'\u0a4d'
  | '\u0a51'..'\u0a51'
  | '\u0a70'..'\u0a71'
  | '\u0a75'..'\u0a75'
  | '\u0a81'..'\u0a83'
  | '\u0abc'..'\u0abc'
  | '\u0abe'..'\u0ac5'
  | '\u0ac7'..'\u0ac9'
  | '\u0acb'..'\u0acd'
  | '\u0ae2'..'\u0ae3'
  | '\u0b01'..'\u0b03'
  | '\u0b3c'..'\u0b3c'
  | '\u0b3e'..'\u0b44'
  | '\u0b47'..'\u0b48'
  | '\u0b4b'..'\u0b4d'
  | '\u0b56'..'\u0b57'
  | '\u0b62'..'\u0b63'
  | '\u0b82'..'\u0b82'
  | '\u0bbe'..'\u0bc2'
  | '\u0bc6'..'\u0bc8'
  | '\u0bca'..'\u0bcd'
  | '\u0bd7'..'\u0bd7'
  | '\u0c01'..'\u0c03'
  | '\u0c3e'..'\u0c44'
  | '\u0c46'..'\u0c48'
  | '\u0c4a'..'\u0c4d'
  | '\u0c55'..'\u0c56'
  | '\u0c62'..'\u0c63'
  | '\u0c82'..'\u0c83'
  | '\u0cbc'..'\u0cbc'
  | '\u0cbe'..'\u0cc4'
  | '\u0cc6'..'\u0cc8'
  | '\u0cca'..'\u0ccd'
  | '\u0cd5'..'\u0cd6'
  | '\u0ce2'..'\u0ce3'
  | '\u0d02'..'\u0d03'
  | '\u0d3e'..'\u0d44'
  | '\u0d46'..'\u0d48'
  | '\u0d4a'..'\u0d4d'
  | '\u0d57'..'\u0d57'
  | '\u0d62'..'\u0d63'
  | '\u0d82'..'\u0d83'
  | '\u0dca'..'\u0dca'
  | '\u0dcf'..'\u0dd4'
  | '\u0dd6'..'\u0dd6'
  | '\u0dd8'..'\u0ddf'
  | '\u0df2'..'\u0df3'
  | '\u0e31'..'\u0e31'
  | '\u0e34'..'\u0e3a'
  | '\u0e47'..'\u0e4e'
  | '\u0eb1'..'\u0eb1'
  | '\u0eb4'..'\u0eb9'
  | '\u0ebb'..'\u0ebc'
  | '\u0ec8'..'\u0ecd'
  | '\u0f18'..'\u0f19'
  | '\u0f35'..'\u0f35'
  | '\u0f37'..'\u0f37'
  | '\u0f39'..'\u0f39'
  | '\u0f3e'..'\u0f3f'
  | '\u0f71'..'\u0f84'
  | '\u0f86'..'\u0f87'
  | '\u0f8d'..'\u0f97'
  | '\u0f99'..'\u0fbc'
  | '\u0fc6'..'\u0fc6'
  | '\u102b'..'\u103e'
  | '\u1056'..'\u1059'
  | '\u105e'..'\u1060'
  | '\u1062'..'\u1064'
  | '\u1067'..'\u106d'
  | '\u1071'..'\u1074'
  | '\u1082'..'\u108d'
  | '\u108f'..'\u108f'
  | '\u109a'..'\u109d'
  | '\u135d'..'\u135f'
  | '\u1712'..'\u1714'
  | '\u1732'..'\u1734'
  | '\u1752'..'\u1753'
  | '\u1772'..'\u1773'
  | '\u17b4'..'\u17d3'
  | '\u17dd'..'\u17dd'
  | '\u180b'..'\u180d'
  | '\u18a9'..'\u18a9'
  | '\u1920'..'\u192b'
  | '\u1930'..'\u193b'
  | '\u19b0'..'\u19c0'
  | '\u19c8'..'\u19c9'
  | '\u1a17'..'\u1a1b'
  | '\u1a55'..'\u1a5e'
  | '\u1a60'..'\u1a7c'
  | '\u1a7f'..'\u1a7f'
  | '\u1b00'..'\u1b04'
  | '\u1b34'..'\u1b44'
  | '\u1b6b'..'\u1b73'
  | '\u1b80'..'\u1b82'
  | '\u1ba1'..'\u1bad'
  | '\u1be6'..'\u1bf3'
  | '\u1c24'..'\u1c37'
  | '\u1cd0'..'\u1cd2'
  | '\u1cd4'..'\u1ce8'
  | '\u1ced'..'\u1ced'
  | '\u1cf2'..'\u1cf4'
  | '\u1dc0'..'\u1de6'
  | '\u1dfc'..'\u1dff'
  | '\u20d0'..'\u20dc'
  | '\u20e1'..'\u20e1'
  | '\u20e5'..'\u20f0'
  | '\u2cef'..'\u2cf1'
  | '\u2d7f'..'\u2d7f'
  | '\u2de0'..'\u2dff'
  | '\u302a'..'\u302f'
  | '\u3099'..'\u309a'
  | '\ua66f'..'\ua66f'
  | '\ua674'..'\ua67d'
  | '\ua69f'..'\ua69f'
  | '\ua6f0'..'\ua6f1'
  | '\ua802'..'\ua802'
  | '\ua806'..'\ua806'
  | '\ua80b'..'\ua80b'
  | '\ua823'..'\ua827'
  | '\ua880'..'\ua881'
  | '\ua8b4'..'\ua8c4'
  | '\ua8e0'..'\ua8f1'
  | '\ua926'..'\ua92d'
  | '\ua947'..'\ua953'
  | '\ua980'..'\ua983'
  | '\ua9b3'..'\ua9c0'
  | '\uaa29'..'\uaa36'
  | '\uaa43'..'\uaa43'
  | '\uaa4c'..'\uaa4d'
  | '\uaa7b'..'\uaa7b'
  | '\uaab0'..'\uaab0'
  | '\uaab2'..'\uaab4'
  | '\uaab7'..'\uaab8'
  | '\uaabe'..'\uaabf'
  | '\uaac1'..'\uaac1'
  | '\uaaeb'..'\uaaef'
  | '\uaaf5'..'\uaaf6'
  | '\uabe3'..'\uabea'
  | '\uabec'..'\uabed'
  | '\ufb1e'..'\ufb1e'
  | '\ufe00'..'\ufe0f'
  | '\ufe20'..'\ufe26'
/*
  | '\u101fd'..'\u101fd'
  | '\u10a01'..'\u10a03'
  | '\u10a05'..'\u10a06'
  | '\u10a0c'..'\u10a0f'
  | '\u10a38'..'\u10a3a'
  | '\u10a3f'..'\u10a3f'
  | '\u11000'..'\u11002'
  | '\u11038'..'\u11046'
  | '\u11080'..'\u11082'
  | '\u110b0'..'\u110ba'
  | '\u11100'..'\u11102'
  | '\u11127'..'\u11134'
  | '\u11180'..'\u11182'
  | '\u111b3'..'\u111c0'
  | '\u116ab'..'\u116b7'
  | '\u16f51'..'\u16f7e'
  | '\u16f8f'..'\u16f92'
  | '\u1d165'..'\u1d169'
  | '\u1d16d'..'\u1d172'
  | '\u1d17b'..'\u1d182'
  | '\u1d185'..'\u1d18b'
  | '\u1d1aa'..'\u1d1ad'
  | '\u1d242'..'\u1d244'
*/
  ;

fragment
FormattingChar
  : '\u00ad'
  | '\u0600'..'\u0604'
  | '\u06dd'
  | '\u070f'
  | '\u200b'..'\u200f'
  | '\u202a'..'\u202e'
  | '\u2060'..'\u2064'
  | '\u206a'..'\u206f'
  | '\ufeff'
  | '\ufff9'..'\ufffb'
/*
  | '\u110bd'..'\u110bd'
  | '\u1d173'..'\u1d17a'
  | '\ue0001'..'\ue0001'
*/
  ;

fragment
IdentUnderscore
  : '_'
  ;

fragment
IdentStartChar
  : LetterChar
  | IdentUnderscore
  ;

fragment
IdentChar
  : LetterChar
  | DigitChar
  | ConnectingChar
  | CombiningChar
  | FormattingChar
  | '\''
  | IdentUnderscore
  ;

fragment
IdentText
  : IdentStartChar IdentChar*
  ;

Ident
  : IdentText
  | '``' ( ~('`' | '\n' | '\r' | '\t') | '`' ~('`' | '\n' | '\r' | '\t') )+ '``'
  ;

/*
 * A.1.5 Strings and Characters
 */

fragment
EscapeChar
  : '\\' ('"' | '\'' | 'n' | 't' | 'b' | 'r')
  ;

fragment
NonEscapeChars
  : '\\' ~('"' | '\'' | 'n' | 't' | 'b' | 'r')
  ;

fragment
SimpleCharChar
  : ~('\n' | '\t' | '\r' | '\b' | '\'' | '\\' | '"')
  ;

fragment
UnicodegraphShort
  : '\\' 'u' Hexdigit Hexdigit Hexdigit Hexdigit
  ;

fragment
UnicodegraphLong
  : '\\' 'U' Hexdigit Hexdigit Hexdigit Hexdigit Hexdigit Hexdigit Hexdigit Hexdigit
  ;

fragment
Trigraph
  : '\\' DigitChar DigitChar DigitChar
  ;

fragment
CharChar
  : (SimpleCharChar | EscapeChar | Trigraph | UnicodegraphShort)
  ;

fragment
StringChar
  : (SimpleCharChar | EscapeChar | NonEscapeChars | Trigraph | UnicodegraphShort | UnicodegraphLong | NewLine)
  ;

/*
StringElem
  : StringChar '\\' NewLine Whitespace* StringElem
  ;
*/

Char
  : '\'' CharChar '\''
  ;

String
  : '"' StringChar* '"'
  ;

fragment
VerbatimStringChar
  : (SimpleCharChar | NonEscapeChars | NewLine | '\\' | '""')
  ;

VerbatimString
  : '@"' VerbatimStringChar* '"'
  ;

Bytechar
  : '\'' SimpleOrEscapeChar* '\'B'
  ;

Bytearray
  : '"' SimpleOrEscapeChar* '"B'
  ;

VerbatimBytearray
  : '@"' SimpleOrEscapeChar* '"B'
  ;

fragment
SimpleOrEscapeChar
  : (EscapeChar | SimpleChar)
  ;

fragment
SimpleChar
  : ~('\n' | '\t' | '\r' | '\b' | '\'' | '\\' | '"')
  ;

TripleQuotedString
 : '"""' SimpleOrEscapeChar* '"""'
 ;

/*
 * A.1.6 Numeric Literals
 */

fragment
Digit
  : '0'..'9'
  ;

fragment
Hexdigit
  : Digit
  | 'A'..'F'
  | 'a'..'f'
  ;

fragment
Octaldigit
  : '0'..'7'
  ;

fragment
Bitdigit
  : '0'..'1'
  ;

fragment
Int
  : Digit+
  ;

fragment
Xint
  : '0' ('x' | 'X') Hexdigit+
  | '0' ('o' | 'O') Octaldigit+
  | '0' ('b' | 'B') Bitdigit+
  ;

Sbyte
  : (Int | Xint) 'y'
  ;

Byte
  : (Int | Xint) 'uy'
  ;

Int16
  : (Int | Xint) 's'
  ;

Uint16
  : (Int | Xint) 'us'
  ;

Int32
  : (Int | Xint) 'l'?
  ;

Uint32
  : (Int | Xint) 'ul'
  ;

NativeInt
  : (Int | Xint) 'n'
  ;

NativeUint
  : (Int | Xint) 'un'
  ;

Int64
  : (Int | Xint) 'L'
  ;

Uint64
  : (Int | Xint) 'UL'
  | (Int | Xint) 'uL'
  ;

Ieee32
  : Float ('F' | 'f')
  | Xint 'lf'
  ;

Ieee64
  : Float
  | Xint 'LF'
  ;

Bignum
  : Int ('Q' | 'R' | 'Z' | 'I' | 'N' | 'G')
  ;

Decimal
  : ( Float | Int ) ('M' | 'm')
  ;

fragment
Float
  : Digit+ Dot Digit+
  | Digit+ ( Dot Digit* )? ('e' | 'E') ('+' | '-')? Digit+
  ;

/*
 * A.1.7 Line Directives
 */

fragment
LineDirective
  : Hash Int
  | Hash Int String
  | Hash Int VerbatimString
  | '#line' Int
  | '#line' Int String
  | '#line' Int VerbatimString
  ;

/*
 * A.1.9 Operators
 */

/*
 * A.1.9.1 Operator Names
 */

identOrOp
  : Ident
  | '(' OpName ')'
  | '(*)'
  ;

fragment
OpName
  : SymbolicOp
/*
  | RangeOpName
*/
  | ActivePatternOpName
  ;

fragment
RangeOpName
  : '..'
  | '..' '..'
  ;

fragment
ActivePatternOpName
  : '|' Ident '|' (Ident '|')*
  | '|' Ident '|' (Ident '|')* '|' '_' '|'
  ;

/*
 * A.1.9.2 Symbolic Operators
 */

fragment
FirstOpChar
  : '!'
  | '%'
  | '&'
  | '*'
  | '+'
  | '-'
  | '.'
  | '/'
  | '<'
  | Equals
  | '>'
  | '@'
  | '^'
  | '|'
  | '~'
  ;

fragment
OpChar
  : FirstOpChar
  | '?'
  ;

/*
fragment
QuoteOpLeft
  : '<@'
  | '<@@'
  ;

fragment
QuoteOpRight
  : '@>'
  | '@@>'
  ;
*/

fragment
SymbolicOp
  : '?'
  | '?<-'
/*
  | QuoteOpLeft
  | QuoteOpRight
*/
  | FirstOpChar OpChar*
  ;

/*
 * A.1.9.3 Infix and prefix operators
 */

fragment
InfixOrPrefixOp
  : '+'
  | '-'
  | '+.'
  | '-.'
  | '%'
  | '&'
  | '&&'
  ;

fragment
OP
  : SymbolicOp
  ;

prefixOp
  : InfixOrPrefixOp
  | '~'
  | '~~'
  | '~~~'
  | (~('!='))=> '!' OP
  ;

infixOp
  : InfixOrPrefixOp
  | (~('-.'))=> '-' OP
  | (~('+.'))=> '+' OP
  | '||'
  | '<' OP
  | '>' OP
  | Equals
  | (~('|='))=> '|' OP
  | (~('&&'))=> '&' OP
  | '^' OP
  | '*' OP
  | '/' OP
  | '%' OP
  | '!='
  | ':='
  | '::'
  | '$'
  | 'or'
  | '?'
  ;

/*
 * A.1.9.4 Constants
 */

constant returns [Const n]
  : Sbyte              { $n = new Const($Sbyte.text);              }
  | Int16              { $n = new Const($Int16.text);              }
  | Int32              { $n = new Const($Int32.text);              }
  | Int64              { $n = new Const($Int64.text);              }
  | Byte               { $n = new Const($Byte.text);               }
  | Uint16             { $n = new Const($Uint16.text);             }
  | Uint32             { $n = new Const($Uint32.text);             }
  | Uint64             { $n = new Const($Uint64.text);             }
  | Ieee32             { $n = new Const($Ieee32.text);             }
  | Ieee64             { $n = new Const($Ieee64.text);             }
  | Bignum             { $n = new Const($Bignum.text);             }
  | Char               { $n = new Const($Char.text);               }
  | String             { $n = new Const($String.text);             }
  | VerbatimString     { $n = new Const($VerbatimString.text);     }
  | TripleQuotedString { $n = new Const($TripleQuotedString.text); }
  | Bytearray          { $n = new Const($Bytearray.text);          }
  | VerbatimBytearray  { $n = new Const($VerbatimBytearray.text);  }
  | Bytechar           { $n = new Const($Bytechar.text);           }
  | False              { $n = new Const($False.text);              }
  | True               { $n = new Const($True.text);               }
  | '()'               { $n = new Const("()");                     }
  | Sbyte '<' measureLiteral '>'
  | Int16 '<' measureLiteral '>'
  | Int32 '<' measureLiteral '>'
  | Int64 '<' measureLiteral '>'
  | Ieee32 '<' measureLiteral '>'
  | Ieee64 '<' measureLiteral '>'
  | Decimal '<' measureLiteral '>'
  ;
