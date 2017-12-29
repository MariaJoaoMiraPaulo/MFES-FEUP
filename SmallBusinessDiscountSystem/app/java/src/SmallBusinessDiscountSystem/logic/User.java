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

    return name;
  }

  public Boolean fullTextSearch(final String text) {

    String temp_text = name;
    Boolean match = false;
    Boolean whileCond_1 = true;
    while (whileCond_1) {
      Boolean andResult_10 = false;

      if (temp_text.length() >= text.length()) {
        if (!(match)) {
          andResult_10 = true;
        }
      }

      whileCond_1 = andResult_10;

      if (!(whileCond_1)) {
        break;
      }

      {
        match = true;
        long toVar_1 = text.length();

        for (Long index = 1L; index <= toVar_1; index++) {
          Boolean andResult_11 = false;

          if (match) {
            if (!(Utils.equals(
                text.charAt(Utils.index(index)), temp_text.charAt(Utils.index(index))))) {
              andResult_11 = true;
            }
          }

          if (andResult_11) {
            match = false;
          }
        }
        if (match) {
          return true;

        } else {
          temp_text = SeqUtil.tail(temp_text);
          match = false;
        }
      }
    }

    return false;
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
