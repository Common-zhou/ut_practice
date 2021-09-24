package com.zhou.lesson09;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.core.CombinableMatcher.either;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * hamcrest的一些方法
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/27 00:19
 */
public class AssertMatcherTest {
  @Test
  public void test() {
    int i = 10;
    assertThat(i, equalTo(10));
    assertThat(i, is(10));
    assertThat(i, not(20));

    double price = 23.45;

    // 二选一 有一个对就行
    assertThat(price, either(equalTo(23.45)).or(equalTo(23.54)));
    //assertThat(price, either(equalTo(23.485)).or(equalTo(23.54)));
  }

  @Test
  public void test2() {
    double price = 23.45;
    //assertThat(price, both(equalTo(23.45)).and(equalTo(23.54)));
    assertThat(price, both(equalTo(23.45)).and(equalTo(23.45)));
    assertThat(price, anyOf(is(23.45), is(15.65), not(10.01)));

    assertThat(Stream.of(1, 2, 3).anyMatch(i -> i > 2), equalTo(true));
    assertThat(Stream.of(1, 2, 3).allMatch(i -> i > 0), equalTo(true));

  }

  //java.lang.NoSuchMethodError: org.hamcrest.Matcher.describeMismatch(Ljava/lang/Object;Lorg/hamcrest/Description;)V
  // 单元测试失败 就会返回这个
  @Test
  public void test3() {
    double price = 23.45;

    assertThat("NotMatch", price, equalTo(23.4));
  }
}
