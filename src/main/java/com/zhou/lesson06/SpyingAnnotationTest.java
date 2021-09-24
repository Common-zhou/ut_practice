package com.zhou.lesson06;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

/**
 * Mockito.spy 作用于真实对象，执行方法会被真实执行，但是可以对部分方法进行Mock。Stubbing所有的方法都是Mock的（虚假的）。
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/26 09:39
 */
public class SpyingAnnotationTest {

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Spy
  List<String> listByAnno = new ArrayList<>();

  @Test
  public void testSpyByMethod() {

    // Spy by method
    // spy 就是先假设它是真实对象 之后stubbing 部分动作
    // mock 就是先假设它是加对象 之后stubbing 部分动作
    // 这是通过 方法获取Spy对象
    List<String> listRaw = new ArrayList<>();
    List<String> list = Mockito.spy(listRaw);


    list.add("Mockito");
    list.add("PowerMock");
    assertThat(list.get(0), equalTo("Mockito"));
    assertThat(list.get(1), equalTo("PowerMock"));

    // 以上是真实的 ========== 以下为stubbing动作
    when(list.isEmpty()).thenReturn(true);
    when(list.size()).thenReturn(100);

    assertThat(list.get(0), equalTo("Mockito"));
    assertThat(list.get(1), equalTo("PowerMock"));
    assertThat(list.isEmpty(), equalTo(true));
    assertThat(list.size(), equalTo(100));

  }

  @Test
  public void testSpyByAnno() {
    listByAnno.add("test1");
    listByAnno.add("test2");

    assertThat(listByAnno.get(0), equalTo("test1"));
    assertThat(listByAnno.get(1), equalTo("test2"));


    // stubbing 语法 模拟list 的isEmpty() 模拟返回size()
    when(listByAnno.isEmpty()).thenReturn(true);
    when(listByAnno.size()).thenReturn(0);

    assertThat(listByAnno.get(0), equalTo("test1"));
    assertThat(listByAnno.get(1), equalTo("test2"));
    assertThat(listByAnno.isEmpty(), equalTo(true));
    assertThat(listByAnno.size(), equalTo(0));


  }

}
