package com.zhou.lesson08;

import java.io.Serializable;
import java.util.Collection;

/**
 * 两个测试方法  一个有返回值 一个没有返回值
 * 主要测试的 参数的匹配 以及无返回值时如何操作
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/26 23:30
 */
public class SimpleService {
  public int method1(int i, String s, Collection<?> c, Serializable serializable){
    throw new RuntimeException();
  }
  public void method2(int i, String s, Collection<?> c, Serializable serializable){
    throw new RuntimeException();
  }

}
