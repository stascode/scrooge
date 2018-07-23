package com.twitter.scrooge.csharp_generator

import com.twitter.scrooge.ast._

class DeserializeFieldController(
    fieldType: FieldType,
    fieldName: String,
    fieldOptional: Boolean,
    prefix: String,
    generator: CsharpGenerator,
    ns: Option[Identifier])
  extends BaseSerializeController(fieldType, fieldName, prefix, generator, ns) {
  val optional = fieldOptional
  val obj = if (field_type.is_container) generator.tmp("_" + field_type.get_type.toLowerCase) else false
  val tmp_i = if (field_type.is_container) generator.tmp("_i") else false

  def deserialize_map_element = {
    fieldType match {
      case MapType(k, v, _) => {
        val tmpKey = generator.tmp("_key")
        val tmpVal = generator.tmp("_val")
        Map(
          "tmp_key" -> tmpKey,
          "tmp_val"-> tmpVal,
          "key_type" -> new FieldTypeController(k, generator),
          "val_type" -> new FieldTypeController(v, generator),
          "deserialize_key" -> indent(generator.deserializeField(k, tmpKey, ns, false), 8),
          "deserialize_val" -> indent(generator.deserializeField(v, tmpVal, ns, false), 8)
        )
      }
      case _ => false
    }
  }

  def deserialize_set_or_list_element = {
    fieldType match {
      case SetType(x, _) => deserialize_elem(x)
      case ListType(x, _) => deserialize_elem(x)
      case _ => false
    }
  }

  def deserialize_elem(x: FieldType): Map[String, Object] = {
    val tmpElem = generator.tmp("_elem")
    Map(
      "tmp_elem" -> tmpElem,
      "elem_type" -> new FieldTypeController(x, generator),
      "deserialize_elem" -> indent(generator.deserializeField(x, tmpElem, ns, false), 8)
    )
  }
}