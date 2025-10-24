package v4;

import java.util.*;

// MenuItem 클래스를 관리하는 클래스
public class Menu {

    private Map<String, List<MenuItem>> menuMap; // 카테고리명 -> 메뉴 리스트

    public Menu() {
        menuMap = new LinkedHashMap<>(); // 순서가 유지되는 맵

        // Burgers 카테고리
        List<MenuItem> burgers = Arrays.asList(
                new MenuItem("빅맥", 5500, "906 kcal"),
                new MenuItem("상하이버거", 5500, "825 kcal"),
                new MenuItem("페퍼로니피자버거", 7300, "970 kcal"),
                new MenuItem("더블불고기버거", 4500, "959 kcal")
        );

        // Drinks 카테고리
        List<MenuItem> drinks = Arrays.asList(
                new MenuItem("코카콜라", 1900, "133 kcal"),
                new MenuItem("코카콜라-제로", 1900, "0 kcal"),
                new MenuItem("스프라이트", 1900, "140 kcal")
        );

        // Desserts 카테고리
        List<MenuItem> sides = Arrays.asList(
                new MenuItem("후렌치후라이", 4900, "545 kcal"),
                new MenuItem("치킨모짜렐라스낵랩", 5900, "365 kcal"),
                new MenuItem("맥윙", 5500, "490 kcal")
        );

        // 맵에 key-value 저장
        menuMap.put("Burgers", burgers);
        menuMap.put("Drinks", drinks);
        menuMap.put("Sides", sides);
    }

    // 카테고리 리스트 반환
    public List<String> getCategories() {
        // return menuMap.keySet();
        // 컬렉션의 캡슐화, 리스트 객체를 생성(복사)해서 반환 (외부에서 수정이 불가능하게)
        // return new ArrayList<>(menuMap.keySet());
        // 불변 리스트 반환
        return List.copyOf(menuMap.keySet());
    }

    // 특정 카테고리의 메뉴 목록 반환
    public List<MenuItem> getMenuItems(String category) {
        // return menuMap.getOrDefault(category, new ArrayList<>());
        return List.copyOf(menuMap.get(category));
    }

    // 특정 카테고리의 메뉴 출력
    public void showMenuItems(String category) {
        List<MenuItem> items = menuMap.get(category);
        if (items == null) {
            System.out.println("해당 카테고리가 존재하지 않습니다.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            System.out.println((i + 1) + ". " + item.getMenuName() +
                    " | W " + (item.getPrice() / 1000.0) +
                    " | " + item.getDescription());
        }
    }
}
