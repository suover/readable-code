package cleancode.studycafe.tobe;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;

@DisplayName("StudyCafeSeatPass 테스트")
class StudyCafeSeatPassTest {

	@Test
	@DisplayName("할인율이 0이면 getDiscountPrice()는 0을 반환해야 한다.")
	void discountPriceIsZeroWhenNoDiscount() {
		// given
		// HOURLY,2,4000,0.0
		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
			StudyCafePassType.HOURLY,
			2,
			4000,
			0.0
		);

		// when
		int discountPrice = seatPass.getDiscountPrice();

		// then
		assertThat(discountPrice).isEqualTo(0);
	}

	@Test
	@DisplayName("할인율이 0.1이고 가격이 100000원일 때 getDiscountPrice()는 10000원을 반환해야 한다.")
	void discountPriceIsCorrectWithTenPercent() {
		// given
		// WEEKLY,2,100000,0.1
		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
			StudyCafePassType.WEEKLY,
			2,
			100000,
			0.1
		);

		// when
		int discountPrice = seatPass.getDiscountPrice();

		// then
		assertThat(discountPrice).isEqualTo(10000);
	}

	@Test
	@DisplayName("HOURLY 이용권은 cannotUseLocker()가 true를 반환해야 한다.")
	void lockerUnavailableForHourlyPass() {
		// given
		// HOURLY,2,4000,0.0
		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
			StudyCafePassType.HOURLY,
			2,
			4000,
			0.0
		);

		// when
		boolean result = seatPass.cannotUseLocker();

		// then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("FIXED 이용권은 cannotUseLocker()가 false를 반환해야 한다.")
	void lockerAvailableForFixedPass() {
		// given
		// FIXED,4,250000,0.1
		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
			StudyCafePassType.FIXED,
			4,
			250000,
			0.1
		);

		// when
		boolean result = seatPass.cannotUseLocker();

		// then
		assertThat(result).isFalse();
	}

}
