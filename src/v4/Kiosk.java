package v4;

import java.util.List;
import java.util.Scanner;

// 프로그램 순서 및 흐름 제어를 담당하는 클래스
public class Kiosk {

    // ---------------------------------- 속성 ----------------------------------
//    private List<MenuItem> menuList;  Menu클래스로 이동
    private Scanner sc = new Scanner(System.in);
    private Menu menu = new Menu(); // Menu 내부에서 모든 메뉴 초기화됨


    // ---------------------------------- 생성자 ----------------------------------
    // List<MenuItem> menuList 는 Kiosk 클래스 생성자를 통해 값을 할당
    // Kiosk 객체를 생성하고 사용하는 main 함수에서 객체를 생성할 때 값을 넘겨줌
//    public Kiosk(List<MenuItem> menuList) {   Menu클래스로 이동
//        this.menuList = menuList;
//    }


    // ---------------------------------- 기능 ----------------------------------
    // 메인 메뉴 표시, 카테고리 선택 입력 대기
    public void start() {


        boolean loopFlag = true;

        System.out.println("Kiosk V4 showMenu");

        while (loopFlag) {
            System.out.println("\n[ MAIN MENU ]");
            // List<String> categories = new ArrayList<>(menuMap.keySet());
            // List<String> categories = List.copyOf(menuMap.keySet());
            List<String> categories = menu.getCategories();

            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i));
            }
            System.out.println("0. 종료");

            System.out.print(">> 메뉴 번호 입력: ");
            int mainChoice = getIntInput();

            if (mainChoice == 0) {
                System.out.println("프로그램을 종료합니다.");
                loopFlag = false;
                break;
            }

            if (mainChoice < 1 || mainChoice > categories.size()) {
                System.out.println("올바른 번호를 입력해주세요.");
                continue;
            }

            String selectedCategory = categories.get(mainChoice - 1);
            showSubMenu(selectedCategory);

        }   // while문 끝
        sc.close(); // 자원 정리

    }   // start() 끝

    // 카테고리 내 세부 메뉴 표시, 메뉴 선택 입력 대기
    private void showSubMenu(String category) {
        List<MenuItem> items = menu.getMenuItems(category);

        while (true) {
            System.out.println("\n[ " + category.toUpperCase() + " MENU ]");
            menu.showMenuItems(category);
            System.out.println("0. 뒤로가기");

            System.out.print(">> 메뉴 번호 입력: ");
            int choice = getIntInput();

            if (choice == 0) {
                break;
            }

            if (choice < 1 || choice > items.size()) {
                System.out.println("올바른 번호를 입력해주세요.");
                continue;
            }

            MenuItem selected = items.get(choice - 1);
            System.out.println("선택한 메뉴: " + selected.getMenuName() +
                    " | W " + (selected.getPrice() / 1000.0) +
                    " | " + selected.getDescription());
        }
    }

    // 숫자 입력 처리
    private int getIntInput() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return -1;
        }
    }

}
