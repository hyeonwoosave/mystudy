package com.eomcs.oop.ex02.test.util;

public class Calculator {

  // 계산 결과를 담을 변수를 준비한다.
  public int result = 0;

  public void plus(int value) {
    this.result += value;
  }

  public void minus(int value) {
    this.result -= value;
  }

  public void multiple(int value) {
    this.result *= value;
  }

  public void divide(int value) {
    this.result /= value;
  }

  // 다음 메서드는 계산 결과를 사용하지 않는다.
  // 파라미터 값에 대해 절대값을 리턴하는 일을 한다.
  public static int abs(int a) {
    return a >= 0 ? a : a * -1;
  }

}
