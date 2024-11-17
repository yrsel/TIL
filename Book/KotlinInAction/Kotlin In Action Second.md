# Kotlin in Action

> '코틀린 인 액션'책을 읽으면서 메모하듯이 기록한 내용들  
> 내용이 길어져 파일을 나눠 작성하였다.  
> (2/4)

---

### 코틀린에서는 관례를 사용한다.

- 관례 : 어떤 언어 기능과 미리 정해진 이름의 함수를 연결해주는 기법
    - 오버로딩으로 관례 함수를 재구현할 수 있다. (operator 키워드를 붙여 관례를 따르고 있음을 명시적으로 표시한다)
    - 확장함수로 관례 키워드들을 구현할 수도 있다.
    - 오버로딩 가능한 이항 산술 연산자 예시
        - times, div, mod 또는 rem(코틀린 1.1버전부터), plus, minus, ...
    - 컬렉션이나 Map 자료구조 사용할 때 `[]`로 index 또는 key 값을 사용할 수 있는 것도 관례를 활용했기에 가능하다
    - get, set 관례
    - in 관례 -> contains 를 확장함수로 구현해서 관례를 활용할 수 있다.
    - 반복문 in -> iterator의 hasNext, next 호출을 반복해서 구현
        - iterator() 메소드를 확장함수로 정의해서 사용할 수 있다.
    - rangeTo 관례 -> .. 으로 범위연산을 할 수 있는 이유도 관례를 사용했기에 가능하다.
    - data class 주 생성자에 들어 있는 프로퍼티에 대해서 컴파일러가 자동으로 componentN 함수를 만들어준다.
        - 구조 분해 선언은 각 변수를 초기화하기 위해 componentN 함수를 호출한다.
        - N은 구조 분해 선언에 있는 변수의 위치, 최대 5개의 원소까지 제공한다.
    - Map 자료구조를 사용할 때 key, value 상으로 구조분해 하여 순회할 수 있다.
        - iterator() 관례와 구조분해 관례(Map.Entry에 대한 확장함수 사용)를 사용해서 가능하다.
    - 위임 프로퍼티 : 객체가 직접 작업을 수행하지 않고 다른 도우미 객체가 그 작업을 처리하게 맡긴다.
      ```kotlin
          class Foo {
              var p : Type by Delegate()
          }
      ```
        - p 프로퍼티는 접근자 로직을 다른 객체에게 위임한다
        - 위임 관례를 따르는 Delegate 클래스는 getValue, setValue 메소드를 제공해야 한다.
        - by 키워드는 프로퍼티와 위임 객체를 연결한다.
    - by lazy() 사용한 프로퍼티 초기화 지연
        - 객체의 일부분을 초기화하지 않고 남겨뒀다가 실제로 그 부분의 값이 필요한 경우 초기화할 때 쓰이는 패턴
        - 초기화 과정에서 자원을 많이 사용하거나 객체를 사용할 때마다 꼭 초기화하지 않아도 되는 프로퍼티에 대해 지연 초기화 패턴 사용 가능하다
      ```kotlin
         class Person(val name: String) {
            private var _emails: List<Email>? = null
            val emails: List<Email>
                get() {
                    if(_email == null) {
                        _emails = loadEmails(this)
                    }
                    return _emails!!
                }           
         } 
      ```
        - _emails 프로퍼티는 값을 저장하고, emails 프로퍼티는 _emails에 대한 읽기 연산만을 제공한다.
        - 하지만 이러한 지연 초기화 프로퍼티가 많아지면 코드가 복잡해지고 thread safe 하지 않게 된다. -> 위임 프로퍼티를 사용해보자
      ```kotlin
        class Person(val name: String) {
            val emails by lazy { loadEmails(this) }
        }
      ```
        - lazy 함수는 기본적으로 thread safe 하다.
        - 하지만, 필요에 따라 동기화에 사용할 lock을 lazy 함수에 전달할 수도 있고, 다중스레드 환경에서 사용하지 않을 프로퍼티를 위해 lazy 함수가 동기화 하지 못하게 막을 수도 있다.

---

### 고차함수

- 고차함수 : 다른 함수를 인자로 받거나 함수를 반환하는 함수
- 함수 타입 정의 : 함수 파라미터의 타입을 괄호 안에 넣고, 그 뒤에 화살표를 추가한 다음 반환 타입을 지정하면 된다.
    ```kotlin
        (Int, String) -> Unit
        
        val sum: (Int, Int) -> Int = { x, y -> x + y }
        val sum = { x : Int, y : Int -> x + y } // 위와 동일하다
  
        // 함수 타입에 이름을 지정할 수 있다.
        // 파라미터 타입 검사시에 이름은 무시되기에 사용할 때 다른 이름으로 사용해도 되지만 가독성과 IDE 자동완성 기능을 제공받을 수 있다.
        fun performRequest(
            url: String,
            callback: (code: Int, content: String) -> Unit
        ){
            / ... /
        } 
    ```
- filter 고차함수 내부의 고차함수 확인해보기 (String.filter의 내부 구현)

    ```kotlin
      public inline fun String.filter(predicate: (Char) -> Boolean): String {
           return filterTo(StringBuilder(), predicate).toString()
      }
    
      public inline fun <C : Appendable> CharSequence.filterTo(destination: C, predicate: (Char) -> Boolean): C {
        for (index in 0 until length) {
            val element = get(index)
            if (predicate(element)) destination.append(element)
        }
        return destination
    }
    
    ```
    - predicate 라는 이름으로 함수를 받으며 filterTo는 StringBuilder와 판별하는 함수를 받아서 true일 경우에만 문자를 추가한다.
    - 사용 예시
      ```kotlin
          val message = "this is message"
          val result = message.filter { it!= ' ' }
         // result -> thisismessage
      ```
- 자바에서 코틀린 함수 타입 사용
    - 컴파일된 코드 안에서 함수 타입은 일반 인터페이스로 바뀐다 -> 함수 타입 변수는 FunctionN 인터페이스를 구현하는 객체를 저장한다.
    - FunctionN에서 N은 인자의 개수 ( Function0<R>, Function1<P1,R> , ... )
    - 각 인터페이스에는 invoke 메소드 정의 하나가 들어있다. -> invoke 호출하면 함수를 실행할 수 있다. (invoke 메소드 본문에 람다 본문이 들어간다.)
    ```kotlin
        // 코틀린
        fun process(f: (Int) -> Int) {
            println(f(42))
        } 
        
        // 자바 8 버전 이후
        process(number -> number + 1);
        
        // 자바 8 버전 이전
        process(
            new Function1<Integer,Integer>() {
                @Override
                public Integer invoke(Integer number) {
                    System.out.println(number);
                    return number + 1;
                }
            } 
        ) 
    ```
    - 디폴트 값 지정한 함수 타입 파라미터
        - Collection 의 joinToString 확장함수의 경우 Collection을 순회하면서 매번 원소의 toString을 호출해야 한다
            - 핵심 요소 하나를 제어할 수 없다는 단점이 있다.
            - 함수 타입의 디폴트 값을 추가하여 해결할 수 있다.
    ```kotlin
        fun <T> Collection<T>.joinToString(
            separator: String = ", ",
            prefix: String = "",
            postfix: String = "",
            transform: ((T) -> String)? = null // null이 될 수 있는 함수 타입 파라미터로 선언
        ) : String {
            val result = StringBuilder(prefix)
            
            for ((index, element) in this.withIndex()) {
                if(index > 0) result.append(separator)
                val str = transform?.invoke(element) // 안전한 호출로 함수 타입 파라미터를 호출
                    ?: element.toString() // 엘비스 연산자를 사용해 람다를 인자로 받지 않은 경우 처리
                result.append(str)            
            }
            
            result.append(postfix)
            return result.toString()
        }
    ```
    - 코드의 중복을 줄일 때에는 함수 타입을 사용하는 것이 도움된다.

---

- 람다의 부가 비용 없애기 -> inline 키워드
    - inline 변경자가 붙은 함수는 컴파일러가 그 함수를 호출하는 모든 문장을 함수 본문에 해당하는 바이트코드로 바꿔치기 해준다.
        - 함수를 호출하는 바이트코드 대신 함수 본문을 번역한 바이트 코드로 컴파일한다.
    - 함수 파라미터 중에 함수 타입인 파라미터가 있고 그 파라미터에 해당하는 인자(람다)를 함께 인라이닝함으로써 얻는 이익이 더 큰 경우에만 함수를 인라인 함수로 만들자
- Sequence 통해 성능을 향상시킬 수 있는 경우는 컬렉션 크기가 큰 경우 뿐이다.
- 람다를 인자로 받는 함수를 인라이닝하면 이익이 많다.
    - 인라이닝을 통해 없앨 수 있는 부가 비용이 크다.
        - 함수 호출비용을 줄일 수 있고 람다를 표현하는 클래스와 람다 인스턴스에 해당하는 개체를 만들 필요도 없어진다.
    - JVM은 함수 호출과 람다를 인라이닝해줄 정도로 똑똑하지 못하다.
    - 일반 람다에서는 사용할 수 없는 몇 가지 기능을 사용할 수 있다. (기능 중 하나는 non-local 반환이 있다.)
- 인라인 함수를 사용할 때는 해당하는 바이트코드를 호출지점에 복사해 넣어서 사용하기에 인라인 함수는 아주 작게 설정해야 한다.
- 자원 관리를 위해 인라인된 람다 사용
    - 자바의 try-with-resource 문은 autocloseable 객체를 예외가 발생하거나 자원 사용이 끝나면 정리해주는 기능을 제공한다.
    - 코틀린에서는 use 함수로 동일한 효과를 누릴 수 있다. ( closeable 자원에 대한 확장함수이며 람다를 인자로 받고 use 함수도 인라인 함수이다.)
        - use 함수 내에 return 값은 non-local return 이다.
        - 람다 안에서 return을 사용하면 람다로부터만 반환되는 게 아니라 그 람달를 호출하는 함수가 실행을 끝내고 반환된다.
        - non-local return : 자신을 둘러싸고 있는 블록보다 더 바깥에 있는 다른 블록을 반환하게 만드는 return문
        - `return 이 바깥쪽 함수를 반환시킬 수 있는 상황은 람다를 인자로 받는 함수가 인라인 함수인 경우 뿐이다.`
            - 인라인되지 않은 함수는 람다를 변수에 저장할 수 있고, 바깥쪽 함수로부터 반환된 뒤에 저장해둔 람다가 호출될 수 있다.
            - 그런 경우에는 return 실행시점이 바깥쪽 함수를 반환시키기엔 너무 늦은 시점일수 있다.
- 물론 인라인 람다에서도 로컬 return 할수도 있다.
    - 레이블 또는 함수 이름을 이용해서 local return
  ```kotlin
      people.forEach label@{
        if (it.name == "Alice") return@label
      } 
  
      people.forEach {
        if (it.name == "Alice") return@forEach
      }
  ```
- 무명함수 : 기본적으로 local return
    - 무명함수는 일반 함수와 비슷해보이지만 함수 이름이나 파라미터 타입을 생략할 수 있다는 차이점이 존재한다.
    - 람다식을 대신할 수 있으며 return을 처리하는 규칙이 일반 람다식과 다르기에 본문 여러곳에서 return하는 코드블록을 만들어야 할 때 람다식 대신 사용할 수 있다.
    ```kotlin
        fun lookForAlice(people: List<Person>) {
            people.forEach(fun (person) {
                if (person.name == "Alice") return // return은 가장가까운 함수를 가리키며 현재 상황에서는 무명함수를 의미한다.
            })
        }     
  
        people.filter(fun (person): Boolean {
            return person.age < 30
        })
        
        people.filter(fun (person) = person.age < 30)
    ```