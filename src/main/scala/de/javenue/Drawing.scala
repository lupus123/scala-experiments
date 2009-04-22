package de.javenue

import java.awt.Color

/**
 * Declaring abstract draw methods.
 *
 * @author Lutz Beckmann
 */
trait Drawing {
  def drawLine(p1: Point, p2: Point)

  def drawFilledRectangle(p1: Point, p2: Point)

  def colorize(color: Color)
}