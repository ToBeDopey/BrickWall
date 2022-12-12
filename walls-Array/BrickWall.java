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

    private String[] colors;
    private int currentColor; 

    private Rectangle[] bricks;

    private boolean multicolor;
    private boolean decreasing;
    private boolean symmetrical;

    private int wallX;
    private int wallY;

    private int brickHeight;
    private int brickWidth;
    private int bricksUsed;
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

        bricks = new Rectangle[wallHeight * wallWidth];
        //complete the constructor   
    }

    private void setUpColors() {
        colors = new String[6];
        colors[0] = "red";
        colors[1] = "yellow";
        colors[2] = "blue";
        colors[3] = "green";
        colors[4] = "magenta";
        colors[5] = "black";    
    }

    public boolean getMulticolor() {
        return multicolor;
    }

    public void toggleMultiColor() {
        multicolor = !multicolor;
        currentColor = 0;
    }
    /**
     * @return the number of bricks in the current wall.
     */

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

    public void drawTriangle(){
        int yPos = wallY;
        int newWall = wallWidth;
        for(int numOfRowsDrawn = 0; numOfRowsDrawn < wallHeight; numOfRowsDrawn++){
            drawRow(wallX ,yPos, newWall--);
            yPos -= brickHeight;
        }
    }

    public void eraseWall() {
        Canvas canvas = Canvas.getCanvas();
        for(int i = 0; i < bricks.length; ++i) {
            canvas.erase(bricks[i]);
        }
        bricks = new Rectangle[wallWidth * wallHeight];
    }

    /**
     * Accessor for the colors array
     */
    public String[] getColors(){
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

    public void setSymmetrical(boolean newSymmetrical){
        symmetrical = newSymmetrical;
    }

    public void setDecreasing(boolean newDecreasing){
        decreasing = newDecreasing;
    }

    public void drawSymmetrical(){
        if (symmetrical == true){
            int yPos = wallY;
            int newWall = wallWidth;
            for(int numOfRowsDrawn = 0; numOfRowsDrawn < wallHeight; numOfRowsDrawn++){
                drawRow(wallX + (26*numOfRowsDrawn) ,yPos, newWall--);
                yPos -= brickHeight;
            }
        } else if (symmetrical == false) {
            drawRectangle();
        }
    }

    public void draw(){
        if (decreasing == true){
            drawTriangle();
        } else if (decreasing == false) {
            drawRectangle();
        }
    }

    public int getCurrentColor(){
        return currentColor;
    }

    public void drawBrick(int x, int y){
        Rectangle newBrick = new Rectangle();
        newBrick.setPosition(x,y);
        newBrick.changeSize(brickWidth, brickHeight);
        newBrick.changeColor(colors[currentColor]);
        newBrick.makeVisible();
        if (multicolor == true){
            currentColor++;
            currentColor = currentColor % 6;
        }
        bricks[bricksUsed] = newBrick;
        bricksUsed = bricksUsed + 1;
    }

    public int getBricksUsed(){
        return bricksUsed;
    }

    public Rectangle getBrick(int brickNo){
        return bricks[brickNo];
    }

    public Rectangle[] getBricks(){
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

