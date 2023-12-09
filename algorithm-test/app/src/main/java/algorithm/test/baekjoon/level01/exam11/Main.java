package algorithm.test.baekjoon.level01.exam11;

import java.util.Scanner;

public class Main {
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    int a, b, c, d, e;

    a = sc.nextInt();
    b = sc.nextInt();

    c = b % 10;
    d = ((b % 100) - c) / 10;
    e = (b - c - d) / 100;

    System.out.println(a * c);
    System.out.println(a * d);
    System.out.println(a * e);
    System.out.println(a * b);

  }
}
