# Kotlin in Action

> '코틀린 인 액션'책을 읽으면서 메모하듯이 기록한 내용들  
> 내용이 길어져 파일을 나눠 작성하였다.  
> (4/4)

---

### 리플렉션

- 실행 시점에 동적으로 객체의 프로퍼티와 메소드에 접근할 수 있도록 지원하는 방법
- 코틀린에서 리플렉션을 사용하려면 두 가지 서로 다른 리플렉션 API를 다뤄야 한다
    - 자바가 java.lang.reflect 패키지를 통해 제공하는 표준 리플렉션 API
        - 코틀린 클래스는 일반 자바 바이트코드로 컴파일되므로 자바 리플렉션 API도 코틀린 클래스를 컴파일한 바이트코드를 완벽히 지원한다.
    - 코틀린이 kotlin.reflect 패키지를 통해 제공하는 코틀린 리플렉션 API
        - 자바에는 없는 프로퍼티나 null type 같은 코틀린 고유 개념에 대한 리플렉션 제공
        - 안드로이드 같이 런타임 라이브러리 크기가 문제가 되는 플랫폼을 위해 코틀린 리플렉션 API는 kotlin-reflect.jar 라는 별도의 파일에 담겨 제공된다.
            - 자동으로 추가 되지 않으며, 코틀린 리플렉션 API를 사용한다면 직접 프로젝트 의존관계에 리플렉션 .jar 파일을 추가해야 한다. (org.jetbrains.kotlin:
              kotlin-reflect)

### 코틀린 리플렉션 API

- KClass
    - 클래스를 `컴파일 시점`에 알고 있다면 ClassName::class 라는 식으로 KClass 인스턴스를 얻을 수 있다.
    - `실행 시점`에 객체의 클래스를 얻으려면 먼저 객체의 `javaClass 프로퍼티`를 사용해 객체의 자바 클래스를 얻어야 한다.
        - javaClass == 자바의 java.lang.Object.getClass()
    - 자바 클래스를 얻은 후 .kotlin 확장 프로퍼티를 통해 자바에서 코틀린 리플렉션 API로 옮겨올 수 있다.
    - KClass 인터페이스 내부 (kotlin-reflect 라이브러리)
      ```kotlin
        interface KClass <T : Any> {
            val simpleName: String?
            val qualifiedName: String?
            val members: Collection<KCallable<*>>
            val constructors: Collection<KFunction<T>>
            val nestedClasses: Collection<KClass<*>>
            /* ... */
        }
      ```
    - 클래스의 모든 멤버 목록은 KCallable 인스턴스의 컬렉션
        - KCallable은 함수와 프로퍼티를 아우르는 공통 상위 인터페이스, 안에는 call 메서드가 존재한다. (함수나 프로퍼티의 getter 호출)
          ```kotlin
              interface KCallable<out R> {
                  fun call(vararg args: Any?): R
                    /* ... */
              }
          ```
        - call을 사용할 때는 함수 인자를 vararg 리스트로 전달한다.
          ```kotlin
              fun foo(x: Int) = println(x)
              val kFunction = ::foo
              kFunction.call(42)  // 42 출력  
          ```
        - 함수를 호출하기 위해 더 구체적인 메소드를 사용할 수도 있다.
            - ::foo 타입은 KFunction1<Int,Unit> 으로 파라미터와 반환값 타입 정보가 들어있다. (1 : 파라미터가 1개 존재한다는 의미 )
            - KFunctionN 인터페이스를 통해 함수를 호출하려면 invoke 메소드를 사용해야 하고, invoke는 정해진 개수 인자만을 받아들인다. (인자 개수나 타입이 일치하지 않으면 컴파일 되지
              않는다.)
            ```kotlin
               import kotlin.reflect.KFunction2
                    
               fun sum(x: Int, y: Int) = x + y
               val kFunction : KFunction2<Int, Int, Int> = ::sum
               println(kFunction.invoke(1, 2) + kFunction(3, 4)) // 10 출력
                    
               kFunction(1) // ERROR: No value passed for parameter p2 출력
            ```
        - call 메소드는 모든 타입의 함수에 적용할 수 있지만 타입 안전성을 보장해주지 않는다.
- KProperty의 call 메소드를 호출할 수 있다. (KProperty의 call은 프로퍼티 getter를 호출한다)
    - 프로퍼티 인터페이스는 프로퍼티 값을 얻는 더 좋은 방법으로 get 메소드를 제공한다.
    - 최상위 프로퍼티는 KProperty0 인터페이스의 인스턴스로 표현되며, KProperty0 안에는 인자가 없는 get 메소드가 있다.
    - 수신 객체가 있는 프로퍼티, 멤버 프로퍼티는 KProperty1 인스턴스로 표현되며, 1개의 인자를 갖는 get 메소드가 있다.
        - KProperty<수신 객체 타입, 프로퍼티 타입>
        - 멤버 프로퍼티는 어떤 객체에 속해 있는 프로퍼티 이므로, 멤버 프로퍼티의 값을 가져오려면 get 메소드에게 프로퍼티를 얻고자하는 객체 인스턴스를 넘겨야 한다.

    ```kotlin
        class Person(val name: String, val age: Int)
    
        val person = Person("Ace", 29)
        val memberProperty = Person::age  // memberProperty 는 KProperty 객체
        println(memberProperty.get(person)) // 멤버 프로퍼티의 값을 가져오기 위해 생성한 Person 인스턴스를 넘겨준다.
        // 29 출력
    ```

---

### DSL (Domain-Specific Language)

- 코틀린 DSL 설계는 코틀린 언어의 여러 특성을 활용한다.
    - 수신 객체 지정 람다, invoke 관례, ...
- 간결한 구문을 제공하는 기능, 그런 구문을 확장해서 여러 메소드 호출을 조합하여 구조를 만들어내는 기능에 의존한다.
- 컴파일 시점에 타입이 정해진다.

### 내부 DSL

- 독립적인 문법 구조를 가진 외부 DSL과는 반대로 범용 언어로 작성된 프로그램의 일부이며, 범용 언어와 동일한 문법을 사용한다.
- 외부 DSL

```
    SELECT Country.name, COUNT(Customer.id)
      FROM Country
      JOIN Customer
		ON Country.id = Customer.country_id
    GROUP BY Country.name
    ORDER BY COUNT(Customer.id) DESC
    LIMIT 1
```

- 내부 DSL

```kotlin
  (Country join Customer)
    .slice(Country.name, Count(Customer.id))
    .selectAll()
    .groupBy(Country.name)
    .orderBy(Count(Customer.id), isAsc = false)
    .limit(1)
```

### 수신 객체 지정 람다

- buildString 내부 구현
    ```kotlin
        fun buildString(
            builderAction: StringBuilder.() -> Unit
        ): String {
            val sb = StringBuilder()
            sb.builderAction() // StringBuilder 인스턴스를 람다의 수신 객체로 넘긴다
            return sb.toString()
        }
        
        buildString {
            append("kotlin")
            this.append("in Action")
        }
        
        fun buildString(builderAction: StringBuilder.() -> Unit): String = StringBuilder().apply(builderAction).toString()
    ```

- buildString 인자는 확장함수 타입 파라미터와 대응된다.
- apply, with 모두 수신 객체로 확장 함수 타입의 람다를 호출한다.
    ```kotlin
        inline fun <T> T.apply(block: T.() -> Unit): T {
            block()
            return this
        }
        
        inline fun <T, R> T.with(receiver: T, block: T.() -> R): R = receiver.block()
    ```

### 코틀린 빌더

- HTML 만들기 위한 코틀린 DSL을 보통은 HTML 빌더라고 부른다.
- 빌더를 사용해서 객체 계층 구조를 선언적으로 정의할 수 있다.
- 코틀린 빌더는 사용하기도 편리하고 타입 안전성이 보장된다.
    ```kotlin
        fun createSimpleTable() = createHTML().
            table {
                tr { 
                    td { +"cell" }  
                }     
            }     
    ```
    - 각 함수는 고차함수로 수신 객체 지정 람다를 인자로 받는다.
        - 각 수신 객체 지정 람다가 이름 결정 규칙을 바꾼다. table 함수에 넘겨진 람다에서는 tr을 만들 수 있지만 그 외에서는 tr 함수에 접근할 수 없다.
    ```kotlin
        open class Tag(val name: String) {
            private val children = mutableListOf<Tag>() // 모든 중첩 태그 저장
            
            protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
                child.init() // 자식 태그 초기화
                children.add(child) // 자식 태그에 대한 참조 저장            
            }
  
            override fun toString() = "<$name>${children.joinToString { "" }}</$name>"
        }
  
        fun table(init: TABLE.() -> Unit) = TABLE().apply(init)
        
        class TABLE : Tag("table") {
            fun tr(init: TR.() -> Unit) = doInit(TR(), init)
        }
  
        class TR : Tag("tr") {
            fun td(init: TD.() -> Unit) = doInit(TD(), init)
        }    
  
        class TD : Tag("td")
    ```

### invoke 관례

- 관례 : 특별한 이름이 붙은 함수를 일반 메소드 호출 구문으로 호출하지 않고 더 간단한 다른 구문으로 호출할 수 있게 지원하는 기능
- invoke 관례는 operator 변경자가 붙은 invoke 메소드 정의가 들어 있는 클래스의 객체를 함수처럼 호출할 수 있다.
- get 관례의 경우 각괄호를 통해 get과 동일하게 사용할 수 있고, invoke 관례의 경우 괄호를 사용해서 invoke와 동일하게 사용할 수 있다.
    ```kotlin
      class Greeter(val greeting: String) {
          operator fun invoke(name: String) { // invoke 정의
              println("$greeting, $name!")
          }
      }
    
      val greeter = Greeter("Hello")
      greeter("Mr") // 인스턴스를 함수처럼 호출
      // Hello, Mr! 출력
    ```

- 일반적인 람다 호출 방식 : invoke 관례를 적용한 것이다

### DSL 중위함수 활용

- 테스트 프레임워크인 Kotest 구현 예시
    ```kotlin
      object start
    
      infix fun String.should(x: start): StartWrapper = StartWrapper(this)
    
      class StartWrapper(val value: String) {
          infix fun with(prefix: String) =
              if (!value.startsWith(prefix))
                  throw AssertionError("String does not start with $prefix: $value")
              else
                  Unit
      }
    
      "Kotlin" should start with "Kot"
      == "Kotlin".should(start).with("Kot")
      // should 와 with : 중위함수 
    ```

- start 객체는 함수에 데이터를 넘기기 위한 것이 아니라 DSL 문법을 정의하기 위해 사용
- start를 인자로 넘김으로써 should를 오버로딩한 함수 중에서 적절한 함수를 선택할 수 있고, 그 함수를 호출한 결과로 StartWrapper 인스턴스를 받을 수 있다.
    - `"kotlin" should end with "in"`, `"Kotlin" should have substring "otl"` 같은 문장을 지원하기 위해 should에는 end, have 같은 싱글턴
      객체 인스턴스를 취하는 오버로딩 버전이 존재한다.
    - EndWrapper, HaveWrapper 인스턴스 반환해서 위 예시처럼 중위함수를 구현할 수 있다.

---

### 코틀린 사용에 편의성을 제공하는 도구들

- 코틀린 API 문서 생성 도구 -> 도카
- 코틀린 테스팅 -> Kotest(표현력 좋은 DSL 제공), Spek(BDD 스타일의 코틀린 테스트 프레임워크)
    - JUnit에 만족하지만 단언문 작성에 조금 더 표현력 좋은 DSL을 원한다면 Hamkrest 찾아보기
    - 테스트에 모킹을 사용한다면 Mockito-Kotlin 사용 권장
- 의존관계 주입 -> Dagger, Guice, Spring, Kodein(코틀린으로 작성한 DI 프레임워크, 코틀린 DSL 제공)
- JSON 직렬화 -> Jackson-module-kotlin : 잭슨을 코틀린에 통합, Kotson : GSON에 코틀린 래퍼를 제공, 경량 순수 코틀린 솔루션을 원한다면 Klaxon 찾아보기
- HTTP 클라이언트 -> Retrofit, 저수준 솔루션이 필요하다면 OkHttp 또는 순수 코틀린 구현인 Fuel 찾아보기
- 웹 애플리케이션 -> 스프링, 스파크 자바, Vert.x
    - 케이토(Ktor): 젯브레인스에서 만든 코틀린다운 API 사용하는 완전한 웹 애플리케이션 프레임워크
    - 와사비(Wasabi): Netty 위에 만들어진 HTTP 프레임워크로 표현력이 풍부한 코틀린 API 제공
    - 코버트(Kovert): vert.x 위에 만들어진 REST 프레임워크
- 데이터베이스 접근 -> Exposed, ...
- 유틸리티와 데이터 구조 -> 반응형 프로그래밍 RxJava
    - funKTionale: 다양한 함수형 프로그래밍 기본 구조 제공
    - 커버넌트(Kovenant): 코틀린과 안드로이드를 위한 promise 구현
- 테스크탑 프로그래밍 -> 자바FX, 토네이도FX