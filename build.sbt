scalacOptions ++= Seq("-deprecation", "-feature", "-language:implicitConversions")

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.2.3"
)

scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")
