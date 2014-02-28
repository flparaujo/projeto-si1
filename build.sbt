name := "lab3"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)  

libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"  

mappings in (Compile, packageBin) += {
 (baseDirectory.value / "disciplinas-do-curso.xml") -> "disciplinas-do-curso.xml"
}

play.Project.playJavaSettings
