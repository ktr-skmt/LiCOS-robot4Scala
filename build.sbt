val robotLibraryName: String = "LiCOS-robot4Scala"

name := robotLibraryName

resolvers += Resolver.sonatypeRepo("snapshots")

coverageEnabled := true

lazy val javaVersion: String = "8"

lazy val wartremoverSettings = Seq(
  wartremoverWarnings in (Compile, compile) ++= Warts.allBut(Wart.Throw)
)

lazy val scalafmtSettings = Seq(
  scalafmtOnCompile := true,
  version := "2.2.1"
)

lazy val commonSettings = Seq(
  scalaVersion := "2.12.10",
  organization := "online.licos",
  fork in run := true
) ++ {
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding",
    "UTF-8", // yes, this is 2 args
    "-feature",
    "-language:implicitConversions",
    "-unchecked",
    "-Xlint",
    "-Ypartial-unification",
    s"-target:jvm-1.$javaVersion"
  )
} ++ {
  javacOptions ++= Seq(
    "-source",
    javaVersion,
    "-target",
    javaVersion
  )
}

val fileProtocol: String = "file://"
val mavenRepo:    String = "maven-repo"
val snapshotsStr: String = "snapshots"
val releasesStr:  String = "releases"

def getPublishTo(isSnapshot: Boolean, n: String): Option[Resolver] = {
  val version: String = {
    if (isSnapshot) {
      snapshotsStr
    } else {
      releasesStr
    }
  }

  Option(
    Resolver.file(
      s"$n-$version-repository",
      file(s"$mavenRepo/$version")
    )
  )
}
val licensesTemplate = Seq(
  "Apache License Version 2.0" -> url("https://raw.githubusercontent.com/ktr-skmt/LiCOS-robot4Scala/master/LICENSE")
)
val homepageTemplate = Some(url("https://github.com/ktr-skmt/LiCOS-robot4Scala"))
val pomExtraTemplate = {
  <scm>
    <url>git@github.com:ktr-skmt/LiCOS-robot4Scala.git</url>
    <connection>scm:git@github.com:ktr-skmt/LiCOS-robot4Scala.git</connection>
  </scm>
    <developers>
      <developer>
        <id>ktr-skmt</id>
        <name>sakamoto-kotaro-pn@ynu.jp</name>
        <url>https://linkedin.com/in/kotaro-sakamoto-19168b4a</url>
      </developer>
    </developers>
}

lazy val akkaVersion = "2.5.23"

def excludeScalaJava8Compat(module: ModuleID): ModuleID = {
  module.excludeAll(ExclusionRule("org.scala-lang.modules", "scala-java8-compat_2.12"))
}

lazy val robot = (project in file("."))
  .settings(commonSettings: _*)
  .settings(wartremoverSettings: _*)
  .settings(scalafmtSettings: _*)
  .settings(
    scalacOptions in (Compile, doc) ++= Seq(
      "-groups",
      "-implicits",
      "-doc-root-content",
      (sourceDirectory in Compile).value + "/rootdoc.txt"
    ),
    autoAPIMappings := true
  )
  .settings(
    isSnapshot := true,
    version := "0.0.1",
    name := robotLibraryName,
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ =>
      false
    },
    publishTo := getPublishTo(isSnapshot.value, name.value),
    licenses := licensesTemplate,
    homepage := homepageTemplate,
    pomExtra := pomExtraTemplate
  )
  .settings(
    resolvers += "LiCOS-JSON4Scala-snapshots-repository" at "https://github.com/ktr-skmt/LiCOS-JSON4Scala/raw/master/maven-repo/snapshots"
  )
  .settings(
    libraryDependencies ++= {
      Seq(
        guice exclude ("com.google.guava", "guava"),
        "online.licos" %% "licos-json4scala" % "0.3.1",
        "com.typesafe.play" %% "play-json" % "2.7.4",
        "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
        "org.slf4j" % "slf4j-api" % "1.7.28" % "compile",
        "ch.qos.logback" % "logback-classic" % "1.2.3",
        "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
        "org.typelevel" %% "cats-core" % "2.0.0",
        "com.typesafe.akka" %% "akka-http" % "10.1.11",
        "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
        "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test
      )
    }
  )
  .settings(
    libraryDependencies ~= (_.map(excludeScalaJava8Compat))
  )
  .settings(
    dependencyOverrides ++= {
      Seq(
        "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.8",
        "org.slf4j" % "slf4j-api" % "1.7.28"
      )
    }
  )
