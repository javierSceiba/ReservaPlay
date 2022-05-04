name := """reservas"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  guice,
  javaJdbc,
  javaJpa,
  jdbc,
  evolutions,
  "mysql" % "mysql-connector-java" % "8.0.28",
  "org.hibernate" % "hibernate-core" % "5.2.17.Final",
  "net.jodah" % "failsafe" % "1.0.3",
  "org.mockito" % "mockito-core" % "4.4.0" % "test",
  "io.vavr" %  "vavr" % "0.9.1",
  "io.vavr" %  "vavr-jackson" % "0.9.1",
  "org.jdbi" %  "jdbi" % "2.78",
  "org.projectlombok" % "lombok" % "1.18.24",
  "org.apache.commons" % "commons-lang3" % "3.12.0",
  "com.github.javafaker"              % "javafaker"                        % "1.0.2",
  "com.fasterxml.jackson.module"      % "jackson-module-parameter-names"   % "2.12.0",
  "com.fasterxml.jackson.datatype"    % "jackson-datatype-jdk8"            % "2.12.0",
  "com.fasterxml.jackson.datatype"    % "jackson-datatype-jsr310"          % "2.12.0",
  "com.fasterxml.jackson.module"      %% "jackson-module-scala"            % "2.12.0",
  "com.fasterxml.jackson.module"     %  "jackson-module-parameter-names" % "2.9.3"
)

