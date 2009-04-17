package de.javenue


import java.awt.Color
import java.awt.image.BufferedImage
import xml.Elem
import java.io.File
import javax.imageio.ImageIO

/**
 * Creates a chart layout and returns a buffered image from an XML description.
 */
class ChartLayout(val format: GraphicsFormat.Value, val xml: Elem, val outputPath: File) {
  val layout = (xml \\ "@type").text
  val image = process(layout)
  write(image)

  private def process(layout: String): BufferedImage = layout match {
    case "XY-Layout" => new XYLayout(xml).create
    case _ => new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB)
  }

  private def write(image: BufferedImage) = ImageIO.write(image, format.toString, outputPath)
}

/*
 * TODO Description
 */
abstract class Layout

case class XYLayout(xml: Elem) extends Layout {

  val x_offset = 50
  val y_offset = 50

  val x_size = (xml \ "x-size").text.toInt
  val y_size = (xml \ "y-size").text.toInt
  val background_color = (xml \ "background-color").text

  val image = new BufferedImage(x_size, y_size, BufferedImage.TYPE_INT_ARGB)
  val g2d = image.createGraphics

  //val r = Integer.valueOf("ff", 16).intValue
  //val g = Integer.valueOf("66", 16).intValue
  //val b = Integer.valueOf("00", 16).intValue

  def create: BufferedImage = {
    // Background
    g2d.setColor(new Color(238, 238, 238, 255))
    g2d.fillRect(0, 0, x_size, y_size);

    // X-Scale
    g2d.setColor(new Color(128, 128, 128, 255))
    g2d.drawLine(x1, y1, x2, y2)

    // Y-Scale
    g2d.drawLine(x1, y1, x3, y3)
    image
  }

  val x1 = (x_size - (x_size - 2 * x_offset)) / 2

  val y1 = y_size - y_offset

  val x2 = x_size - x_offset

  val y2 = y1

  val x3 = x_offset

  val y3 = y_offset

}