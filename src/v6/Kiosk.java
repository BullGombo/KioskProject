package v6;

import java.util.List;
import java.util.Scanner;

// 프로그램 순서 및 흐름 제어를 담당하는 클래스
public class Kiosk {

    // ---------------------------------- 속성 ----------------------------------
//    private List<MenuItem> menuList;  Menu클래스로 이동
    private final Scanner sc = new Scanner(System.in);
    private final Menu menu = new Menu(); // Menu 내부에서 모든 메뉴 초기화됨
    private final Cart cart = new Cart(); // 장바구니 객체 생성

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
            //int mainChoice = getIntInput();
            // v6 - 모든 입력이 가능하게 처리
            String choice = sc.nextLine().trim();

            // v6 - 장바구니 조회 분기
            if  (choice.equals("c")) {
                cart.showCart();
                continue;
            }

            // v6 - 결제 분기
            if (choice.equalsIgnoreCase("p")) {
                paymentActivate();
                continue;
            }
            
            // 입력 예외처리
            // v6 - 문법 수정
            if (choice.equals("0")) {
                System.out.println("프로그램을 종료합니다.");
                loopFlag = false;
                break;
            }

            // v6 - 정수 입력 예외처리 정규식
            if (!choice.matches("\\d+")) {
                System.out.println("올바른 번호를 입력해주세요.");
                continue;
            }
            
            // v6 - 비교를 위한 정수형 치환
            int numChoice = Integer.parseInt(choice);
            if (numChoice < 1 || numChoice > categories.size()) {
                System.out.println("올바른 번호를 입력해주세요.");
                continue;
            }

            // 선택된 서브 화면 출력
            String selectedCategory = categories.get(numChoice - 1);
            showSubMenu(selectedCategory);

        }   // while문 끝
        sc.close(); // 자원 정리

    }   // start() 끝

    // 해당 메서드는 루프문 안에 위치하기 때문에, categories를 반환하는 리스트 타입 메서드로 선언
    // 메인 카테고리 메뉴 표시
    private List<String> showMainMenu() {
        List<String> categories = menu.getCategories();

        System.out.println("\n[ MAIN MENU ]");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        // v6 - 장바구니 + 결제
        System.out.println("c. 장바구니");
        System.out.println("p. 결제");
        System.out.println("0. 종료");

        // 자원 반환 필수!
        return categories;
    }

    // 선택한 카테고리의 메뉴 표시, 메뉴 선택 입력 대기
    private void showSubMenu(String category) {
        List<MenuItem> items = menu.getMenuItems(category);

        while (true) {
            System.out.println("\n[ " + category.toUpperCase() + " MENU ]");
            showMenuItems(category);
            // v6 - 장바구니 + 결제
            System.out.println("c. 장바구니");
            System.out.println("p. 결제하기");
            System.out.println("0. 뒤로가기");
            System.out.print(">> 메뉴 번호 입력: ");
            //int choice = getIntInput();
            // v6 - 모든 입력이 가능하게 처리
            String choice = sc.nextLine().trim();

            // v6 - 장바구니 조회 분기
            if (choice.equals("c")) {
                cart.showCart();
                continue;
            }

            // v6 - 결제 분기
            if (choice.equalsIgnoreCase("p")) {
                paymentActivate();
                continue;
            }

            // v6 - 문법 수정
            if (choice.equals("0")) {
                break;
            }

            // v6 - 숫자가 아닌 경우 예외처리 수정
            // 문자열이 숫자로만 이루어져 있는지 검사하는 정규식
            if (!choice.matches("\\d+")) {
                System.out.println("올바른 메뉴를 입력해주세요.");
            }

            // v6 - 입력 예외 처리
            try {
                // 정수처리용 변수 선언
                int numChoice = Integer.parseInt(choice);
                if (numChoice < 1 || numChoice > items.size()) {
                    System.out.println("올바른 번호를 입력해주세요.");
                    continue;
                }

                // 실제 선택한 메뉴 정보 텍스트 출력
                MenuItem selected = items.get(numChoice - 1);
                System.out.println("선택한 메뉴: " + selected.getMenuName() +
                        " | W " + (selected.getPrice() / 1000.0) +
                        " | " + selected.getDescription());

                // v6 - 장바구니 추가 재차 확인
                System.out.print("장바구니에 추가하시겠습니까? (y/n): ");
                String confirm = sc.nextLine().trim().toLowerCase();
                if (confirm.equals("y")) {
                    System.out.print("수량을 입력해주세요: ");
                    String qtyStr = sc.nextLine().trim();
                    if (qtyStr.matches("\\d+")) { // 정수 입력 검사
                        int qty = Integer.parseInt(qtyStr);

                        // 장바구니 추가 메서드 호출
                        cart.addCartItem(selected.getMenuName(), selected.getPrice(), qty);
                        cart.showCart();

                    } else {    // 정수가 아닐 경우
                        System.out.println("수량은 숫자로 입력해주세요.");
                    }
                    // 'y' 이외의 입력 처리
                } else if (confirm.equals("n")) {
                    System.out.println("취소되었습니다.");
                } else { 
                    System.out.println("y/n 이 입력되지 않아 취소되었습니다.");
                }

                // 입렵된 값이 숫자나 'c'가 아닌 경우
            } catch (NumberFormatException e) {
                System.out.println("올바른 입력이 아닙니다.");
                continue;
            }

        } // while문 끝
    } // showSubMenu(String category) 끝

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
    } // showMenuItems(String category) 끝
    
    // 결제 메서드
    private  void paymentActivate() {
        System.out.println("\n[ 결제 확인 ]");
        // 장바구니 출력
        cart.showCart();
        
        // 장바구니 isEmpty 판별
        if (cart.isEmpty()) {
            System.out.println("결제할 항목이 없습니다.");
            return;
        } else {    // 결제 재차 확인
            System.out.print("결제를 진행하시겠습니까? (y = 결제 진행, 그외 입력 = 취소): ");
            String confirm = sc.nextLine().trim().toLowerCase();
            if (confirm.equals("y")) {  // 'y'
                System.out.println("💳 결제가 완료되었습니다. 감사합니다!");
                cart.clearCart(); // 장바구니 초기화
            } else {
                System.out.println("결제가 취소되었습니다.");
            }
        }
    } // paymentActivate() 끝

//    private void showCart() {
//    }

    // 숫자 입력 처리
//    private int getIntInput() {
//        try {
//            return Integer.parseInt(sc.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("숫자를 입력해주세요.");
//            return -1;
//        }
//    }

}
