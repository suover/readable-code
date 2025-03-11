package cleancode.studycafe.mission;

import java.util.List;

import cleancode.studycafe.mission.exception.AppException;
import cleancode.studycafe.mission.io.InputHandler;
import cleancode.studycafe.mission.io.OutputHandler;
import cleancode.studycafe.mission.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.model.StudyCafeOrder;
import cleancode.studycafe.mission.model.StudyCafePass;
import cleancode.studycafe.mission.model.StudyCafePassType;
import cleancode.studycafe.mission.repository.StudyCafeFileRepository;
import cleancode.studycafe.mission.repository.StudyCafeRepository;

public class StudyCafePassMachine {

	private final InputHandler inputHandler = new InputHandler();
	private final OutputHandler outputHandler = new OutputHandler();
	private final StudyCafeRepository repository = new StudyCafeFileRepository();

	public void run() {
		try {
			outputHandler.showWelcomeMessage();
			outputHandler.showAnnouncement();

			outputHandler.askPassTypeSelection();
			StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

			// 1) Pass 선택
			StudyCafePass selectedPass = selectPass(passType);

			// 2) 사물함 선택 (if FIXED)
			StudyCafeLockerPass lockerPass = maybeSelectLocker(selectedPass);

			// 3) 주문 객체 생성 후 결과 출력
			StudyCafeOrder order = new StudyCafeOrder(selectedPass, lockerPass);
			outputHandler.showOrderResult(order);

		} catch (AppException e) {
			outputHandler.showSimpleMessage(e.getMessage());
		} catch (Exception e) {
			outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
		}
	}

	/**
	 * 주어진 passType에 맞는 이용권 목록을 보여주고, 사용자 선택을 받음
	 */
	private StudyCafePass selectPass(StudyCafePassType passType) {
		List<StudyCafePass> allPasses = repository.readStudyCafePasses();
		// passType 일치하는 것만 필터
		List<StudyCafePass> filteredPasses = allPasses.stream()
			.filter(p -> p.getPassType() == passType)
			.toList();

		outputHandler.showPassListForSelection(filteredPasses);
		return inputHandler.getSelectPass(filteredPasses);
	}

	/**
	 * 고정석(FIXED)일 때만 사물함 사용 여부 확인
	 */
	private StudyCafeLockerPass maybeSelectLocker(StudyCafePass pass) {
		if (pass.getPassType() != StudyCafePassType.FIXED) {
			return null;  // 시간권 or 주단위권은 사물함 선택 X
		}
		// locker 리스트 중 pass와 동일한 (passType, duration)
		List<StudyCafeLockerPass> lockerPasses = repository.readLockerPasses();
		StudyCafeLockerPass matchedLocker = lockerPasses.stream()
			.filter(lp -> lp.getPassType() == pass.getPassType()
				&& lp.getDuration() == pass.getDuration())
			.findFirst()
			.orElse(null);

		if (matchedLocker == null) {
			return null; // 해당 고정석 기간에 맞는 사물함 없으면 pass
		}

		// 이용할지 여부 묻기
		outputHandler.askLockerPass(matchedLocker);
		boolean useLocker = inputHandler.getLockerSelection();
		return useLocker ? matchedLocker : null;
	}
}
