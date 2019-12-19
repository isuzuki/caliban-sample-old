package com.github.isuzuki

import zio.Task

case class Mutations(
  createCharacter: String => Task[Character],
  deleteCharacter: String => Task[Boolean],
)
