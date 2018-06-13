package converters;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class SlideToImageConverter {

    private BufferedImage toBufferedImage(XSLFSlide slide, Dimension dimension) {
        BufferedImage bufferedImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();

        graphics.setPaint(Color.black);
        graphics.fill(new Rectangle2D.Float(0, 0, dimension.width, dimension.height));

        slide.draw(graphics);

        graphics.dispose();
        return bufferedImage;
    }

    public Image toImage(XSLFSlide slide, Dimension dimension) {
        BufferedImage bufferedImage = toBufferedImage(slide, dimension);
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }
}
