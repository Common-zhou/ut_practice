package com.zhou.lesson04;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/26 00:08
 */
@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {
  private List<String> list;

  @Before
  public void init() {
    this.list = Mockito.mock(ArrayList.class);
  }

  @After
  public void destroy() {
    Mockito.reset(this.list);
  }

  /**
   * stubbing 就是代表模拟行为
   */
  @Test
  public void howToUseStubbing() {
    // 完成一次Stubbing 当调用list.get(0)方法的时候 会返回first字符串
    // 如果这时候调用其他呢 会返回null
    Mockito.when(list.get(0)).thenReturn("first");
    assertThat(list.get(0), equalTo("first"));

    // 单元测试内部 一定要用断言 否则你的单元测试没有作用
    assertThat(list.get(1), equalTo(null));

    // 这时候 将所有get行为 都改成了抛异常
    Mockito.when(list.get(anyInt())).thenThrow(new RuntimeException());
    try {
      list.get(0);
      fail();
    } catch (Exception e) {
      assertThat(e, instanceOf(RuntimeException.class));
    }

  }

  @Test
  public void howToStubbingVoidMethod() {
    Mockito.doNothing().when(list).clear();
    list.clear();

    // 打扰了 不懂这里
    Mockito.verify(list, Mockito.times(1)).clear();

    // 意思是 先定义行为。之后是哪个对象 做什么行为
    Mockito.doThrow(RuntimeException.class).when(list).clear();

    // 只有Void方法可以实用这种方法
    // StubbingService mock = Mockito.mock(StubbingService.class);
    // Mockito.doNothing().when(mock).getI();

    // mock.getI();
    // Mockito.verify(mock, Mockito.times(1)).getI();


  }

  @Test
  public void stubbingDoReturn() {
    // 这两种方式等价
    Mockito.when(list.get(0)).thenReturn("first");
    Mockito.doReturn("second").when(list).get(1);

    assertThat(list.get(0), equalTo("first"));
    assertThat(list.get(1), equalTo("second"));
  }

  /**
   * 迭代 调用次序不同 返回内容不同
   */
  @Test
  public void iterateStubbing() {
    // 这样写 就代表 每调一次 就返回一个
    Mockito.when(list.size()).thenReturn(1, 2, 3, 4);
    // Mockito.when(list.size()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4);

    assertThat(list.size(), equalTo(1));
    assertThat(list.size(), equalTo(2));
    assertThat(list.size(), equalTo(3));
    assertThat(list.size(), equalTo(4));
  }

  @Test
  public void stubbingWithAnswer() {

    Mockito.when(list.get(anyInt())).thenAnswer(invocationOnMock -> {
          Integer index = invocationOnMock.getArgumentAt(0, Integer.class);
          return String.valueOf(index * 10);
        }
    );
    assertThat(list.get(0), equalTo("0"));
    assertThat(list.get(1), equalTo("10"));
    assertThat(list.get(99), equalTo("990"));
  }

  @Test
  public void stubbingWithRealCall(){
    StubbingService stubbingService = Mockito.mock(StubbingService.class);

    Mockito.when(stubbingService.getS()).thenReturn("String");
    Mockito.when(stubbingService.getI()).thenReturn(1);

    assertThat(stubbingService.getS(), equalTo("String"));
    assertThat(stubbingService.getI(), equalTo(1));

    // 更改stubbing行为
    Mockito.when(stubbingService.getI()).thenCallRealMethod();
    assertThat(stubbingService.getI(), equalTo(10));
  }


}
