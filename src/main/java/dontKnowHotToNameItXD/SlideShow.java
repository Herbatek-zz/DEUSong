package dontKnowHotToNameItXD;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.*;
import java.util.List;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class SlideShow {
    private BufferedImage img;
    private int slideNumber;
    private XMLSlideShow sShow;
    private List<XSLFSlide> slide;
    private Dimension size;

    private BufferedImage Slide2Img(int id) {
        BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = img.createGraphics();
        //clear the drawing area
        graphics.setPaint(Color.black);
        graphics.fill(new Rectangle2D.Float(0, 0, size.width, size.height));

        //  graphics.
        //render
        slide.get(id).draw(graphics);


        graphics.dispose();
        return img;
    }


    public boolean open(String filename) {
        boolean success = true;
        try {
            this.sShow = new XMLSlideShow(new FileInputStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        }
        size = sShow.getPageSize();
        slide = sShow.getSlides();
        return success;
    }

    public void end() {
        try {
            sShow.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void firstSlide(Project disp) {
        slideNumber = 0;
        img = Slide2Img(slideNumber);
        disp.loadImage(img);
    }

    public void nextSlide(Project disp) {
        if (slide.size() > slideNumber) {
            slideNumber++;
            img = Slide2Img(slideNumber);
            disp.loadImage(img);
        }
    }

    public void prevSlide(Project disp) {
        if (0 < slideNumber) {
            slideNumber--;
            img = Slide2Img(slideNumber);
            disp.loadImage(img);
        }
    }

    public static void main(String[] args) {
        Project display = new Project();
        SlideShow test = new SlideShow();

        if (test.open("piesni//gorzkie żale cz.3.pptx")) {
            test.firstSlide(display);

            // test.nextSlide(display);

        } else {
            System.out.print("Nie wczytano pieśni!");
        }
        test.end();

    }
}
