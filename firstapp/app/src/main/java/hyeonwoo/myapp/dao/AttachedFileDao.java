package hyeonwoo.myapp.dao;

import hyeonwoo.myapp.vo.AttachedFile;
import java.util.List;

public interface AttachedFileDao {

  void add(AttachedFile file);

  int addAll(List<AttachedFile> files);

  int delete(int no);

  int deleteAll(int freeboardNo);

  List<AttachedFile> findAllByBoardNo(int freeboardNo);

  AttachedFile findByNo(int no);
}

