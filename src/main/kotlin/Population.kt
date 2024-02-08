package me.kunalbhagawati

class Population(var individuals: MutableList<Individual>) {
  companion object {
    fun fromPopulationSize(size: Int): Population = 
      Population(individuals = MutableList(size) { Individual() })
  }

  operator fun get(idx: Int) = individuals[idx]

  operator fun set(idx: Int, value: Individual) {
    individuals[idx] = value
  }

  val size: Int
    get() = individuals.size

  val fittest: Individual
    get() = individuals.maxBy { it.fitness }
}
