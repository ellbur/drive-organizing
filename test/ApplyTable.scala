
import java.io.{File, FileReader}

import org.apache.commons.csv.{CSVFormat, CSVParser}

import scala.collection.mutable
import scala.jdk.CollectionConverters._
import scala.sys.process._

object ApplyTable extends App {
  {
    val json = ujson.read(new File("/home/owen/drive-organizing/resources/before.json"))

    val dirs = mutable.Set[String]()
    json.arr foreach { item =>
      if (item("IsDir").bool) dirs += item("Path").str
    }

    val parser = CSVParser.parse(new FileReader("/home/owen/drive-organizing/resources/table-filled-in.csv"), CSVFormat.DEFAULT)
    val rows = parser.iterator

    val headerRow = rows.next()
    val dirVector = headerRow.asScala.drop(1).toIndexedSeq

    //dirVector foreach { dir =>
    //  if (!dirs.contains(dir)) {
    //    println(s"Adding $dir")
    //    Seq("rclone", "mkdir", "mygdrive:" + dir).!!
    //  }
    //}

    rows.asScala.zipWithIndex foreach { case (row, i) =>
      val fileName = row.get(0)
      val selectVector = row.asScala.drop(1).toIndexedSeq
      selectVector.indexWhere (_.strip.nonEmpty) match {
        case -1 =>
        case i =>
          val dirName = dirVector(i)
          println(s"$i Move $fileName to $dirName")
          println(Seq("rclone", "move", "mygdrive:" + fileName, "mygdrive:" + dirName).!!)
      }
    }
  }
}
