/* Made by Joel Vikström, Jacob Adlers & Tim Wayburn */

import java.lang.Math;

public class Main {

  public static void main(String[] arg) {
    Kattio io = new Kattio(System.in, System.out);



    int n1 = io.getInt(); // current
    int n2 = io.getInt(); // wanted



    int result1 = n2-n1;
    int result2 = 360-(n1-n2);
    if (Math.abs(result1) == 180) {
      io.println(180);
    }
    else if(Math.abs(result1) > Math.abs(result2)) {
      io.println(result2);
    }
    else{
      io.println(result1);
    }
/*

    int needlepos;

    int result1 = 0;
    int result2 = 0;

    needlepos = n1;

    while(needlepos != n2){
      needlepos++;
      result1++;
      if(needlepos == 360){
        needlepos = 0;
      }
    }

    needlepos = n1;

    while(needlepos != n2){
      needlepos--;
      result2--;
      if(needlepos == 0){
        needlepos = 360;
      }
    }

    if(Math.abs(result1) > Math.abs(result2)) {
      io.print(result2);
    }
    else{
      io.print(result1);
    }

*/
    io.flush();
  }

}
