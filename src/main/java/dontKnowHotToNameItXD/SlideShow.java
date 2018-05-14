package dontKnowHotToNameItXD;

import javafx.scene.image.ImageView;
import javafx.stage.Screen;
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

    private int slideNumber;
    XMLSlideShow sShow;
    java.util.List<XSLFSlide> slide;
    Dimension size;

    SlideShow()
    {

    }

    public BufferedImage Slide2Img(int id)
    {
        BufferedImage img = new BufferedImage(size.width,size.width,BufferedImage.TYPE_INT_RGB);

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

    public void nextSlide()
    {
        if(this.slide.size() > this.slideNumber)
            this.slideNumber ++;
    }

    public void prevSlide()
    {
        if(this.slide.size() < this.slideNumber)
            this.slideNumber --;
    }

    public static void main(String[] args) {
        Project display= new Project();
        SlideShow test=new SlideShow();
        test.open("piesni//gorzkie żale cz.1.pptx");

        test.slideNumber =0;
        BufferedImage img = test.Slide2Img(test.slideNumber);
        display.loadImage(img);

        test.slideNumber =0;
        img = test.Slide2Img(test.slideNumber);
        display.loadImage(img);



        test.end();

    }
}
