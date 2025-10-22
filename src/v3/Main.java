package v3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 프로그램 시작점으로써의 역할
public class Main {
    // 실행 흐름 :
// 메뉴 텍스트 출력(MenuItem접근) -> 입력 대기 -> 번호 입력 -> 입력 번호에 맞는 기능 실행
// -> 메뉴 텍스트 출력...(반복)

    // 프로그램 시작점
    public static void main(String[] args) {

        List <MenuItem> menuList = MenuItem.getMenuList();

        Kiosk kiosk = new Kiosk(menuList);

        kiosk.start();

    }   // main 끝

}
