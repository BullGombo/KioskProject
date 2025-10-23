package v3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 세부 메뉴 속성 가지는 클래스
// 개별 음식 항목을 관리하는 클래스
public class MenuItem {

    // ---------------------------------- 속성 ----------------------------------
    private String menuName;
    private int price;
    private String description;
    //private int quantity;
    //private String category;

    // ---------------------------------- 생성자 ----------------------------------
    // 기본 생성자 -> MenuItem() {}
    // 1. 클래스와 이름이 동일
    // 2. 반환 데이터 타입이 없음
    // 3. 여러개 존재 가능
    
    // 기본 생성자
    public MenuItem() {} // 생략 가능

    public MenuItem(String menuName, int price, String description) {
        this.menuName = menuName;
        this.price = price;
        this.description = description;
    }

    // ---------------------------------- 기능 ----------------------------------

    // getter / setter
    // getter
    public String getMenuName() {return menuName;}
    public int getPrice() {return price;}
    public String getDescription() {return description;}
    public static List<MenuItem> getMenuList() {return menuList;}
    // 특정 메뉴 반환 (올바른 인덱스 접근을 위해 -1 처리 할 것 잊지말기)
    public static MenuItem getMenu(int index) {return menuList.get(index);}

    // setter
    public void setMenuName(String menuName) {this.menuName = menuName;}
    public void setPrice(int price) {this.price = price;}
    public void setDescription(String description) {this.description = description;}

    // 메뉴 리스트 세팅
    private static final List<MenuItem> menuList = new ArrayList<>(
            Arrays.asList(
                    new MenuItem("치즈버거", 7000, "빵 + 치즈 + 소고기 패티 + 양상추 + 토마토 + 양파 + 피클 + 소스"),
                    new MenuItem("불고기버거", 6000, "빵 + 치즈 + 소고기 패티 + 양상추 + 불고기 소스"),
                    new MenuItem("치킨버거", 7000, "빵 + 치킨 패티 + 양상추 + 토마토 + 마요네즈 소스")
            )
    );

}
