package v6;

import java.util.List;
import java.util.Scanner;

// 프로그램 순서 및 흐름 제어를 담당하는 클래스
public class Kiosk {

    // ---------------------------------- 속성 ----------------------------------
//    private List<MenuItem> menuList;  Menu클래스로 이동
    private final Scanner sc = new Scanner(System.in);
    private final Menu menu = new Menu(); // Menu 내부에서 모든 메뉴 초기화됨


    // ---------------------------------- 생성자 ----------------------------------
//    public Kiosk(List<MenuItem> menuList) {   Menu클래스로 이동
//        this.menuList = menuList;
//    }


    // ---------------------------------- 기능 ----------------------------------
    // 메인/서브 메뉴 표시 함수 호출, 선택 입력 대기
    public void start() {
        boolean loopFlag = true;

        System.out.println("Kiosk V6 start");

        while (loopFlag) {
            // 메인 화면 출력, categories 리스트 반환
            List<String> categories = showMainMenu();

            // 카테고리 선택 입력
            System.out.print(">> 메뉴 번호 입력: ");
            int mainChoice = getIntInput();

            // 입력 예외처리
            if (mainChoice == 0) {
                System.out.println("프로그램을 종료합니다.");
                loopFlag = false;
                break;
            }
            if (mainChoice < 1 || mainChoice > categories.size()) {
                System.out.println("올바른 번호를 입력해주세요.");
                continue;
            }

            // 선택된 서브 화면 출력
            String selectedCategory = categories.get(mainChoice - 1);
            showSubMenu(selectedCategory);

        }   // while문 끝
        sc.close(); // 자원 정리

    }   // start() 끝

    // 해당 메서드는 루프문 안에 위치하기 때문에, categories를 반환하는 리스트 타입 메서드로 선언
    // 메인 카테고리 메뉴 표시, 카테고리 선택 입력 대기
    private List<String> showMainMenu() {
        List<String> categories = menu.getCategories();

        System.out.println("\n[ MAIN MENU ]");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        System.out.println("0. 종료");

        // 
        return categories;
    }

    // 선택한 카테고리의 메뉴 표시, 메뉴 선택 입력 대기
    private void showSubMenu(String category) {
        List<MenuItem> items = menu.getMenuItems(category);

        while (true) {
            System.out.println("\n[ " + category.toUpperCase() + " MENU ]");
            showMenuItems(category);
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

    // 특정 카테고리의 메뉴 출력
    private void showMenuItems(String category) {
        List<MenuItem> items = menu.getMenuItems(category);
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
