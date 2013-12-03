# Fjord, F&#35; programming language for the JVM.

[![Build Status](https://travis-ci.org/penberg/fjord.png?branch=master)](http://travis-ci.org/penberg/fjord)

Fjord is an implementation of the [F#][F#] programming language for the JVM.

*The implementation is at very early stages, so if you are looking to use F# on
the JVM, Fjord is probably not for you.* However, if you are interested in
hacking on F# implementation for the JVM, welcome on board!

## Building Fjord

### Requirements

Fjord is built and packaged with [Apache Maven][Apache Maven] and requires Java
1.8 or higher to build and run.

### Building from sources

To build F#, run:

```
export MAVEN_OPTS="-Xmx1g"
mvn package
```

### Trying it out

To launch the F# interactive shell run:

```
./bin/fji
```

and you are greeted with:

```
Fjord

For help type #help
> _
```

## Features

* Aims at F# 3.0 language compatibility

## Want to help?

Want to help? Grab the [F# specification][F# specification], fork the GitHub
repository, and send a pull request!

For questions and comments, drop me a line on [Twitter] or on #fjord at
irc.freenode.net, or send me email at penberg@iki.fi.

## License

Copyright © 2013 Pekka Enberg and contributors.

Hornet is distributed under the Apache License, Version 2.0.

## Credits

*(in order of appearance)*

* Alex Biehl

[Apache Maven]: http://maven.apache.org/
[F# specification]: http://fsharp.org/about/files/spec.pdf
[F#]: http://fsharp.org/
[Twitter]: https://twitter.com/penberg
