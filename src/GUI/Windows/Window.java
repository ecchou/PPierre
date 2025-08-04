package GUI.Windows;

import GUI.Components.Component;
import GUI.Scenes.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Window extends JFrame {

    private BufferedImage image;
    private JPanel panel;
    private boolean running;

    private String title;
    private int width;
    private int height;
    private List<Component> components;
    private List<Component> clicking = new ArrayList<>();

    private int mouseX;
    private int mouseY;
    private boolean mouseMoved = false;

    ///  CONSTRUCTEURS
    public Window(String title, int width, int height) {
        // Fenêtre vide
        this.title = title;
        this.width = width;
        this.height = height;
        components = new ArrayList<>();

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        running = false;
    }
    public Window(String title, int width, int height, List<Component> components) {
        // Liste de components
        this.title = title;
        this.width = width;
        this.height = height;
        this.components = components;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        running = false;
    }
    public Window(String title, int width, int height, Scene scene) {
        // Scène
        this.title = title;
        this.width = width;
        this.height = height;
        this.components = scene.getComponents();

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        running = false;
    }

    ///  GETTERS


    ///  SETTERS
    public void setComponents(List<Component> components) {
        this.components = components;
    }
    public void addComponent(Component component) {components.add(component);}
    public void removeComponent(Component component) {components.remove(component);}

    /// RUNNING
    public void init() throws IOException{

        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        panel.setDoubleBuffered(true);

        panel.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent e){
                mouseX = e.getX();
                mouseY = e.getY();
                mouseMoved = true;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                mouseMoved = true;
            }
        });

        panel.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                press(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e){
                release(e.getX(), e.getY());
            }
        });

        pack();
        setLocationRelativeTo(null);

        running = true;
        setVisible(true);
        new Thread(() -> {
            try {
                mainLoop();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void mainLoop() throws IOException {
        long lastTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long optimalTime = 1000000000 /  TARGET_FPS;

        while(running){
            long now = System.nanoTime();
            long elapsedTime =  now - lastTime;
            lastTime = now;

            draw();

            long sleepTime = (optimalTime - elapsedTime) / 1000000;
            if (sleepTime > 0){
                try{
                    Thread.sleep(sleepTime);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            SwingUtilities.invokeLater(panel::repaint);
        }
    }

    public void draw() throws IOException {
        Graphics2D g2d = image.createGraphics();


        if (mouseMoved){
            for (Component component : components)
                component.setHovered(mouseX, mouseY);
            mouseMoved = false;
        }
        for (Component component : components) {
            component.draw(g2d);
        }

        g2d.dispose();

    }

    public void press(int x, int y){
        System.out.println("Click en " + mouseX + " ; " + mouseY);
        for (Component component : components) {
            if (component.getHovered())
                clicking.add(component);
        }
    }

    public void release(int x, int y){
        for (Component component : components) {
            if (component.getHovered() && clicking.contains(component))
                action(component.getAction());
        }
        clicking.clear();
    }

    /*
    public void click(int x, int y){
        System.out.println(String.format("Click en %d ; %d", x, y));

        //for (Component component : components)
       //    if (component.getHovered())
       //        action(component.getAction());
    }
    */

    public abstract void action(int action);
}
