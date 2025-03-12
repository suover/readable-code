package cleancode.studycafe.mission.io;

import java.util.List;
import java.util.Scanner;

import cleancode.studycafe.mission.exception.AppException;
import cleancode.studycafe.mission.model.StudyCafePass;
import cleancode.studycafe.mission.model.StudyCafePassType;

public class InputHandler {

	private static final Scanner SCANNER = new Scanner(System.in);

	/**
	 * 이용권 유형 선택
	 */
	public StudyCafePassType getPassTypeSelectingUserAction() {
		String userInput = SCANNER.nextLine().trim();

		// 1: 시간권, 2: 주단위권, 3: 고정석
		if ("1".equals(userInput)) {
			return StudyCafePassType.HOURLY;
		}
		if ("2".equals(userInput)) {
			return StudyCafePassType.WEEKLY;
		}
		if ("3".equals(userInput)) {
			return StudyCafePassType.FIXED;
		}
		throw new AppException("잘못된 입력입니다. 1~3 중에 선택하세요.");
	}

	/**
	 * 특정 Pass 목록 중 하나를 선택
	 */
	public StudyCafePass getSelectPass(List<StudyCafePass> passes) {
		String userInput = SCANNER.nextLine().trim();
		int selectedIndex = Integer.parseInt(userInput) - 1;

		if (selectedIndex < 0 || selectedIndex >= passes.size()) {
			throw new AppException("잘못된 이용권 번호입니다.");
		}
		return passes.get(selectedIndex);
	}

	/**
	 * 사물함 선택 (고정석일 경우만)
	 */
	public boolean getLockerSelection() {
		String userInput = SCANNER.nextLine().trim();
		return "1".equals(userInput);
	}
}
