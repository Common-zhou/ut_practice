package com.zhou.lesson03.mockmethod;

import com.zhou.common.User;
import com.zhou.common.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/24 10:00
 */
public class MockByAnnotationTest {

  @Before
  public void init(){
    MockitoAnnotations.initMocks(this);
  }

  @Mock(answer = Answers.RETURNS_SMART_NULLS)
  private UserDao userDao;


  @Test
  public void testMock(){
    User user = userDao.findUserByUsernameAndPassword("Alice", "123456");
    System.out.println(user);
  }

}
