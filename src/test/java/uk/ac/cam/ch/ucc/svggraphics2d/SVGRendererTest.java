package uk.ac.cam.ch.ucc.svggraphics2d;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.layout.StructureDiagramGenerator;
import org.openscience.cdk.renderer.AtomContainerRenderer;
import org.openscience.cdk.renderer.font.AWTFontManager;
import org.openscience.cdk.renderer.generators.BasicAtomGenerator;
import org.openscience.cdk.renderer.generators.BasicBondGenerator;
import org.openscience.cdk.renderer.generators.BasicSceneGenerator;
import org.openscience.cdk.renderer.generators.IGenerator;
import org.openscience.cdk.renderer.visitor.AWTDrawVisitor;
import org.openscience.cdk.templates.MoleculeFactory;
import org.xmlcml.graphics.svg.SVGUtil;

public class SVGRendererTest {

	@Test
	public void testSVGRenderer() throws IOException {
		int width = 200;
		int height = 200;

		Rectangle drawArea = new Rectangle(width, height);
		Image image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		IAtomContainer triazole = MoleculeFactory.make123Triazole();
		StructureDiagramGenerator sdg = new StructureDiagramGenerator();
		sdg.setMolecule(triazole);
		try {
			sdg.generateCoordinates();
		} catch (Exception e) {
		}
		triazole = sdg.getMolecule();

		List<IGenerator<IAtomContainer>> generators = new ArrayList<IGenerator<IAtomContainer>>();
		generators.add(new BasicSceneGenerator());
		generators.add(new BasicBondGenerator());
		generators.add(new BasicAtomGenerator());

		AtomContainerRenderer renderer = new AtomContainerRenderer(generators, new AWTFontManager());

		renderer.setup(triazole, drawArea);

		Graphics2D g2 = (Graphics2D) image.getGraphics();
		
		SVGRenderer svgR = new SVGRenderer(g2);
		
		renderer.paint(triazole, new AWTDrawVisitor(svgR));
		
		SVGUtil.debug(svgR.getSVG(), new FileOutputStream(new File("C:\\workspace\\triazole.svg")), 0);
		
		Assert.assertEquals("Test for number of SVG elements", 13, svgR.getSVG().getChildCount());
	}
	
}