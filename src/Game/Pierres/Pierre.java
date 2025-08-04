package Game.Pierres;

import GUI.Components.Text;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Pierre {

    ///  ATTRIBUTS
    // Frame
    private final static int FRAME_WIDTH = 200;
    private final static int FRAME_HEIGHT = 300;
    private final static Point FRAME_PIERRE_LOCATION = new Point(52, 35);
    private final static int FRAME_PIERRE_SIZE = 96;
    private final static Point FRAME_NAME_LOCATION = new Point(FRAME_WIDTH / 2, 145);
    private final static Point FRAME_PRICE_LOCATION = new Point(FRAME_WIDTH / 2, 220);
    private final static int FRAME_PRICE_SIZE = 30;
    private final static List<String> framePaths = List.of(
            "img/game/frames/1.png",
            "img/game/frames/2.png",
            "img/game/frames/3.png"
    );
    private final static List<Image> frames = new ArrayList<>();

    // Pierre
    private int player;
    private double price;
    private boolean frozen;

    ///  CONSTRUCTEUR
    public Pierre(int player, double price) throws IOException {
        this.player = player;
        this.price = price;

        for (String framePath : framePaths) {
            frames.add(ImageIO.read(new File(framePath)));
        }
    }

    ///  ABSTRACT
    public abstract int getID();
    public abstract int getLevel();
    public abstract double getInitialPrice();
    public abstract String getName();
    public abstract String getImagePath();

    ///  GETTERS
    public int getPlayer(){
        return player;
    }
    public double getPrice(){
        return price;
    }
    public boolean isFrozen(){
        return frozen;
    }
    protected void forceSetFreeze(boolean gel){ this.frozen = gel;}
    public Image getSprite() throws IOException { return ImageIO.read(new File(getImagePath())); }

    ///  SETTERS
    public void setPrice(double prix){
        this.price = prix;
    }

    ///  IMAGE CARTE
    public int getFrameNameFontSize(){ return 20; }     // A écraser pour les pierres avec des noms longs éventuellement
    public Image getCardSprite() throws IOException {
        BufferedImage sprite = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = sprite.createGraphics();

        g2d.drawImage(frames.get(getLevel()),0,0, FRAME_WIDTH, FRAME_HEIGHT,null);
        g2d.drawImage(getSprite(), FRAME_PIERRE_LOCATION.x, FRAME_PIERRE_LOCATION.y, FRAME_PIERRE_SIZE, FRAME_PIERRE_SIZE, null);

        Text txtName = new Text(
                FRAME_NAME_LOCATION.x,
                FRAME_NAME_LOCATION.y,
                getFrameNameFontSize(),
                getName(),
                Text.Padding.CENTERED,
                Color.BLACK
        );
        txtName.draw(g2d);

        Text txtPrice = new Text(
                FRAME_PRICE_LOCATION.x,
                FRAME_PRICE_LOCATION.y,
                FRAME_PRICE_SIZE,
                Double.toString(getPrice()),
                Text.Padding.CENTERED,
                Color.BLACK
        );
        txtPrice.draw(g2d);

        g2d.dispose();
        return sprite;
    }

    ///  GAMEPLAY
    public boolean freeze(){
        forceSetFreeze(true);
        return true;
    }

}
