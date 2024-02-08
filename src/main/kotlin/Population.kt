package me.kunalbhagawati

class Population(populationSize: Int, initialise: Boolean) {
    var individuals: Array<Individual?> = arrayOfNulls(populationSize)

    /*
    * Constructors
    */
    // Create a population
    init {
        // Initialise population
        if (initialise) {
            // Loop and create individuals
            for (i in 0 until size()) {
                val newIndividual = Individual()
                newIndividual.generateIndividual()
                saveIndividual(i, newIndividual)
            }
        }
    }

    /* Getters */
    fun getIndividual(index: Int): Individual? {
        return individuals[index]
    }

    val fittest: Individual?
        get() {
            var fittest = individuals[0]
            // Loop through individuals to find fittest
            for (i in 0 until size()) {
                if (fittest!!.getFitness() <= getIndividual(i)!!.getFitness()) {
                    fittest = getIndividual(i)
                }
            }
            return fittest
        }

    /* Public methods */ // Get population size
    fun size(): Int {
        return individuals.size
    }

    // Save individual
    fun saveIndividual(index: Int, indiv: Individual?) {
        individuals[index] = indiv
    }
}
