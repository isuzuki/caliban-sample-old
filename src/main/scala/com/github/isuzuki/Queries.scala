package com.github.isuzuki

case class Queries(
  characters: () => List[Character],
  character: String => Option[Character],
)
