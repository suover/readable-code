package cleancode.studycafe.tobe;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

@DisplayName("OutputHandler 테스트")
class OutputHandlerTest {

	@Test
	@DisplayName("환영 메시지와 공지사항을 출력한다.")
	void showWelcomeAndAnnouncement() {
		// given
		OutputHandler outputHandler = new OutputHandler();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		// when
		outputHandler.showWelcomeMessage();
		outputHandler.showAnnouncement();

		// then
		String output = outContent.toString();
		assertThat(output).contains("*** 프리미엄 스터디카페 ***");
		assertThat(output).contains("* 사물함은 고정석 선택 시 이용 가능합니다.");

		// cleanup
		System.setOut(originalOut);
	}

	@Test
	@DisplayName("주문 내역 요약을 올바르게 출력한다.")
	void showPassOrderSummary() {
		// given
		OutputHandler outputHandler = new OutputHandler();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		// 테스트용 생성
		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4000, 0.0);
		StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);
		StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

		// when
		outputHandler.showPassOrderSummary(order);
		String output = outContent.toString();

		// then
		assertThat(output).contains("이용 내역");
		assertThat(output).contains("이용권:");
		assertThat(output).contains("사물함:");
		assertThat(output).contains("총 결제 금액:");

		// cleanup
		System.setOut(originalOut);
	}
}
