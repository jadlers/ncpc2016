/* Made by Joel Vikström, Jacob Adlers & Tim Wayburn */

import java.util.Arrays;
import java.util.ArrayList;

public class Main {

  static public int stocks;
  static public long money;

  public static void main(String[] arg) {
    Kattio io = new Kattio(System.in, System.out);
    money = 100;
    stocks = 0;

    int days = io.getInt();

    if (days == 1) {
      io.println(money);
      io.flush();
      return;
    }

    int[] temp = new int[days];
    for (int i = 0; i < days; i++) {
      temp[i] = io.getInt();
    }
    ArrayList<Integer> prices = optimiseList(temp);


    for (int i = 0; i < prices.size()-1 ; i++) {
      int curPrice = prices.get(i);
      int nextPrice = prices.get(i+1);

      if (curPrice < nextPrice) {
        buy(curPrice);
      } else {
        sell(curPrice);
      }
    }

    sell(prices.get(prices.size()-1));
    io.println(money);
    io.close();
  }

  static void buy(int price) {
    money = money % price;
    stocks += money/price;
  }

  static void sell(int price) {
    money += price*stocks;
    stocks = 0;
  }


  static ArrayList<Integer> optimiseList(int[] prices) {
    ArrayList<Integer> newPrices = new ArrayList<>();
    newPrices.add(prices[0]);

    for (int i=1; i<prices.length-1; i++) {
      if (prices[i] == prices[i-1]){

      } else if (isLocalMax(prices[i], prices[i-1], prices[i+1])) {
        newPrices.add(prices[i]);
      } else if (isLocalMin(prices[i], prices[i-1], prices[i+1])) {
        newPrices.add(prices[i]);
      }
    }

    newPrices.add(prices[prices.length-1]);
    if (newPrices.size() > 2) {
      if (newPrices.get(0) > newPrices.get(1)) {
        newPrices.remove(0);
      }
      if (newPrices.size() > 2) {
        int newPriceSize = newPrices.size();
        if (newPrices.get(newPriceSize - 2) > newPrices.get(newPriceSize - 1)) {
          newPrices.remove(newPriceSize - 1);
        }
      }
    }

    return newPrices;
  }

  static boolean isLocalMax(int current, int previous, int next) {
    return (previous <= current && current > next);
  }

  static boolean isLocalMin(int current, int previous, int next) {
    return (previous >= current && current < next);
  }
}
