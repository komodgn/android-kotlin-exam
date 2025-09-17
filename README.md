### 목적
과거에 진행했던 프로젝트에서 모듈에 대한 이해 부족으로 멀티 모듈로 설계하고서는 단일 모듈로 구현한 것을 알게 되었다. 멀티 모듈에 대한 이해를 높여 추후 진행하는 프로젝트에 적용하고자 한다.  


### 실습을 위한 멀티 모듈 구성 
```
app
core: presentation: designsystem (Android Library)
home: presentation (Android Library)
home: domain (Java or Kotlin Library) - 비즈니스 로직
home: data (Android Library)
```
### 참고 
아래 링크를 참고하여 실습을 진행한다.
- [안드로이드 멀티 모듈](https://dev-inventory.com/55)
