## 안드로이드 학습을 위한 레포

- 간단한 실습 진행 ([멀티 모듈](https://github.com/komodgn/android-kotlin-exam/tree/test))

### 참고 자료 
- [일반적인 모듈화 패턴](https://developer.android.com/topic/modularization/patterns?hl=ko&_gl=1*wa7wmw*_up*MQ..*_ga*MjExNjEyMjgzMS4xNzU4MDExMjc4*_ga_6HH9YJMN9M*czE3NTgwMTEyNzgkbzEkZzAkdDE3NTgwMTEyNzgkajYwJGwwJGgxODgwNjQwMjY2)

### 안드로이드 멀티 모듈 레이어별 역할 정리
1. `Presentation(UI 레이어)`
- 역할: 사용자에게 보이는 화면과 상호작용을 담당
- 포함 요소:
    - ActivityFragment: 화면을 호스팅하는 컴포넌트.
    - Composable: Text, Button, Scaffold 등 사용자가 보는 UI 요소들을 그림.
    - ViewModel: Presentation 레이어의 상태(State)와 비즈니스 로직을 관리. Presentation 레이어는 ViewModel의 데이터를 구독하고, ViewModel에 사용자 이벤트를 전달.

2. `Domain(비즈니스 로직 레이어)`
- 역할: 앱의 핵심적인 비즈니스 규칙을 정의하고 구현. 즉, _**무엇을 할 것인가**_ 를 결정하는 로직이 들어감. 이 레이어는 플랫폼에 의존하지 않는 순수 Kotlin/Java 코드로 구성되어야 함.
- 포함 요소:
    - Use Case: 앱의 특정 기능을 담당하는 클래스. 예를 들어, "상품 목록을 가져오는 UseCase" 또는 "결제를 처리하는 UseCase"
    - Repository Interface: Data 레이어에 대한 추상적인 계약(Contract)을 정의. Domain 레이어는 어떻게 데이터를 가져오는지 모르고, 무엇을 가져올지만 알게 함.

3. `Data(데이터 레이어)`
- 역할: 앱의 데이터를 관리하는 역할. 즉, _**"어떻게 데이터를 가져올 것인가"**_ 를 결정.
- 포함 요소:
    - Repository Implementation: Domain 레이어에서 정의한 Repository Interface를 구현. 실제 데이터 소스(네트워크, 로컬 데이터베이스 등)와 통신하는 로직이 들어감.
    - DataSource: 네트워크 통신(Retrofit)이나 로컬 데이터베이스(Room)와 같은 실제 데이터 소스에 접근하는 클래스.

위의 구조를 꼭 따를 필요는 없으며(모듈 이름, 폴더 구조), 프로젝트 상황에 따라 자유롭게 변경하여 사용한다.
