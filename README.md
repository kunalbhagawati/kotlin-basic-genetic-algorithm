# Basic Genetic Algorithm

Kotlin version of the code from Lee Jacobson's simple but amazing blog post on genetic algorithms.  
https://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3

Basically an idiomatic kotlin port of the Java code in the post.
Also introduced immutability wherever possible:
- Custom constructors for ease of initialization.
- Simplified+abstracted getters/setters that Kotlin offers.
- Immutable properties wherever possible.
- Change classes with only static methods into `object`s. 
- Decompose the Fitness Calculator class into State and split it's functionality amongst other relevant classes. 
