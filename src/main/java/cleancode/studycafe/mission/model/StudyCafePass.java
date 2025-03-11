package cleancode.studycafe.mission.model;

public class StudyCafePass {

	private final StudyCafePassType passType;
	private final int duration;         // 시간 or 주 단위
	private final int price;
	private final double discountRate;

	private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
		this.passType = passType;
		this.duration = duration;
		this.price = price;
		this.discountRate = discountRate;
	}

	public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
		return new StudyCafePass(passType, duration, price, discountRate);
	}

	public StudyCafePassType getPassType() {
		return passType;
	}

	public int getDuration() {
		return duration;
	}

	public int getPrice() {
		return price;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public String display() {
		// passType 에 따라 다른 표현
		if (passType == StudyCafePassType.HOURLY) {
			return String.format("%d시간권 - %d원", duration, price);
		} else if (passType == StudyCafePassType.WEEKLY) {
			return String.format("%d주권 - %d원", duration, price);
		} else if (passType == StudyCafePassType.FIXED) {
			return String.format("%d주권 - %d원", duration, price);
		}
		return "";
	}
}
