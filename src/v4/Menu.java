package v4;

import java.util.*;

// 전체 메뉴를 관리하는 클래스
public class Menu {

    private Map<String, List<MenuItem>> menuMap; // 카테고리명 -> 메뉴 리스트

    public Menu() {
        menuMap = new LinkedHashMap<>(); // 순서가 유지되는 맵

        // --------------------------
        // 1️⃣ Burgers 카테고리
        // --------------------------
        List<MenuItem> burgers = Arrays.asList(
                new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거")
        );

        // --------------------------
        // 2️⃣ Drinks 카테고리
        // --------------------------
        List<MenuItem> drinks = Arrays.asList(
                new MenuItem("Coke", 2500, "시원한 코카콜라"),
                new MenuItem("Sprite", 2500, "톡 쏘는 사이다"),
                new MenuItem("Iced Tea", 3000, "상큼한 아이스티")
        );

        // --------------------------
        // 3️⃣ Desserts 카테고리
        // --------------------------
        List<MenuItem> desserts = Arrays.asList(
                new MenuItem("Frozen Custard", 4900, "진한 바닐라 아이스크림"),
                new MenuItem("Shake", 5900, "부드러운 밀크셰이크"),
                new MenuItem("Apple Pie", 5500, "따뜻한 사과 파이")
        );

        // --------------------------
        // 맵에 저장
        // --------------------------
        menuMap.put("Burgers", burgers);
        menuMap.put("Drinks", drinks);
        menuMap.put("Desserts", desserts);
    }

    // 카테고리 리스트 반환
    public List<String> getCategories() {
        return new ArrayList<>(menuMap.keySet());
    }

    // 특정 카테고리의 메뉴 목록 반환
    public List<MenuItem> getMenuItems(String category) {
        return menuMap.getOrDefault(category, new ArrayList<>());
    }

    // 특정 카테고리의 메뉴 출력
    public void showMenuItems(String category) {
        List<MenuItem> items = menuMap.get(category);
        if (items == null) {
            System.out.println("⚠️ 해당 카테고리가 존재하지 않습니다.");
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
