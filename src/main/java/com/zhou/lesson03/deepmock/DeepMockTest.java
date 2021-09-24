package com.zhou.lesson03.deepmock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * 通过when去模拟。
 * 通过Answer去模拟
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/24 20:22
 */
public class DeepMockTest {
  @Mock
  private Lesson03Service lesson03Service;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Lesson03Service lesson03Service2;

  @Mock
  private Lesson03 lesson03;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testMock(){
    Lesson03 lesson03 = lesson03Service.get();
    lesson03.foo();
  }

  @Test
  public void testDeepMock(){
    Mockito.when(lesson03Service.get()).thenReturn(lesson03);

    Lesson03 lesson03 = lesson03Service.get();
    lesson03.foo();
  }

  @Test
  public void testDeepMockByAnswers(){
    lesson03Service2.get().foo();
  }



}
