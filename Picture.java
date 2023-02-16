import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method that will set red and green to 0*/
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  /**Method that will negate all the colors*/
	public void negate()
	{
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				pixelObj.setRed(255-pixelObj.getRed());
				pixelObj.setGreen(255-pixelObj.getGreen());
				pixelObj.setBlue(255-pixelObj.getBlue());
			}
		}
	}
	/** Method that will grayscale the picture*/
	public void grayScale()
	{
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				int average = pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue();
				average /= 3;
				pixelObj.setBlue(average);
				pixelObj.setRed(average);
				pixelObj.setGreen(average);
			}
		}
	}
	/**Method that will make the fish that is underwater easier to see*/
	public void fixUnderwater()
	{
		//under construction
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				if(pixelObj.getBlue() < 175)
				{
					pixelObj.setBlue(255);
					pixelObj.setRed(255);
					pixelObj.setGreen(255);
				}
			}
		}
	}
	
	/**Method that will pixelate the image
	 * @param size Side length of square area  to pixelate*/
	public void pixelate(int size)
	{
		Pixel[][] pixels = this.getPixels2D();
		int averageRed = 0;
		int averageBlue = 0;
		int averageGreen = 0;
		int counter = 0;
		for(int i = 0; i < pixels.length/size; i++)
		{
			for(int j = 0; j < pixels[0].length/size; j++)
			{
				for(int k = i*size; k < i*size + size; k++)
				{
					for(int l = j*size; l < j*size + size; l++)
					{
						averageRed += pixels[k][l].getRed();
						averageBlue += pixels[k][l].getBlue();
						averageGreen += pixels[k][l].getGreen();
						counter++;
					}
				}
				int rowBounds = i*size + size;
				int colBounds = j*size + size;
				if(i == pixels.length/size -1)
				{
					for(int k = i*size; k < pixels.length; k++)
					{
						for(int l = j*size; l < j*size + size; l++)
						{
							averageRed += pixels[k][l].getRed();
							averageBlue += pixels[k][l].getBlue();
							averageGreen += pixels[k][l].getGreen();
							counter++;
						}
					}
					rowBounds = pixels.length;
				}

				if(j == pixels[0].length/size -1)
				{
					for(int k = i*size; k < i*size + size; k++)
					{
						for(int l = j*size; l < pixels[0].length; l++)
						{
							averageRed += pixels[k][l].getRed();
							averageBlue += pixels[k][l].getBlue();
							averageGreen += pixels[k][l].getGreen();
							counter++;
						}
					}
					colBounds = pixels[0].length;
				} 
				for(int k = i*size ; k < rowBounds; k++)
				{
					for(int l = j*size; l < colBounds; l++)
					{
						pixels[k][l].setBlue(averageBlue/counter);
						pixels[k][l].setRed(averageRed/counter);
						pixels[k][l].setGreen(averageGreen/counter);
					}
				}
				averageRed = averageBlue = averageGreen = counter = 0;
			}
		}
	}
	/** Method that blurs the picture
	 * @param size Blur size, greater is more blur
	 * @return Blurred picture
	 */
	 public Picture blur(int size)
	 {
		 Pixel[][] pixels = this.getPixels2D();
		 Picture result = new Picture(pixels.length, pixels[0].length);
		 Pixel[][] resultPixels = result.getPixels2D();
		 int sizeHalf = size/2;
		 for(int i = 0; i < resultPixels.length; i++)
		 {
			 for(int j = 0; j < resultPixels[0].length; j++)
			 {
				 int averageRed = 0;
				 int averageBlue = 0;
				 int averageGreen = 0;
				 int counter = 0;
				 for(int a = i - sizeHalf; a < i + sizeHalf; a++)
				 {
					 for(int b = j -sizeHalf; b < j + sizeHalf; b++)
					 {
						 if(a >= 0 && a < pixels.length && b >= 0 && b < pixels[0].length)
						 {
							 averageRed += pixels[a][b].getRed();
							 averageBlue += pixels[a][b].getBlue();
							 averageGreen += pixels[a][b].getGreen();
							 counter++;
						 }
					 }
				 }
				 resultPixels[i][j].setRed(averageRed/counter);
				 resultPixels[i][j].setGreen(averageGreen/counter);
				 resultPixels[i][j].setBlue(averageBlue/counter);
			 }
		 }
		 
		 return result;
	 }
	 
	 /** Method that enhances a picture by getting average Color around
	 * a pixel then applies the following formula:
	 *
	 * pixelColor <- 2 * currentValue - averageValue
	 *
	 * size is the area to sample for blur.
	 *
	 * @param size Larger means more area to average around pixel
	 * and longer compute time.
	 * @return enhanced picture
	 */
	 public Picture enhance(int size)
	 {
		 Pixel[][] pixels = this.getPixels2D();
		 Picture result = new Picture(pixels.length, pixels[0].length);
		 Pixel[][] resultPixels = result.getPixels2D();
		 int sizeHalf = size/2;
		 for(int i = 0; i < resultPixels.length; i++)
		 {
			 for(int j = 0; j < resultPixels[0].length; j++)
			 {
				 int averageRed = 0;
				 int averageBlue = 0;
				 int averageGreen = 0;
				 int counter = 0;
				 for(int a = i - sizeHalf; a < i + sizeHalf; a++)
				 {
					 for(int b = j -sizeHalf; b < j + sizeHalf; b++)
					 {
						 if(a >= 0 && a < pixels.length && b >= 0 && b < pixels[0].length)
						 {
							 averageRed += pixels[a][b].getRed();
							 averageBlue += pixels[a][b].getBlue();
							 averageGreen += pixels[a][b].getGreen();
							 counter++;
						 }
					 }
				 }
				 resultPixels[i][j].setRed(2*pixels[i][j].getRed() - averageRed/counter);
				 resultPixels[i][j].setGreen(2*pixels[i][j].getGreen() - averageGreen/counter);
				 resultPixels[i][j].setBlue(2*pixels[i][j].getBlue() - averageBlue/counter);
			 }
		 }
		 
		 return result;
	 }
	/**
	 * Method that will swap the left and right of the image
	 * @return The swapped picture
	 */
	public Picture swapLeftRight()
	{
		 Pixel[][] pixels = this.getPixels2D();
		 Picture result = new Picture(pixels.length, pixels[0].length);
		 Pixel[][] resultPixels = result.getPixels2D();
		 for(int i = 0; i < pixels.length; i++)
		 {
			 for(int j = 0; j < pixels[0].length; j++)
			 {
				 int newColumn = (j + pixels[0].length / 2)% pixels[0].length;
				 resultPixels[i][newColumn].setBlue(pixels[i][j].getBlue());
				 resultPixels[i][newColumn].setGreen(pixels[i][j].getGreen());
				 resultPixels[i][newColumn].setRed(pixels[i][j].getRed());
			 }
		 }
		 return result;
	}
	/** 
	 * Method that will shift the image in a stairform
	 * @param shiftCount The number of pixels to shift to the right
	 * @param steps The number of steps
	 * @return The picture with pixels shifted in stair steps
	 */
	 public Picture stairStep(int shiftCount, int steps)
	 {
		 Pixel[][] pixels = this.getPixels2D();
		 Picture result = new Picture(pixels.length, pixels[0].length);
		 Pixel[][] resultPixels = result.getPixels2D();
		 int size = pixels.length/steps;
		 for(int i = 0; i < pixels.length; i++)
		 {

			 for(int j = 0; j < pixels[0].length; j++)
			 {
				 int newColumn = i / size *shiftCount;
				 newColumn = (newColumn + j) % pixels[0].length;
				 resultPixels[i][newColumn].setBlue(pixels[i][j].getBlue());
				 resultPixels[i][newColumn].setGreen(pixels[i][j].getGreen());
				 resultPixels[i][newColumn].setRed(pixels[i][j].getRed());
			 }
		 }
		 return result;
	 }
     
	 /** Method to liquify an image(stretches it horizontally using a curve)
	 * @param maxFactor Max height (shift) of curve in pixels
	 * @return Liquified picture
	 */
	 public Picture liquify(int maxHeight)
	 {
		 Pixel[][] pixels = this.getPixels2D();
		 Picture result = new Picture(pixels.length, pixels[0].length);
		 Pixel[][] resultPixels = result.getPixels2D();
		 int bellWidth = 100;
		 for(int r = 0; r < pixels.length; r++)
		 {
			 for(int c = 0; c < pixels[0].length; c++)
			 {
				double exponent = Math.pow(r - pixels.length / 2.0, 2) / (2.0 * Math.pow(bellWidth, 2));
				int rightShift = (int)(maxHeight * Math.exp(- exponent));
				rightShift = (rightShift + c) % pixels[0].length;
				resultPixels[r][rightShift].setBlue(pixels[r][c].getBlue()); 
				resultPixels[r][rightShift].setRed(pixels[r][c].getRed()); 
				resultPixels[r][rightShift].setGreen(pixels[r][c].getGreen()); 
			 }
		 }
		 return result;
	 }
	 /**Method that will distort an image based on a sinusoidal function
	  * @param amplitude	The amplitude of the sin function
	  * @return	The distorted image(wavy picture)
	  */ 
	 public Picture wavy(int amplitude) 
	 {
		 Pixel[][] pixels = this.getPixels2D();
		 Picture result = new Picture(pixels.length, pixels[0].length);
		 Pixel[][] resultPixels = result.getPixels2D();
		 int phaseShift = 14;
		 int frq = 3;
		 for(int r = 0; r < pixels.length; r++)
		 {
			 for(int c = 0; c < pixels[0].length; c++)
			 {
				int sin = (int)(amplitude * Math.sin(2.0 * Math.PI * frq * (r/(double)pixels.length) + phaseShift));
				int rightShift = sin + c;
				if(rightShift >= pixels[0].length)
					rightShift -= pixels[0].length;
				else if(rightShift < 0)
					rightShift += pixels[0].length;
				resultPixels[r][rightShift].setBlue(pixels[r][c].getBlue()); 
				resultPixels[r][rightShift].setRed(pixels[r][c].getRed()); 
				resultPixels[r][rightShift].setGreen(pixels[r][c].getGreen()); 
			 }
		 }
		 return result;
	 }
	 /**Method that superimposes two images onto another image and scales it
	  * (hard code) Mr.Conlin would very much dissaprove of this method
	  * @return The image with the superimposed subimages on it
	  */
	  public Picture greenScreen()
	  {
		 Pixel[][] pixels = this.getPixels2D();
		 Picture result = new Picture(this);
		 Pixel[][] resultPixels = result.getPixels2D();
		 
		 Picture meowBig = new Picture("GreenScreenCatMouse/kitten1greenScreen.jpg");
		 Pixel [][]meow = meowBig.scale(70,70).getPixels2D();
		 System.out.println("mooooooooo");
		 Picture woofBig = new Picture("GreenScreenCatMouse/puppy1greenScreen.jpg");
		 Pixel [][]woof =  woofBig.scale(100,75).getPixels2D();
		 
		 //Green rgb 51  204 51
		 int xc = 420; // where the meow gonna go
		 int yc = 50; //meow meow meow meow meow meow meow 
		 int xd = 420; //where the woof gonna go
		 int yd = 500; //woof woof woof woof woof woof woof
		 for(int i = 0; i < meow.length; i++)
		 {
			 for(int j = 0; j < meow[0].length;j++)
			 {
				 if(meow[i][j].getRed() != 51 && meow[i][j].getBlue() != 51
					&& meow[i][j].getGreen() != 204)
				 {
					 resultPixels[xc + i][yc + j].setBlue(meow[i][j].getBlue());
					 resultPixels[xc + i][yc + j].setGreen(meow[i][j].getGreen());
					 resultPixels[xc + i][yc + j].setRed(meow[i][j].getRed());
					 System.out.println("meow");
				 }
			 }
		 }
		 for(int i = 0; i < woof.length; i++)
		 {
			 for(int j = 0; j < woof[0].length;j++)
			 {
				 if(woof[i][j].getRed() != 51 && woof[i][j].getBlue() != 51
					&& woof[i][j].getGreen() != 204)
				 {
					 resultPixels[xd + i][yd + j].setBlue(woof[i][j].getBlue());
					 resultPixels[xd + i][yd + j].setGreen(woof[i][j].getGreen());
					 resultPixels[xd + i][yd + j].setRed(woof[i][j].getRed());
					 System.out.println("woof");
				 }
			 }
		 }
		 return this;
	 }
		  
	  
  /** Method to show large changes in color 
    * @param threshold What the color difference is in order to consider it an edge
    * @return a black and white picture showing the edges of the images
    */
  public Picture edgeDetectionBelow(int threshold)
  {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
    for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; col < pixels[0].length-1; col++)
      {
		int r = pixels[row + 1][col].getRed();
		int g = pixels[row + 1][col].getGreen();
		int b = pixels[row + 1][col].getBlue();
		int rr = pixels[row][col].getRed();
		int rg = pixels[row][col].getGreen(); 
		int rb = pixels[row][col].getBlue();
        if (Math.sqrt((r-rr)^2 + (g-rg)^2 + (b-rb)^2) > threshold)
          resultPixels[row][col].setColor(Color.BLACK);
        else
		  resultPixels[row][col].setColor(Color.WHITE);
      }
    }
    return result;
  } 
		
	  
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
   
  }
  
} // this } is the end of class Picture, put all new methods before this
