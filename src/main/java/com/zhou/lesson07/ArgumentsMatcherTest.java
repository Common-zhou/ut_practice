package com.zhou.lesson07;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import javafx.scene.Parent;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/26 10:11
 */
@RunWith(MockitoJUnitRunner.class)
public class ArgumentsMatcherTest {
  @Test
  public void basicTest() {
    List<Integer> list = Mockito.mock(ArrayList.class);
    when(list.get(0)).thenReturn(100);

    assertThat(list.get(0), equalTo(100));
    assertThat(list.get(1), equalTo(null));
    assertThat(list.get(1), CoreMatchers.nullValue());
  }

  @Test
  public void testComplex() {
    Foo foo = Mockito.mock(Foo.class);

    // 子类的 匹配 只会匹配子类
    Mockito.when(foo.function(Mockito.isA(Child1.class))).thenReturn(100);

    int res = foo.function(new Child1());
    assertThat(res, equalTo(100));

    res = foo.function(new Child2());
    assertThat(res, equalTo(0));

    // 父类的 子类都会匹配上
    Mockito.when(foo.function(Mockito.isA(Parent.class))).thenReturn(100);

    res = foo.function(new Child1());
    assertThat(res, equalTo(100));

    res = foo.function(new Child2());
    assertThat(res, equalTo(100));


  }

  static class Foo {
    int function(Parent p) {
      return p.work();
    }
  }


  interface Parent {
    int work();
  }


  class Child1 implements Parent {

    @Override
    public int work() {
      throw new RuntimeException();
    }
  }


  class Child2 implements Parent {

    @Override
    public int work() {
      throw new RuntimeException();
    }
  }

}
