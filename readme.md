# BinaryTreeSets

A Scala learning project implementing binary tree sets.

## Overview

This project is a hands-on exploration of functional programming concepts in Scala, focusing on the implementation of binary tree sets. It serves as a practical learning exercise for working with immutable data structures and type classes in Scala.

## Features

- Generic binary tree set implementation
- Support for numeric types
- Immutable data structure
- Basic set operations (insert, contains, union, intersection, difference)

## Prerequisites

- Scala 3.5.0
- SBT 1.10.1

## Getting Started

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/BinaryTreeSets.git
   cd BinaryTreeSets
   ```

2. Build the project:
   ```
   sbt compile
   ```

3. Run tests:
   ```
   sbt test
   ```

## Usage

Example usage of the BinaryTreeSet:

```scala
scala
import example.BinaryTreeSet
val set1 = BinaryTreeSet.fromList(List(1, 2, 3, 4, 5))
val set2 = BinaryTreeSet.fromList(List(4, 5, 6, 7, 8))
val union = set1.union(set2)
val intersection = set1.intersect(set2)
val difference = set1.diff(set2)
println(s"Union: $union")
println(s"Intersection: $intersection")
println(s"Difference: $difference")
```


## Project Structure

- `src/main/scala/`: Source code
- `src/test/scala/`: Test files

## Contributing

This is a personal learning project, but feedback and suggestions are welcome. Feel free to open an issue or submit a pull request.

## License

This project is open-source and available under the MIT License.