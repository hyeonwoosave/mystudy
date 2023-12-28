package algorithm.test.baekjoon.level03.exam03;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int i = 1;
    int p;
    int t;
    int u = 0;

    while (i <= n) {
      p = i;
      i++;
      t = p;
      u = u + p;
    }
    System.out.println(u);
  }
}
