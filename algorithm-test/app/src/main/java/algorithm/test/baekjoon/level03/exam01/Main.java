package algorithm.test.baekjoon.level03.exam01;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    int i = 1;

    while (i <= 9) {
      System.out.println(N + " " + "*" + " " + i + " " + "=" + " " + (N * i));
      i++;
    }
  }
}

