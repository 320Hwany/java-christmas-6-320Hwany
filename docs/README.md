# ⛳️ 프로젝트 소개

이 프로젝트의 목표는 크리스마스 프로모션 이벤트를 준비하는 것이다.     
먼저 방문 정보를 입력 받고 주문 정보를 처리한다.   
처리한 주문의 결과를 보고 이벤트 적용 대상을 확인하고 이벤트를 적용한다.    
마지막으로 이벤트 결과를 출력한다.

위와 같은 개발 요구 사항들은 모두 다음과 같은 목표를 만족하기 위해 수행한다.     
1. 중복된 할인/증정을 허용하여 고객들이 혜택을 많이 받는다는 것을 체감할 수 있게 하는 것
2. 올해 12월에 지난 5년 중 최고의 판매 금액을 달성
3. 12월 이벤트 참여 고객의 5%가 내년 1월 새해 이벤트에 재참여하는 것

개발자로서 제시된 요구 사항을 정확히 이해하고 개발을 진행하여 위와 같은 목표를 달성할 수 있도록 한다.  

# 📚기능 목록

## 방문 정보 받기

- 우테코 식당 이벤트 플래너 인사 문구 출력
- 식당 예상 방문 날짜 입력
  - [예외처리] 방문할 날짜는 1이상 31이하의 숫자
- 주문할 메뉴와 개수 입력 
  - [예외처리] 메뉴판에 없는 메뉴
  - [예외처리] 메뉴 개수는 1이상의 숫자
  - [예외처리] 메뉴는 한 번에 최대 20개까지만 주문 가능 
  - [예외처리] 메뉴의 형식이 맞지 않음
  - [예외처리] 중복 메뉴를 입력
  - [예외처리] 음료만 주문

## 주문 정보 처리

- 주문 메뉴 출력
- 할인 전 총주문 금액 계산
- 할인 전 총주문 금액 출력

## 이벤트 적용 

- 증정 이벤트 적용 대상 확인
- 증정 메뉴 출력
- 할인 적용
  - 크리스마스 디데이 할인
  - 평일 할인
  - 주말 할인
  - 특별 할인

## 이벤트 결과 

- 혜택 내역 출력
- 총혜택 금액 계산 
- 총혜택 금액 출력
- 할인 후 예상 결제 금액 계산
- 할인 후 예상 결제 금액 출력
- 이벤트 배지 부여 대상 확인 
- 적용된 이벤트 배지 출력

# 🚗 패키지 구조 및 클래스 설명

### config : 의존 관계 설정 패키지
  - EventConfig : 클래스 간의 <U>**의존 관계 설정을 담당**</U>하는 클래스, 이곳에서 모든 의존관계를 주입해줌
### constant : 상수 관리 담당 패키지
  - BadgeConstant : 배지 정보를 상수로 관리하기 위한 Enum 클래스
  - DaysConstant : 날짜 정보를 상수로 관리하기 위한 Enum 클래스
  - DiscountInfoConstant : 할인 정보를 상수로 관리하기 위한 Enum 클래스
  - ExceptionConstant: 예외 처리 문구를 상수로 관리하기 위한 Enum 클래스
  - MenuInfoConstant : 메뉴 정보를 상수로 관리하기 위한 Enum 클래스
  - MenuTypeConstant : 메뉴 타입을 상수로 관리하기 위한 Enum 클래스
  - MessageConstant : 출력 문구를 상수로 관리하기 위한 Enum 클래스
  - PriceConstant : 가격 정보를 상수로 관리하기 위한 Enum 클래스
  - SymbolConstant : 기호를 상수로 관리하기 위한 Enum 클래스
### domain : <U>**도메인 정보를 가지며 상세한 구현을 담당하는 패키지**</U>
  - date 
    - ExpectedVisitDate : 예상 방문 날짜에 대한 정보를 필드로 가지며 이와 관련된 상세한 구현을 담당하는 클래스
  - discount
    - DiscountPrice : 할인 가격 목록을 필드로 가지며 이와 관련된 상세한 구현을 담당하는 클래스
    - DiscountResult : 이벤트 할인 적용의 결과를 필드로 가지며 이와 관련된 상세한 구현을 담당하는 클래스
  - menu
    - Menu : MenuInfo, 메뉴 수량을 필드로 가지며 이와 관련된 상세한 구현을 담당하는 클래스
    - MenuInfo : 메뉴 타입, 메뉴 이름, 가격 정보를 가지며 이와 관련된 상세한 구현을 담당하는 Enum 클래스
    - Menus : Menu 목록을 필드로 가지며 이와 관련된 상세한 구현을 담당하는 클래스
  - order
    - Order : Menus, ExpectedVisitDate를 필드로 가지며 이와 관련된 상세한 구현을 담당하는 클래스
### service : 특정 기능의 전체 흐름을 알 수 있고 상세한 구현은 도메인에 위임하는 패키지
  - DiscountPolicyManager : 할인 정책과 관련된 기능의 전체 흐름을 알 수 있는 클래스
  - DiscountPolicy : 할인 정책을 추상화한 인터페이스
  - implement
    - ChristmasDiscountPolicy : DiscountPolicy를 구현하여 크리스마스 이벤트 할인을 적용한 클래스
    - SpecialDiscountPolicy : DiscountPolicy를 구현하여 스페셜 할인을 적용한 클래스
    - WeekdayDiscountPolicy : DiscountPolicy를 구현하여 평일 할인을 적용한 클래스
    - WeekdayDiscountPolicy : DiscountPolicy를 구현하여 주말 할인을 적용한 클래스
### view : 입력과 출력을 담당하는 패키지 
  - MessagePrinter : 메세지 출력을 담당하는 클래스
  - MessageReceiver : 메세지 입력을 담당하는 클래스       
  - PriceFormatter : 가격을 원하는 출력 형식으로 변경해주는 클래스
  - valid
    - ViewValidator : view 입력받는 형식을 검증하기 위한 클래스
    - InputValidation : 입력값을 검증할 때 <U>**변하는 부분을 추상화한 인터페이스**</U>
      - ExpectedVisitDateValidation : InputValidation을 구현하여 예상 구입 날짜를 검증하기 위한 클래스
      - MenusValidation : InputValidation을 구현하여 예상 구입 날짜를 검증하기 위한 클래스
### EventManager : <U>**전체적인 비즈니스 로직의 흐름**</U>을 파악할 수 있는 클래스
### Application : 코드를 실행하는 main 클래스   