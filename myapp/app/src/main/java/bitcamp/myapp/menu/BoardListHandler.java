package bitcamp.myapp.menu;

import bitcamp.menu.MenuHandler;
import bitcamp.myapp.vo.Board;

public class BoardListHandler implements MenuHandler {

  BoardRepository boardRepository;

  public BoardListHandler(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }


  @Override
  public void action() {
    System.out.println("게시글 목록:");
    System.out.printf("%-20s\t%10s\t%s\n", "Title", "Writer", "Date");

    for (int i = 0; i < this.boardRepository.length; i++) {
      Board board = this.boardRepository.boards[i];
      System.out.printf("%-20s\t%10s\t%s\n", board.title, board.writer, board.createdDate);
    }
  }
}
