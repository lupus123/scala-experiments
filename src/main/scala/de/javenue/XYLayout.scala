package de.javenue

import java.awt.Color
import java.awt.image.BufferedImage
import xml.Elem

/**
 * Implementation of the abstract class Layout.
 * Defines 
 * User: bck
 */
class XYLayout(xml: Elem) extends Layout with Offset with Colored {
  val x_size = (xml \ "x-size").text.toInt
  val y_size = (xml \ "y-size").text.toInt
  val background_color = (xml \ "background-color").text

  val x1 = (x_size - (x_size - 2 * x_offset)) / 2
  val y1 = y_size - y_offset
  val x2 = x_size - x_offset
  val y2 = y1
  val x3 = x_offset
  val y3 = y_offset

  val chart = new XYChart(x_size, y_size)

  def draw: BufferedImage = {
    // Background
    chart.colorize(LIGHT_GRAY)
    chart.drawFilledRectangle(new Point(0, 0), new Point(x_size, y_size))

    // Draw area
    chart.colorize(WHITE)
    chart.drawFilledRectangle(new Point(x3, y3), new Point(x_size - 2 * x_offset, y_size - 2 * y_offset))

    // X-Scale
    chart.colorize(new Color(128, 128, 128, 255))
    chart.drawLine(new Point(x1, y1), new Point(x2, y2))

    // Y-Scale
    chart.drawLine(new Point(x1, y1), new Point(x3, y3))
    chart.image
  }
}

