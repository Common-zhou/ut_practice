package com.zhou.common;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2021/09/23 23:33
 */
public class UserController {

  private UserDao userDao;

  public UserController(UserDao userDao) {
    this.userDao = userDao;
  }

  public String login(HttpServletRequest request) {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    System.out.println("username = " + username);
    System.out.println("password = " + password);

    try {
      User user = userDao.findUserByUsernameAndPassword(username, password);
      if (user == null) {
        return "not found";
      } else {
        return "login success";
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "505";
    }

  }

}
