package com.zhou.lesson04;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/26 00:05
 */
public class StubbingService {
  public int getI() {
    System.out.println("===========[getI]");
    return 10;
  }

  public String getS() {
    System.out.println("===========[getS]");
    throw new RuntimeException();
  }
}
