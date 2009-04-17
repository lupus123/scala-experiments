package de.javenue


import java.awt.Color
import java.awt.image.BufferedImage
import xml.Elem
import java.io.File
import javax.imageio.ImageIO

/**
 * Creates a chart layout from 
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


private case class XYLayout(xml: Elem) {
  val x_size = (xml \ "x-size").text.toInt
  val y_size = (xml \ "y-size").text.toInt
  val background_color = (xml \ "background-color").text

  val image = new BufferedImage(x_size, y_size, BufferedImage.TYPE_INT_ARGB)
  val g2d = image.createGraphics

  val r = Integer.valueOf("ff", 16).intValue
  val g = Integer.valueOf("66", 16).intValue
  val b = Integer.valueOf("00", 16).intValue

  def create: BufferedImage = {
    g2d.setColor(new Color(r, g, b, 255))
    g2d.fillRect(0, 0, x_size, y_size);
    g2d.setColor(Color.RED)
    g2d.fillRect(0, 0, 50, 50)
    image
  }

}