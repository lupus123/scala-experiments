package de.javenue


import java.awt.Graphics2D
import java.awt.image.BufferedImage

/**
 * Created by IntelliJ IDEA.
 * User: BCK
 * Date: 20.04.2009
 * Time: 11:06:02
 * To change this template use File | Settings | File Templates.
 */
trait Chart {
  def image: BufferedImage

  def g2d: Graphics2D
}

class XYChart(val length: Int, val height: Int) extends Chart {
  var image: BufferedImage = new BufferedImage(length, height, BufferedImage.TYPE_INT_ARGB)

  var g2d: Graphics2D = image.createGraphics
}