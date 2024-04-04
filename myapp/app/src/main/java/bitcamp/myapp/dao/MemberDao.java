package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Member;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDao {

  public void add(Member member);

  public int delete(int no);

  public List<Member> findAll(
      @Param("offset") int offset,
      @Param("rowCount") int rowCount);

  public Member findBy(int no);

  public Member selectMemberInfo(@Param("email") String email);

  public int myinfoUpdate(Member member);

  public int update(Member member);

  public Member findByEmailAndPassword(
      @Param("email") String email,
      @Param("password") String password);

  int countAll();
}
