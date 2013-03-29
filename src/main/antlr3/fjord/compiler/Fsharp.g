/*
 * F# grammar as defined in Appendix A ("F# Grammar Summary") of the F# 3.0
 * Language Specification.
 */

grammar Fsharp;

options {
  language = Java;
}

@header {
  package fjord.compiler;

  import fjord.ast.*;
}

@lexer::header {
  package fjord.compiler;

  import fjord.ast.*;
}

scriptFragment returns [Node n]
  : c = moduleElem { $n = c; }
  ;

moduleElem returns [Node n]
  : compilerDirectiveDecl { $n = $compilerDirectiveDecl.n; }
  ;

compilerDirectiveDecl returns [CompilerDirectiveDecl n]
  : '#' IdentText { $n = new CompilerDirectiveDecl($IdentText.text); }
  ;

/*
 * A.1 Lexical Grammar
 */

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
  : Whitespace | NewLine { skip(); }
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

Tailcall
  : 'tailcall'
  ;

Trait
  : 'trait'
  ;

Virtual
  : 'virtual'
  ;

Volatile
  : 'volatile'
  ;

/*
 * A.1.4.1 Identifiers
 */

fragment
DigitChar
  : '0'..'9'
  ;

fragment
LetterChar
  : '\u0041'..'\u005a'
  | '\u005a'..'\u007a'
  | '\u007a'..'\u00aa'
  | '\u00aa'..'\u00b5'
  | '\u00b5'..'\u00ba'
  | '\u00ba'..'\u00d6'
  | '\u00d6'..'\u00f6'
  | '\u00f6'..'\u02c1'
  | '\u02c1'..'\u02d1'
  | '\u02d1'..'\u02e4'
  | '\u02e4'..'\u02ec'
  | '\u02ec'..'\u02ee'
  | '\u02ee'..'\u0374'
  | '\u0374'..'\u0377'
  | '\u0377'..'\u037d'
  | '\u037d'..'\u0386'
  | '\u0386'..'\u038a'
  | '\u038a'..'\u038c'
  | '\u038c'..'\u03a1'
  | '\u03a1'..'\u03f5'
  | '\u03f5'..'\u0481'
  | '\u0481'..'\u0527'
  | '\u0527'..'\u0556'
  | '\u0556'..'\u0559'
  | '\u0559'..'\u0587'
  | '\u0587'..'\u05ea'
  | '\u05ea'..'\u05f2'
  | '\u05f2'..'\u064a'
  | '\u064a'..'\u066f'
  | '\u066f'..'\u06d3'
  | '\u06d3'..'\u06d5'
  | '\u06d5'..'\u06e6'
  | '\u06e6'..'\u06ef'
  | '\u06ef'..'\u06fc'
  | '\u06fc'..'\u06ff'
  | '\u06ff'..'\u0710'
  | '\u0710'..'\u072f'
  | '\u072f'..'\u07a5'
  | '\u07a5'..'\u07b1'
  | '\u07b1'..'\u07ea'
  | '\u07ea'..'\u07f5'
  | '\u07f5'..'\u07fa'
  | '\u07fa'..'\u0815'
  | '\u0815'..'\u081a'
  | '\u081a'..'\u0824'
  | '\u0824'..'\u0828'
  | '\u0828'..'\u0858'
  | '\u0858'..'\u08a0'
  | '\u08a0'..'\u08ac'
  | '\u08ac'..'\u0939'
  | '\u0939'..'\u093d'
  | '\u093d'..'\u0950'
  | '\u0950'..'\u0961'
  | '\u0961'..'\u0977'
  | '\u0977'..'\u097f'
  | '\u097f'..'\u098c'
  | '\u098c'..'\u0990'
  | '\u0990'..'\u09a8'
  | '\u09a8'..'\u09b0'
  | '\u09b0'..'\u09b2'
  | '\u09b2'..'\u09b9'
  | '\u09b9'..'\u09bd'
  | '\u09bd'..'\u09ce'
  | '\u09ce'..'\u09dd'
  | '\u09dd'..'\u09e1'
  | '\u09e1'..'\u09f1'
  | '\u09f1'..'\u0a0a'
  | '\u0a0a'..'\u0a10'
  | '\u0a10'..'\u0a28'
  | '\u0a28'..'\u0a30'
  | '\u0a30'..'\u0a33'
  | '\u0a33'..'\u0a36'
  | '\u0a36'..'\u0a39'
  | '\u0a39'..'\u0a5c'
  | '\u0a5c'..'\u0a5e'
  | '\u0a5e'..'\u0a74'
  | '\u0a74'..'\u0a8d'
  | '\u0a8d'..'\u0a91'
  | '\u0a91'..'\u0aa8'
  | '\u0aa8'..'\u0ab0'
  | '\u0ab0'..'\u0ab3'
  | '\u0ab3'..'\u0ab9'
  | '\u0ab9'..'\u0abd'
  | '\u0abd'..'\u0ad0'
  | '\u0ad0'..'\u0ae1'
  | '\u0ae1'..'\u0b0c'
  | '\u0b0c'..'\u0b10'
  | '\u0b10'..'\u0b28'
  | '\u0b28'..'\u0b30'
  | '\u0b30'..'\u0b33'
  | '\u0b33'..'\u0b39'
  | '\u0b39'..'\u0b3d'
  | '\u0b3d'..'\u0b5d'
  | '\u0b5d'..'\u0b61'
  | '\u0b61'..'\u0b71'
  | '\u0b71'..'\u0b83'
  | '\u0b83'..'\u0b8a'
  | '\u0b8a'..'\u0b90'
  | '\u0b90'..'\u0b95'
  | '\u0b95'..'\u0b9a'
  | '\u0b9a'..'\u0b9c'
  | '\u0b9c'..'\u0b9f'
  | '\u0b9f'..'\u0ba4'
  | '\u0ba4'..'\u0baa'
  | '\u0baa'..'\u0bb9'
  | '\u0bb9'..'\u0bd0'
  | '\u0bd0'..'\u0c0c'
  | '\u0c0c'..'\u0c10'
  | '\u0c10'..'\u0c28'
  | '\u0c28'..'\u0c33'
  | '\u0c33'..'\u0c39'
  | '\u0c39'..'\u0c3d'
  | '\u0c3d'..'\u0c59'
  | '\u0c59'..'\u0c61'
  | '\u0c61'..'\u0c8c'
  | '\u0c8c'..'\u0c90'
  | '\u0c90'..'\u0ca8'
  | '\u0ca8'..'\u0cb3'
  | '\u0cb3'..'\u0cb9'
  | '\u0cb9'..'\u0cbd'
  | '\u0cbd'..'\u0cde'
  | '\u0cde'..'\u0ce1'
  | '\u0ce1'..'\u0cf2'
  | '\u0cf2'..'\u0d0c'
  | '\u0d0c'..'\u0d10'
  | '\u0d10'..'\u0d3a'
  | '\u0d3a'..'\u0d3d'
  | '\u0d3d'..'\u0d4e'
  | '\u0d4e'..'\u0d61'
  | '\u0d61'..'\u0d7f'
  | '\u0d7f'..'\u0d96'
  | '\u0d96'..'\u0db1'
  | '\u0db1'..'\u0dbb'
  | '\u0dbb'..'\u0dbd'
  | '\u0dbd'..'\u0dc6'
  | '\u0dc6'..'\u0e30'
  | '\u0e30'..'\u0e33'
  | '\u0e33'..'\u0e46'
  | '\u0e46'..'\u0e82'
  | '\u0e82'..'\u0e84'
  | '\u0e84'..'\u0e88'
  | '\u0e88'..'\u0e8a'
  | '\u0e8a'..'\u0e8d'
  | '\u0e8d'..'\u0e97'
  | '\u0e97'..'\u0e9f'
  | '\u0e9f'..'\u0ea3'
  | '\u0ea3'..'\u0ea5'
  | '\u0ea5'..'\u0ea7'
  | '\u0ea7'..'\u0eab'
  | '\u0eab'..'\u0eb0'
  | '\u0eb0'..'\u0eb3'
  | '\u0eb3'..'\u0ebd'
  | '\u0ebd'..'\u0ec4'
  | '\u0ec4'..'\u0ec6'
  | '\u0ec6'..'\u0edf'
  | '\u0edf'..'\u0f00'
  | '\u0f00'..'\u0f47'
  | '\u0f47'..'\u0f6c'
  | '\u0f6c'..'\u0f8c'
  | '\u0f8c'..'\u102a'
  | '\u102a'..'\u103f'
  | '\u103f'..'\u1055'
  | '\u1055'..'\u105d'
  | '\u105d'..'\u1061'
  | '\u1061'..'\u1066'
  | '\u1066'..'\u1070'
  | '\u1070'..'\u1081'
  | '\u1081'..'\u108e'
  | '\u108e'..'\u10c5'
  | '\u10c5'..'\u10c7'
  | '\u10c7'..'\u10cd'
  | '\u10cd'..'\u10fa'
  | '\u10fa'..'\u1248'
  | '\u1248'..'\u124d'
  | '\u124d'..'\u1256'
  | '\u1256'..'\u1258'
  | '\u1258'..'\u125d'
  | '\u125d'..'\u1288'
  | '\u1288'..'\u128d'
  | '\u128d'..'\u12b0'
  | '\u12b0'..'\u12b5'
  | '\u12b5'..'\u12be'
  | '\u12be'..'\u12c0'
  | '\u12c0'..'\u12c5'
  | '\u12c5'..'\u12d6'
  | '\u12d6'..'\u1310'
  | '\u1310'..'\u1315'
  | '\u1315'..'\u135a'
  | '\u135a'..'\u138f'
  | '\u138f'..'\u13f4'
  | '\u13f4'..'\u166c'
  | '\u166c'..'\u167f'
  | '\u167f'..'\u169a'
  | '\u169a'..'\u16ea'
  | '\u16ea'..'\u16f0'
  | '\u16f0'..'\u170c'
  | '\u170c'..'\u1711'
  | '\u1711'..'\u1731'
  | '\u1731'..'\u1751'
  | '\u1751'..'\u176c'
  | '\u176c'..'\u1770'
  | '\u1770'..'\u17b3'
  | '\u17b3'..'\u17d7'
  | '\u17d7'..'\u17dc'
  | '\u17dc'..'\u1877'
  | '\u1877'..'\u18a8'
  | '\u18a8'..'\u18aa'
  | '\u18aa'..'\u18f5'
  | '\u18f5'..'\u191c'
  | '\u191c'..'\u196d'
  | '\u196d'..'\u1974'
  | '\u1974'..'\u19ab'
  | '\u19ab'..'\u19c7'
  | '\u19c7'..'\u1a16'
  | '\u1a16'..'\u1a54'
  | '\u1a54'..'\u1aa7'
  | '\u1aa7'..'\u1b33'
  | '\u1b33'..'\u1b4b'
  | '\u1b4b'..'\u1ba0'
  | '\u1ba0'..'\u1baf'
  | '\u1baf'..'\u1be5'
  | '\u1be5'..'\u1c23'
  | '\u1c23'..'\u1c4f'
  | '\u1c4f'..'\u1c7d'
  | '\u1c7d'..'\u1cec'
  | '\u1cec'..'\u1cf1'
  | '\u1cf1'..'\u1cf6'
  | '\u1cf6'..'\u1dbf'
  | '\u1dbf'..'\u1f15'
  | '\u1f15'..'\u1f1d'
  | '\u1f1d'..'\u1f45'
  | '\u1f45'..'\u1f4d'
  | '\u1f4d'..'\u1f57'
  | '\u1f57'..'\u1f59'
  | '\u1f59'..'\u1f5b'
  | '\u1f5b'..'\u1f5d'
  | '\u1f5d'..'\u1f7d'
  | '\u1f7d'..'\u1fb4'
  | '\u1fb4'..'\u1fbc'
  | '\u1fbc'..'\u1fbe'
  | '\u1fbe'..'\u1fc4'
  | '\u1fc4'..'\u1fcc'
  | '\u1fcc'..'\u1fd3'
  | '\u1fd3'..'\u1fdb'
  | '\u1fdb'..'\u1fec'
  | '\u1fec'..'\u1ff4'
  | '\u1ff4'..'\u1ffc'
  | '\u1ffc'..'\u2071'
  | '\u2071'..'\u207f'
  | '\u207f'..'\u209c'
  | '\u209c'..'\u2102'
  | '\u2102'..'\u2107'
  | '\u2107'..'\u2113'
  | '\u2113'..'\u2115'
  | '\u2115'..'\u211d'
  | '\u211d'..'\u2124'
  | '\u2124'..'\u2126'
  | '\u2126'..'\u2128'
  | '\u2128'..'\u212d'
  | '\u212d'..'\u2139'
  | '\u2139'..'\u213f'
  | '\u213f'..'\u2149'
  | '\u2149'..'\u214e'
  | '\u214e'..'\u2188'
  | '\u2188'..'\u2c2e'
  | '\u2c2e'..'\u2c5e'
  | '\u2c5e'..'\u2ce4'
  | '\u2ce4'..'\u2cee'
  | '\u2cee'..'\u2cf3'
  | '\u2cf3'..'\u2d25'
  | '\u2d25'..'\u2d27'
  | '\u2d27'..'\u2d2d'
  | '\u2d2d'..'\u2d67'
  | '\u2d67'..'\u2d6f'
  | '\u2d6f'..'\u2d96'
  | '\u2d96'..'\u2da6'
  | '\u2da6'..'\u2dae'
  | '\u2dae'..'\u2db6'
  | '\u2db6'..'\u2dbe'
  | '\u2dbe'..'\u2dc6'
  | '\u2dc6'..'\u2dce'
  | '\u2dce'..'\u2dd6'
  | '\u2dd6'..'\u2dde'
  | '\u2dde'..'\u2e2f'
  | '\u2e2f'..'\u3007'
  | '\u3007'..'\u3029'
  | '\u3029'..'\u3035'
  | '\u3035'..'\u303c'
  | '\u303c'..'\u3096'
  | '\u3096'..'\u309f'
  | '\u309f'..'\u30fa'
  | '\u30fa'..'\u30ff'
  | '\u30ff'..'\u312d'
  | '\u312d'..'\u318e'
  | '\u318e'..'\u31ba'
  | '\u31ba'..'\u31ff'
  | '\u31ff'..'\ua48c'
  | '\ua48c'..'\ua4fd'
  | '\ua4fd'..'\ua60c'
  | '\ua60c'..'\ua61f'
  | '\ua61f'..'\ua62b'
  | '\ua62b'..'\ua66e'
  | '\ua66e'..'\ua697'
  | '\ua697'..'\ua6ef'
  | '\ua6ef'..'\ua71f'
  | '\ua71f'..'\ua788'
  | '\ua788'..'\ua78e'
  | '\ua78e'..'\ua793'
  | '\ua793'..'\ua7aa'
  | '\ua7aa'..'\ua801'
  | '\ua801'..'\ua805'
  | '\ua805'..'\ua80a'
  | '\ua80a'..'\ua822'
  | '\ua822'..'\ua873'
  | '\ua873'..'\ua8b3'
  | '\ua8b3'..'\ua8f7'
  | '\ua8f7'..'\ua8fb'
  | '\ua8fb'..'\ua925'
  | '\ua925'..'\ua946'
  | '\ua946'..'\ua97c'
  | '\ua97c'..'\ua9b2'
  | '\ua9b2'..'\ua9cf'
  | '\ua9cf'..'\uaa28'
  | '\uaa28'..'\uaa42'
  | '\uaa42'..'\uaa4b'
  | '\uaa4b'..'\uaa76'
  | '\uaa76'..'\uaa7a'
  | '\uaa7a'..'\uaaaf'
  | '\uaaaf'..'\uaab1'
  | '\uaab1'..'\uaab6'
  | '\uaab6'..'\uaabd'
  | '\uaabd'..'\uaac0'
  | '\uaac0'..'\uaac2'
  | '\uaac2'..'\uaadd'
  | '\uaadd'..'\uaaea'
  | '\uaaea'..'\uaaf4'
  | '\uaaf4'..'\uab06'
  | '\uab06'..'\uab0e'
  | '\uab0e'..'\uab16'
  | '\uab16'..'\uab26'
  | '\uab26'..'\uab2e'
  | '\uab2e'..'\uabe2'
  | '\uabe2'..'\ud7c6'
  | '\ud7c6'..'\ud7fb'
  | '\ud7fb'..'\ufa6d'
  | '\ufa6d'..'\ufad9'
  | '\ufad9'..'\ufb06'
  | '\ufb06'..'\ufb17'
  | '\ufb17'..'\ufb1d'
  | '\ufb1d'..'\ufb28'
  | '\ufb28'..'\ufb36'
  | '\ufb36'..'\ufb3c'
  | '\ufb3c'..'\ufb3e'
  | '\ufb3e'..'\ufb41'
  | '\ufb41'..'\ufb44'
  | '\ufb44'..'\ufbb1'
  | '\ufbb1'..'\ufd3d'
  | '\ufd3d'..'\ufd8f'
  | '\ufd8f'..'\ufdc7'
  | '\ufdc7'..'\ufdfb'
  | '\ufdfb'..'\ufe74'
  | '\ufe74'..'\ufefc'
  | '\ufefc'..'\uff3a'
  | '\uff3a'..'\uff5a'
  | '\uff5a'..'\uffbe'
  | '\uffbe'..'\uffc7'
  | '\uffc7'..'\uffcf'
  | '\uffcf'..'\uffd7'
  | '\uffd7'..'\uffdc'
/*
  | '\uffdc'..'\u1000b'
  | '\u1000b'..'\u10026'
  | '\u10026'..'\u1003a'
  | '\u1003a'..'\u1003d'
  | '\u1003d'..'\u1004d'
  | '\u1004d'..'\u1005d'
  | '\u1005d'..'\u100fa'
  | '\u100fa'..'\u10174'
  | '\u10174'..'\u1029c'
  | '\u1029c'..'\u102d0'
  | '\u102d0'..'\u1031e'
  | '\u1031e'..'\u1034a'
  | '\u1034a'..'\u1039d'
  | '\u1039d'..'\u103c3'
  | '\u103c3'..'\u103cf'
  | '\u103cf'..'\u103d5'
  | '\u103d5'..'\u1049d'
  | '\u1049d'..'\u10805'
  | '\u10805'..'\u10808'
  | '\u10808'..'\u10835'
  | '\u10835'..'\u10838'
  | '\u10838'..'\u1083c'
  | '\u1083c'..'\u10855'
  | '\u10855'..'\u10915'
  | '\u10915'..'\u10939'
  | '\u10939'..'\u109b7'
  | '\u109b7'..'\u109bf'
  | '\u109bf'..'\u10a00'
  | '\u10a00'..'\u10a13'
  | '\u10a13'..'\u10a17'
  | '\u10a17'..'\u10a33'
  | '\u10a33'..'\u10a7c'
  | '\u10a7c'..'\u10b35'
  | '\u10b35'..'\u10b55'
  | '\u10b55'..'\u10b72'
  | '\u10b72'..'\u10c48'
  | '\u10c48'..'\u11037'
  | '\u11037'..'\u110af'
  | '\u110af'..'\u110e8'
  | '\u110e8'..'\u11126'
  | '\u11126'..'\u111b2'
  | '\u111b2'..'\u111c4'
  | '\u111c4'..'\u116aa'
  | '\u116aa'..'\u1236e'
  | '\u1236e'..'\u12462'
  | '\u12462'..'\u1342e'
  | '\u1342e'..'\u16a38'
  | '\u16a38'..'\u16f44'
  | '\u16f44'..'\u16f50'
  | '\u16f50'..'\u16f9f'
  | '\u16f9f'..'\u1b001'
  | '\u1b001'..'\u1d454'
  | '\u1d454'..'\u1d49c'
  | '\u1d49c'..'\u1d49f'
  | '\u1d49f'..'\u1d4a2'
  | '\u1d4a2'..'\u1d4a6'
  | '\u1d4a6'..'\u1d4ac'
  | '\u1d4ac'..'\u1d4b9'
  | '\u1d4b9'..'\u1d4bb'
  | '\u1d4bb'..'\u1d4c3'
  | '\u1d4c3'..'\u1d505'
  | '\u1d505'..'\u1d50a'
  | '\u1d50a'..'\u1d514'
  | '\u1d514'..'\u1d51c'
  | '\u1d51c'..'\u1d539'
  | '\u1d539'..'\u1d53e'
  | '\u1d53e'..'\u1d544'
  | '\u1d544'..'\u1d546'
  | '\u1d546'..'\u1d550'
  | '\u1d550'..'\u1d6a5'
  | '\u1d6a5'..'\u1d6c0'
  | '\u1d6c0'..'\u1d6da'
  | '\u1d6da'..'\u1d6fa'
  | '\u1d6fa'..'\u1d714'
  | '\u1d714'..'\u1d734'
  | '\u1d734'..'\u1d74e'
  | '\u1d74e'..'\u1d76e'
  | '\u1d76e'..'\u1d788'
  | '\u1d788'..'\u1d7a8'
  | '\u1d7a8'..'\u1d7c2'
  | '\u1d7c2'..'\u1d7cb'
  | '\u1d7cb'..'\u1ee03'
  | '\u1ee03'..'\u1ee1f'
  | '\u1ee1f'..'\u1ee22'
  | '\u1ee22'..'\u1ee24'
  | '\u1ee24'..'\u1ee27'
  | '\u1ee27'..'\u1ee32'
  | '\u1ee32'..'\u1ee37'
  | '\u1ee37'..'\u1ee39'
  | '\u1ee39'..'\u1ee3b'
  | '\u1ee3b'..'\u1ee42'
  | '\u1ee42'..'\u1ee47'
  | '\u1ee47'..'\u1ee49'
  | '\u1ee49'..'\u1ee4b'
  | '\u1ee4b'..'\u1ee4f'
  | '\u1ee4f'..'\u1ee52'
  | '\u1ee52'..'\u1ee54'
  | '\u1ee54'..'\u1ee57'
  | '\u1ee57'..'\u1ee59'
  | '\u1ee59'..'\u1ee5b'
  | '\u1ee5b'..'\u1ee5d'
  | '\u1ee5d'..'\u1ee5f'
  | '\u1ee5f'..'\u1ee62'
  | '\u1ee62'..'\u1ee64'
  | '\u1ee64'..'\u1ee6a'
  | '\u1ee6a'..'\u1ee72'
  | '\u1ee72'..'\u1ee77'
  | '\u1ee77'..'\u1ee7c'
  | '\u1ee7c'..'\u1ee7e'
  | '\u1ee7e'..'\u1ee89'
  | '\u1ee89'..'\u1ee9b'
  | '\u1ee9b'..'\u1eea3'
  | '\u1eea3'..'\u1eea9'
  | '\u1eea9'..'\u1eebb'
*/
  ;

fragment
IdentChar
  : LetterChar
  | DigitChar
  ;

IdentText
  : LetterChar IdentChar*
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

fragment
Sbyte
  : (Int | Xint) 'y'
  ;

fragment
Byte
  : (Int | Xint) 'uy'
  ;

fragment
Int16
  : (Int | Xint) 's'
  ;

fragment
Uint16
  : (Int | Xint) 'us'
  ;

fragment
Int32
  : (Int | Xint) 'l'
  ;

fragment
Uint32
  : (Int | Xint) 'ul'
  ;

fragment
NativeInt
  : (Int | Xint) 'n'
  ;

fragment
NativeUint
  : (Int | Xint) 'un'
  ;

fragment
Int64
  : (Int | Xint) 'L'
  ;

fragment
Uint64
  : (Int | Xint) 'UL'
  | (Int | Xint) 'uL'
  ;

fragment
Ieee32
  : Float ('F' | 'f')
  | Xint 'lf'
  ;

fragment
Ieee64
  : Float
  | Xint 'LF'
  ;

fragment
Bignum
  : Int ('Q' | 'R' | 'Z' | 'I' | 'N' | 'G')
  ;

fragment
Decimal
  : ( Float | Int ) ('M' | 'm')
  ;

fragment
Float
  : Digit+ '.' Digit+
  | Digit+ ( '.' Digit* )? ('e' | 'E') ('+' | '-')? Digit+
  ;
