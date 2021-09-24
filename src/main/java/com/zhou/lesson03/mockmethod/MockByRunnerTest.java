package com.zhou.lesson03.mockmethod;

import com.zhou.common.User;
import com.zhou.common.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/24 10:03
 */
@RunWith(MockitoJUnitRunner.class)
public class MockByRunnerTest {

  @Test
  public void testMock() {
    UserDao userDao = Mockito.mock(UserDao.class, Mockito.RETURNS_SMART_NULLS);

    // 如果直接使用之前的对象，会报错
    // UserDao userDao = new UserDao();

    User user = userDao.findUserByUsernameAndPassword("Alice", "123456");

    System.out.println(user);
  }
}
