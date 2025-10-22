package v1;

import java.util.Scanner;

// 메인의 흐름
// 시작점으로써의 역할
public class Main {

    static Scanner sc = new Scanner(System.in);

    // 프로그램 시작점
    public static void main(String[] args) {

        boolean loopFlag = true;

        System.out.println("Hello Kiosk!");

        // 햄버거 메뉴 출력 및 선택
        // - scanner를 사용하여 여러 햄버거 메뉴를 입력받고 출력
        // - 제시된 메뉴 중 입력받은 숫자에 따라 다른 로직을 실행하는 코드를 작성
        // - 반복문을 이용하여 특정 번호가 입력되면 프로그램을 종료

        // 실행 흐름 :
        // 메뉴 텍스트 출력 -> 입력 대기 -> 번호 입력 -> 입력 번호에 맞는 기능 실행 -> 메뉴 텍스트 출력...(반복)

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
            System.out.println("1. 치즈버거 | 7000원 | 구성 : 재료1 + 재료2 + 재료3 + ...");
            System.out.println("2. 불고기버거 | 6000원 | 구성 : 재료1 + 재료2 + 재료3 + ...");
            System.out.println("3. 치킨버거 | 7000원 | 구성 : 재료1 + 재료2 + 재료3 + ...");
            System.out.println("8. 장바구니");
            System.out.println("9. 주문");
            System.out.println("0. 종료");
            System.out.println("========================================");

            // 입력 대기
            System.out.print("메뉴 번호 입력 : ");
            int menuSelectNum = sc.nextInt();

            // 0 입력 시 종료
            if (menuSelectNum == 0) {
                System.out.println("키오스크를 종료합니다.");
                loopFlag = false;
            } 

            // 입력된 번호에 맞는 기능 실행
            switch (menuSelectNum) {
                case 1:
                    System.out.println("치즈버거가 장바구니에 등록되었습니다.");
                    break;

                case 2:
                    System.out.println("불고기버거가 장바구니에 등록되었습니다.");
                    break;

                case 3:
                    System.out.println("치킨버거가 장바구니에 등록되었습니다.");
                    break;

                case 8:
                    System.out.println("장바구니로 이동합니다.");
                    break;
                    
                case 9:
                    System.out.println("주문 결재창으로 이동합니다.");
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
