package fr.feavy.javaPlatformer;

import fr.feavy.javaPlatformer.map.BasicTile;
import fr.feavy.javaPlatformer.map.Map;
import fr.feavy.javaPlatformer.map.Tile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        try {
            JavaPlatformer game = new JavaPlatformer(32);
            game.setMap(Map.fromFile("/map", new Tile[]{new BasicTile(new Color(0x2196f3)), new Tile(new Color(0xF34F52)) {
                @Override
                public void onCollision(Entity e, Side side) {
                    if (side == Side.TOP && !e.isFrozen()) {
                        //e.freeze();
                        //System.err.println("Vous êtes mort !");
                    }
                }
            }}));
            game.setPlayer(new Player(128, game.getMap().getHeight() - 2 * 32));
            game.setCamera(new Camera(game.getPlayer(), 800, 800));

            for (int i = 0; i < 3; i++)
                game.addEntity(new Entity(256, 100, 28, 32) {

                    private int count = 0;
                    private Random rand = new Random();

                    @Override
                    public void onCollision(Entity other, Side side) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void update() {
                        super.update();
                        count++;
                        if (count >= 10) {
                            count = 0;
                            int r = rand.nextInt(2);
                            int r2 = rand.nextInt(2);
                            if (r2 == 0)
                                jump();
                            if (r == 0) {
                                startMoving(Direction.LEFT);
                            } else {
                                startMoving(Direction.RIGHT);
                            }
                        }
                    }
                });

            frame.setContentPane(game);

            game.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        frame.setVisible(true);
    }
}
