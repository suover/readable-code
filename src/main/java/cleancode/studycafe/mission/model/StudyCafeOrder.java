package cleancode.studycafe.mission.model;

/**
 * 사용자가 선택한 이용권 & 사물함 정보를 담고,
 * 이벤트 할인 금액 및 총액을 계산하는 도메인 객체
 */
public class StudyCafeOrder {

	private final StudyCafePass selectedPass;
	private final StudyCafeLockerPass lockerPass;

	public StudyCafeOrder(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
		this.selectedPass = selectedPass;
		this.lockerPass = lockerPass;
	}

	public StudyCafePass getSelectedPass() {
		return selectedPass;
	}

	public StudyCafeLockerPass getLockerPass() {
		return lockerPass;
	}

	/**
	 * 할인 금액 계산
	 */
	public int getDiscountAmount() {
		double discountRate = selectedPass.getDiscountRate();
		return (int) (selectedPass.getPrice() * discountRate);
	}

	/**
	 * 최종 결제 금액
	 */
	public int getTotalPrice() {
		int discountPrice = getDiscountAmount();
		int lockerPrice = (lockerPass != null) ? lockerPass.getPrice() : 0;
		return selectedPass.getPrice() - discountPrice + lockerPrice;
	}
}
