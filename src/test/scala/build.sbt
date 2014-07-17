name := "Calvins final project"

version := "1.0"

scalaVersion := "2.10.1"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.2.2" % "test", 
  "com.typesafe.akka"   %%  "akka-actor"    % "2.2.4",
  "io.spray" % "spray-routing" % "1.2.1",
  "io.spray" % "spray-can" % "1.2.1",
  "com.typesafe.akka"   %%  "akka-testkit"  % "2.2.4"   % "test",
  "io.spray" %% "spray-json" % "1.2.6"
)



parallelExecution in Test := false



