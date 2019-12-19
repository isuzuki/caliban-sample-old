package com.github.isuzuki

case class CharacterName(name: String)
case class Queries(
  characters: () => List[Character],
  character: CharacterName => Option[Character],
)
