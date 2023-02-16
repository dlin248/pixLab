/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test keepOnlyBlue*/
  public static void testKeepOnlyBlue()
  {
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
	  beach.keepOnlyBlue();
	  beach.explore();
  }
  
  /**Method to test negate*/
  public static void testNegate()
  {
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
	  beach.negate();
	  beach.explore();
  }
  
  /**Method to test grayscale*/
  public static void testGrayscale()
  {
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
	  beach.grayScale();
	  beach.explore();
  }
  
  /**Method to test fixUnderwater*/
  public static void testFixUnderwater()
  {
	  Picture water = new Picture("images/water.jpg");
	  water.explore();
	  water.fixUnderwater();
	  water.explore();
  }
  /** Method to test pixelate
   * @param size	The variable to pass into pixelate*/
  public static void testPixelate(int size)
  {
	  Picture beach = new Picture("images/jayden.jpg");
	  beach.explore();
	  beach.pixelate(size);
	  beach.explore();
  }
  
  /**Method to test blur
   * @param size The variable to pass into blur*/
   public static void testBlur(int size)
   {
	  Picture beach = new Picture("images/jayden.jpg");
	  beach.explore();
	  beach = beach.blur(size);
	  beach.explore();
  }
  /**Method to test blur
   * @param size The variable to pass into blur*/
   public static void testEnhance(int size)
   {
	  Picture beach = new Picture("images/water.jpg");
	  beach.explore();
	  beach = beach.enhance(size);
	  beach.explore();
  }
  
  /**Method to test swapLeftRight*/
  public static void testSwapLeftRight()
  {
	  Picture picture = new Picture("images/jayden.jpg");
	  picture.explore();
	  picture = picture.swapLeftRight();
	  picture.explore();
  }
  /**Method to test stairStep*/
  public static void testStairStep()
  {
	  Picture picture = new Picture("images/jayden.jpg");
	  picture.explore();
	  picture = picture.stairStep(1,400);
	  picture.explore();
  }
  /**Method to test liquify*/
  public static void testLiquify()
  {
	  Picture picture = new Picture("images/jayden.jpg");
	  picture.explore();
	  picture = picture.liquify(100);
	  picture.explore();
  }
  /**Method to test wavy*/
  public static void testWavy()
  {
	  Picture picture = new Picture("images/swan.jpg");
	  picture.explore();
	  picture = picture.wavy(30);
	  picture.explore();
  }
  
	  
  /**Method to test edgeDetectionBelow*/
  public static void testEdgeDetectionBelow()
  {
	  Picture picture = new Picture("images/beach.jpg");
	  picture.explore();
	  picture = picture.edgeDetectionBelow(3);
	  picture.explore();
  }
  /**Method to test greenScreen*/
  public static void testGreenScreen()
  {
	  Picture picture = new Picture("GreenScreenCatMouse/IndoorJapeneseRoomBackground.jpg");
	  picture.explore();
	  picture = picture.greenScreen();
	  picture.explore();
  }
	  
	   
	  
	  
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testPixelate(11);
    //testBlur(11);
    //testEnhance(11);
    //testSwapLeftRight();
    //testStairStep();
    //testEdgeDetectionBelow();
    //testLiquify();
    //testWavy();
    testGreenScreen();
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();	
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}
