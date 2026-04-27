## BaseActivity, BaseViewModel, ... 을 사용하는 방식 좋을까?

BaseXXX 같이 프로젝트 내에서 공통적으로 사용하는 클래스를 만드는 것은 좋을 지 고민이 된다.

이러한 파일들은 전역에서 사용하기 위한 CoroutineExceptionHandler를 설정하거나, MVI 패턴의 규약으로 State, Event, Effect를 반드시 구현하도록 강제하는 등 프로젝트 전반의 규칙을
설정할 수 있다.  
하지만, Activity 또는 Screen이 점차 늘어나고 프로젝트가 커지다보면 BaseXX 에서 제공하는 함수가 일부 파일에서는 사용하지 않는 상황 또는 일부 화면들에서만 동일하게 처리하도록 조건 분기를 추가한
상황 등, SRP를 위반하게 되며 임시방편인 코드들이 증가하는 상황을 마주하게 될 수도 있다.  
다른 문제로는 BaseXX 내부를 살펴보지 않고서는 내부에서 어떤 변수와 로직들이 있는지 파악하기 힘들다. 차라리 Base라는 이름이 아닌 특정한 역할을 수행하는 prefix를 사용하는 것이 더 나을지도
모르겠다.  
또한, Java, Kotlin 에서의 상속은 단일 상속을 지원하므로 다른 상속관계가 필요한 경우 BaseXX 또는 사용하고자 하는 클래스의 상속 중 하나만 선택하는 것이 불가피하다.

상속 대신 인터페이스를 구현함으로써 결합도를 낮추는 방향으로 동일한 기능을 제공할 수도 있다.
예를 들어 BaseActivity 내부에서 로깅만 제공한다면 다음과 같은 방식으로도 처리할 수 있다.  
여러 Activity 별로 로깅 레벨을 다르게 설정할 수도 있으며, 로깅이 필요없다면 구현하지 않는 유연성을 제공한다.

```kotlin
// as-is
class UserActivity : BaseActivity()

// to-be
class UserActivity : ComponentActivity(),
    MyLogger by MyLoggerImpl()


interface MyLogger {
    fun log(message: String)
}

class MyLoggerImpl : MyLogger {
    override fun log(message: String) {
        // 로직
    }
}
```
