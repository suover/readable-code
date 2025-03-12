package cleancode.studycafe.mission.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import cleancode.studycafe.mission.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.model.StudyCafePass;
import cleancode.studycafe.mission.model.StudyCafePassType;

public class StudyCafeFileRepository implements StudyCafeRepository {

	@Override
	public List<StudyCafePass> readStudyCafePasses() {
		try {
			List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/pass-list.csv"));
			List<StudyCafePass> studyCafePasses = new ArrayList<>();
			for (String line : lines) {
				String[] values = line.split(",");
				StudyCafePassType passType = StudyCafePassType.valueOf(values[0]);
				int duration = Integer.parseInt(values[1]);
				int price = Integer.parseInt(values[2]);
				double discountRate = Double.parseDouble(values[3]);

				StudyCafePass pass = StudyCafePass.of(passType, duration, price, discountRate);
				studyCafePasses.add(pass);
			}
			return studyCafePasses;
		} catch (IOException e) {
			throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
		}
	}

	@Override
	public List<StudyCafeLockerPass> readLockerPasses() {
		try {
			List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/locker.csv"));
			List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
			for (String line : lines) {
				String[] values = line.split(",");
				StudyCafePassType passType = StudyCafePassType.valueOf(values[0]);
				int duration = Integer.parseInt(values[1]);
				int price = Integer.parseInt(values[2]);

				StudyCafeLockerPass locker = StudyCafeLockerPass.of(passType, duration, price);
				lockerPasses.add(locker);
			}
			return lockerPasses;
		} catch (IOException e) {
			throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
		}
	}
}
