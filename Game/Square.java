package Game;
import java.awt.*;
import java.awt.Color;

public class Square extends Rectangle {

  //Hvor mange kolloner og rækker af firkanter
  static int rows = 15;
  static int collums = 15;

  //Array indeholder alle firkanterne
  public static Square grid[][] = new Square[rows][collums];

  //Den specifikke bane
  public static int[][] makeMap = { { 0, 0 }, { 5, 0 }, { 5, 5 }, { 3, 5 }, { 3, 3 }, { 0, 3 }, { 0, 10 }, { 10, 10 },
      { 10, 5 }, { 14, 5 } };

  public int x, y;
  public static int width = 44;
  public static int strokeWeigth = 2;

  Color bodyColor = new Color(29, 69, 64);
  public Color strokeColor = new Color(0, 0, 0);
  public boolean isTowerPlacebel = true;

  //den rute som Vectoids følger
  public static int[][] vectoidRoute;

  Square(int x, int y) {
    this.x = x;
    this.y = y;
  }

  //tegner firkanten
  private void draw(Graphics g) {
    g.setColor(strokeColor);
    g.fillRect(x, y, width, width);
    g.setColor(bodyColor);
    g.fillRect(x + strokeWeigth, y + strokeWeigth, width - strokeWeigth * 2, width - strokeWeigth * 2);
  }

  //fylder arralisten grid op med classen Square
  public static void makeGrid() {
    for (int i = 0; i < collums; i++) {
      for (int j = 0; j < rows; j++) {

        grid[i][j] = new Square(i * width, j * width);
      }
    }
    makeMap();
    calculateRouteLength();
    makeVectoidRoute();
  }

  //Tegner alle firkanterne
  public static void drawGrid(Graphics g) {
    for (int i = 0; i < collums; i++) {
      for (int j = 0; j < rows; j++) {
        grid[i][j].draw(g);
      }
    }
  }

  //Gør så alle firkanter mellem de 2 punkter man har skrevet ind i makeMap bliver til banen.
  private static void makeMap() {

    for (int i = 1; i < makeMap.length; i++) {

      if (makeMap[i - 1][0] < makeMap[i][0]) {

        for (int j = makeMap[i - 1][0]; j <= makeMap[i][0]; j++) {

          grid[j][makeMap[i][1]].isTowerPlacebel = false;
          grid[j][makeMap[i][1]].bodyColor = new Color(0, 41, 0);
        }
      } else if (makeMap[i - 1][1] < makeMap[i][1]) {

        for (int j = makeMap[i - 1][1]; j <= makeMap[i][1]; j++) {

          grid[makeMap[i][0]][j].isTowerPlacebel = false;
          grid[makeMap[i][0]][j].bodyColor = new Color(0, 41, 0);
        }

      } else if (makeMap[i - 1][0] > makeMap[i][0]) {

        for (int j = makeMap[i - 1][0]; j >= makeMap[i][0]; j--) {

          grid[j][makeMap[i][1]].isTowerPlacebel = false;
          grid[j][makeMap[i][1]].bodyColor = new Color(0, 41, 0);
        }

      } else if (makeMap[i - 1][1] > makeMap[i][1]) {

        for (int j = makeMap[i - 1][1]; j >= makeMap[i][1]; j--) {

          grid[makeMap[i][0]][j].isTowerPlacebel = false;
          grid[makeMap[i][0]][j].bodyColor = new Color(0, 41, 0);
        }
      }
    }
  }


   //Regner ud hvor lang ruten som Vectoids følger er
  private static void calculateRouteLength() {
    int length = 0;

    for (int i = 1; i < makeMap.length; i++) {

      length += Math.abs(grid[makeMap[i][0]][makeMap[i][1]].x - grid[makeMap[i - 1][0]][makeMap[i - 1][1]].x);
      length += Math.abs(grid[makeMap[i][0]][makeMap[i][1]].y - grid[makeMap[i - 1][0]][makeMap[i - 1][1]].y);

    }
    length += width * 2;

    vectoidRoute = new int[length + 1][2];
  }


  //Laver ruten som Vectoids følger
  private static void makeVectoidRoute() {

    int number = 0;
    int x2 = 0;
    int y2 = 0;

    for (int i = 1; i < makeMap.length; i++) {

      int x1 = grid[makeMap[i - 1][0]][makeMap[i - 1][1]].x;
      int y1 = grid[makeMap[i - 1][0]][makeMap[i - 1][1]].y;

      x2 = grid[makeMap[i][0]][makeMap[i][1]].x;
      y2 = grid[makeMap[i][0]][makeMap[i][1]].y;

      int xDist = x2 - x1;
      int yDist = y2 - y1;

      if (i == 1) {

        if (xDist > 0) {
          x1 -= width;
          xDist += width;
        } else if (xDist < 0) {

          x1 += width;
          xDist -= width;
        } else if (yDist > 0) {
          y1 -= width;
          yDist += width;

        } else {
          y1 += width;
          yDist -= width;
        }
      } else if (i == makeMap.length - 1) {

        if (xDist > 0) {
          x2 += width;
          xDist += width;
        } else if (xDist < 0) {

          x2 -= width;
          xDist -= width;
        } else if (yDist > 0) {
          y2 += width;
          yDist += width;

        } else {
          y2 -= width;
          yDist -= width;
        }
      }

      if (xDist > 0 || yDist > 0) {

        for (int j = 0; j != xDist + yDist; j++) {

          if (yDist == 0) {
            vectoidRoute[number][0] = x1 + j;
            vectoidRoute[number][1] = y1;

          } else {
            vectoidRoute[number][0] = x1;
            vectoidRoute[number][1] = y1 + j;

          }
          number++;
        }
      } else {

        for (int j = 0; j != Math.abs(xDist + yDist); j++) {

          if (yDist == 0) {
            vectoidRoute[number][0] = x1 - j;
            vectoidRoute[number][1] = y1;

          } else {
            vectoidRoute[number][0] = x1;
            vectoidRoute[number][1] = y1 - j;

          }
          number++;
        }
      }

    }

    vectoidRoute[number][0] = x2;
    vectoidRoute[number][1] = y2;

  }

}