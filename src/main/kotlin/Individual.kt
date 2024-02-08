package me.kunalbhagawati

class Individual(genes: ByteArray? = null) {
  companion object {
    var defaultGeneLength: Int = 64

    /** Get back a random genetic sequence. */
    fun generateGenes(size: Int? = null): ByteArray =
        (0 ..< (size ?: defaultGeneLength)).map { Math.round(Math.random()).toByte() }.toByteArray()
  }

  val genes: ByteArray

  operator fun get(idx: Int) = genes[idx]

  operator fun set(idx: Int, value: Byte) {
    genes[idx] = value
  }

  val size: Int
    get() = genes.size

  // Cache
  var fitness: Int = 0
    get() {
      if (field == 0) {
        field = Algorithm.getFitness(this)
      }
      return field
    }

  init {
    this.genes = genes ?: generateGenes(size = defaultGeneLength)
  }

  override fun toString(): String =
      genes.toList().chunked(8).map { it.joinToString("") }.joinToString("-")
}
