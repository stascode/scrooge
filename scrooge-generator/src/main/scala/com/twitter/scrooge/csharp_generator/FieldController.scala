package com.twitter.scrooge.csharp_generator

import com.twitter.scrooge.ast.{Requiredness, Identifier, Field}

class FieldController(f: Field, generator: CsharpGenerator, ns: Option[Identifier])
  extends BaseController(generator, ns) {
  val name = f.sid.name
  val requirement = getRequirement(f)
  val default = !f.default.isEmpty
  val optional = f.requiredness.isOptional
  val required = f.requiredness.isRequired

  val field_type = new FieldTypeController(f.fieldType, generator)

  def getRequirement(field: Field) = {
    field.requiredness match {
      case Requiredness.Required => "TFieldRequirementType.Required"
      case Requiredness.Optional => "TFieldRequirementType.Optional"
      case _ => "TFieldRequirementType.Default"
    }
  }

  val i_if_nullable = newHelper { input =>
    if (generator.isNullableType(f.fieldType)) indent(input, 4, false) else input
  }

  val i_if_optional = newHelper { input =>
    if (optional) indent(input, 4, false) else input
  }
}
