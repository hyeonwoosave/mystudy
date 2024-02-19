package bitcamp.myapp.dao;

import java.util.List;

public interface UserDao {

  public void add(bitcamp.myapp.vo.User user);

  public int delete(int no);

  public List<bitcamp.myapp.vo.User> findAll();

  public bitcamp.myapp.vo.User findBy(int no);

  public int update(bitcamp.myapp.vo.User user);

  public bitcamp.myapp.vo.User findByEmailAndPassword(String email, String password);
}
