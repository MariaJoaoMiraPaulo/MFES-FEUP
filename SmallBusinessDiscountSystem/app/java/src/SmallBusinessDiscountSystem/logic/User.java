package SmallBusinessDiscountSystem.logic;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  private String name;
  public static Number userId = 0L;
  public Number id = User.userId;

  public void cg_init_User_1(final String userName) {

    name = userName;
    id = User.userId;
    userId = User.userId.longValue() + 1L;
    return;
  }

  public User(final String userName) {

    cg_init_User_1(userName);
  }

  public String GetName() {

    throw new UnsupportedOperationException();
  }

  public User() {}

  public String toString() {

    return "User{"
        + "name := "
        + Utils.toString(name)
        + ", userId := "
        + Utils.toString(userId)
        + ", id := "
        + Utils.toString(id)
        + "}";
  }
}
