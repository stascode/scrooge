package com.twitter.scrooge.csharp_generator

import com.twitter.scrooge.ast.{Definition, Identifier}

/**
 * Helps generate a top-level java class.
 */
abstract class TypeController(val name: String, generator: CsharpGenerator, ns: Option[Identifier])
  extends BaseController(generator, ns) {
  def this(typeId: Definition, generator: CsharpGenerator, ns: Option[Identifier]) = {
    this(typeId.sid.name, generator, ns)
  }
}
