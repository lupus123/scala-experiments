package de.javenue

import java.awt.image.BufferedImage
import java.awt.{Graphics2D, Color}
import xml.Elem
import java.io.File
import javax.imageio.ImageIO

/**
 * Creates a chart layout and returns a buffered image from a XML description.
 */
class ChartLayout(val format: String, val xml: Elem, val outputPath: File) extends LayoutFormat {
  val layout = (xml \\ "@type").text
  val image = process(layout)
  this.write(image)

  private def process(layout: String): BufferedImage = layout match {
    case XY_LAYOUT =>
      val layout = new XYLayout(xml)
      layout.draw
    case _ => new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB)
  }

  private def write(image: BufferedImage) = ImageIO.write(image, format, outputPath)
}

/*
 * Defines an abstract layout and draws a BufferedImage.
 */
trait Layout {
  def draw: BufferedImage
}