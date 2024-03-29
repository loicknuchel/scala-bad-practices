# Scala bad practices

Project to prepare a scala.io talk.
This project references bad practices in Scala, some a language specific and some more general.

## Summary of the talk

- intro
- consensual bad practices
    - mixing patterns is bad (Scala vs Java styles: null, throw, Any, lib wrapper, Try & throw in the same time...)
    - local reasoning
    - mutate function params
    - use Any or AnyRef
- generic bad practices
    - optional overuse, too much default value (`optionalOveruse` package)
    - impure calls (Instant.now() or Random.next()), can be replace by a parameter
- ugliest code
    - implicit conversions
    - implicits in package objects
    - define common operators (`defineOperators` package)
    - threadlocal, doesn't work well with async (thread pool)
    - get or create transaction
- conclusion

## Language syntax & features

- OK implicits in package objects
- OK use throw or null, even if they are inherited from a library (a Java one for example)
- OK Any: when returned by a lib, cast/type (pattern matching) it asap
- overuse of implicit: implicit conversions, too common types
- OK redefine common operators (->) (we lose the advantage of local reasoning)
- apply method that return an other type than it's object
- OK threadlocal to keep context (http request or transaction) => use implicits parameters
- long methods => keep them short and pure. Their name is a small piece of documentation
- OK in transactional context: get current transaction or create one => you never know when you finish the transaction
- OK optional overuse (id: Option[String] or id: String with special value)
- mutable state
    - prefer a var instead of a mutable val
    - update it from a single place (or the less possible)
- impure operations: Instant.now() or Random, take theses values as parameter, it will be easier to test
- misleading type alias (`type ProcessResult = Unit`)
- abuse of default case, non exhaustive match
- function: return Try but throw anyway
- primitive obsession
- avoid explicit type annotations to implicits (see bug https://github.com/scala/bug/issues/8697)

## Design

- use a mutable API: if you can avoid mutability, if you must, do it locally but never rely on mutability in your API
- when designing API, forcing consumers to inherit from classes instead of exposing static methods (should be the last resort)
- prefer type classes over inheritance to encourage separation of data type and behavior (Orient Mapper example)

## WTF

- type Option[Int] which contains a String!!! (cf orient ZResult)

## Conclusion

Bad practices comes in group, so, unlike the previous example, you may find a very tasty combination of them in your codebase, which make things fun ^^


"best practices"
- least power => Use implicit class when an extension is needed and do not use implicit conversion
( Why : Implicit conversions are applied in two situations: If an expression e is of type S, 
and S does not conform to the expression’s expected type T. 
[Or:] In a selection e.m with e of type S, if the selector m does not denote a member of S.)
- In general avoid implicit conversion. If used make sure that you are not dealing with a non-total conversion => risk of runtime exceptions.
- explicit instead of implicit
