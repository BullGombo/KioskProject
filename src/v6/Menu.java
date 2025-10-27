package v6;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// MenuItem 클래스를 관리하는 클래스
public class Menu {
    // 순서가 유지되는 LinkedHashMap을 private final로 선언
    // key=String(카테고리) - value=List<MenuItem>(메뉴리스트)
    private final Map<String, List<MenuItem>> menuMap = new LinkedHashMap<>();

    public Menu() {
        initMenu();
    }

    private void initMenu() {

        // Burgers 카테고리
        menuMap.put("Burgers", Arrays.asList(
                new MenuItem("빅맥", 5500, "906 kcal"),
                new MenuItem("상하이버거", 5500, "825 kcal"),
                new MenuItem("페퍼로니피자버거", 7300, "970 kcal"),
                new MenuItem("더블불고기버거", 4500, "959 kcal")
        ));
        // Drinks 카테고리
        menuMap.put("Drinks", Arrays.asList(
                new MenuItem("코카콜라", 1900, "133 kcal"),
                new MenuItem("코카콜라-제로", 1900, "0 kcal"),
                new MenuItem("스프라이트", 1900, "140 kcal")
        ));
        // Sides 카테고리
        menuMap.put("Sides", Arrays.asList(
                new MenuItem("후렌치후라이", 4900, "545 kcal"),
                new MenuItem("치킨모짜렐라스낵랩", 5900, "365 kcal"),
                new MenuItem("맥윙", 5500, "490 kcal")
        ));

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

}
