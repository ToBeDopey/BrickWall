import java.util.*;
import java.util.ArrayList; 
/**
 * A Java tool to draw brick walls
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BrickWall
{
    private int wallHeight; //Wall height in bricks, not pixels
    private int wallWidth; //Wall width in bricks, not pixels

    private ArrayList<String> colors;
    private int currentColor; 

    private ArrayList<Rectangle> bricks;

    private boolean multicolor;
    private boolean decreasing;
    private boolean symmetrical;

    private int wallX;
    private int wallY;

    private int brickHeight;
    private int brickWidth;

    // TO-DO validate wall height and width and maximum height and width
    //complete with remaining field declarations

    /**
     * Constructor for objects of class BrickWall.
     * @param rows The number of rows in the wall
     * @param rowlen The maximum number of bricks in a row
     */
    public BrickWall(int wallHeight, int wallWidth)
    {
        setUpColors();
        currentColor = 0;
        this.wallHeight = wallHeight;
        this.wallWidth = wallWidth;

        wallX = 20;
        wallY = 500;

        brickHeight = 15;
        brickWidth = 52;

        multicolor = false;
        decreasing = false;
        symmetrical = false;

        bricks = new ArrayList<Rectangle>();
        //complete the constructor   
    }

    private void setUpColors() {
        colors = new ArrayList<String>();
        colors.add("red");
        colors.add("yellow");
        colors.add("blue");
        colors.add("green");
        colors.add("magenta");
        colors.add("black");    
    }

    public boolean getMulticolor() {
        return multicolor;
    }

    public void switchMultiColor() {
        multicolor = !multicolor;
        currentColor = 0;
    }

    /**
     * Draw the wall according to the specification.
     */
    public void drawRectangle()
    {
        int yPos = wallY;
        for(int numOfRowsDrawn = 0; numOfRowsDrawn < wallHeight; numOfRowsDrawn++){
            drawRow(wallX ,yPos, wallWidth);
            yPos -= brickHeight;
        }
    }

    public void drawDecrease(){
        int yPos = wallY;
        int newDecrease = wallWidth;
        for(int numOfRowsDrawn = 0; numOfRowsDrawn < wallHeight; numOfRowsDrawn++){
            drawRow(wallX ,yPos, newDecrease--);
            yPos -= brickHeight;
        }
    }

    public void eraseWall() {
        Canvas canvas = Canvas.getCanvas();

        for(int i = 0; i < bricks.size(); ++i) {
            canvas.erase(bricks.get(i));
        }
        bricks = new ArrayList<Rectangle>();
    }

    /**
     * Accessor for the colors array
     */
    public ArrayList<String> getColors(){
        return colors;
    }

    public int getWallHeight(){
        return wallHeight;
    }

    public int getWallWidth(){
        return wallWidth;
    }

    public int getBrickHeight(){
        return brickHeight;
    }

    public int getBrickWidth(){
        return brickWidth;
    }

    public int getWallX(){
        return wallX;
    }

    public int getWallY(){
        return wallY;
    }

    public boolean getDecreasing(){
        return decreasing;
    }

    public boolean getSymmetrical(){
        return symmetrical;
    }

    public int getCurrentColor(){
        return currentColor;
    }

    public void setSymmetrical(boolean newSymmetrical){
        symmetrical = newSymmetrical;
    }

    public void setDecreasing(boolean newDecreasing){
        decreasing = newDecreasing;
    }

    public void drawBrick(int x, int y){
        Rectangle newBrick = new Rectangle();
        newBrick.setPosition(x,y);
        newBrick.changeSize(brickWidth, brickHeight);
        newBrick.changeColor(colors.get(currentColor));
        newBrick.makeVisible();

        if (multicolor == true){
            currentColor++;
            currentColor = currentColor % 6;
        }

        bricks.add(newBrick);

    }

    public void drawSymmetrical(){
        if (symmetrical == true){
            int yPos = wallY;
            int newDecrease = wallWidth;
            for(int numOfRowsDrawn = 0; numOfRowsDrawn < wallHeight; numOfRowsDrawn++){
                drawRow(wallX + (26 * numOfRowsDrawn) ,yPos, newDecrease--);
                yPos -= brickHeight;
            }
        } else if (symmetrical == false) {
            drawRectangle();
        }
    }

    public void draw(){
        if (decreasing == true){
            drawDecrease();
        } else if (decreasing == false) {
            drawRectangle();
        }
    }

    public Rectangle getBrick(int brickNo){
        return bricks.get(brickNo);
    }

    public ArrayList<Rectangle> getBricks(){
        return bricks;
    }

    public void drawRow(int x, int y, int numOfBricks){
        int xPos = x;
        for (int numOfBricksDrawn = 0; numOfBricksDrawn < numOfBricks; numOfBricksDrawn++){
            drawBrick(xPos , y);
            xPos += brickWidth;
        }
    }
}

