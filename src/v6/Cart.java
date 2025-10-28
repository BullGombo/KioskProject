package v6;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// 장바구니 컬렉션과 관련 기능을 관리하는 클래스
public class Cart {

    // ---------------------------------- 속성 ----------------------------------
    // private Map<String, List<CartItem>> cart = new LinkedHashMap<>();
    // key: 메뉴이름(String) | value: CartItem
    private Map<String, CartItem> cart = new LinkedHashMap<>();
    private final Scanner sc = new Scanner(System.in);

    // ---------------------------------- 생성자 ----------------------------------
    // 생략 가능

    // ---------------------------------- 기능 ----------------------------------
    // 장바구니에 음식 추가
    public void addCartItem(String itemName, int price, int quantity) {
        CartItem existing = cart.get(itemName);
        // 기존 수량과 가격에 추가하는 로직
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            existing.setcPrice(existing.getcPrice() + price * quantity);
        } else {
            // 존재하지 않던 품목을 새롭게 추가하는 로직
            cart.put(itemName, new CartItem(quantity, price * quantity));
        }
        System.out.println("=> [" + itemName + "] " + quantity +
                "개가 장바구니에 추가되었습니다.");
    }

    // 장바구니 표시
    public void showCart() {
        // 장바구니가 비어있는 경우
        if (cart.isEmpty()) {
            System.out.println("\n 장바구니가 비어 있습니다.");
            return;
        }

        System.out.println("\n[ 장바구니 목록 ]");
        // 번호 매김을 위한 초기화
        int index = 1;
        int total = 0;

        // 향상된 for문 - 장바구니 컬렉션 출력
        for (Map.Entry<String, CartItem> entry : cart.entrySet()) {
            String itemName = entry.getKey();
            CartItem item = entry.getValue();
            System.out.println(index + ". " + itemName + " | " + item);
            total += item.getcPrice();
            index++;
        }

        System.out.println("-------------------------------------");
        System.out.println("총 금액: W " + (total / 1000.0));
        System.out.println("-------------------------------------");
        System.out.println("'d' 입력 시 삭제 / '0' 입력 시 뒤로가기");

        System.out.print(">> ");
        String input = sc.nextLine().trim();

        // 삭제 분기
        if (input.equalsIgnoreCase("d")) {
            deleteCartItem();
        }
    }

    // 장바구니 항목 삭제
    public void deleteCartItem() {
        if (cart.isEmpty()) {
            System.out.println("삭제할 항목이 없습니다.");
            return;
        }

        // 기존 장바구니 컬렉션 출력
        int index = 1;
        for (String itemName : cart.keySet()) {
            System.out.println(index + ". " + itemName);
            index++;
        }

        // 삭제 입력받기
        System.out.print("삭제할 음식 번호, 수량 입력 (예: 1,2): ");
        String[] input = sc.nextLine().trim().split(",");

        // 입력 문자열 길이와 비교한 예외처리
        if (input.length != 2) {    // 이부분은 확장이 필요하긴함
            System.out.println("잘못된 입력입니다.");
            return;
        }

        try {
            int itemIndex = Integer.parseInt(input[0]);
            int delQty = Integer.parseInt(input[1]);

            // 존재하지 않는 품목 선택시 예외처리
            if (itemIndex < 1 || itemIndex > cart.size()) {
                System.out.println("올바른 번호를 입력해주세요.");
                return;
            }

            // 입력된 번호의 음식 특정
            String targetItem = (String) cart.keySet().toArray()[itemIndex - 1];
            CartItem item = cart.get(targetItem);

            System.out.print("[" + targetItem + "] " + delQty +
                    "개 삭제하시겠습니까? (y = 삭제, 그외 입력 = 취소): ");
            // .trim().toLowerCase(); = 대문자 입력시 소문자로 변환
            String confirm = sc.nextLine().trim().toLowerCase();

            // 실제 삭제 로직
            if (confirm.equals("y")) {
                // 현재 수량보다 입력된 수량이 크거나 같을 경우, 해당 키 자체를 삭제
                if (item.getQuantity() <= delQty) {
                    cart.remove(targetItem);
                } else { // 기존 수량과 가격에서 입력값 만큼 빼기
                    item.setQuantity(item.getQuantity() - delQty);
                    item.setcPrice(item.getcPrice() - delQty * (item.getcPrice() / item.getQuantity()));
                }
                System.out.println("삭제 완료");
            } else {
                System.out.println("삭제를 취소했습니다.");
            }
            // 수량 입력에 숫자가 입력되지 않았을 경우
        } catch (NumberFormatException e) {
            System.out.println("숫자를 올바르게 입력해주세요.");
        }
    } // deleteCartItem() 끝

    // 장바구니 내부 상태 판별
    public boolean isEmpty() {
        return cart.isEmpty();
    }

    // 장바구니 비우기 (결제)
    public void clearCart() {
        cart.clear();
    }
}


