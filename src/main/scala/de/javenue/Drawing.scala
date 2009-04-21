package de.javenue


import java.awt.Color

/**
 * Created by IntelliJ IDEA.
 * User: bck
 * Date: 21.04.2009
 * Time: 10:15:08
 * To change this template use File | Settings | File Templates.
 */

trait Drawing {
  def drawLine(p1: Point, p2: Point)

  def drawFilledRectangle(p1: Point, p2: Point)

  def colorize(color: Color)

}