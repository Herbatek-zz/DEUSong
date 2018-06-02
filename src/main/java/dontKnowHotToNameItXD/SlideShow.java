package dontKnowHotToNameItXD;

import javafx.scene.control.Alert;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class SlideShow {
    private BufferedImage image;
    private int slideNumber;
    private XMLSlideShow sShow;
    private List<XSLFSlide> slides;
    private Dimension size;

    private BufferedImage Slide2Img(int id) {
        BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = img.createGraphics();
        //clear the drawing area
        graphics.setPaint(Color.black);
        graphics.fill(new Rectangle2D.Float(0, 0, size.width, size.height));

        //  graphics.
        //render
        slides.get(id).draw(graphics);


        graphics.dispose();
        return img;
    }


    public void open(String filePath) {
        try {
            this.sShow = new XMLSlideShow(new FileInputStream(filePath));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Niestety wystąpił błąd...");
            alert.showAndWait();
        }
        size = sShow.getPageSize();
        slides = sShow.getSlides();
        slideNumber = 0;
    }

    public void end() {
        try {
            sShow.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CurrentSlide(Project disp) {
        image = Slide2Img(slideNumber);
        disp.loadImage(image);
    }

    public void nextSlide(Project disp) {
        if (slides.size() > slideNumber) {
            slideNumber++;
            image = Slide2Img(slideNumber);
            disp.loadImage(image);
        }
    }

    public void prevSlide(Project disp) {
        if (0 < slideNumber) {
            slideNumber--;
            image = Slide2Img(slideNumber);
            disp.loadImage(image);
        }
    }


}
