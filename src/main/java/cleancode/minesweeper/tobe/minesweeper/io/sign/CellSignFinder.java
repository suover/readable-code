package cleancode.minesweeper.tobe.minesweeper.io.sign;

import java.util.List;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;

public class CellSignFinder {

	private static final List<CellSignProvidable> CELL_SIGN_PROVIDERS = List.of(
		new EmptyCellSignProvider(),
		new FlagCellSignProvider(),
		new LandMineCellSignProvider(),
		new NumberCellSignProvider(),
		new UncheckedCellSignProvider()
	);

	public String findCellSignFrom(CellSnapshot snapshot) {
		return CELL_SIGN_PROVIDERS.stream()
			.filter(provider -> provider.supports(snapshot))
			.findFirst()
			.map(provider -> provider.provide(snapshot))
			.orElseThrow(() -> new IllegalArgumentException("확인할 수 없는 셀입니다."));
	}
}
