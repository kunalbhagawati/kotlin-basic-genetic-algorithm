package me.kunalbhagawati

import me.kunalbhagawati.Algorithm.evolvePopulation

fun main() {
  // Set a candidate solution
  State.setSolution("1111000000000000000000000000000000000000000000000000000000001111")

  // Create an initial population
  var population = Population.fromPopulationSize(50)

  // Evolve our population until we reach an optimum solution
  var generationCount = 0
  while (population.fittest.fitness < State.maxFitness) {
    generationCount++
    println("Generation: $generationCount Fittest: ${population.fittest} Fitness: ${population.fittest.fitness}")
    population = evolvePopulation(population)
  }
  println("Solution found!")
  println("Generation: $generationCount")
  println("Genes:")
  println(population.fittest)
}
