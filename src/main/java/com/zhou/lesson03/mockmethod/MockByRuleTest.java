package com.zhou.lesson03.mockmethod;

import com.zhou.common.User;
import com.zhou.common.UserDao;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/24 10:13
 */
public class MockByRuleTest {
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock(answer = Answers.RETURNS_SMART_NULLS)
  private UserDao userDao;

  @Test
  public void testMock(){
    User user = userDao.findUserByUsernameAndPassword("Alice", "123456");
    System.out.println(user);
  }

}
