package com.zhou.quickstart;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.zhou.common.User;
import com.zhou.common.UserController;
import com.zhou.common.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/23 23:40
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

  private UserController userController;
  private UserDao userDao;

  private HttpServletRequest request;

  @Before
  public void setUp() {
    this.userDao = Mockito.mock(UserDao.class);
    this.userController = new UserController(this.userDao);
    this.request = Mockito.mock(HttpServletRequest.class);

  }

  @Test
  public void testLoginSuccess() {
    User user = new User("zhangsan", "123456");
    when(request.getParameter("username")).thenReturn("Sun");
    when(request.getParameter("password")).thenReturn("123456");

    when(userDao.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(user);

    assert "login success".equals(userController.login(request));

  }

  @Test
  public void testLoginNotFound(){
    when(request.getParameter("username")).thenReturn("Sun");
    when(request.getParameter("password")).thenReturn("123456");
    when(userDao.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(null);

    assert "not found".equals(userController.login(request));

  }

  @Test
  public void testLogin505(){
    when(request.getParameter("username")).thenReturn("Sun");
    when(request.getParameter("password")).thenReturn("123456");
    when(userDao.findUserByUsernameAndPassword(anyString(), anyString())).thenThrow(new RuntimeException("run time error"));

    assert "505".equals(userController.login(request));

  }




}
