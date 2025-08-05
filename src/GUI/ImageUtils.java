package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class ImageUtils{

    public static BufferedImage adjustBrightness(BufferedImage img, float brightnessFactor) {
        // brightnessFactor > 1.0 augmente la luminosité, < 1.0 l'assombrit
        RescaleOp rescaleOp = new RescaleOp(brightnessFactor, 0, null);
        BufferedImage brightenedImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        rescaleOp.filter(img, brightenedImage);
        return brightenedImage;
    }

    public static Image toGrayScale(Image image){return toGrayscale(image, false, 25);}
    public static Image toGrayscale(Image img, boolean brighter, int brightnessPercent) {
        ImageFilter filter = new GrayFilter(brighter, brightnessPercent);
        ImageProducer producer = new FilteredImageSource(img.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(producer);
    }

    public static BufferedImage convertToARGB(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

}
