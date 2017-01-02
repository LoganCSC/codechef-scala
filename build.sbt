name := "codechef"

version := "1.0"

scalaVersion := "2.11.6"

resolvers += "bb4-release" at "http://repo1.maven.org/maven2/"
// this is where SNAPSHOT releases will come from
resolvers += "bb4-staging" at "https://oss.sonatype.org/content/groups/staging"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
libraryDependencies += "com.barrybecker4" % "bb4-A-star" % "1.0"