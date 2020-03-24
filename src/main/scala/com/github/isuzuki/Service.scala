package com.github.isuzuki

import scala.collection.mutable.ListBuffer

object Service {
  val characters = ListBuffer(
    Character("Luke Skywalker"),
    Character("C-3PO"),
    Character("R2-D2"),
  )

  def getCharacters: List[Character] =
    characters.toList

  def findCharacter(name: String): Option[Character] =
    characters.find(_.name == name)

  def createCharacter(name: String): Character = {
    val character = Character(name)
    characters.addOne(character)
    character
  }

  def deleteCharacter(name: String): Boolean =
    findCharacter(name).map(characters.subtractOne).isDefined
}
