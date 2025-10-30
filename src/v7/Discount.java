package v7;

// 할인 유형 Enum + 할인율 필드 및 getter 추가
public enum Discount {

    // ---------------------------------- 속성 ----------------------------------
    NATIONALMERIT("국가 유공자", 1, 0.1),
    SOLDIER("군인",2, 0.05),
    STUDENT("학생",3, 0.03),
    CHILD("어린이",4, 0.01),
    ORDINARY("일반",5, 0.0);

    private final String discountName;
    private final int selectnum;
    private final double rate;

    // ---------------------------------- 생성자 ----------------------------------
    Discount(String discountName, int selectnum, double rate) {
        this.discountName = discountName;
        this.selectnum = selectnum;
        this.rate = rate;
    }

    // ---------------------------------- 기능 ----------------------------------
    public String getDiscountName() {return this.discountName;}
    public int getSelectnum() { return this.selectnum; }
    public double getRate() {
        return this.rate;
    }

}


