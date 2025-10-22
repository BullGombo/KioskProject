package v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// 프로그램 시작점으로써의 역할
public class Main {
    // 실행 흐름 :
    // 메뉴 텍스트 출력 -> 입력 대기 -> 번호 입력 -> 입력 번호에 맞는 기능 실행 -> 메뉴 텍스트 출력...(반복)

    static Scanner sc = new Scanner(System.in);

    // 프로그램 시작점
    public static void main(String[] args) {

        List<MenuItem> menuList = MenuItem.getMenuList();

        boolean loopFlag = true;

        System.out.println("Hello Kiosk!");

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
            int menuSelectNum = sc.nextInt();

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

    }   // main 끝

}
