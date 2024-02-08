package me.kunalbhagawati

object FitnessCalc {
    var solution: ByteArray = ByteArray(64)

    /* Public methods */ // Set a candidate solution as a byte array
    fun setSolution(newSolution: ByteArray) {
        solution = newSolution
    }

    // To make it easier we can use this method to set our candidate solution
    // with string of 0s and 1s
    fun setSolution(newSolution: String) {
        solution = ByteArray(newSolution.length)
        // Loop through each character of our string and save it in our byte
        // array
        for (i in 0 until newSolution.length) {
            val character = newSolution.substring(i, i + 1)
            if (character.contains("0") || character.contains("1")) {
                solution[i] = character.toByte()
            } else {
                solution[i] = 0
            }
        }
    }

    // Calculate inidividuals fittness by comparing it to our candidate solution
    fun getFitness(individual: Individual): Int {
        var fitness = 0
        // Loop through our individuals genes and compare them to our cadidates
        var i = 0
        while (i < individual.size() && i < solution.size) {
            if (individual.getGene(i) == solution[i]) {
                fitness++
            }
            i++
        }
        return fitness
    }

    val maxFitness: Int
        // Get optimum fitness
        get() {
            val maxFitness = solution.size
            return maxFitness
        }
}
