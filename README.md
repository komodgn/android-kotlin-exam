### 🍋 목적
이전에 진행한 프로젝트에서 모듈에 대한 이해 부족으로 멀티 모듈로 설계하고, 실제로는 단일 모듈로 구현한 것을 알게 되었다. 멀티 모듈에 대한 이해를 높여 추후 진행하는 프로젝트에 적용한다.

### 🍑 멀티 모듈 구성
```
.
├── app : 프로젝트의 진입점이자 모든 모듈을 통합하는 최상위 모듈 - 자체적인 UI나 비즈니스 로직은 최소화하고, 모든 feature 모듈을 포함하여 하나의 앱으로 빌드하는 역할만 수행
├── build-logic : 프로젝트의 빌드 로직을 중앙에서 관리하는 모듈
├── core : 모든 모듈에서 재사용되는 공통 코드를 담는 모듈
│   └── common
│         └── 
│   └── data : 주요 비즈니스 데이터를 다루는 모듈
│         ├── api
│         └── impl
│   └── datastore : 시스템 설정과 같은 간단한 데이터를 다루는 모듈
│         ├── api
│         └── impl
│   └── designsystem : 테마와 같은 디자인 시스템을 정의한 모듈
│   └── network
│   └── testing
│   └── ui 
│   └── ...
├── feature : 앱의 특정 기능 단위를 담당하는 모듈
│   └── main : 메인 액티비티가 위치한 모듈
│   └── screens : Circuit의 Screen을 담는 모듈
│   └── home
│   └── login
│   └── ...
└── gradle
...
```

### 🍋 의존 관계 그래프
<img width="1414" height="598" alt="project-dependency-graph" src="https://github.com/user-attachments/assets/f1041800-f33e-4ab1-9308-b47b90e7b1d1" />

### 🍑 data 관련 모듈에서 api, impl와 같이 모듈을 분리한 이유
1. 빌드 성능 최적화 및 격리   
모듈 분리는 빌드 시스템의 효율성을 높이는 데 직접적인 영향을 미친다.  
   - 빠른 컴파일: `api` 모듈은 인터페이스만 포함하므로 매우 가볍다. 
     `feature` 모듈들이 `api` 모듈만 의존하게 되면, `impl` 모듈 내부의 복잡한 로직이나 `Room/Retrofit` 같은 무거운 라이브러리가 변경되어도, 
     `api`가 변경되지 않는 한 `feature` 모듈은 재빌드할 필요가 없다.
   - 변경 격리 (Change Isolation): 데이터 저장 방식이나 기술 스택을 변경해야 할 때, 
     변경의 영향 범위가 오직 `impl` 모듈 내부로만 제한된다.

### 🍑 data 관련 모듈에서 repository와 datasource의 의미
data 관련 모듈에서 하위 폴더로 repository와 datasource로 나뉘는 것은 다음과 같이 정의한다.
- repository는 비즈니스적인 
- datasource는 해당 데이터를 어떻게 저장하고 읽을지에 대한 기술적인

### 🍋 기술 스택
- Circuit
- Compose
- Hilt
- build-logic
- Gradle
- Version Catalog
- Firebase Remote Config

멀티 모듈 구조에 컨벤션 플러그인을 적용하는 방법은 해당 블로그를 참고한다.
- [안드로이드 멀티 모듈](https://dev-inventory.com/55)
