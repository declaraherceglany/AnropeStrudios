/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchthebus;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Level {

    private final int ROAD_WIDTH = 100;
    private final int ROAD_HEIGHT = 100;
    ArrayList<Road> roads;
    ArrayList<Pair> coordinates;
    String directions;

    public Level(String levelPath, String fileName) throws IOException {
        loadLevel(levelPath);
        loadCoordinates(fileName);
    }

    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        roads = new ArrayList<>();
        int y = 0;
        String line;
        //String stratLine = br.readLine();
        while ((line = br.readLine()) != null) {
            int x = 0;
            for (char type : line.toCharArray()) {
                if (type == 'r') {
                    Image image = new ImageIcon("src/data/pngs/road.png").getImage();
                    roads.add(new Road(x * ROAD_WIDTH, y * ROAD_HEIGHT, ROAD_WIDTH, ROAD_HEIGHT, image));
                }
                x++;
            }
            y++;
        }
    }

    public void loadCoordinates(String fileName) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        coordinates = new ArrayList<>();
        String line;
        directions = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] pair = line.split(",");
            int p0 = Integer.parseInt(pair[0]);
            int p1 = Integer.parseInt(pair[1]);
            Pair p = new Pair(p0,p1);
            coordinates.add(p);
        }
    }
    public ArrayList<Pair> getCoordinates(){
        
        return coordinates;
    }

    public String getDirections() {
        return directions;
    }
    
    
    
    /*TODO COLLIDE LETÉVENDŐ TORONY*/
    public void draw(Graphics g) {
        for (Road road : roads) {
            road.draw(g);
        }
        
    }
    
     public class Pair {

        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
