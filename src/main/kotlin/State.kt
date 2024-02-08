package me.kunalbhagawati

object State {
  var solution: ByteArray = ByteArray(64)

  // To make it easier we can use this method to set our candidate solution
  // with string of 0s and 1s
  fun setSolution(newSolution: String) {
    solution =
        (0 until newSolution.length)
            .map { i: Int ->
              val character = newSolution.substring(i, i + 1)

              if (character.contains("0") || character.contains("1")) {
                character.toByte()
              } else {
                0
              }
            }
            .toByteArray()
  }
}

/** Get optimum [fitness][Individual.fitness] an individual can have. */
val State.maxFitness: Int
  get() = solution.size
