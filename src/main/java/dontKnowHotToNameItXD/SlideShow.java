package dontKnowHotToNameItXD;

import javafx.scene.control.Alert;
import org.apache.poi.EmptyFileException;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class SlideShow {

    private int slideNumber;
    private XMLSlideShow slideShow;
    private List<XSLFSlide> slides;
    private Dimension size;

    private BufferedImage slide2Img(int id) {
        BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = img.createGraphics();

        graphics.setPaint(Color.black);
        graphics.fill(new Rectangle2D.Float(0, 0, size.width, size.height));

        slides.get(id).draw(graphics);

        graphics.dispose();
        return img;
    }

    public boolean open(Song song) {
        try {
            this.slideShow = new XMLSlideShow(new FileInputStream(song.getFile()));
            size = slideShow.getPageSize();
            slides = slideShow.getSlides();
        } catch (IOException e) {
            AlertFactory
                    .createError("Plik nie istnieje")
                    .showAndWait();
            return false;
        } catch (EmptyFileException e) {
            AlertFactory
                    .createError("Plik jest pusty")
                    .showAndWait();
            return false;
        }
        slideNumber = 0;
        return true;
    }

    public void end() {
        try {
            slideShow.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage currentSlide() {
        return slide2Img(slideNumber);
    }

    public BufferedImage nextSlide() {
        if (slides.size() > slideNumber) {
            slideNumber++;
            return slide2Img(slideNumber);
        }
        throw new RuntimeException("Nie ma następnego slajdu");
    }

    public BufferedImage prevSlide() {
        if (0 < slideNumber) {
            slideNumber--;
            return slide2Img(slideNumber);
        }
        throw new RuntimeException("Nie ma wcześniejszego slajdu");
    }
}
