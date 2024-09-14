import Dependencies._

ThisBuild / scalaVersion     := "3.5.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.github"
ThisBuild / organizationName := "roman91DE"

lazy val root = (project in file("."))
  .settings(
    name := "BinaryTreeSets",
    libraryDependencies += munit % Test
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.

// Add these lines at the end of the file
