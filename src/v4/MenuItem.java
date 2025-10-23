package v4;

// 개별 메뉴 항목 클래스
public class MenuItem {

    private String menuName;
    private int price;
    private String description;

    public MenuItem(String menuName, int price, String description) {
        this.menuName = menuName;
        this.price = price;
        this.description = description;
    }

    public String getMenuName() { return menuName; }
    public int getPrice() { return price; }
    public String getDescription() { return description; }
}
