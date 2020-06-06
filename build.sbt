
scalaVersion := "2.13.1"

scalaSource in Compile := { baseDirectory.value / "src" }

javaSource in Compile := { baseDirectory.value / "src" }

scalaSource in Test := { baseDirectory.value / "test" }

javaSource in Test := { baseDirectory.value / "test" }

resourceDirectory in Compile := { baseDirectory.value / "resources" }

resourceDirectory in Test := { baseDirectory.value / "test-resources" }

javacOptions ++= Seq("-encoding", "UTF-8")

libraryDependencies ++= Seq(
  "org.apache.pdfbox" % "pdfbox" % "2.0.15",
  "org.apache.commons" % "commons-io" % "1.3.2",
  "org.docx4j" % "docx4j-JAXB-ReferenceImpl" % "11.1.3",
  "org.apache.commons" % "commons-csv" % "1.7",
  "org.jsoup" % "jsoup" % "1.12.1",
  "software.amazon.awssdk" % "transcribe" % "2.10.23",
  "software.amazon.awssdk" % "s3" % "2.10.23",
  "com.lihaoyi" % "ujson_2.13" % "0.8.0",
  "software.amazon.awssdk" % "workmail" % "2.10.52",
  "software.amazon.awssdk" % "ses" % "2.10.52",
  "com.microsoft.ews-java-api" % "ews-java-api" % "2.0",
  "com.sun.xml.ws" % "jaxws-rt" % "2.3.2",
  "jakarta.xml.ws" % "jakarta.xml.ws-api" % "2.3.2",
  "com.lihaoyi" %% "upickle" % "1.0.0"
)

fork := true

cancelable in Global := true

javaOptions ++= Seq("-Xmx6g")

