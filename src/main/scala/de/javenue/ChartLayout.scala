package de.javenue


import java.awt.image.BufferedImage
import java.awt.{Graphics2D, Color}
import xml.Elem
import java.io.File
import javax.imageio.ImageIO

/**
 * Creates a chart layout and returns a buffered image from an XML description.
 */
class ChartLayout(val format: String, val xml: Elem, val outputPath: File) extends LayoutFormat {
  val layout = (xml \\ "@type").text
  val image = process(layout)
  this.write(image)

  private def process(layout: String): BufferedImage = layout match {
    case XY_LAYOUT =>
      val layout = new XYLayout(xml)
      layout.createImage()
    case _ => new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB)
  }

  private def write(image: BufferedImage) = ImageIO.write(image, format, outputPath)
}

/*
 * TODO Description
 */
abstract class Layout

case class XYLayout(xml: Elem) extends Layout with Colored with Offset with Chart {
  val x_size = (xml \ "x-size").text.toInt
  val y_size = (xml \ "y-size").text.toInt
  val background_color = (xml \ "background-color").text

  val chart = new XYChart(x_size, y_size)
  val image = chart.image
  val g2d = chart.g2d

  //val r = Integer.valueOf("ff", 16).intValue
  //val g = Integer.valueOf("66", 16).intValue
  //val b = Integer.valueOf("00", 16).intValue

  def createImage(): BufferedImage = {
    // Background
    colorize(LIGHT_GRAY)
    //g2d.setColor(LIGHT_GRAY)
    g2d.fillRect(0, 0, x_size, y_size);

    // Draw area
    g2d.setColor(WHITE)
    g2d.fillRect(x3, y3, x_size - 2 * x_offset, y_size - 2 * y_offset)

    // X-Scale
    g2d.setColor(new Color(128, 128, 128, 255))
    //g2d.setColor(RED)
    g2d.drawLine(x1, y1, x2, y2)

    // Y-Scale
    g2d.drawLine(x1, y1, x3, y3)
    image
  }

  def colorize(color: Color) = g2d.setColor(color)


  val x1 = (x_size - (x_size - 2 * x_offset)) / 2

  val y1 = y_size - y_offset

  val x2 = x_size - x_offset

  val y2 = y1

  val x3 = x_offset

  val y3 = y_offset

}