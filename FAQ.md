# FAQ

### Why is it important to have F# run on the JVM?

I want a high-performance, modern, no nonsense ML for the JVM. I think F# fits
the bill.

### Have you considered cross-compiling the existing compiler?

I have. It's an interesting project for someone else.

### Have you considered cross-compiling .NET bytecode to the JVM?

I have. It's a world of pain.

### How are you going to handle tail calls on the JVM?

With branches. (It's not a perfect fit, I agree, but it's not an unsolvable
problem either.)

### How are you going to handle value types?

Turn them into classes. Not a perfect fit but not a huge problem either.

### How are you planning to deal with inferior generics of the JVM (most F#
libraries rely on the JIT specializing the generic code for efficient
execution)

To be honest, I haven't thought about this too much. AFAIK, JRuby uses
annotations to carry similar information so it's I think it's a solvable
problem. But I completely agree, it's not a nice fit either.

### Why are you using Java as an implementation language?

I'm following similar design to JRuby, Clojure, and Smalltalk Redline, for
example. The main objective is to be a minimal standalone core that doesn't
require bootstrapping (see 'the other project').

### Why start from the parser (the least important and interesting component)?

You have to start somewhere.

### Why focus on F# instead of a better defined (smaller) functional language in the ML family like SML?

Because I'm interested in F#, not SML. See answer to the first question.
