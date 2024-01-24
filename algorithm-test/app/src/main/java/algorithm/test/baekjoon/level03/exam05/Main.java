package algorithm.test.baekjoon.level03.exam05;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int count = N / 4;

    for (int i = 0; i < count; i++) {
      System.out.print("long ");
    }
    System.out.println("int");

  }
}
