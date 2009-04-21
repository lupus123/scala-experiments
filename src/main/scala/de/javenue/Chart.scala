package de.javenue


import java.awt.image.BufferedImage
import java.awt.{Color, Graphics2D}

/**
 * Declaring an abstract chart object.
 *
 * @author Lutz Beckmann
 */
trait Chart extends Drawing  {
  def image: BufferedImage

  def g2d: Graphics2D
}

/**
 * Declaring a concrete xy chart object.
 *
 * @author Lutz Beckmann
 */
class XYChart(val length: Int, val height: Int) extends Chart {

  var image: BufferedImage = new BufferedImage(length, height, BufferedImage.TYPE_INT_ARGB)

  var g2d: Graphics2D = image.createGraphics

  def drawLine(p1: Point, p2: Point) = g2d.drawLine(p1.x, p1.y, p2.x, p2.y)

  def drawFilledRectangle(p1: Point, p2: Point) = g2d.fillRect(p1.x, p1.y, p2.x, p2.y)

  def colorize(color: Color) = g2d.setColor(color)
}