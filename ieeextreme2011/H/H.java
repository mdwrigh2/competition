import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class H {
  /* The first line contains the number of scenarios.
   * Each one of the other lines in the input contains a scenario:
   * The first number, N, is the number of balls; followed by N pairs of numbers: the
   * distance in centimeters from the south end of the table and the speed (positive speed
   * meaning it moves northward); the last two numbers are the number i of the target ball
   * you should track and the time T in seconds.
   */
  public static void main (String [] args) throws Exception
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    for ( int i = 0; i < n; i++) {
      if( i != 0 ) {
        System.out.println();
      }
      String inputs[] = br.readLine().split(" ");
      int k = Integer.parseInt(inputs[0]);
      if(k == 0){
        continue;
      }

      List<Ball> balls = new ArrayList<Ball>();
      for(int j = 0; j < k; j++){
        int pos = Integer.parseInt(inputs[2*j+1]);
        int vel = Integer.parseInt(inputs[2*j+2]);
        if(pos <= 0){
          pos = 0;
          vel = 0;
        } else if (pos >= 100) {
          pos = 100;
          vel = 0;
        }
        balls.add(new Ball(pos, vel, j));
      }
      //Collections.sort(balls);
      int target = Integer.parseInt(inputs[2*k+1]);
      double time = (double) Integer.parseInt(inputs[2*k+2]);
      runSimulation(balls, target, time);
    }
    System.out.println();
  }

  public static void runSimulation(List<Ball> balls, int target, double time) {
    // Calculate the event order
    List<Event> events = new ArrayList<Event>();
    events.add( new Event(time, Event.END));
    for(Ball b : balls) {
      double pocketTime = b.pocketTime();
      if (pocketTime > 0){ 
        Event e = new Event(pocketTime, Event.POCKET);
        e.ballA = b;
        events.add(e);
      }
      for (Ball b2 : balls) {
        if (b2 != b) {
          double collisionTime = b.collisionTime(b2);
          if(collisionTime > 0) {
            Event e = new Event(collisionTime, Event.COLLISION);
            e.ballA = b;
            e.ballB = b2;
            if(!events.contains(e)){
              events.add(e);
            }
          }
        }
      }
    }
    Collections.sort(events);
    Event e = events.get(0);
    for(Ball b : balls) {
      b.oldPos = b.pos;
      if(b.pos > 0 && b.pos < 100){
        b.pos = b.pos + b.vel * e.time;
      }
    }
    int i = 0;
    double eventTime = e.time;
    while(e.time == eventTime) {
      if( e.event == Event.POCKET ) {
        Ball b = e.ballA;
        if (b.pos >= 100) {
          b.pos = 100;
        } else if( b.pos <= 0) {
          b.pos = 0;
        }
        b.vel = 0;
        if(b.index == target - 1){
          System.out.print(Math.round(b.pos));
          return;
        } 
      } else if (e.event == Event.END) {
        for(Ball b : balls) {
          if (b.index == (target - 1)) {
            System.out.print(Math.round(b.pos));
            return;
          }
        }
      } else if (e.event == Event.COLLISION) {
        /* check if there's a three way collision */
        if(e.ballA.pos >0 && e.ballA.pos < 100 && e.ballB.pos > 0 && e.ballB.pos < 100){
          double temp = e.ballA.vel;
          e.ballA.vel = e.ballB.vel;
          e.ballB.vel = temp;
        }
      }
      i++;
      if( i < events.size() ){
        e = events.get(i);
      } else {
        break;
      }
    }
    runSimulation(balls, target, time - eventTime);
  }

  
  public static class Event implements Comparable<Event>{
    public static final int COLLISION = 0;
    public static final int POCKET = 1;
    public static final int END = 2;
    
    public Ball ballA;
    public Ball ballB;

    public double time;
    public int event;
    
    public Event(double time, int eventType){
      this.time = time;
      this.event = eventType;
    }

    public int compareTo(Event e){
      return (int) ((this.time - e.time) * 100);
    }

    public boolean equals(Object o) {
      if(o == null || o.getClass() != Event.class) {
        return false;
      }
      Event e = (Event) o;
      if(this.ballB == e.ballB && this.ballA == e.ballA){
        if(this.time == e.time && this.event == e.event){
          return true;
        }
      }
      if(this.ballA == e.ballB && this.ballB == e.ballA){
        if(this.time == e.time && this.event == e.event){
          return true;
        }
      }
      return false;
    }

    public String toString() {
      if ( event == END) {
        return "Event: END @ " + time;
      } else if( event == POCKET) {
        return "Event: POCKET @ " + time;
      } else {
        return "Event: COLLISION @ " + time;
      }
    }
  }



  public static class Ball implements Comparable<Ball>{
    public double pos;
    public double vel;
    public double oldPos;
    public int index;

    public Ball(double pos, double vel, int index){
      this.pos = pos;
      this.vel = vel;
      this.index = index;
    }

    public String toString() {
      return "Ball: pos " + pos + " vel " + vel;
    }


    public double collisionTime(Ball b){
      if (b.vel == this.vel) {
        return -1;
      }
      double time = (this.pos - b.pos) /  (b.vel - this.vel);
      if (time <= 0) { 
        return -1;
      }
      return time;
    }

    public double pocketTime() {
      if (vel > 0) {
        return (100 - this.pos) / this.vel;
      } else if( vel < 0) {
        return this.pos / (-this.vel);
      } else {
        return -1;
      }
    }

    public int compareTo(Ball b){
      return (int) ((this.pos - b.pos) * 100);
    }

    public boolean equals(Object obj) {
      if(obj == null || obj.getClass() != Ball.class){
        return false;
      }
      if(this.index == ((Ball) obj).index){
        return true;
      } 
      return false;
    }
  }
}
