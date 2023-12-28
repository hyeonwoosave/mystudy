package algorithm.test.baekjoon.level03.exam02;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    int i = 1;

    while (i <= T) {
      int A = sc.nextInt();
      int B = sc.nextInt();

      System.out.println(A + B);
      i++;
    }
  }
}
