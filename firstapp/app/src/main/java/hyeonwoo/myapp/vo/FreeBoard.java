package hyeonwoo.myapp.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FreeBoard implements Serializable {

  private static final long serialVersionUID = 100L;

  private int no;
  private String title;
  private String content;
  private User writer;
  private Date createdDate;
  private List<AttachedFile> files;

  @Override
  public String toString() {
    return "FreeBoard{" +
        "no=" + no +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", writer=" + writer +
        ", createdDate=" + createdDate +
        ", files=" + files +
        '}';
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getWriter() {
    return writer;
  }

  public void setWriter(User writer) {
    this.writer = writer;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public List<AttachedFile> getFiles() {
    return files;
  }

  public void setFiles(List<AttachedFile> files) {
    this.files = files;
  }
}
