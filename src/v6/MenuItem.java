package v6;

// 세부 메뉴 속성을 가지는 클래스
public class MenuItem {

    // ---------------------------------- 속성 ----------------------------------
    private String menuName;
    private int price;
    private String description;

    // ---------------------------------- 생성자 ----------------------------------
    public MenuItem(String menuName, int price, String description) {
        this.menuName = menuName;
        this.price = price;
        this.description = description;
    }

    // ---------------------------------- 기능 ----------------------------------
    public String getMenuName() { return menuName; }
    public int getPrice() { return price; }
    public String getDescription() { return description; }
}
