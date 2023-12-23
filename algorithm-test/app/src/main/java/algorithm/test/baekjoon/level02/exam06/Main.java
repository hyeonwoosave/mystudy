package algorithm.test.baekjoon.level02.exam06;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int A = sc.nextInt();
    int B = sc.nextInt();
    int C = sc.nextInt();

    if (B + C < 60) {
      B = B + C;
      System.out.print(A + " " + B);
    } else {
      A = (B + C) / 60 + A;
      B = (B + C) % 60;
      if (A < 24) {
        System.out.println(A + " " + B);
      } else {
        if (A == 24) {
          A = 0;
          System.out.println(A + " " + B);
        } else {
          if (A > 24) {
            A = A % 24;
            System.out.println(A + " " + B);
          }
        }
      }
    }
  }
}
