package hyeonwoo.myapp.dao.mysql;

import hyeonwoo.myapp.dao.DaoException;
import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.myapp.vo.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

  Connection con;

  public UserDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void add(User user) {
    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate(String.format(
          "insert into users(email,name,password) values('%s','%s',sha2('%s',256))",
          user.getEmail(), user.getName(), user.getPassword()));

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format("delete from users where user_no=%d", no));

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<User> findAll() {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from users");

      ArrayList<User> list = new ArrayList<>();

      while (rs.next()) {
        User user = new User();
        user.setNo(rs.getInt("user_no"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        user.setCreatedDate(rs.getDate("created_date"));

        list.add(user);
      }
      return list;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public User findBy(int no) {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from users where user_no = " + no);

      if (rs.next()) {
        User user = new User();
        user.setNo(rs.getInt("user_no"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        user.setCreatedDate(rs.getDate("created_date"));

        return user;
      }
      return null;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(User user) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format(
          "update users set email='%s', name='%s', password=sha2('%s',256) where user_no=%d",
          user.getEmail(), user.getName(), user.getPassword(), user.getNo()));

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
