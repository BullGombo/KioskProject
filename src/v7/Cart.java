package v7;

import java.util.*;

// 장바구니 컬렉션과 관련 기능을 관리하는 클래스
// v7 - 제네릭 처리
public class Cart<T extends CartItem> {

    // ---------------------------------- 속성 ----------------------------------
    // private Map<String, List<CartItem>> cart = new LinkedHashMap<>();
    // key: 메뉴이름(String) | value: CartItem
    // private Map<String, CartItem> cart = new LinkedHashMap<>();
    
    // v7 - 제네릭 컬렉션
    private final Map<String, T> cart = new LinkedHashMap<>();
    private final Scanner sc = new Scanner(System.in);

    // ---------------------------------- 생성자 ----------------------------------
    // 생략 가능

    // ---------------------------------- 기능 ----------------------------------
    // v7 - 총액 합산 메서드 (스트림+람다 처리)
    public int getTotalPrice() {
        return cart.values().stream()
                // CartItem::getcPrice 는 람다식 item -> item.getcPrice() 와 동일
                .mapToInt(CartItem::getcPrice)
                .sum();
    }

    // v7 - 장바구니에 음식 추가 (제네릭 사용 + 스트림&람다 처리)
    public void addCartItem(String itemName, int price, int quantity) {
//        // CartItem existing = cart.get(itemName);
//        T existing = cart.get(itemName); // v7 - 제네릭 처리
//        // 기존 수량과 가격에 추가하는 로직
//        if (existing != null) { // 이미 존재하던 품목 처리
//            existing.setQuantity(existing.getQuantity() + quantity);
//            existing.setcPrice(existing.getcPrice() + price * quantity);
//        } else { // 존재하지 않던 품목을 새롭게 추가하는 로직
//            // cart.put(itemName, new CartItem(quantity, price * quantity));
//            cart.put(itemName, (T) new CartItem(quantity, price * quantity));
//        }

        // (Stream + Lambda 버전)
        // entrySet() : (key)(value) 한 쌍을 Map.Entry 객체로 묶어서 Set 형태로 반환
        cart.entrySet().stream()
                // e = 스트림의 각 원소 쌍,
                // 여기서는 cart.Map.Entry<String, CartItem>의 entrySet

                // 장바구니의 각 key값이 itemName과 같은 이름의 음식이 있는지 비교
                .filter(e -> e.getKey().equals(itemName))
                // 유효성 검사 (존재하는지, 없는지) - 첫 번째 요소를 Optional로 반환
                .findFirst()
                // 조건문 - Optional의 값이 존재하는지 여부에 따라 다른 동작 수행
                .ifPresentOrElse(
                        e -> {
                            // 존재할 때
                            CartItem item = (CartItem) e.getValue();
                            item.setQuantity(item.getQuantity() + quantity);
                            item.setcPrice(item.getcPrice() + price * quantity);
                        }, // 존재하지 않을 때
                        () -> cart.put(itemName,
                                (T) new CartItem(quantity, price * quantity))
                );

        System.out.println("=> [" + itemName + "] " + quantity +
                "개가 장바구니에 추가되었습니다.");

    }

    // v7 - 장바구니 출력 (Stream + Lambda)
    public void showCart() {
        // 장바구니가 비어있는 경우
        if (cart.isEmpty()) {
            System.out.println("\n 장바구니가 비어 있습니다.");
            return;
        }

        System.out.println("\n==============[ 장바구니 목록 ]==============");
        // 번호 매김을 위한 변수 초기화
        // int index = 1;
        final int[] index = {1};
        // int total = 0;
        // v7 - 신규 총액 합산 메서드 호출
        int total = getTotalPrice();

        // 향상된 for문 - 장바구니 컬렉션 출력
//        for (Map.Entry<String, CartItem> entry : cart.entrySet()) {
//            String itemName = entry.getKey();
//            CartItem item = entry.getValue();
//            System.out.println(index + ". " + itemName + " | " + item);
//            total += item.getcPrice();
//            index++;
//        }
        // v7 - 장바구니 컬렉션 출력 forEach + 람다
        cart.forEach((itemName, item) -> {
            System.out.println(index[0]++ + ". " + itemName + " | " + item);
        });

        System.out.println("-------------------------------------");
        System.out.println("총 금액: W " + (total / 1000.0));
        System.out.println("-------------------------------------");
        System.out.println("==========================================");
        System.out.println("'d' 입력 시 삭제 실행 / 그 외 입력 시 : 넘기기");

        System.out.print(">> ");
        String input = sc.nextLine().trim();

        // 삭제 분기
        if (input.equalsIgnoreCase("d")) {
            deleteCartItem();
        }
    } // showCart() 끝

    // v7 - 장바구니 항목 삭제 (Stream + Lambda)
    public void deleteCartItem() {
        if (cart.isEmpty()) {
            System.out.println("삭제할 항목이 없습니다.");
            return;
        }

        // 기존 장바구니 컬렉션 출력
//        int index = 1;
//        for (String itemName : cart.keySet()) {
//            System.out.println(index + ". " + itemName);
//            index++;
//        }
        // v7 - 컬렉션 순회 반복 - 장바구니 출력
        List<String> itemList = new ArrayList<>(cart.keySet());
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
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
            // String targetItem = (String) cart.keySet().toArray()[itemIndex - 1];
            // CartItem item = cart.get(targetItem);
            // v7 - 컬렉션으로 접근, 변수 제네릭 처리
            String targetItem = itemList.get(itemIndex - 1);
            T item = cart.get(targetItem);

            System.out.print("[" + targetItem + "] " + delQty +
                    "개 삭제하시겠습니까? (y = 삭제, 그외 입력 = 취소): ");
            // .trim().toLowerCase(); = 대문자 입력시 소문자로 변환
            String confirm = sc.nextLine().trim().toLowerCase();

//            // 실제 삭제 로직
//            if (confirm.equals("y")) {
//                // 현재 수량보다 입력된 수량이 크거나 같을 경우, 해당 키 자체를 삭제
//                if (item.getQuantity() <= delQty) {
//                    cart.remove(targetItem);
//                } else { // 기존 수량과 가격에서 입력값 만큼 빼기
//                    item.setQuantity(item.getQuantity() - delQty);
//                    item.setcPrice(item.getcPrice() - delQty * (item.getcPrice() / item.getQuantity()));
//                }
//                System.out.println("삭제 완료");
//            } else {
//                System.out.println("삭제를 취소했습니다.");
//            }
            // (Stream + Lambda 버전)
            if (confirm.equals("y")) {
                cart.entrySet().stream()
                        // targetItem과의 비교연산 후 동일한 이름의 key를 반환
                        .filter(e -> e.getKey().equals(targetItem))
                        // 첫번째 항목을 Optional로 반환
                        .findFirst()
                        // 존재 여부에 따른 조건 분기
                        .ifPresent(e -> {
                            // cart 형식의 Map value, 즉 수량과 가격
                            T itemVal = e.getValue();
                            // 현재 수량이 삭제 수량과 같거나 작을 경우 - 해당 map의 key 전체 삭제
                            if (itemVal.getQuantity() <= delQty) {
                                cart.remove(e.getKey());
                            } else {
                                itemVal.setQuantity(itemVal.getQuantity() - delQty);
                                itemVal.setcPrice(itemVal.getcPrice() -
                                        delQty * (itemVal.getcPrice() / itemVal.getQuantity()));
                            }
                        });
                System.out.println("삭제 완료");
            } else {
                System.out.println("삭제를 취소했습니다.");
            }


        } catch (NumberFormatException e) { // 수량 입력에 숫자가 입력되지 않았을 경우
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


