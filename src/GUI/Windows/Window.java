package GUI.Windows;

import GUI.Components.Component;
import GUI.Scenes.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Window extends JFrame {

    private Canvas canvas;
    private BufferedImage image;
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
        this.title = title;
        this.width = width;
        this.height = height;
        this.components = new ArrayList<>();
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.running = false;
    }

    public Window(String title, int width, int height, List<Component> components) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.components = components;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.running = false;
    }

    public Window(String title, int width, int height, Scene scene) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.components = scene.getComponents();
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.running = false;
    }

    ///  INIT
    public void init() throws IOException {
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setIgnoreRepaint(true);

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(canvas);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        canvas.createBufferStrategy(2); // Double buffering

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
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

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                press(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                release(e.getX(), e.getY());
            }
        });

        running = true;
        new Thread(() -> {
            try {
                mainLoop();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    ///  MAIN LOOP
    private void mainLoop() throws IOException {
        BufferStrategy bs = canvas.getBufferStrategy();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1_000_000_000 / TARGET_FPS;

        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            long elapsedTime = now - lastTime;
            lastTime = now;

            draw(); // Draw on image

            do {
                do {
                    Graphics g = bs.getDrawGraphics();
                    g.drawImage(image, 0, 0, null); // Push image to screen
                    g.dispose();
                } while (bs.contentsRestored());
                bs.show();
            } while (bs.contentsLost());

            long sleepTime = (OPTIMAL_TIME - (System.nanoTime() - now)) / 1_000_000;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    ///  DRAW
    public void draw() throws IOException {
        Graphics2D g2d = image.createGraphics();

        drawBG(g2d);

        // Mise à jour des hover si souris bougée
        if (mouseMoved) {
            //System.out.println(mouseX + " ; " + mouseY);
            for (Component component : components)
                component.setHovered(mouseX, mouseY);
            mouseMoved = false;
        }

        // Dessiner tous les composants
        for (Component component : components) {
            component.draw(g2d);
        }

        g2d.dispose();
    }

    // Par défaut fond blanc, à Override
    protected void drawBG(Graphics2D g2d){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
    }

    ///  INTERACTIONS
    public void press(int x, int y) {
        for (Component component : components) {
            if (component.isHovered())
                clicking.add(component);
        }
    }

    public void release(int x, int y) {
        for (Component component : components) {
            if (component.isHovered() && clicking.contains(component)){
                try {
                    action(component.getAction());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        clicking.clear();
    }

    ///  MANIPULATIONS
    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    public abstract void action(int action) throws IOException;
}