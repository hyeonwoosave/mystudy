package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.DaoException;
import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements bitcamp.myapp.dao.UserDao {

  DBConnectionPool connectionPool;

  public UserDaoImpl(DBConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public void add(bitcamp.myapp.vo.User user) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "insert into members(email,name,password) values(?,?,sha2(?,256))")) {
      pstmt.setString(1, user.getEmail());
      pstmt.setString(2, user.getName());
      pstmt.setString(3, user.getPassword());
      pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "delete from members where member_no=?")) {
      pstmt.setInt(1, no);
      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<bitcamp.myapp.vo.User> findAll() {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select member_no, email, name, created_date from members");
        ResultSet rs = pstmt.executeQuery();) {

      ArrayList<bitcamp.myapp.vo.User> list = new ArrayList<>();

      while (rs.next()) {
        bitcamp.myapp.vo.User user = new bitcamp.myapp.vo.User();
        user.setNo(rs.getInt("member_no"));
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
  public bitcamp.myapp.vo.User findBy(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select member_no, email, name, created_date from members where member_no=?")) {
      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          bitcamp.myapp.vo.User user = new bitcamp.myapp.vo.User();
          user.setNo(rs.getInt("member_no"));
          user.setEmail(rs.getString("email"));
          user.setName(rs.getString("name"));
          user.setCreatedDate(rs.getDate("created_date"));
          return user;
        }
        return null;
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(bitcamp.myapp.vo.User user) {
    String sql = null;
    if (user.getPassword().length() == 0) {
      sql = "update members set email=?, name=? where member_no=?";
    } else {
      sql = "update members set email=?, name=?, password=sha2(?,256) where member_no=?";
    }

    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setString(1, user.getEmail());
      pstmt.setString(2, user.getName());
      pstmt.setString(3, user.getPassword());
      pstmt.setInt(4, user.getNo());
      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }

  @Override
  public bitcamp.myapp.vo.User findByEmailAndPassword(String email, String password) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select member_no, email, name, created_date from members where email=? and password=sha2(?,256)")) {
      pstmt.setString(1, email);
      pstmt.setString(2, password);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          bitcamp.myapp.vo.User user = new bitcamp.myapp.vo.User();
          user.setNo(rs.getInt("member_no"));
          user.setEmail(rs.getString("email"));
          user.setName(rs.getString("name"));
          user.setCreatedDate(rs.getDate("created_date"));
          return user;
        }
        return null;
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }
}
