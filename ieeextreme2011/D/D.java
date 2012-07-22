import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
public class D {
  public static final int MAX_PRIME = 104779;

  public static int squares[] = new int[100];

  public int students[];

  public static void main(String[] args) throws Exception
  {
    generateSquares();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int numStudents = Integer.parseInt(br.readLine());
    List<Integer> primes = calcPrimes(numStudents);
    Map<Integer, Point> primeMap = new HashMap<Integer, Point>();
    int squareSize = 1;
    // This doesn't handle the case where numStudents < 8
    while(squareSize * squareSize < numStudents) {
      squareSize += 2;
    }
    int middle = (squareSize - 1) / 2;
    // Place students
    int students[][] = new int[squareSize][squareSize];
    int studentsLeft = numStudents;
    int movement = 1;
    int posX = middle;
    int posY = middle;
    int index = 0;
    while(studentsLeft > 0){
      // Right
      int maxX = movement + posX;
      while(posX < maxX && studentsLeft > 0){
        posX++;
        students[posX][posY] = primes.get(index);
        primeMap.put(primes.get(index), new Point(posX, posY, index));
        index++;
        studentsLeft--;
        //System.out.println("Placing " + primes.get(index-1)+ " at ("+posX+", "+ posY+")");
      }
      // Up
      int maxY = movement + posY;
      while(posY < maxY && studentsLeft > 0){
        posY++;
        students[posX][posY] = primes.get(index);
        primeMap.put(primes.get(index), new Point(posX, posY, index));
        index++;
        studentsLeft--;
        //System.out.println("Placing " + primes.get(index-1)+ " at ("+posX+", "+ posY+")");
      }

      movement++;
      // Left
      int minX = posX - movement;
      while(posX > minX && studentsLeft > 0){
        posX--;
        students[posX][posY] = primes.get(index);
        primeMap.put(primes.get(index), new Point(posX, posY, index));
        index++;
        studentsLeft--;
        //System.out.println("Placing " + primes.get(index-1)+ " at ("+posX+", "+ posY+")");
      }

      // Down
      int minY = posY - movement;
      while(posY > minY && studentsLeft > 0){
        posY--;
        students[posX][posY] = primes.get(index);
        primeMap.put(primes.get(index), new Point(posX, posY, index));
        index++;
        studentsLeft--;
        //System.out.println("Placing " + primes.get(index-1)+ " at ("+posX+", "+ posY+")");
      }

      movement++;
    }
    while(true){
      String input[] = br.readLine().split(" ");
      int x =  Integer.parseInt(input[0]);
      int y =  Integer.parseInt(input[1]);
      if(x == -999){
        break;
      }
      x = x + middle;
      y = y + middle;
      if(x >= squareSize || y >= squareSize || students[x][y] == 0){
        System.out.println("No student at this location");
        continue;
      }
      System.out.println("I should write " + students[x][y]);
      // Catch from the person AFTER you.
      int end  = students[x][y] % 10;
      int primeIndex = primeMap.get(students[x][y]).primeIndex + 1;
      while(primeIndex < index && end != primes.get(primeIndex) % 10 ){
        primeIndex++;
      }
      if (primeIndex == index){
        System.out.println("I should catch from nobody");
      } else {
        Point point = primeMap.get(primes.get(primeIndex));
        System.out.println("I should catch from " + (point.x-middle) + " " + (point.y-middle));
      }
      // Throw to the person ahead of you
      primeIndex = primeMap.get(students[x][y]).primeIndex - 1;
      while(primeIndex >= 0 && end != primes.get(primeIndex) % 10) {
        primeIndex--;
      }
      if (primeIndex < 0){
        System.out.println("I should throw to 0 0");
      } else {
        Point point = primeMap.get(primes.get(primeIndex));
        System.out.println("I should throw to " + (point.x-middle) + " " + (point.y - middle));
      }


    }
  }

  public static void generateSquares(){
    int index = 0;
    for(int i = 3; i <= 100; i += 2){
      squares[index] = i*i - 1;
      index++;
    }
  }


  public static List<Integer> calcPrimes(int num){
    LinkedList<Integer> primes = new LinkedList<Integer>();
    BitSet nums = new BitSet(MAX_PRIME);
    for(int i = 2; i < MAX_PRIME; i = nums.nextClearBit(i+1)){
      if (i != 2 && i!= 3 && i!=5){
        primes.add(i);
        num -= 1;
      }
      if (num == 0){
        return primes;
      }
      for(int j = i+i; j<MAX_PRIME; j += i){
        nums.set(j);
      }
    }
    return primes;
  }

  public static class Point {
    public int x;
    public int y;
    public int primeIndex;
    public Point(int x, int y, int index) {
      this.x = x;
      this.y = y;
      this.primeIndex = index;
    }
  }
}
