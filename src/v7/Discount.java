package v7;

// 할인 유형 Enum + 할인율 필드 및 getter 추가
public enum Discount {

    // ---------------------------------- 속성 ----------------------------------
    NATIONALMERIT(0.1),
    SOLDIER(0.05),
    STUDENT(0.03),
    CHILD(0.01),
    ORDINARY(0.0);

    private final double rate;

    // ---------------------------------- 생성자 ----------------------------------
    Discount(double rate) {
        this.rate = rate;
    }

    // ---------------------------------- 기능 ----------------------------------
    public double getRate() {
        return rate;
    }

}


