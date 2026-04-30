# Navigation3

- Modeling navigation state
    - `back stack` 이라는 콘텐츠의 스택을 사용해서 네비게이션의 상태를 관리합니다.
    - back stack 에는 실제 콘텐츠가 아닌 콘텐츠에 대한 참조만 가지고 있습니다.
        - 해당 key에 들어올 수 있는 값은 직렬화 가능한 타입이어야 합니다.
            - 직렬화 가능한 타입이어야 하는 이유: 구성 변경과 프로세스 종료 시에도 영구 저장소에 저장할 수 있어야 하기 떄문입니다.
    - Navigation2 와의 차이점으로 back stack을 소유하고 관리할 수 있다는 장점이 있습니다.
        - 관리하는 back stack은 snapshot-state List<T\> 타입 기반으로 사용할 수 있습니다.

- NavDisplay
    - backstack을 관찰하고 UI에 상태를 반영하는 역할을 수행합니다.
        - back stack은 SnapshotStateList<T\> 타입이어야 하며, T는 백스택 키의 타입입니다. 관찰 가능한 List 타입이므로 변경될 때 NavDisplay의 리컴포지션을
          트리거합니다.
- NavEntry
    - 콘텐츠를 모델링하는 역할을 하며 컴포저블 함수를 포함하는 클래스입니다.
    - Destination을 나타내며, 사용자가 앞 또는 뒤로 화면을 이동할 수 있도록 합니다.
    - 콘텐츠에 대한 정보, 다시 말해 메타데이터를 포함할 수도 있습니다.
        - 메타데이터는 key는 String,value는 Any 타입인 Map 자료구조로 구성됩니다.
    - 해당 메타데이터는 NavDisplay 같은 컨테이너 객체가 읽을 수 있으며, 이를 통해 NavEntry의 콘텐츠가 어떻게 표시될 지 결정하는 데 도움을 줍니다.
- Entry Provider
    - back stack의 key를 NavEntry로 변환하기 위한 역할을 수행합니다.
        - key에 대한 NavEntry를 반환하는 함수이며, 보통 NavDisplay를 생성할 때 람다 매개변수로 정의됩니다.
            - 생성하는 방법으로 람다 함수를 직접 지정하는 방식, entryProvider DSL을 사용하는 방식 2가지가 있습니다.
                - entryProvider 람다함수를 사용하면 key를 람다 파라미터로 전달해주고 있어 해당 key에 대한 NavEntry를 지정해줍니다.
                ```kotlin
                  entryProvider = { key ->
                      when(key) {
                          is ProductList -> NavEntry(key) { Text("product List") }
                          is ProductDetail -> NavEntry(
                              key,
                              metadata = mapOf("extraDataKey" to "extraDataValue")
                          ) { Text("Product ${key.id}") }
                          else -> {
                              NavEntry(Unit) { Text(text = "Invalid Key: $it") }
                          }
                      }
                  }
                ```   

                - entryProvider DSL : 키 유형에 대한 분기처리 및 각각에 대해 NavEntry를 구성할 필요없이 람다함수를 단순화할 수 있습니다.
                    - 기본적으로 키를 찾을 수 없을 경우 예외를 던지도록 설계되었습니다.
                    - entry: NavEntry를 정의하는 데 사용되며 주어진 타입 기반으로 컴포저블 콘텐츠를 정의합니다.
                        - metadata 매개변수를 받아 NavEntry.metadata를 설정할 수 있습니다.

                ```kotlin
                  entryProvider = entryProvider {
                      entry<ProductList> { Text("Product List") }
                      entry<ProductDetail>(
                          metadata = mapOf("extraDataKey" to "extraDataValue")
                      ) { key -> Text("Product ${key.id}") }
                  }
                ```

- Navigation State를 관리하는 방법
    1. rememberNavBackStack
        - configuration changes와 process death 상황에서도 back stack이 지속되도록 디자인된 컴포저블 함수입니다.
        - 모든 backStack의 key는 모두 NavKey 인터페이스를 구현해야 하며, @Serializable 어노테이션과 함께 선언해야 합니다.
            - NavKey: 마커 인터페이스 역할을 수행합니다.
    2. ViewModel에 저장하기
        - rememberNavBackStack과 동일하게 Key는 직렬화 가능해야 합니다.
        - 앱이 백그라운드 전환되거나 복원될 때, 각 키의 직렬화, 역직렬화 로직을 직접 처리해야합니다.

- `ViewModels`는 화면 회전 같은 Configuration Change가 발생하는 경우 UI 상태를 유지하는 데 사용됩니다.
    - 기본적으로 가장 가까운 ViewModelStoreOwner에 scope이 지정되며 일반적으로 Activity 또는 Fragment 입니다.
    - 그러나, Activity 대신 특정 NavEntry에 ViewModel을 범위로 지정하기를 원한다면 NavEntry가 back stack에서 제거될 때 함께 지워지게 할 수 있습니다.
- `androidx.lifecycle:lifecycle-viewmodel-navigation3` 라이브러리에서 `NavEntryDecorator`를 제공합니다.
    - NavEntryDecorator는 각 NavEntry에 대해 ViewModelStoreOwner를 제공합니다.
        - NavEntry의 콘텐츠 내에서 ViewModel을 생성하면 자동으로 해당 특정 NavEntry의 key에 대해 backstack scope가 지정되며, viewModel은 backstack에
          추가될 때 생성되며, 제거될 때 지워지게됩니다.
    - 사용 방법
        1. `androidx.lifecycle:lifecycle-viewmodel-navigation3` 의존성 추가
        2. `NavDisplay`를 구성할 때 `rememberSaveableStateHolderNavEntryDecorator()`를 entryDecorators에 추가합니다.
        3. entryDecorators에 `rememberViewModelStoreNavEntryDecorator()`도 추가합니다.
    ```kotlin
        NavDisplay(
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            backStack = backStack,
            entryProvider = entryProvider { },
        )
    ```
  

          