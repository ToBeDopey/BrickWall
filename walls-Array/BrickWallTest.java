
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BrickWallTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BrickWallTest
{
    /**
     * Default constructor for test class BrickWallTest
     */
    public BrickWallTest()
    {
    }

    @Test
    public void constructorTest(){
        BrickWall wall = new BrickWall(3, 4);
        assertEquals(3 , wall.getWallHeight());
        assertEquals(4, wall.getWallWidth());

        assertEquals(20, wall.getWallX());
        assertEquals(500, wall.getWallY());

        assertFalse(wall.getMulticolor());
        assertFalse(wall.getDecreasing());
        assertFalse(wall.getSymmetrical());

        assertEquals(52 ,wall.getBrickWidth());
        assertEquals(15 ,wall.getBrickHeight());
        assertEquals(0, wall.getCurrentColor());
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void drawBrickTest(){
        BrickWall wall = new BrickWall(3,5);
        wall.drawBrick(200, 100);
        Rectangle firstBrick = wall.getBricks()[0];
        assertEquals(1, wall.getBricksUsed());
        assertEquals(200, firstBrick.getXPosition());
        assertEquals(100, firstBrick.getYPosition());
        assertEquals("red", firstBrick.getColor());

        wall.drawBrick(300, 100);
        assertEquals(2, wall.getBricksUsed());
        Rectangle secondBrick = wall.getBrick(1);

        assertEquals(300, secondBrick.getXPosition());
        assertEquals(100, secondBrick.getYPosition());
        assertEquals("red", secondBrick.getColor());
    }

    @Test
    public void drawRowTest(){
        BrickWall wall = new BrickWall(3,5);
        wall.drawRow(20,500,4);

        assertEquals(4, wall.getBricksUsed());

        Rectangle firstBrick = wall.getBrick(0);
        assertEquals(20, firstBrick.getXPosition());
        assertEquals(500, firstBrick.getYPosition());
        assertEquals("red", firstBrick.getColor());

        // test 2nd brick.
        Rectangle secondBrick = wall.getBrick(1);
        assertEquals(72, secondBrick.getXPosition());
        assertEquals(500, secondBrick.getYPosition());
        assertEquals("red", secondBrick.getColor());
        Rectangle thirdBrick = wall.getBrick(2);
        assertEquals(124, thirdBrick.getXPosition());
        assertEquals(500, thirdBrick.getYPosition());
        assertEquals("red", thirdBrick.getColor());
        Rectangle fourthBrick = wall.getBrick(3);
        assertEquals(176, fourthBrick.getXPosition());
        assertEquals(500, fourthBrick.getYPosition());
        assertEquals("red", fourthBrick.getColor());
    }

    @Test
    public void drawRectangleWallTest(){
        BrickWall wall = new BrickWall(3,4);
        wall.drawRectangle();
        assertEquals(12, wall.getBricksUsed());
        Rectangle firstBrick = wall.getBrick(0);
        assertEquals(20, firstBrick.getXPosition());
        assertEquals(500, firstBrick.getYPosition());
        assertEquals("red", firstBrick.getColor());

        Rectangle lastBrick = wall.getBrick(11);
        assertEquals(176, lastBrick.getXPosition());
        assertEquals(470 , lastBrick.getYPosition());
        assertEquals("red", lastBrick.getColor());

    }

    /**
     * This test checks that the color array has been correctly
     * created and is of the intended length.
     */
    @Test
    public void checkColorsArrayLength(){
        BrickWall wall = new BrickWall(3, 5);
        assertNotNull(wall.getColors());
        assertEquals(6, wall.getColors().length);
    }

    /**
     * This test checks some of the content of the color array 
     * to verify that it contains the names of colours as expected.
     */
    @Test
    public void checkColorsArrayContent(){
        BrickWall wall = new BrickWall(3, 5);
        assertNotNull(wall.getColors());
        /* 
         * Local reference to the colors array to make code easier
         * to read.
         * English spelling for readability.
         */
        String[] colours = wall.getColors();

        assertEquals("red", colours[0]);
        assertEquals("blue", colours[2]);
        assertEquals("black", colours[5]);
    }

    @Test
    public void getMulticolorTest(){
        BrickWall wall = new BrickWall(3,5);
        wall.toggleMultiColor();
        wall.drawRectangle();
        assertEquals("red", wall.getBrick(0).getColor());
        assertEquals("yellow", wall.getBrick(1).getColor());
        assertEquals("blue", wall.getBrick(2).getColor());
        assertEquals("green", wall.getBrick(3).getColor());
        assertEquals("magenta", wall.getBrick(4).getColor());
        assertEquals("black", wall.getBrick(5).getColor());
    }

    @Test
    public void getSymmetricalTest(){
        BrickWall wall = new BrickWall(3,4);
        wall.setSymmetrical(true);
        wall.drawSymmetrical();
        
        Rectangle firstBrick = wall.getBrick(0);
        Rectangle fifthBrick = wall.getBrick(4);
        
        assertEquals(20, firstBrick.getXPosition());
        assertEquals(46, fifthBrick.getXPosition());
        
        
    } 

    @Test
    public void getDecreasingTest(){
        BrickWall wall = new BrickWall(3,4);
        wall.setDecreasing(true);
        wall.draw();

        assertEquals(9, wall.getBricksUsed());

        Rectangle firstBrick = wall.getBrick(0);
        Rectangle ninthBrick = wall.getBrick(8);

        assertEquals(20, firstBrick.getXPosition());
        assertEquals(500, firstBrick.getYPosition());

        assertEquals(72, ninthBrick.getXPosition());
        assertEquals(470, ninthBrick.getYPosition());
    }
}
