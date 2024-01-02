package algorithm.test.baekjoon.level03.exam04;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int X = sc.nextInt();
    int N = sc.nextInt();

    int i = 1;
    int p = 0;
    int w = 0;

    while (i <= N) {
      Scanner sc1 = new Scanner(System.in);

      int a = sc.nextInt();
      int b = sc.nextInt();

      a = a * b;
      i++;
      p = a;
      w = w + p;
    }
    if (w == X) {
      System.out.println("Yes");
    } else {
      System.out.println("No");
    }
  }
}
