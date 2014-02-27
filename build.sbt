name := "lab3"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)  

mappings in (Compile, packageBin) += {
 (baseDirectory.value / "disciplinas-do-curso.xml") -> "disciplinas-do-curso.xml"
}

play.Project.playJavaSettings
