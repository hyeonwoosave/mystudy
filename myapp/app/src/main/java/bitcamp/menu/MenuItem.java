package bitcamp.menu;

import bitcamp.myapp.util.Prompt;

public class MenuItem implements Menu {

  String title;
  MenuHandler menuHandler;

  public MenuItem(String title) {
    this.title = title;
  }

  public MenuItem(String title, MenuHandler menuHandler) {
    this(title);
    this.menuHandler = menuHandler;
  }

  public void execute(Prompt prompt) {
    if (this.menuHandler != null) {
      this.menuHandler.action();
    }
  }

  public String getTitle() {
    return this.title;
  }
}
