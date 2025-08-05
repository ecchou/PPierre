package GUI.Components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture extends Component{

    private Image image;
    private int width;
    private int height;

    ///  CONSTRUCTEURS
    public Picture(int x, int y, int width, int height, int action, Image image){
        // BufferedImage donnée direct
        super(x, y, action);
        init(image, width, height);
    }
    public Picture(int x, int y, int width, int height, int action, String imagePath) throws IOException {
        // Lien vers image donné
        super(x, y, action);
        Image img = ImageIO.read(new File(imagePath));
        init(img, width, height);
    }


    private void init(Image img, int w, int h){
        this.image = img;
        this.width = w;
        this.height = h;
    }

    public void draw(Graphics2D g2d){

        g2d.drawImage(image, getX(), getY(), width, height, null);

    }

}
