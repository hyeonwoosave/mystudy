package algorithm.test.baekjoon.level02.exam05;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int H = sc.nextInt();
    int M = sc.nextInt();

    if (M >= 45) {
      M -= 45;
      System.out.println(H + " " + M);
    } else {
      M = 60 + M - 45;
      if (H == 0) {
        H = 23;
        System.out.println(H + " " + M);
      } else {
        H -= 1;
        System.out.println(H + " " + M);
      }
    }
  }
}


