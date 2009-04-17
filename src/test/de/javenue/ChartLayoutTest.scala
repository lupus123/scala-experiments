package de.javenue


import java.io.{BufferedInputStream, FileInputStream, File}
import org.scalatest.Suite
import xml.XML

/**
 * ScalaTest
 * @TODO  Description, Parametrization
 */
class ChartLayoutTest extends Suite {
  val format = GraphicsFormat.PNG

  val outputPath = new File("D:\\svn_home\\scala-experiments\\output\\test." + format)

  val xml = XML.load(new BufferedInputStream(new FileInputStream(new File("D:\\svn_home\\scala-experiments\\layout\\test-layout.xml"))))

  def testLayout: Unit = {
    new ChartLayout(format, xml, outputPath)
  }
}