package bitcamp.myapp;

import static bitcamp.myapp.Prompt.input;

import bitcamp.myapp.vo.Assignment;

public class AssignmentMenu {

  static Assignment assignment = new Assignment();

  static void printMenu() {
    System.out.println(MainMenu.AM_TITLE);
    System.out.println("1. 등록");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("0. 이전");
  }

  static void execute() {
    printMenu();

    while (true) {
      String input = input("메인/과제> ");

      switch (input) {
        case "1":
          assignment.add();
          break;
        case "2":
          assignment.view();
          break;
        case "3":
          assignment.modify();
          break;
        case "4":
          assignment.delete();
          break;
        case "0":
          MainMenu.printMenu();
          return;
        case "menu":
          printMenu();
          break;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다.");
      }
    }
  }
}
