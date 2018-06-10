package dontKnowHotToNameItXD;

import converters.SlideToImageConverter;
import javafx.scene.image.Image;
import org.apache.poi.EmptyFileException;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class SlideShow {

    private int slideNumber;
    private List<XSLFSlide> slides;
    private Dimension dimension;
    private SlideToImageConverter slide2Img = new SlideToImageConverter();

    public void open(Song song) {
        XMLSlideShow slideShow = songToSlideShow(song);
        dimension = slideShow.getPageSize();
        slides = slideShow.getSlides();
        slideNumber = 0;
    }

    private XMLSlideShow songToSlideShow(Song song) {
        XMLSlideShow slideShow = null;
        try {
            slideShow = new XMLSlideShow(new FileInputStream(song.getFile()));
        } catch (IOException e) {
            AlertFactory
                    .createError("Plik nie istnieje")
                    .showAndWait();
        } catch (EmptyFileException e) {
            AlertFactory
                    .createError("Plik jest pusty")
                    .showAndWait();
        }
        return slideShow;
    }

    public Image currentSlide() {
        XSLFSlide slide = slides.get(slideNumber);
        return slide2Img.toImage(slide, dimension);
    }

    public Image nextSlide() {
        if (slides.size() > slideNumber) {
            slideNumber++;
            XSLFSlide slide = slides.get(slideNumber);
            return slide2Img.toImage(slide, dimension);
        }
        throw new RuntimeException("Nie ma następnego slajdu");
    }

    public Image prevSlide() {
        if (0 < slideNumber) {
            slideNumber--;
            XSLFSlide slide = slides.get(slideNumber);
            return slide2Img.toImage(slide, dimension);
        }
        throw new RuntimeException("Nie ma wcześniejszego slajdu");
    }
}
