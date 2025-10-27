package v6;

// 장바구니 품목의 수량과 가격
public class CartItem {

    // ---------------------------------- 속성 ----------------------------------
    private int quantity;
    private int cPrice;

    // ---------------------------------- 생성자 ----------------------------------
    public  CartItem(int quantity, int cPrice) {
        this.quantity = quantity;
        this.cPrice = cPrice;
    }

    // ---------------------------------- 기능 ----------------------------------
    // getter/setter
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getcPrice() { return cPrice; }
    public void setcPrice(int cPrice) { this.cPrice = cPrice; }

    //

}
