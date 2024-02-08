package me.kunalbhagawati

class Individual {
    private val genes = ByteArray(defaultGeneLength)

    // Cache
    private var fitness = 0

    // Create a random individual
    fun generateIndividual() {
        for (i in 0 until size()) {
            val gene = Math.round(Math.random()).toByte()
            genes[i] = gene
        }
    }

    fun getGene(index: Int): Byte {
        return genes[index]
    }

    fun setGene(index: Int, value: Byte) {
        genes[index] = value
        fitness = 0
    }

    /* Public methods */
    fun size(): Int {
        return genes.size
    }

    fun getFitness(): Int {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this)
        }
        return fitness
    }

    override fun toString(): String {
        var geneString = ""
        for (i in 0 until size()) {
            geneString += getGene(i)
        }
        return geneString
    }

    companion object {
        var defaultGeneLength: Int = 64

        /* Getters and setters */ // Use this if you want to create individuals with different gene lengths
        fun setDefaultGeneLength(length: Int) {
            defaultGeneLength = length
        }
    }
}
