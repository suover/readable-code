package cleancode.studycafe.mission.model;

public class StudyCafeLockerPass {

	private final StudyCafePassType passType;
	private final int duration;
	private final int price;

	private StudyCafeLockerPass(StudyCafePassType passType, int duration, int price) {
		this.passType = passType;
		this.duration = duration;
		this.price = price;
	}

	public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, int price) {
		return new StudyCafeLockerPass(passType, duration, price);
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
