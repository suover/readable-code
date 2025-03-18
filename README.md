# 🎯 스터디 카페 이용권 선택 시스템 테스트 미션

이번 미션은 스터디 카페 이용권 선택 시스템의 핵심 도메인 로직과 출력 관련 기능이 올바르게 동작하는지를 검증하기 위한 테스트 코드를 작성하는 것이 목표였습니다. 각 테스트 케이스는 주요 기능(환영 메시지 출력, 주문 내역 요약, 할인 및 총 결제 금액 계산, 패스별 사물함 사용 가능 여부 등)을 집중적으로 다루었습니다.

## 1. 미션 개요

- **목적**: 스터디 카페 시스템의 기능별 테스트 케이스를 작성하여
    - 올바른 메시지 출력 및 주문 내역 요약
    - 할인율 및 가격 계산의 정확성
    - 각 이용권 타입에 따른 사물함 사용 가능 여부 등
      기능들이 의도대로 동작하는지를 검증합니다.
- **테스트 요약**
    - **출력 처리(OutputHandler)**: 환영 메시지, 공지사항, 주문 요약 출력
    - **주문 처리(StudyCafePassOrder)**: 할인 금액과 총 결제 금액 계산, 사물함 이용 여부 확인
    - **이용권 처리(StudyCafeSeatPass)**: 할인율 적용, 사물함 사용 조건 판별

## 2. 테스트 구성 및 설명

### 2.1 OutputHandlerTest

- **목표**: 사용자에게 출력되는 환영 메시지, 공지사항, 그리고 주문 내역 요약이 올바른지 확인합니다.
- **테스트 케이스**
    - **환영 메시지와 공지사항 출력**
        - `showWelcomeMessage()`와 `showAnnouncement()` 메서드 호출 시, 출력 스트림에 "프리미엄 스터디카페" 및 "사물함은 고정석 선택 시 이용 가능합니다."라는 메시지가 포함되는지 검증.
    - **주문 내역 요약 출력**
        - StudyCafeSeatPass와 StudyCafeLockerPass를 포함한 주문을 생성한 후, 출력 메시지에 이용 내역, 이용권, 사물함 그리고 총 결제 금액 문자열이 포함되는지 확인.

### 2.2 StudyCafePassOrderTest

- **목표**: 주문 내역에 포함된 할인 및 총 결제 금액 계산 로직이 올바르게 수행되는지 확인합니다.
- **테스트 케이스**
    - **할인액 계산 검증**
        - 할인율 0.1, 가격 100,000원인 경우 할인액이 10,000원으로 계산되는지 확인.
    - **총 결제 금액 계산 검증**
        - SeatPass와 LockerPass가 동시에 존재할 때, 할인액을 반영한 총 결제 금액(예: (250,000 + 10,000) - 25,000 = 235,000원)이 올바른지 확인.
    - **LockerPass 존재 여부**
        - 주문에 LockerPass가 포함된 경우, 해당 객체가 Optional로 정상 반환되는지 검증.

### 2.3 StudyCafeSeatPassTest

- **목표**: 스터디 카페 이용권(SeatPass)의 할인 계산과 사물함 사용 가능 여부가 올바르게 처리되는지 검증합니다.
- **테스트 케이스**
    - **할인율 0인 경우**
        - 할인율이 0이면 할인 금액이 0원임을 확인.
    - **할인율 0.1인 경우**
        - 할인율 0.1, 가격 100,000원일 때 할인 금액이 10,000원으로 계산되는지 확인.
    - **HOURLY 이용권의 사물함 사용 불가**
        - HOURLY 타입 이용권은 `cannotUseLocker()`가 `true`를 반환하는지 검증.
    - **FIXED 이용권의 사물함 사용 가능**
        - FIXED 타입 이용권은 `cannotUseLocker()`가 `false`를 반환하는지 확인.

![Image](https://github.com/user-attachments/assets/826eb1d9-b788-4fc5-8821-f1064df05dc6)

## 3. 테스트를 통한 기대 효과

- **안정성 강화**: 각 기능별로 단위 테스트를 수행하여, 리팩토링 및 기능 추가 시 기존 기능의 회귀(regression)를 방지합니다.
- **유지보수 용이**: 계산 로직과 출력 로직이 명확하게 테스트되므로, 수정 시 의도치 않은 부작용을 빠르게 발견할 수 있습니다.
- **개발 효율성 증대**: 테스트 케이스가 명세 역할을 수행하여, 다른 개발자들이 시스템의 주요 기능을 쉽게 이해할 수 있도록 돕습니다.

## 4. 마무리 및 향후 개선 방향

이번 테스트 코드 미션을 통해, 스터디 카페 이용권 시스템의 핵심 도메인 로직이 의도한 대로 동작하는지 효과적으로 검증할 수 있음을 확인했습니다. 향후 추가 검증 포인트로는 다음을 고려할 수 있습니다.

- **통합 테스트**: 각 모듈 간의 상호작용을 검증하는 통합 테스트 케이스 작성.
- **경계 값 및 예외 처리 테스트**: 잘못된 입력이나 예상치 못한 상황에 대한 테스트 케이스 보완.
- **테스트 커버리지 확장**: 기능 추가 시 테스트 케이스를 확장하여 전체 시스템의 신뢰성을 높임.

이번 미션을 통해 테스트 코드 작성의 중요성을 다시 한번 확인했으며, 앞으로의 개발 및 리팩토링 작업에 있어 안정적인 코드베이스 유지에 크게 기여할 것으로 기대합니다.

---

# 🎯 스터디 카페 이용권 선택 시스템 리팩토링 미션

### 미션 개요
이번 미션은 [스터디 카페 이용권 선택 시스템]을 **객체지향적**으로 개선하기 위한 리팩토링 미션입니다.  
원본 코드는 시간이 지날수록 중복된 분기와 로직이 분산되어 유지보수가 어려워지는 문제를 안고 있었습니다.

본 README에서는 **기존 코드에서 무엇을 어떻게 변경했고, 어떤 효과**를 기대하며 리팩토링이 이뤄졌는지 설명합니다.  
또한 이번 미션에서 느꼈던 점과 제 개인적인 개선 아이디어를 정리해 공유합니다.

## 1. 리팩토링 동기
- **중복 분기**: 시간권(HOURLY), 주단위(WEEKLY), 고정석(FIXED)마다 거의 비슷한 분기 로직을 `if-else` 구문으로 반복하고 있었습니다.
- **도메인 로직 혼재**: 할인가 계산과 사물함 비용 처리 등이 `OutputHandler`나 `StudyCafePassMachine`에 분산되어 있어, 유지보수가 쉽지 않았습니다.
- **확장성 부족**: 새로운 타입의 이용권(예: MONTHLY 등)을 추가할 때마다, `StudyCafePassMachine` 내부 코드를 많이 수정해야 했습니다.

**추상화 레벨 조절, SRP(단일 책임), DIP(의존성 역전), 객체 간 협력** 개념을 적용하여, 중복을 줄이고 각 클래스에 명확한 책임을 부여하고자 했습니다.

## 2. 주요 변경 요약
아래는 리팩토링 과정에서 이루어진 **핵심 변경사항**과 그 의도를 정리한 것입니다.

1. **`StudyCafeOrder` 객체 신설**
    - **이유**: 할인 금액, 총 결제 금액 등의 도메인 로직을 전담하는 클래스를 만들어, 메서드 호출부가 깔끔해지도록 했습니다.
    - **효과**:
        - `OutputHandler` 등에서 할인 계산 로직이 사라지고, `StudyCafeOrder`가 “무엇을 어떻게 할인하는지”를 담당합니다.
        - 유지보수 시, 할인 규칙이 달라져도 `StudyCafeOrder` 내부만 수정하면 됩니다.

2. **Repository 인터페이스(`StudyCafeRepository`) + 구현체(`StudyCafeFileRepository`)로 파일 접근 로직 분리**
    - **이유**: DIP(Dependency Inversion Principle)를 적용해, `StudyCafePassMachine`이 “파일을 어떤 식으로 읽는지”에 의존하지 않도록 하였습니다.
    - **효과**:
        - 장차 데이터 소스가 DB, API 등으로 바뀌어도, `StudyCafeRepository` 인터페이스를 구현하는 새 클래스를 추가하기만 하면 됩니다.
        - `StudyCafePassMachine`은 “Pass와 LockerPass를 읽어오는” 추상적 행위에만 집중합니다.

3. **중복된 `if-else` 분기 제거**
    - **기존**: `if (HOURLY) { ... } else if (WEEKLY) { ... } else if (FIXED) { ... }` 형태로 각 케이스마다 반복되는 코드가 많았습니다.
    - **리팩토링**: `selectPass(passType)`라는 메서드를 통해 한 번의 흐름으로 처리하고, 사물함 로직은 별도 메서드(`maybeSelectLocker`)로 분리했습니다.
    - **효과**:
        - 코드 줄 수가 줄어들고 가독성이 향상됩니다.
        - “고정석만 사물함을 선택할 수 있다”는 로직이 한 곳에서만 제어됩니다.

4. **메서드/클래스 책임 분산 & 네이밍 개선**
    - **`selectPass()`**: passType에 해당하는 목록만 필터 → 유저가 고름 → 반환
    - **`maybeSelectLocker()`**: 고정석(FIXED)일 때만 locker를 조회하고, 유저 선택을 받음
    - **`showOrderResult()`**: 주문의 결과(`StudyCafeOrder`)를 보여주는 로직으로 단순화
    - **효과**: “하나의 메서드가 오직 하나의 목적”을 수행하도록 해 **SRP**(단일 책임 원칙)에 가깝게 만들었습니다.

## 3. 상세 변경 내역
### 3.1 `StudyCafePassMachine`
- **Before**: `if (HOURLY) { ... } else if (WEEKLY) { ... } else if (FIXED) { ... }` 로직이 길고, 사물함 로직도 그 안에서 처리.
- **After**:
  ```java
  public void run() {
      // 1) passType 선택 (시간권/주단위/고정석)
      StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

      // 2) pass 선택
      StudyCafePass selectedPass = selectPass(passType);

      // 3) 사물함 선택 (고정석 only)
      StudyCafeLockerPass lockerPass = maybeSelectLocker(selectedPass);

      // 4) 주문 객체 생성 & 결과 출력
      StudyCafeOrder order = new StudyCafeOrder(selectedPass, lockerPass);
      outputHandler.showOrderResult(order);
  }
  ```
- **이점**: 각 단계가 메서드로 분리되어 이해하기 쉬움. passType별로 중복된 분기 로직이 크게 줄었음.

### 3.2 `StudyCafeOrder`

- **Before**: 할인금액, 총액 계산 로직이 `OutputHandler.showPassOrderSummary` 안에 위치.
- **After**: `StudyCafeOrder`라는 도메인 객체가 이 로직을 담당.
  ```java
  public int getDiscountAmount() {
      return (int) (selectedPass.getPrice() * selectedPass.getDiscountRate());
  }

  public int getTotalPrice() {
      int discountPrice = getDiscountAmount();
      int lockerPrice = (lockerPass != null) ? lockerPass.getPrice() : 0;
      return selectedPass.getPrice() - discountPrice + lockerPrice;
  }
  ```
- **이점**: 필요 시 “할인 정책”이 바뀌어도 StudyCafeOrder만 수정하면 되며, 출력부는 깔끔하게 유지.

### 3.3 `StudyCafeRepository` / `StudyCafeFileRepository`

**Before**: `StudyCafeFileHandler`라는 클래스에서 직접 파일 접근 + 변환.  
**After**: `StudyCafeRepository`(추상) + `StudyCafeFileRepository`(구현).
- `StudyCafePassMachine`은 `StudyCafeRepository repository`에만 의존 → DIP 준수.
- 향후 DBRepo나 InMemoryRepo 추가 시 `StudyCafeRepository`만 구현하면 됨.

### 3.4 `if` vs `else if` (배타 조건 명시)

- **Before**: 여러 조건을 `if`로만 나열
  ```java
  if (passType == StudyCafePassType.HOURLY) {
      ...
  }
  if (passType == StudyCafePassType.WEEKLY) {
      ...
  }
  if (passType == StudyCafePassType.FIXED) {
      ...
  }
  ```
- **After**: `if-else if` 구문으로 “배타적 조건”임을 표현
  ```java
  if (passType == StudyCafePassType.HOURLY) {
      ...
  } else if (passType == StudyCafePassType.WEEKLY) {
      ...
  } else if (passType == StudyCafePassType.FIXED) {
      ...
  }
  ```
- **이점**: 한 조건이 만족되면 나머지는 확인하지 않으므로, “오직 하나만 성립하는 케이스”임을 코드 상에서 명시적으로 드러낼 수 있음.  
  else가 많으면 복잡도가 높아져서 줄이는 편이 좋지만, 이번 상황처럼 배타적인 조건을 나타낼 때는 else if 가 오히려 의도를 명확히 보여준다고 판단함.

## 4. 리팩토링 후 기대 효과

1. **객체지향 원칙 준수**: SRP, DIP, DRY(중복 제거) 등.
2. **확장성 향상**: 새 `passType`이나 새로운 할인 정책 추가 시 변경 범위가 최소화.
3. **가독성 증가**: 메서드가 짧고 역할이 명확.
4. **유지보수성 증가**: 할인 로직, 사물함 로직, IO 로직이 각각 다른 클래스로 나뉘어, 수정이 용이.

## 5. 미션을 진행하며 배운 점 & 느낀 점

- **추상화 레벨 맞추기**: 메서드 추출, 책임 분리가 왜 중요한지 실감했습니다. 코드가 길어진다고 무조건 나쁜 게 아니라, 가독성을 위해 적절히 분리할 필요가 있음을 배웠습니다.
- **도메인 객체(`StudyCafeOrder`)의 위력**: 할인 계산 로직을 한 군데로 모으니, 협업 시 “이 할인 규칙 어디 있지?”라는 질문에 즉답할 수 있었습니다.
- **else if 줄이는 것 vs. 배타 조건 명시**: ‘조건이 서로 배타적일 때는 else if가 오히려 직관적’이라는 점. 무작정 else를 없애는 게 아니라, 상황에 맞춰 **조건의 배타성**을 명시할 필요가 있음을 체득했습니다.
- **DIP를 통한 유연성**: 파일에서 읽든, DB에서 읽든, `Repo` 인터페이스를 교체하기만 하면 되니, 의존성 역전의 가치를 다시금 깨달았습니다.

## 6. 향후 개선 아이디어

- **테스트 코드**: 단위 테스트, 통합 테스트를 추가해 리팩토링 시 안정성을 확보할 수 있습니다.
- **다형성**: 예를 들어, `StudyCafePass`를 상속받는 `HourlyPass`, `WeeklyPass`, `FixedPass`로 나누는 방안도 있습니다.
- **별도 할인정책 인터페이스**: 할인 정책을 교체 가능하도록 인터페이스화할 수도 있습니다.

## 마무리

이번 리팩토링을 통해, **유연하고 읽기 좋은** 스터디 카페 이용권 선택 시스템을 만들 수 있었습니다. 미션을 진행하면서, “메서드 분리와 책임 분산, 그리고 DIP를 잘 지키면 코드가 얼마나 깔끔해지는가”를 확인하게 되었고, 다른 프로젝트에도 적용할 수 있는 통찰을 얻었습니다.

앞으로도 다양한 상황에서 이처럼 리팩토링을 반복하며, 더욱 깨끗하고 유지보수하기 좋은 코드를 작성해 나가고 싶습니다. 감사합니다!
