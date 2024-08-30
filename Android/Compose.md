- 자료
  - [안드로이드 로드맵](https://velog.io/@skydoves/2022-android-developer-roadmap-part4)
  - [안드로이드 로드맵 - 원문](https://getstream.io/blog/design-patterns-and-architecture-the-android-developer-roadmap-part-4)

## Jetpack Compose

- Jetpack Compose 구조
- Compose Compiler : 코틀린 멀티플랫폼을 타겟, 코틀린으로 작성된 Jetpack Compose의 핵심 구성 요소
  - KAPT 및 KSP(Kotlin Symbol Processing API) 같은 기존의 어노테이션 프로세서와 달리 Compose 컴파일러 플러그인은 FIR(Frontend Intermediate Representation)과 직접 상호 작용한다.
  - 독특한 접근 방식을 통해 플러그인은 컴파일 타임에 정적 코드를 더 많은 정보로 분석하고, 코틀린 소스 코드를 동적으로 수정하고, 궁극적으로 자바 바이트코드를 생성할 수있다.
  - `@Composable`과 같은 Compose 라이브러리의 어노테이션은 Compose Compiler가 내부적으로 수행하는 다양한 작업과 복잡하게 관련되어있다.
- Compose Runtime : Compose의 모델 및 상태 관리의 초석 역할
  - 이 라이브러리는 자료구조의 한 개념인 슬롯 테이블을 사용하여 컴포지션의 상태를 기억하는 형태로 작동
    - 슬롯 테이블 : gap buffer 데이터 구조에서 파생된 개념
  - 프로젝트 개발에서 일반적으로 사용하는 필수 기능들을 처리 (side effect 관리, remember를 이용한 값 보존, CompositionLocal 저장, Compose Layout 노드 구성 등등)
- Compose UI : 개발자가 Composable 함수를 통해 UI를 소비하여 레이아웃 형태로 만들 수 있도록 하는 라이브러리 집합체
  - Compose 레이아웃 트리의 구성을 용이하게 하는 다양한 구성 요소를 제공한다.
  - 이러한 레이아웃 트리는 생성되면 Compose Runtime에 의하여 소비된다.
  - 코틀린 멀티플랫폼의 기능을 활용하여 젯브레인은 컴포즈 멀티플랫폼의 stable 버전을 출시하여 Android,IOS,데스크톱 및 웹 어셈블리와 같은 다양한 플랫폼에 대해 Compose UI 라이브러리로 동일한 레이아웃을 빌드 할 수 있다.
- 자료
  - [안드로이드 공식문서 - 컴포즈에서 생각해볼 사항](https://developer.android.com/develop/ui/compose/mental-model)
  - [안드로이드 공식문서 - 컴포저블](https://developer.android.com/develop/ui/compose/lifecycle)

## Compose UI

- Jetpack Compose의 필수 구성 요소
- 선언적 방식으로 UI 레이아웃을 만들 수 있다.
  - 코틀린에서 레이아웃을 논리적으로 작성하여 Frontend View를 명령식으로 조작할 필요없이 UI 레이아웃을 유지할 수 있다.
  - 재사용성이 높고 직관적인 UI 레이아웃 개발에 도움이 된다.

### Theming

- Jetpack Compose를 사용하면 디자인 시스템을 쉽게 구현할 수 있고, source-of-truth 방식으로 미리 정의된 테마를 제공하여 앱이 일관된 모양을 유지하도록 할 수 있다.
- Compose UI는 Material Design 2, Material Design 3 두 가지 테마 라이브러리를 제공한다.
- Button, Card, Switch, Bottom Sheet 등 다양한 필수 디자인 구성 요소를 제공하며, 모두 내부적으로 해당 테마 스타일을 반영한다.
- 예시
  ```kotlin
    MaterialTheme(
      colors = // ...
      typography = // ...
      shapes = // ...
    ){
      // content details
    }
  ```
- 애플리케이션 UI 사양이 구글의 Material Design 가이드라인과 다른 경우 MaterialTheme Composable과 유사한 사용자 정의 디자인 시스템을 구현할 수 있는 유연성이 있다. -> Stream Video SDK의 사용자 정의 테마 시스템을 참조할 수 있다.
  - 이 접근방식을 사용하면 제공된 각 UI 구성 요소에 대해 일관된 스타일을 유지하는 동시에 사용자 관점에서 구성 요소 스타일을 쉽게 정의 할 수 있다.
- 자료

  - [안드로이드 공식문서 - 컴포즈에서 디자인시스템](https://developer.android.com/develop/ui/compose/designsystems)
  - [Material Design - 가이드 라인](https://m2.material.io/design/guidelines-overview)
  - [Stream Video SDK 사용자 정의 테마 시스템](https://github.com/GetStream/stream-video-android/tree/develop/stream-video-android-ui-compose/src/main/kotlin/io/getstream/video/android/compose/theme)

### Modifier

- Jetpack Compose의 Modifier는 UI 개발에 있어 중요한 구성 요소다.
- 개발자에게 Composable 요소의 속성을 변경하고 개선할 수 있는 유연성을 제공한다.
- 너비/높이 조정, 패딩 크기, 배경색, 클릭 콜백 추가 등등
- 레이아웃 트리 계층을 통해 효과적으로 전달되어 Root Composable의 속성을 유지하고 확장할 수 있다.
  - 이 기능은 UI요소 전체에 걸쳐 레이아웃 구성과 스타일을 일관되고 효율적으로 전파할 수 있도록 한다.
- Modifier는 표준 Kotlin 객체이며 상태가 없으므로 아래 예시와 같이 빌더 클래스를 이용한 메서드 체이닝으로 생성할 수 있다.
  ```kotlin
      @Composable
      private fun Greeting(name: String) {
          Row(modifier = Modifier.padding(12.dp)) {
              Text(text = "Hello,")
              Text(text = name)
          }
      }
  ```
- 대부분 Compose UI 구성 요소에는 `modifier` 매개변수가 포함되어 있어 사용자가 속성을 사용자 지정하고 스타일을 제어할 수 있다.

  - 자체 Composable 함수를 만들 때는 `modifier` 매개변수를 노출하는 것이 좋다.
  - 호출자 쪽에서 직접 스타일을 수정할 수 있게 만들어, UI 디자인에서 더 큰 유연성과 커스텀을 제공할 수 있다.

  ```kotlin
      @Composable
      public fun Header(modifier: Modifier = Modifier) {
          Row(modifier = modifier.fillMaxWidth()){
              ...
          }
      }
  ```

  - `Header` Composable함수에 modifier를 전달할 수 있다는 것을 명시
    - 구성 요소의 스타일을 외부에서 제어할 수 있게 하여 다양한 요구사항에 따라 스타일을 조정하고 적용할 수 있다.
    - 함수 외부에서 스타일링할 수 있도록 유연성을 제공하여 사용자 정의 및 적응성을 향상 시킬 수 있다.

- Modifier를 사용할 때 중요한 측면 중 하나는 `Modifier 함수가 적용되는 순서`다.
  - Modifier 함수를 체인화하려면 수정자가 가장 높은 것부터 시작해서 아래로 순차적으로 작성해야한다.
  - 프로세스의 각 단계는 기존 modifier를 효과적으로 wrapping 하여 새로운 복합적인 modifier를 구축한다.
    - 체인을 점진적으로 적용되며 modifier 구성요소들이 최종 모양과 동작에 영향을 미친다.
    - 트리의 탐색과 유사하게 위에서 아래로 체계적으로 이동한다.
- 자료 : [안드로이드 공식문서 - Compose modifiers](https://developer.android.com/develop/ui/compose/modifiers)

### Lists and Grids

- 사용자에게 나열된 항목들을 효과적으로 표시하기 위해 자주 사용되는 개념
- 기존 XML 기반 Android 개발에서는 성능이 뛰어나고 효율적인 List를 만들기 위해 복잡함이 수반되었다. (특히, RecyclerView)
- Jetpack Compose는 복잡한 구현을 간소화하였다.
  - 각 항목에 대한 재사용 가능한 구성요소에 쉽게 접근할 수 있다.
  - Composable함수는 본질적으로 독립적으로 동작하기에 높은 수준의 재사용성을 제공한다.
- 예시

  ```kotlin
      @Composable
      fun MessageList(messages: List<Message>){
          Column(modifier = Modifier.verticalScroll()){
              messages.forEach { message ->
                  MessageItem(message)
              }
          }
      }

      @Composable
      fun MessageItem(message: Message){
        ..
      }
  ```

  - Row Composable 함수를 사용하여 가로로 된 목록을 구현할 수도 있다.

- Jetpack Compose의 `Column` 및 `Row` Composable은 모든 항목을 동시에 랜더링하기에 많은 수의 항목을 표시하는 성능 문제가 발생할 수 있어 많은 항목을 처리해야할 때 적합하지 않을 수 있다.

  - 많은 항목을 표시할 때는 Compose UI는 List에 LazyColumn 또는 LazyRow를 사용하거나 LazyVerticalGrid 또는 LazyHorizontalGrid를 사용하는 것이 좋다.
    - 화면에 보여지는 항목만 랜더링하여 대용량 데이터 Set을 효율적으로 처리하고 성능과 리소스 사용을 최적화 해준다.
  - Lazy List와 Grid(LazyColumn 등)는 기존 RecyclerView와 유사한 기능을 제공하며 표준 Column 이나 Row Composable에 비해 향상된 성능을 제공한다.
  - 예시

    ```kotlin
        @Composable
        fun MessageList(messages: List<Message>) {
            LazyColumn{
                items(messages) { message ->
                    MessageItem(message)
                }
            }
        }

        @Composable
        fun MessageItem(message: Message){
            ..
        }
    ```

    - DSL 스타일로 고유한 스코프를 제공한다.
    - 이 스코프내에서 제공된 항목 함수(items,item)을 사용하여 Composable함수를 랜더링할 수 있다.
    - `RecyclerView.Adapter`,`RecyclerView.ViewHolder` 구현이 필요없고 간결한 코드작성으로 구현할 수 있다.

### Animation

- UI 디자인의 하이라이트 역할
- XML 기반 레이아웃에서 정교한 애니메이션을 만드는 것은 어려움이 많았다.
  - 복잡한 Android 뷰 시스템을 탐색하고 측정을 처리해야하는 필요성과 ValueAnimator,ObjectAnimator 같은 복잡한 애니메이션 API를 사용해야만 했다.
- Jetpack Compose는 전용 애니메이션 라이브러리를 제공하여 사용자 친화적인 API를 사용하여 간단하게 애니메이션을 구현할 수 있다.
  - AnimatedVisibility : AnimatedVisibility 함수는 콘텐츠 등장과 사라짐을 애니메이션으로 표현한다.
    - 기본적으로 콘텐츠는 FadeIn,확장 효과와 함께 나타나고 FadeOut,축소 효과와 함께 사라진다.
    - `EnterTransition` 및 `ExitTransition` 매개변수를 지정하여 선호도에 맞게 조정할 수 있다.
  - Crossfade : Crossfade는 Crossfade 효과를 사용하여 두 개의 컴포저블 함수 간에 애니메이션 전환을 제공한다.
    - 서로 다른 화면간에 부드럽고 매력적인 전환을 만드는데 유용하다.
  - AnimatedContent : AnimatedContent는 지정된 애니메이션 스펙 매개변수를 준수하여 주어진 대상의 상태 변경사항에 따라 콘텐츠를 동적으로 애니메이션화 한다.
    - 두 개의 개별 Composable함수 간 애니메이션을 만드는데 유용하다.
  - animate_AsState : animate_AsState는 단일 값을 애니메이션화하기 위한 가장 간단한 애니메이션 API
    - 사용하기 위한 대상 또는 종료 값을 지정하면 된다.
    - `animateDpAsState`,`animateFloatAsState`, ... 여러 함수를 사용할 수 있다.
  - animateContentSize : animateContentSize modifier는 크기 변경을 애니메이션화하는 간단하고 효율적인 방법을 제공한다.
    - 자세한 콜백의 필요성을 없애 애니메이션 프로세스를 간소화한다.
    - modifier를 컴보저블에 적용하고 미리 정의된 상태에 따라 결정된 다양한 콘텐츠 크기에 의해 크기 전환을 자동으로 처리하도록 할 수 있다.
- 자료 : [안드로이드 공식문서 - Animation](https://developer.android.com/develop/ui/compose/animation/introduction)

### State

- Jetpack Compose에서는 State API를 제공해, UI 상태를 효율적으로 관리할 수 있다.

- Jetpack Compose의 주요 단계 흐름

  - Data -> Composition -> Layout -> Drawing -> UI
    - Composition : Composable함수를 컴포지션에 통합하는 작업을 수행한다.
      - Composable 함수에 대한 설명과 메모리 내 슬롯이 여러개 생성된다.
      - 슬롯마다 각각의 Composable 함수를 기억하는데 사용되어 런타임 중에 효율적으로 활용할 수 있다.
    - Layout : Composable 트리 내에서 Composable 노드의 배치가 결정된다.
      - Composable 노드를 측정하고 적절하게 배치하여 모든 요소가 UI의 전체 구조에 올바르게 배열되도록 하는 작업을 포함한다.
    - Drawing : Composable 노드를 일반적으로 장치 화면인 캔버스에 랜더링하는 작업이 포함된다.
      - Composable함수의 시각적 표현이 생성되어 표시된다.

- State and Recomposition
  - State의 변경에 대응하여 UI를 업데이트하는 것은 필수적이다.
  - Recompostion : Composition -> Layout -> Drawing 단계를 거쳐 이미 랜더링된 UI Layout을 다시 업데이트하는 방법
  - Jetpack Compose는 Recomposition을 트리거하는 두 가지 기본 방법을 제공한다.
    - 입력 변경(Input Changes) : 가장 기본적인 방법
      - Compose 런타임은 `equals` 함수를 사용하여 인수의 변경 사항을 감지한다.
      - `equals`가 false를 반환하는 경우 런타임은 입력 데이터의 변경으로 인식한다.
        - 결과적으로 데이터 변경사항에 따라 UI 표현을 업데이트하기 위해 Recomposition을 시작한다.
    - 상태 변경 관찰(Observing State Changes) : Compose 런타임 라이브러리에서 제공하는 State API를 사용하여 상태 변경을 모니터링하여 트리거 하는 방법
      - 일반적으로 remember Composable 함수와 함께 사용된다.
      - 메모리에 상태 객체를 보존하고 Recomposition 중에 복원하여 UI가 현재 상태를 반영하도록 하는 데 도움이 된다.
  - 자료
    - [안드로이드 공식문서 - State](https://developer.android.com/develop/ui/compose/state)
    - [Jetpack Compose 성능 최적화](https://velog.io/@skydoves/compose-stability)
- Stateful vs Stateless

  - Stateful : Composable함수가 `remember` 함수를 활용할 때 `stateful`로 간주
    - 이 접근 방식은 함수가 객체를 메모리에 저장하여 여러 Recomposition에서 상태를 효과적으로 보존할 수 있다.
    - `remember` 함수는 Composable 함수가 반복적으로 호출되더라도 상태가 일관되도록 한다.
  - Stateless : Composable함수가 `remember` 함수를 사용하지 않고 인수를 통해 상태를 수신할 때 `stateless`로 간주
    - 이 접근 방식은 함수는 내부 상태를 유지하지 않고 전달된 데이터에 전적으로 의존하므로 특정 시나리오에서 더 예측가능하고 재사용성이 좋다.
  - Stateful과 Stateless Composable의 차이점을 이해하는 것은 매우 중요하고, 애플리케이션 내에서 상태를 처리하고 유지하는 방법에 영향을 미친다.
  - 일반적으로는 유지 관리성과 재사용성 측면에서 Stateless한 Composable함수를 만드는 것이 좋다.
    - 유지 관리성(Maintainability) : Stateful한 컴포저블은 호출자 입장에서 사용하기 편리해보일 수 있지만, 내부 상태와 동작에 대한 이해를 흐리게 만들 수 있다. 이러한 투명성 부족으로 어떻게 동작할지 예측하기 어려워져 유지 관리에 어려움을 겪을 수 있다.
    - 재사용성(Reusability) : 외부 상태 관리에 의존하는 Stateless 컴포저블 함수는 다양한 부분에서 재사용 가능한 경향이 있다. 입력에 따라 출력을 예측할 수 있는 순수함수처럼 작동하며, 예기치 않게 동작에 영향을 미칠 수 있는 내부 상태를 유지하지 않는다.
  - Stateless Composable함수를 선택하면 구성 요소를 다양한 맥락에서 테스트, 유지관리 및 재사용하기 쉽고 더 깔끔하고 모듈화된 코드베이스로 이어질 수 있다.
  - Stateful Composable함수를 Stateless 함수로 변환하는 것은 상태 호이스팅(State Hoisting)이라는 기술을 통해 효과적으로 달성할 수 있다.
  - State Hoisting
    - 상태 관리를 호출자쪽으로 위임하여 Stateful한 Composable함수를 Stateless 함수로 변환하는데 사용되는 방법
    - 핵심 개념은 remember에서 관리되는 상태 변수를 Composable함수의 두 매개변수로 대체하는 것을 포함
    - 이 프로세스는 상태를 유지하는 책임을 Composable함수에서 호출자로 효과적으로 전환하여 함수를 Stateless 함수로 만든다.
  - 예시

    - Stateful Composable

      ```kotlin
        @Composable
        fun HomeScreen() {
          MyTextField()
        }

        @Composable
        fun MyTextField() {
          val (value, onValueChanged) = remember { mutableStateOf("") }

          TextField(value = value, onValueChange = onValueChanged)
        }
      ```

      - MyTextField 는 remember Composable함수를 사용하여 상태를 메모리에 저장하고 입력 변경 사항을 추적하여 상태를 유지한다
        - MyTextField를 내부적으로 상태를 관리하므로 Statefule Composable을 만든다.
        - 장점 : 호출 사이트(HomeScreen)는 MyTextField의 상태를 관리할 필요가 없어 구현이 간소화된다.
        - 단점 : 해당 함수 외부에서 MyTextField의 동작을 이해하고 제어하는 것이 더 어려워진다. ( 재사용이 어려울 수 있다. )

    - Stateless Composable

      ```kotlin
          @Composable
          fun HomeScreen() {
            val (value, onValueChanged) = remember { mutableStateOf("") }

            MyTextField(
              value = value,
              onValueChanged = onValueChanged
            )
          }

          @Composable
          fun MyTextField(
            value : String,
            onValueChanged: (String) -> Unit
          ) {
            TextField(value = value, onValueChange = onValueChanged)
          }
      ```

      - MyTextField Composable은 인수를 통해 값의 변경 사항을 반영하도록 설계되었으며, 호출 사이트(HomeScreen)은 MyTextField의 모든 상태를 관리한다.
        - Stateful 컴포저블 예시에 비해 코드가 길어질 수 있지만, 재사용성이 향상된다는 이점이 있다.

    - State Hoisting

      ```kotlin
        @Composable
        fun HomeScreen(){
          val (value, onValueChanged) = remember { mutableStateOf("") }
          val processedValue by remember(value) { derivedStateOf { value.filter { !it.isDigit() } } }

          MyTextField(
            value = processedValue
            onValueChanged = onValueChanged
          )
        }

        @Composable
        fun MyTextField(
          value: String,
          onValueChanged: (String) -> Unit
        ) {
          TextField(value = value, onValueChange = onValueChanged)
        }
      ```

      - 호출자(여기에서는 MyTextField)에서 호출자(HomeScreen)로 상태관리를 승격 또는 '호이스팅'하는 관행
      - 즉, 계층 구조에서 더 높은 위치에서 관리되므로 보다 유연하고 제어된 상태 관리가 가능하다.

## Side-Effects

- Side-Effects : 프로그램의 한 부분에서 변경한 내용이 다른 부분에 영향을 미쳐 발생하는 의도치 않거나 예상치 못한 상황
- Jetpack Compose에서 발생할 수 있는 일반적인 경우는 Composable 함수 내부에서 발생한다.
  - Composable 함수는 Recomposition과 같은 다양한 트리거로 인해 UI를 업데이트하기 위해 다시 호출될 수 있다.
    - 그렇기에 Composable 함수 내에서 도메인 로직을 직접 실행하는 것은 위험할 수 있다.
      - 반복적인 호출로 인해 UI에서 예기치 않은 동작이나 불일치가 발생할 수 있다.
- 예시

  ```kotlin
    @Composable
    fun HomeScreen(defaultValue: String = "Hello, Compose!") {
      Column {
        val (value, onValueChanged) = remember { mutableStateOf(defaultValue) }

        val context = LocalContext.current
        Toast.makeText(context, defaultValue, Toast.LENGTH_SHORT).show()

        Text(text = value)

        TextField(value = value, onValueChange = onValueChanged)
      }
    }
  ```

  - 문제 발생
    - 텍스트 필드의 입력을 수정할 때마다 기본 메시지가 토스트로 다시 나타난다.
      - 이유 : 텍스트 필드의 상태가 변경되면 Recomposition이 트리거되어 Compose UI가 업데이트 되고 부수효과로 토스트가 반복적으로 표시
      - 해결방법 : 이펙트 핸들러
  - UI 동작으로 인해 네트워크 데이터 가져오거나, DB쿼리, 기타 주요 작업과 같은 도메인 로직과 상호작용해야하는 경우가 많다.
  - 이러한 상호작용은 대부분 애플리케이션에서 UI가 현재 상태와 기본 시스템에서 처리하는 데이터를 반영하도록 하는데 필수적이다.
  - side-effect 문제를 해결하기 위해 Compose에서는 `이펙트 핸들러`라는 개념을 도입했다.
    - Compose 프레임워크내에서 사이드 이펙트를 관리하고 제어하도록 특별히 설계되어 아래 API를 사용하여 UI 상호작용과 업데이트가 제어되고 예측 가능한 방식으로 발생하도록 한다.
      - SideEffect : 성공적인 Recomposition 후에 Composable이 아닌 표준 함수의 실행을 가능하게 한다.
        - 하지만 이 실행결과가 Recomposition 전에 발생할 것이라고 보장하지는 않는다.
        - 본질적으로 Recomposition 후에 작업을 트리거하도록 설계되었다.
      - LaunchedEffect : 초기 구성 단계부터 시작하여 Composable 함수 내에서 suspend 함수를 안전하게 실행할 수 있도록 한다.
        - 중요한 점 : `LaunchedEffect가 Composition에서 제거되면 코루틴 스코프가 자동으로 취소된다는 것`
        - 다양한 조건에 따라 다른 실행을 관리하려면 LaunchedEffect에 고유한 `key` 매개변수를 제공하여 실행흐름을 제어할 수 있다.
        - 특정 상황이나 상태 변경에 따라 현재 실행을 취소하고 새 suspend 함수를 시작할 수 있다.
      - DisposableEffect : Composable이 아닌 표준 함수의 실행을 허용하는 동시에 구성을 벗어날 때 진행 중인 작업을 취소하거나 폐기할 수 있는 기능을 제공하여 리소스 누수나 중복 처리 같은 문제를 효율적으로 방지한다.

- 자료 : [안드로이드 공식문서 - Side-effects in Compose](https://developer.android.com/develop/ui/compose/side-effects)

### CompositionLocal

- Jetpack Compose는 선언적 접근 방식을 사용하여 설계되어 재사용성이 높고 직관적인 UI 레이아웃을 만들 수 있다.
- 그러나 레이아웃 루트에 제공된 정보가 노드의 맨 끝에서 필요할 때 문제가 발생한다.
  - 루트에서 끝까지 필요한 정보를 전달해야하므로, 그 사이에 있는 모든 Composable 함수도 실제로 사용하지 않더라도 이 정보를 전달해야 한다.
  - Composable 전체 세트를 복잡하게 만들어 더 광범위하고 관련 없는 정보로 부담을 주게 된다.
  - 코드의 유지 관리성 또한 떨어지게 된다.
- 이러한 문제를 해결하기 위해 CompositionLocal 매커니즘을 도입
- CompositionLocal은 생성된 후 `크게 변경되지 않는 비교적 정적인 정보`를 전달하는 데 사용된다.
  - 예시, MaterialTheme 객체 (Compose UI 구성 요소 전체에서 일관성 보장하는 데 도움이 된다.)
- 반면, CompositionLocal을 과도하게 사용하면 코드를 디버깅하고 유지 관리하는데 어려움이 발생할 수도 있다.
- 사용시, 신중하게 고려하고 코드베이스에서 과도하게 의존하지 않는 것이 중요하다.
- 자료 : [안드로이드 공식문서 - CompositionLocal](https://developer.android.com/develop/ui/compose/compositionlocal)

## 컴포즈로 마이그레이션 하기

- 자료
  - [안드로이드 공식문서 - Migration 전략](https://developer.android.com/develop/ui/compose/migrate/strategy)
  - [Medium 아티클](https://medium.com/androiddevelopers/migrating-to-jetpack-compose-an-interop-love-story-part-1-3693ca3ae981)
