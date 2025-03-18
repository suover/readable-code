package cleancode.studycafe.tobe;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

@DisplayName("StudyCafePassOrder 테스트")
class StudyCafePassOrderTest {

	@Test
	@DisplayName("SeatPass의 할인액이 10000원일 때 getDiscountPrice()는 10000원을 반환해야 한다.")
	void discountPriceIsCorrect() {
		// given
		// WEEKLY,2,100000,0.1 → 할인액 = 100000 * 0.1 = 10000
		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
			StudyCafePassType.WEEKLY,
			2,
			100000,
			0.1
		);
		StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

		// when
		int discount = order.getDiscountPrice();

		// then
		assertThat(discount).isEqualTo(10000);
	}

	@Test
	@DisplayName("SeatPass와 LockerPass가 있으면 getTotalPrice()는 두 금액의 합에서 할인액을 차감한 값을 반환해야 한다.")
	void totalPriceIsSumOfSeatAndLockerPrice() {
		// given
		// FIXED,4,250000,0.1 → 할인액 = 250000 * 0.1 = 25000
		// LOCKER FIXED,4,10000
		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
			StudyCafePassType.FIXED,
			4,
			250000,
			0.1
		);
		StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
			StudyCafePassType.FIXED,
			4,
			10000
		);
		StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

		// when
		// 총액 = (250000 + 10000) - 25000 = 235000
		int totalPrice = order.getTotalPrice();

		// then
		assertThat(totalPrice).isEqualTo(235000);
	}

	@Test
	@DisplayName("LockerPass를 지정했으면 getLockerPass()는 해당 LockerPass가 담긴 Optional을 반환해야 한다.")
	void lockerPassIsPresent() {
		// given
		// FIXED,4,250000,0.1 사용
		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
			StudyCafePassType.FIXED,
			4,
			250000,
			0.1
		);
		// LOCKER FIXED,4,10000 사용
		StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(
			StudyCafePassType.FIXED,
			4,
			10000
		);
		StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

		// when
		Optional<StudyCafeLockerPass> lockerPassOpt = order.getLockerPass();

		// then
		assertThat(lockerPassOpt).isPresent();
		assertThat(lockerPassOpt.get().getPrice()).isEqualTo(10000);
	}
}
