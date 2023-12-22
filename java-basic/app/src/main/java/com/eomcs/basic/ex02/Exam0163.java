// StringBuffer vs StringBuilder
package com.eomcs.basic.ex02;

public class Exam0163 {
  public static void main(String[] args) throws Exception {

    StringBuffer buf = new StringBuffer();

    Worker w1 = new Worker(buf, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    Worker w2 = new Worker(buf, "------------------------------------------------------------");
    Worker w3 = new Worker(buf, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    Worker w4 = new Worker(buf, "************************************************************");

    w1.start();
    w2.start();
    w3.start();
    w4.start();

    Thread.currentThread().sleep(2000);
    System.out.println("실행 끝!");
    System.out.println(buf.length());

  }

  static class Worker extends Thread {
    String message;
    StringBuffer buf;

    public Worker(StringBuffer buf, String message) {
      this.buf = buf;
      this.message = message;
    }

    @Override
    public void run() {
      for (int i = 0; i < 100; i++) {
        buf.append(message);
      }
      System.out.printf("'%s' 메시지 저장 끝!\n", message);
    }
  }
}


