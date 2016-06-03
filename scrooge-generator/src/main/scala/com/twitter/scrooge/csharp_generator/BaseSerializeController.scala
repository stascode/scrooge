package com.twitter.scrooge.csharp_generator

import com.twitter.scrooge.ast._

class BaseSerializeController(
    fieldType: FieldType,
    fieldName: String,
    prefix: String,
    generator: CsharpGenerator,
    ns: Option[Identifier])
  extends BaseController(generator, ns) {
  val field_type = new FieldTypeController(fieldType, generator)
  val name = prefix + fieldName
}
