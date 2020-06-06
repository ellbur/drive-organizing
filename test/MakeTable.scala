
import java.io.{File, FileWriter}

import org.apache.commons.csv.{CSVFormat, CSVPrinter}

import scala.collection.mutable.ArrayBuffer

object MakeTable extends App {
  {
    val json = ujson.read(new File("/home/owen/drive-organizing/resources/before.json"))

    val dirs = ArrayBuffer[String]()
    val files = ArrayBuffer[String]()

    json.arr foreach { item =>
      if (item("IsDir").bool) {
        dirs += item("Path").str
      }
      else {
        files += item("Path").str
      }
    }

    val printer = new CSVPrinter(new FileWriter("/home/owen/drive-organizing/resources/table.csv"), CSVFormat.DEFAULT)
    printer.printRecord(Seq("path") ++ dirs sortBy (_.toLowerCase): _*)
    files sortBy (_.toLowerCase) foreach { file =>
      printer.printRecord(Seq(file) ++ Seq.fill(dirs.length)(""): _*)
    }
    printer.flush()
    printer.close()
  }
}
