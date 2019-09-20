# Scala bad practices

Project to prepare a scala.io talk.
This project references bad practices in Scala, some a language specific and some more general.

## Language syntax & features

- implicits in package objects
- use throw or null, even if they are inherited from a library (a Java one for example)
- Any: when returned by a lib, cast/type (pattern matching) it asap
- overuse of implicit: implicit conversions, too common types
- redefine common operators (->) (we lose the advantage of local reasoning)
- apply method that return an other type than it's object
- threadlocal to keep context (http request or transaction) => use implicits parameters
- long methods => keep them short and pure. Their name is a small piece of documentation
- mutable state
    - prefer a var instead of a mutable val
    - update it from a single place (or the less possible)
- impure operations: Instant.now() or Random, take theses values as parameter, it will be easier to test
- misleading type alias (`type ProcessResult = Unit`)
- abuse of default case, non exhaustive match
- function: return Try but throw anyway
- primitive obsession

## Design

- use a mutable API: if you can avoid mutability, if you must, do it locally but never rely on mutability in your API

## WTF

- type Option[Int] which contains a String!!! (cf orient ZResult)

## Conclusion

Bad practices comes in group, so, unlike the previous example, you may find a very tasty combinaison of them in your codebase, which make things fun ^^


"best practices"
- least power
- explicit instead of implicit