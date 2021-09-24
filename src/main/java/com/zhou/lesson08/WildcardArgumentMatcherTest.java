package com.zhou.lesson08;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.Collections;

/**
 * wildCard 匹配多的 放在前面 特殊的放在后面 这样才会被特殊对待
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/26 23:32
 */
@RunWith(MockitoJUnitRunner.class)
public class WildcardArgumentMatcherTest {
  @Mock
  private SimpleService simpleService;

  @After
  public void destroy() {
    Mockito.reset(simpleService);
  }

  /**
   * 协助在Stubbing语法中，方法中对不同的入参 配置不同的返回值。
   * 【顺序：统配方法最前，特殊返回值方法在后】。就是做一些预设值之类的工作。
   */
  @Test
  public void wildCardMethod1() {
    Mockito.when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class)))
        .thenReturn(100);
    int res = simpleService.method1(1, "Sun", Collections.emptyList(), "Mockito");
    assertThat(res, equalTo(100));
  }

  @Test
  public void wildCardMethodWithSpecial() {

    // 注意顺序 统配的 在前面  特殊的 在后面
    Mockito.when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(-1);
    Mockito.when(simpleService.method1(anyInt(), eq("Jack"), anyCollection(), isA(Serializable.class))).thenReturn(100);
    Mockito.when(simpleService.method1(anyInt(), eq("Rose"), anyCollection(), isA(Serializable.class))).thenReturn(200);

    int res = simpleService.method1(1, "Jack", Collections.emptyList(), "Mockito");
    assertThat(res, equalTo(100));
    res = simpleService.method1(1, "Rose", Collections.emptyList(), "Mockito");
    assertThat(res, equalTo(200));

    res = simpleService.method1(1, "Sun", Collections.emptySet(), "PowerMock");
    // 大家都没匹配上 就匹配上第一个
    assertThat(res, equalTo(-1));
  }

  @Test
  public void wildCardMethod2() {
    Mockito.doNothing().when(simpleService).method2(anyInt(), anyString(), anyCollection(), isA(Serializable.class));
    simpleService.method2(2, "Sun", Collections.emptyList(), "Mockito");
    Mockito.verify(simpleService, Mockito.times(1)).method2(anyInt(), eq("Sun"), anyCollection(),
        isA(Serializable.class));
  }
}
