package de.javenue

/**
 * All supported Graphics Formats.
 */
object GraphicsFormat extends Enumeration {
  type GraphicsFormat = Value

  // PNG-Grafikformat
  val PNG = Value("png")
  // BMP-Grafikformat
  val BMP = Value("bmp")
}