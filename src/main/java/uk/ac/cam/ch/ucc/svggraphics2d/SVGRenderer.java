package uk.ac.cam.ch.ucc.svggraphics2d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
/**
 *    Copyright 2014 Andrew Howlett
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import org.xmlcml.euclid.Real2;
import org.xmlcml.euclid.Real2Array;
import org.xmlcml.graphics.svg.SVGElement;
import org.xmlcml.graphics.svg.SVGLine;
import org.xmlcml.graphics.svg.SVGPolygon;
import org.xmlcml.graphics.svg.SVGRect;
import org.xmlcml.graphics.svg.SVGSVG;
import org.xmlcml.graphics.svg.SVGText;

import nu.xom.Element;

public class SVGRenderer extends Graphics2D {

	private SVGSVG svg = new SVGSVG();
	private Font font;
	private FontRenderContext cont;
	private Color colour;
	private BasicStroke awtStroke;
	
	public SVGRenderer(Graphics2D input) {
		font = input.getFont();
		cont = input.getFontRenderContext();
		svg.setHeight(input.getDeviceConfiguration().getBounds().getHeight());
		svg.setWidth(input.getDeviceConfiguration().getBounds().getWidth());
	}
	
	@Override
	public void draw(Shape s) {
		if (s instanceof Line2D.Double) {
			drawLine(((Line2D.Double) s).x1, ((Line2D.Double) s).y1, ((Line2D.Double) s).x2, ((Line2D.Double) s).y2);
		}
	}

	@Override
	public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawString(String str, int x, int y) {
		SVGText text = new SVGText(new Real2(x, y), str);
		text.setFontSize((double) getFont().getSize());
		setFill(text);
		svg.appendChild(text);
	}

	private void setFill(SVGElement element) {
		element.setFill("rgb(" + colour.getRed() + ", " + colour.getGreen() + ", " + colour.getBlue() + ")");
	}

	private void setStroke(SVGElement element) {
		element.setStroke("rgb(" + colour.getRed() + ", " + colour.getGreen() + ", " + colour.getBlue() + ")");
		element.setStrokeWidth((double) awtStroke.getLineWidth());
	}

	@Override
	public void drawString(String str, float x, float y) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, float x,
			float y) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawGlyphVector(GlyphVector g, float x, float y) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void fill(Shape s) {
		if (s instanceof RoundRectangle2D) {
			SVGRect rect = new SVGRect(new Real2(((RoundRectangle2D) s).getMinX(), ((RoundRectangle2D) s).getMinY()), new Real2(((RoundRectangle2D) s).getMaxX(), ((RoundRectangle2D) s).getMaxY()));
			setFill(rect);
			setStroke(rect);
			svg.appendChild(rect);
		}
	}

	@Override
	public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void setComposite(Composite comp) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void setPaint(Paint paint) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void setStroke(Stroke s) {
		if (s instanceof BasicStroke) {
			awtStroke = (BasicStroke) s;
		}
	}

	@Override
	public void setRenderingHint(Key hintKey, Object hintValue) {
		
	}

	@Override
	public Object getRenderingHint(Key hintKey) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void setRenderingHints(Map<?, ?> hints) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void addRenderingHints(Map<?, ?> hints) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public RenderingHints getRenderingHints() {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void translate(int x, int y) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void translate(double tx, double ty) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void rotate(double theta) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void rotate(double theta, double x, double y) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void scale(double sx, double sy) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void shear(double shx, double shy) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void transform(AffineTransform Tx) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void setTransform(AffineTransform Tx) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public AffineTransform getTransform() {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public Paint getPaint() {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public Composite getComposite() {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void setBackground(Color color) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public Color getBackground() {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public Stroke getStroke() {
		return awtStroke;
	}

	@Override
	public void clip(Shape s) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public FontRenderContext getFontRenderContext() {
		return cont;
	}

	@Override
	public Graphics create() {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public Color getColor() {
		return colour;
	}

	@Override
	public void setColor(Color c) {
		colour = c;
	}

	@Override
	public void setPaintMode() {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void setXORMode(Color c1) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public FontMetrics getFontMetrics(Font f) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public Rectangle getClipBounds() {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void clipRect(int x, int y, int width, int height) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void setClip(int x, int y, int width, int height) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public Shape getClip() {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void setClip(Shape clip) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		SVGLine line = new SVGLine(new Real2(x1, y1), new Real2(x2, y2));
		setStroke(line);
		svg.appendChild(line);
	}

	public void drawLine(double x1, double y1, double x2, double y2) {
		SVGLine line = new SVGLine(new Real2(x1, y1), new Real2(x2, y2));
		setStroke(line);
		svg.appendChild(line);
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void clearRect(int x, int y, int width, int height) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawRoundRect(int x, int y, int width, int height,
			int arcWidth, int arcHeight) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void fillRoundRect(int x, int y, int width, int height,
			int arcWidth, int arcHeight) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawOval(int x, int y, int width, int height) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void fillOval(int x, int y, int width, int height) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle,
			int arcAngle) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle,
			int arcAngle) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		Real2Array points = new Real2Array();
		for (int i = 0; i < nPoints; i++) {
			points.add(new Real2(xPoints[i], yPoints[i]));
		}
		SVGPolygon poly = new SVGPolygon(points);
		setFill(poly);
		setStroke(poly);
		svg.appendChild(poly);
	}

	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height,
			ImageObserver observer) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor,
			ImageObserver observer) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height,
			Color bgcolor, ImageObserver observer) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
			int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
			int sx1, int sy1, int sx2, int sy2, Color bgcolor,
			ImageObserver observer) {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	@Override
	public void dispose() {
		throw new RuntimeException("Attempted to perform unsupported action whilst drawing SVG (e.g. tried to draw unsupported graphical object).");
	}

	public Element getSVG() {
		return svg;
	}

}