## Android Jetpack

- 구글은 Android Jetpack에 정의된 앱 아키텍처를 위해 다양한 라이브러리를 지원한다.
- 올바른 앱 아키텍처를 만드는 데 도움이 될 수 있도록 다양한 라이브러리를 지원한다.

### Ui Layer Libraries

- ViewBinding
  - 각 XML 레이아웃 파일에 대한 바인딩 클래스를 생성, 여기에는 해당 레이아웃에 ID가 있는 모든 뷰에 대한 직접 참조가 포함된다.
  - 뷰 바인딩은 성능 문제와 해당 뷰를 찾는 데 몇 가지 문제를 해결하기 위해 findViewById() 를 대체한다.
  - 자료 : [안드로이드 공식문서 - 뷰 바인딩](https://developer.android.com/topic/libraries/view-binding)
- DataBinding
  - 데이터 바인딩을 사용하면 annotation processor에 기반하여, layout 태그를 포함하는 XML 레이아웃에 대해 바인딩 클래스가 자동적으로 생성된다.
  - View 및 ViewModel을 옵저버 패턴, 속성 및 이벤트 콜백과 연결하는 등 MVVM 아키텍처에서 필수적인 부분
  - 양방향 데이터 바인딩, 레이아웃 변수 또는 레이아웃 표현식을 지원하여 XML레이아웃 파일에서 직접 동적 UI 콘텐츠를 선언
  - 자료 : [안드로이드 공식문서 - 데이터 바인딩](https://developer.android.com/topic/libraries/data-binding)
- ViewModel
  - 화면 변경 시 UI 관련 데이터를 저장하고 관리하기 위한 상태 홀더
  - Activity 또는 Fragment의 수명을 따른다.
  - 일반적으로 ViewModel은 도메인 및 데이터 계층과 같은 다른 계층에서 애플리케이션 데이터를 위임하는 데 사용되며 UI계층(데이터 바인딩 관찰자, Activity 또는 Fragment)에 데이터 변경사항을 알린다.
  - 자료 : [안드로이드 공식문서 - 뷰모델](https://developer.android.com/topic/libraries/architecture/viewmodel)
- LiveData
  - 여러 Observer가 관찰할 수 있는 생명 주기 인식 데이터 홀더
  - Observer는 LifecycleOwner의 상태에 따라 데이터 변경 사항에 대한 알림을 받는다.
  - 일반적으로 LiveData는 ViewModel과 함께 사용하여 Observer 패턴을 구현하고 UI요소를 구성하기 위한 상태 홀더로 사용할 수 있다.
  - 자료 : [안드로이드 공식문서 - LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- Lifecycle
  - Activity 와 Fragment 같은 생명 주기 소유자의 생명 주기 변화를 관찰하는 독립 구성 요소를 구축할 수 있다.
  - 더 깔끔하고 가벼운 코드를 작성하는 데 도움이 되고 lifecycle-aware한 구성 요소를 빌드할 수 있다.
  - 자료 : [안드로이드 공식문서 - Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)

### Data Layer Libraries

- DataStore
  - 로컬 저장소에 간단한 키-값 쌍을 저장할 수 있는 저장소 라이브러리
  - 데이터를 비동기적으로 저장하기 위해 Coroutines,Flow와 함께 작동하며 RxJava도 지원한다.
  - SharedPreferences를 DataStore로 대체하는 것을 고려할 수 있다.
  - 자료 : [안드로이드 공식문서 - DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
- Room
  - 복잡한 SQL문 없이 데이터베이스 쿼리 및 엑세스를 단순화하는 SQLite 데이터베이스에 대한 추상화 계층을 제공한다.
  - Room 데이터베이스를 쉽게 구성하고 데이터베이스 클래스, 데이터 엔티티 및 데이터 엑세스 개체(DAO)를 사용하여 쿼리할 수 있다.
  - 자료 : [안드로이드 공식문서 - Room](https://developer.android.com/training/data-storage/room)
- WorkManager

  - 작업(work)을 예약하여 지연 가능하고, 보장된 백그라운드 작업을 실행하는 백그라운드 처리 API
  - immediate, long-running, deferrable 세 가지 유형의 영구 작업을 예약할 수 있다.
  - 자료 : [안드로이드 공식문서 - WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
