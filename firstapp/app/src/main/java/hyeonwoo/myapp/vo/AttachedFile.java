package hyeonwoo.myapp.vo;

public class AttachedFile {

  private int no;
  private String filePath;
  private int freeboardNo;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public int getFreeBoardNo() {
    return freeboardNo;
  }

  public void setFreeBoardNo(int freeboardNo) {
    this.freeboardNo = freeboardNo;
  }

  public AttachedFile filePath(String filePath) {
    this.filePath = filePath;
    return this;
  }

  public AttachedFile freeboardNo(int freeboardNo) {
    this.freeboardNo = freeboardNo;
    return this;
  }
}
