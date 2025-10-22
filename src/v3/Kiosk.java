package v3;

import java.util.List;
import java.util.Scanner;

// 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스
// main 함수에서 관리하던 전체 순서 제어를 Kiosk 클래스를 통해 관리하도록
public class Kiosk {

    // ---------------------------------- 속성 ----------------------------------
    // MenuItem을 관리하는 리스트가 필드로 존재
    private List<MenuItem> menuList;
    private Scanner sc = new Scanner(System.in);


    // ---------------------------------- 생성자 ----------------------------------
    // List<MenuItem> menuList 는 Kiosk 클래스 생성자를 통해 값을 할당
    // Kiosk 객체를 생성하고 사용하는 main 함수에서 객체를 생성할 때 값을 넘겨줌
    public Kiosk(List<MenuItem> menuList) {
        this.menuList = menuList;
    }


    // ---------------------------------- 기능 ----------------------------------
    // main 함수에서 관리하던 입력과 반복문 로직은 이제 start 함수를 만들어 관리
    public void start() {

        List<MenuItem> menuList = MenuItem.getMenuList();

        boolean loopFlag = true;

        System.out.println("Kiosk V3");

        while (loopFlag) {
//        ========================================
//        ================[ MENU ]================
//        1. 치즈버거 | 7000원 | 구성 : 재료1 + 재료2 + 재료3 + ...
//        2. 불고기버거 | 6000원 | 구성 : 재료1 + 재료2 + 재료3 + ...
//        3. 치킨버거 | 7000원 | 구성 : 재료1 + 재료2 + 재료3 + ...
//        9. 장바구니
//        0. 종료
//        ========================================

            // 메뉴 텍스트 출력
            System.out.println("========================================");
            System.out.println("================[ MENU ]================");
//            System.out.println("1. " + menuList.get(0).getMenuName() + " | " +
//                    menuList.get(0).getPrice() + "원 | 구성 : " +
//                    menuList.get(0).getDescription());
            // 기존 처리를 향상시킨 반복문 - 메뉴 리스트 자동 출력
            for (int i = 0; i < menuList.size(); i++) {
                MenuItem item = menuList.get(i);
                System.out.println((i + 1) + ". " + item.getMenuName() +
                        " | " + item.getPrice() + "원 | 구성 : " + item.getDescription());
            }
            System.out.println("8. 장바구니");
            System.out.println("9. 주문");
            System.out.println("0. 종료");
            System.out.println("========================================");
            // 입력 대기
            System.out.print("메뉴 번호 입력 : ");

            // 입력 처리 (예외 방지)
            int menuSelectNum;
            try {
                menuSelectNum = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("입력 오류 : 숫자를 입력해주세요.\n");
                continue; // 예외 발생시 반복문 처음으로 돌아감
            }

            // 0 입력 시 종료
//            if (menuSelectNum == 0) {
//                System.out.println("키오스크를 종료합니다.");
//                loopFlag = false;
//            }

            // 입력된 번호에 맞는 기능 실행
            switch (menuSelectNum) {
                case 1:
                    // 아무런 처리를 하지 않음으로써 case 3까지 스킵 후 걸쳐짐

                case 2:
                    // 아무런 처리를 하지 않음으로써 case 3까지 스킵 후 걸쳐짐

                case 3:
                    // (올바른 인덱스 접근을 위해 -1 처리 할 것 잊지말기)
                    MenuItem selected = MenuItem.getMenu(menuSelectNum - 1);
                    System.out.println(selected.getMenuName() + "가 장바구니에 등록되었습니다.");
                    break;

                case 8:
                    System.out.println("장바구니로 이동합니다.");
                    break;

                case 9:
                    System.out.println("주문 결재창으로 이동합니다.");
                    break;

                case 0:
                    System.out.println("키오스크를 종료합니다.");
                    loopFlag = false;
                    break;

                default:
                    System.out.println("입력 오류 : 올바른 번호를 입력해주세요.");
                    break;

            }   // switch문 끝
            System.out.println();

        }   // while문 끝
        sc.close(); // 자원 정리

    }


//    - [ ㅇ]  요구사항에 부합하는지 검토
//      - [ ㅇ]  키오스크 프로그램을 시작하는 메서드가 구현되어야 합니다.
//          - [ ㅇ]  콘솔에 햄버거 메뉴를 출력
//          - [ ㅇ]  사용자의 입력을 받아 메뉴를 선택하거나 프로그램을 종료
//          - [ ㅇ]  유효하지 않은 입력에 대해 오류 메시지를 출력
//          - [ ㅇ]  `0`을 입력하면 프로그램이 ‘뒤로가기’되거나 ‘종료’

}
