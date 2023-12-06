package bitcamp.myapp;

import static bitcamp.myapp.Prompt.input;

public class MemberMenu {

  static Member[] members = new Member[3];
  static int length = 0;

  static void printMenu() {
    System.out.println(MainMenu.MM_TITLE);
    System.out.println("1. 입력");
    System.out.println("2. 조회");
    System.out.println("3. 변경");
    System.out.println("4. 삭제");
    System.out.println("0. 이전");
  }

  static void execute() {
    printMenu();

    while (true) {
      String input = input("메인/회원> ");

      switch (input) {
        case "1":
          add();
          break;
        case "2":
          view();
          break;
        case "3":
          modify();
          break;
        case "4":
          delete();
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

  static void add() {
    System.out.println("회원 등록:");

    if (length == members.length) {
      int oldSize = members.length;
      int newSize = oldSize + (oldSize / 2);

      Member[] arr = new Member[newSize];
      for (int i = 0; i < oldSize; i++) {
        arr[i] = members[i];
      }

      members = arr;
    }
    Member member = new Member();
    member.email = Prompt.input("이메일? ");
    member.name = Prompt.input("이름? ");
    member.password = Prompt.input("암호? ");
    member.accessionDate = Prompt.input("가입일? ");

    members[length] = member;
    length++;
  }

  static void view() {
    System.out.println("회원 조회:");

    int index = Integer.parseInt(Prompt.input("번호? "));
    if (index < 0 || index >= length) {
      System.out.println("회원 번호가 유효하지 않습니다.");
      return;
    }

    Member member = members[index];
    System.out.printf("이메일: %s\n", member.email);
    System.out.printf("이름: %s\n", member.name);
    System.out.printf("암호: %s\n", member.password);
    System.out.printf("가입일: %s\n", member.accessionDate);
  }

  static void modify() {
    System.out.println("회원 변경:");

    int index = Integer.parseInt(Prompt.input("번호? "));
    if (index < 0 || index >= length) {
      System.out.println("회원 번호가 유효하지 않습니다.");
      return;
    }

    Member member = members[index];
    member.email = Prompt.input("이메일(%s)? ", member.email);
    member.name = Prompt.input("이름(%s)? ", member.name);
    member.password = Prompt.input("암호(%s)? ", member.password);
    member.accessionDate = Prompt.input("가입일(%s)? ", member.accessionDate);
  }

  static void delete() {
    System.out.println("회원 삭제: ");

    int index = Integer.parseInt(Prompt.input("번호? "));
    if (index < 0 || index >= length) {
      System.out.println("회원 번호가 유효하지 않습니다.");
      return;
    }

    for (int i = index; i < (length - 1); i++) {
      members[i] = members[i + 1];
    }
    members[--length] = null;
  }
}

