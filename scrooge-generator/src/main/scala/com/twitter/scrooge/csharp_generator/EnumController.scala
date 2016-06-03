package com.twitter.scrooge.csharp_generator

import com.twitter.scrooge.ast.{Identifier, Enum}

class EnumConstant(val name: String, val value: Int, val last: Boolean)

class EnumController(e: Enum, generator: CsharpGenerator, ns: Option[Identifier])
  extends TypeController(e, generator, ns) {
  val constants = e.values.zipWithIndex map { case (v, i) =>
    new EnumConstant(v.sid.name, v.value, i == e.values.size - 1)
  }
}
