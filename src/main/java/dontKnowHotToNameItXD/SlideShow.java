package dontKnowHotToNameItXD;

import javafx.scene.image.ImageView;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SlideShow
{
XMLSlideShow sShow;
java.util.List<XSLFSlide> slide;
Dimension size;
SlideShow()
{

}

public BufferedImage Slide2Img(int id)
    {
        BufferedImage img = new BufferedImage(size.width,size.height,BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = img.createGraphics();

        //clear the drawing area
        graphics.setPaint(Color.white);
        graphics.fill(new Rectangle2D.Float(0, 0, size.width, size.height));

        //render
        slide.get(id).draw(graphics);
        graphics.dispose();
        return img;
    }


public void open(String filename) {
    try {
        this.sShow = new XMLSlideShow(new FileInputStream(filename));
    } catch (IOException e) {
        e.printStackTrace();
    }
    size = sShow.getPageSize();
    slide = sShow.getSlides();

}
    public void end()
    {
        try {
            sShow.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
    Project display= new Project();
    SlideShow test=new SlideShow();
    test.open("piesni//gorzkie żale cz.1.pptx");

        BufferedImage img = test.Slide2Img(1);
        display.loadImage(img);
        test.end();
    }
}
