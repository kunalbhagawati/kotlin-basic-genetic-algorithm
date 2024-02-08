package me.kunalbhagawati

object Algorithm {
  /* GA parameters */
  private const val uniformRate = 0.5
  private const val mutationRate = 0.015
  private const val tournamentSize = 5
  private const val elitism = true

  fun evolvePopulation(population: Population): Population {
    fun newIndividual(): Individual {
      val indiv1 = tournamentSelection(population)
      val indiv2 = tournamentSelection(population)
      return crossover(indiv1, indiv2)
    }

    val individuals: List<Individual> =
        List(population.size) { idx ->
          val i =
              if (idx == 0 && elitism) {
                population.fittest
              } else {
                newIndividual()
              }

          return@List i.also { mutate(it) }
        }

    return Population(individuals = individuals.toMutableList())
  }

  /** Crossover individuals. */
  private fun crossover(indiv1: Individual, indiv2: Individual): Individual {
    val genes =
        indiv1.genes.mapIndexed { idx, _ ->
          when (Math.random() <= uniformRate) {
            true -> indiv1[idx]
            false -> indiv2[idx]
          }
        }

    return Individual(genes = genes.toByteArray())
  }

  /** Mutate an individual. */
  private fun mutate(indiv: Individual) {
    indiv.genes.forEachIndexed { idx, _ ->
      if (Math.random() <= mutationRate) {
        // Create random gene
        indiv[idx] = Math.round(Math.random()).toByte()
      }
    }
  }

  /** Select individuals for crossover. */
  private fun tournamentSelection(pop: Population): Individual {
    // Create a tournament population
    val tournament = Population.fromPopulationSize(tournamentSize)
    // For each place in the tournament get a random individual
    (0 ..< tournamentSize).forEach { i ->
      val randomId = (Math.random() * pop.size).toInt()
      tournament[i] = pop[randomId]
    }
    // Get the fittest
    return tournament.fittest
  }

  /**
   * Calculate individual's [fitness][Individual.fitness] by comparing it to our candidate solution.
   */
  fun getFitness(individual: Individual): Int {
    var fitness = 0
    // Loop through our individuals genes and compare them to our candidates
    val s = minOf(individual.size, State.solution.size)
    (0 ..< s).forEach { idx ->
      if (individual[idx] == State.solution[idx]) {
        fitness++
      }
    }
    return fitness
  }
}
