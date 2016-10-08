/* Made by Joel Vikström, Jacob Adlers & Tim Wayburn */

import java.util.LinkedList;

public class Artwork {

  public static void main(String[] arg) {
    Kattio io = new Kattio(System.in, System.out);

    int n, m, q;

    n = io.getInt();
    m = io.getInt();
    q = io.getInt();

    boolean[][] artwork = new boolean[n][m];

    for (int p=0; p<q; p++) {
      int x1 = io.getInt();
      int y1 = io.getInt();
      int x2 = io.getInt();
      int y2 = io.getInt();

      int connectedNeighors = 0;
      if (y1 == y2) {   // horizontal
        boolean previous = isOutOfBound(x1-1, y1+1, artwork) || artwork[x1-1][y1+1];
        boolean current;

        for (int i=x1; i<=x2+1; i++) {
          current = isOutOfBound(i, y1+1, artwork) || artwork[i][y1+1];
          if (previous && !current) {
            connectedNeighors ++;
            if (connectedNeighors > 1) {
              break;
            }
          }
          previous = current;
        }

        if (connectedNeighors < 2) {
          current = isOutOfBound(x2+1, y1, artwork) || artwork[x2+1][y1];
          if (previous && !current) {
            connectedNeighors ++;
          }
          previous = current;
        }

        if (connectedNeighors < 2) {
          for (int i=x2+1; i>=x2-1; i--) {
            current = isOutOfBound(i, y1-1, artwork) || artwork[i][y1-1];
            if (previous && !current) {
              connectedNeighors ++;
              if (connectedNeighors > 1) {
                break;
              }
            }
            previous = current;
          }
        }

        current = isOutOfBound(x2-1, y1, artwork) || artwork[x2-1][y1];
        if (previous && !current) {
          connectedNeighors ++;
        }
        previous = current;
        current = isOutOfBound(x2-1, y1+1, artwork) || artwork[x2-1][y1+1];
        if (previous && !current) {
          connectedNeighors ++;
        }
        previous = current;
      }
      else {            // vertical
        boolean previous = isOutOfBound(x1-1, y1-1, artwork) || artwork[x1-1][y1-1];
        boolean current;

        for (int i=y1; i<=y2+1; i++) {
          current = isOutOfBound(x1-1, i, artwork) || artwork[x1-1][i];
          if (previous && !current) {
            connectedNeighors ++;
            if (connectedNeighors > 1) {
              break;
            }
          }
          previous = current;
        }

        if (connectedNeighors < 2) {
          current = isOutOfBound(x1, y2+1, artwork) || artwork[x1][y2+1];
          if (previous && !current) {
            connectedNeighors ++;
          }
          previous = current;
        }

        if (connectedNeighors < 2) {
          for (int i=y2+1; i>=y1-1; i--) {
            current = isOutOfBound(x1-1, i, artwork) || artwork[x1-1][i];
            if (previous && !current) {
              connectedNeighors ++;
              if (connectedNeighors > 1) {
                break;
              }
            }
            previous = current;
          }
        }

        current = isOutOfBound(x1, y1-1, artwork) || artwork[x1][y1-1];
        if (previous && !current) {
          connectedNeighors ++;
        }
        previous = current;
        current = isOutOfBound(x1-1, y1-1, artwork) || artwork[x1-1][y1-1];
        if (previous && !current) {
          connectedNeighors ++;
        }
        previous = current;
      }

      for (int a=x1-1; a<=x2-1; a++) {
        for (int b=y1-1; b<=y2-1; b++) {
          artwork[a][b] = true;
        }
      }

      if (connectedNeighors > 1) {
        io.println(calculateBeauty(artwork));
      }
    }

    io.flush();
  }

  static boolean isOutOfBound(int x, int y, boolean[][] list) {
    return x<0 || x>=list.length || y<0 || y>=list[0].length;
  }

  static int calculateBeauty(boolean[][] artwork) {
    boolean[][] marked = new boolean[artwork.length][artwork[0].length];
    int regions = 0;

    LinkedList<Position> queue = new LinkedList<>();

    for (int i=0; i<artwork.length; i++) {
      for (int j=0; j<artwork[0].length; j++) {
        if(!artwork[i][j] && !marked[i][j]) {
          regions++;

          queue.add(new Position(i, j));

          while(queue.size() > 0) {
            Position p = queue.removeFirst();

            if (p.x > 0 && !marked[p.x-1][p.y] && !artwork[p.x-1][p.y]) { // left
              queue.add(new Position(p.x-1, p.y));
              marked[p.x-1][p.y] = true;
            }
            if (p.x < artwork.length-1 && !marked[p.x+1][p.y] && !artwork[p.x+1][p.y]) { // right
              queue.add(new Position(p.x+1, p.y));
              marked[p.x+1][p.y] = true;
            }
            if (p.y > 0 && !marked[p.x][p.y-1] && !artwork[p.x][p.y-1]) { // down
              queue.add(new Position(p.x, p.y-1));
              marked[p.x][p.y-1] = true;
            }
            if (p.y < artwork[0].length-1 && !marked[p.x][p.y+1] && !artwork[p.x][p.y+1]) { // up
              queue.add(new Position(p.x, p.y+1));
              marked[p.x][p.y+1] = true;
            }
          }

        }
      }
    }

    return regions;
  }


  static class Position {
    int x;
    int y;
    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
