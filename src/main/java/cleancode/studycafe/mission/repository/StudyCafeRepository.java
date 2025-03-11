package cleancode.studycafe.mission.repository;

import java.util.List;

import cleancode.studycafe.mission.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.model.StudyCafePass;

public interface StudyCafeRepository {

	List<StudyCafePass> readStudyCafePasses();
	List<StudyCafeLockerPass> readLockerPasses();
}
