package me.kunalbhagawati

object Algorithm {
    /* GA parameters */
    private const val uniformRate = 0.5
    private const val mutationRate = 0.015
    private const val tournamentSize = 5
    private const val elitism = true

    /* Public methods */ // Evolve a population
    fun evolvePopulation(pop: Population): Population {
        val newPopulation = Population(pop.size(), false)

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.fittest)
        }

        // Crossover population
        val elitismOffset = if (elitism) {
            1
        } else {
            0
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (i in elitismOffset until pop.size()) {
            val indiv1 = tournamentSelection(pop)
            val indiv2 = tournamentSelection(pop)
            val newIndiv = crossover(indiv1, indiv2)
            newPopulation.saveIndividual(i, newIndiv)
        }

        // Mutate population
        for (i in elitismOffset until newPopulation.size()) {
            mutate(newPopulation.getIndividual(i))
        }

        return newPopulation
    }

    // Crossover individuals
    private fun crossover(indiv1: Individual?, indiv2: Individual?): Individual {
        val newSol = Individual()
        // Loop through genes
        for (i in 0 until indiv1!!.size()) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i))
            } else {
                newSol.setGene(i, indiv2!!.getGene(i))
            }
        }
        return newSol
    }

    // Mutate an individual
    private fun mutate(indiv: Individual?) {
        // Loop through genes
        for (i in 0 until indiv!!.size()) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                val gene = Math.round(Math.random()).toByte()
                indiv.setGene(i, gene)
            }
        }
    }

    // Select individuals for crossover
    private fun tournamentSelection(pop: Population): Individual? {
        // Create a tournament population
        val tournament = Population(tournamentSize, false)
        // For each place in the tournament get a random individual
        for (i in 0 until tournamentSize) {
            val randomId = (Math.random() * pop.size()).toInt()
            tournament.saveIndividual(i, pop.getIndividual(randomId))
        }
        // Get the fittest
        val fittest = tournament.fittest
        return fittest
    }
}
