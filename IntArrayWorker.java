public class IntArrayWorker
{
  /** two dimensional matrix */
  private int[][] matrix = null;
  
  /** set the matrix to the passed one
    * @param theMatrix the one to use
    */
  public void setMatrix(int[][] theMatrix)
  {
    matrix = theMatrix;
  }
  
  /**
   * Method to return the total 
   * @return the total of the values in the array
   */
  public int getTotal()
  {
    int total = 0;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        total = total + matrix[row][col];
      }
    }
    return total;
  }
  
  /**
   * Method to return the number of times a given number appears in the array
   * @param target		The number you want to look for
   * @return 			The number of times that the number appears in the array
   */
  public int getCount(int target)
  {
	  int count = 0;
	  for(int [] row : matrix)
		  for(int num : row)
			  if(target == num)
				count++;
	  return count;
  }
  /**
   * Method to return the largest number in the matrix
   * @return 	The largest number in the array
   */
  public int getLargest()
  {
	  int largest = 0;
	  for(int [] row: matrix)
		  for(int num : row)
			  largest = Math.max(largest, num);
	  return largest;
  }
  /**
   * Method to sum the numbers in a given column
   * @param col		The column that you want to sum up
   * @return 		The total of that column
   */
  public int getColTotal(int col)
  {
	  int count = 0;
	  for(int i = 0; i < matrix[col].length;i++)
		  count += matrix[col][i];
      return count;
  }
  
  public void reverseRows()
  {
	  System.out.println("Arrays before reverseRows:");
	  for(int [] arr : matrix)
	  {
		  for(int num : arr)
		  {
			  System.out.print(" " + num);
		  }
		  System.out.println();
	  }
	  for(int [] arr : matrix)
	  {
		  for(int i = 0; i < arr.length/2;i++)
		  {
			  int temp = arr[i];
			  arr[i] = arr[arr.length-1-i];
			  arr[arr.length-1-i] = temp;
		  }
	  }
	  System.out.println("Arrays after reverseRows:");
	  for(int [] arr : matrix)
	  {
		  for(int num : arr)
		  {
			  System.out.print(" " + num);
		  }
		  System.out.println();
	  }
  }
  
  
  /**
   * Method to return the total using a nested for-each loop
   * @return the total of the values in the array
   */
  public int getTotalNested()
  {
    int total = 0;
    for (int[] rowArray : matrix)
    {
      for (int item : rowArray)
      {
        total = total + item;
      }
    }
    return total;
  }
  
  /**
   * Method to fill with an increasing count
   */
  public void fillCount()
  {
    int numCols = matrix[0].length;
    int count = 1;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < numCols; col++)
      {
        matrix[row][col] = count;
        count++;
      }
    }
  }
  
  /**
   * print the values in the array in rows and columns
   */
  public void print()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        System.out.print( matrix[row][col] + " " );
      }
      System.out.println();
    }
    System.out.println();
  }
  
  
  /** 
   * fill the array with a pattern
   */
  public void fillPattern1()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; 
           col++)
      {
        if (row < col)
          matrix[row][col] = 1;
        else if (row == col)
          matrix[row][col] = 2;
        else
          matrix[row][col] = 3;
      }
    }
  }
 
}
