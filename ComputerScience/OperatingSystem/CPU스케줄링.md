# 🦖 인프런 운영체제 공룡책 강의

## Section 4

### CPU 스케줄링

- 멀티 프로그래밍 시스템에서 여러 응용 프로그램의 프로세스들이 메모리를 점유하고 있기에 적절하게 작업을 진행해야만 한다.
- CPU burst(CPU를 사용하는 시간, running 상태), I/O burst(I/O작업을 기다리는 시간, waiting -> ready 상태)
- CPU burst가 큰 작업은 일부이고, 대부분 I/O burst time이 커 CPU scheduling이 중요하다.
- CPU scheduler : ready 상태에 있는 프로세스들에게 CPU를 할당해주는 역할
  - 다음 실행할 프로세스 선정 방식
    - FIFO Queue, Priority Queue, ...
 
- Non-preemptive (비선점형)
  - 프로세스가 CPU 사용을 반환할 때까지 사용하고 있는 방식
- Preemptive (선점형)
  - 스케줄러에 의해 강제로 프로세스를 변경할 수 있는 스케줄링

- CPU 스케줄링 결정사항
  - running -> waiting
  - running -> ready
  - waiting -> ready
  - terminated
  - 1번,4번의 경우 non-preemptive 하게 이동 (I/O작업을 위해 자발적으로 반환 또는 완료)
  - 2번,3번의 경우 preemptive 또는 non-preemptive 둘 다 적용 가능
- 스케줄링 방법을 생각할 때 지향해야 할 방향들
  - CPU 이용량 : CPU 사용량을 최대화하여 유지
  - 처리량 : 분당 프로세스 완료 수를 늘리자 
  - *실행에서 종료까지의 시간* : 프로세스가 시작되고 종료될 때까지의 시간을 최소화하자
  - *대기 시간* : 대기 시간을 최소화 하자 
  - 응답 시간 : 응답 시간을 최소화 하자

- Dispatcher 
  - CPU 스케줄러로부터 선택된 프로세스를 조작하는 모듈
  - dispatcher의 역할
    - context switching 을 담당
    - user mode로 모드 변경
    - 프로그램 실행을 위해 적절한 위치로 이동시켜 재개하는 것 
  - dispatch latency
    - 현재 실행중인 프로세스를 멈추고, 새로 실행할 프로세스를 복구한 뒤 재개시키는 과정

- CPU 스케줄링 방식
  - FCFS: First-Come, First-Served
    - CPU 요청 순서대로 프로세스 작업을 처리 
    - 순서에 따라 작업 효율에 큰 차이가 발생한다.
      - CPU burst time이 큰 프로세스와 작은 프로세스가 있다면 큰 프로세스가 앞으로 올수록 대기시간이 길어진다.
    - non-preemptive 방식
    - Convoy Effect (콘보이 효과)
      - CPU burst time이 큰 프로세스로 인해 뒤의 CPU burst time은 짧고 I/O bound가 큰 프로세스들이 대기시간이 길어져 CPU 성능이 떨어지는 결과를 초래한다.
  - SJF: Shortest Job First(SRTF: Shortest Remaining Time First)
    - CPU burst time이 작은 프로세스의 작업을 먼저 처리 (같다면 FCFS 처럼 처리)
    - next CPU burst를 정확히 예측하기는 어려워 SJF를 완벽하게 구현하기가 어렵다.
      - 과거의 측정된 값을 활용해 예측값을 활용하는 방식으로 유사하게 구현 가능
    - preemptive, non-preemptive 방식 구현에 따라 모두 사용 가능
    - SRTF는 arrive time 과 burst time을 고려해 적은 시간이 걸리는 작업 먼저 수행할 수 있어 SJF와 비교해 효율적일 수 있다.
  - RR: Round-Robin (시분할)
    - 시간주기에 따라 context switching, preemptive FCFS
    - time quantum 에 따라 context switch 횟수가 정해진다. (적절한 time quantum 설정이 핵심)
    - preemptive 방식
  - Priority-based
    - 우선순위에 따라 CPU 할당
    - preemptive , non-preemptive
    - preemptive -> SRTF , non-preemptive -> SJF
    - 기아현상 발생 가능성 존재
      - ready queue 에 있는 작업이 우선순위가 뒤쳐져 CPU 선점을 무한히 대기
      - 해결 방법 : 에이징
      - ready queue 에 오래 있는 작업일수록 aging으로 우선순위를 조절해 무한히 대기하는 상황을 방지
  - MLQ: Multi-Level Queue
    - 프로세스의 카테고리 큐를 구분하고 카테고리에 우선순위를 부여해 작업을 관리
  - MLFQ: Multi-Level Feedback Queue
    - MLQ에 우선순위에 의해 선점못하는 상황을 방지하기 위해 feedback을 도입 (에이징으로 기아현상을 방지하는 방식과 비슷)

- 현대 운영체제에서 스케줄링
  - 프로세스가 아닌 커널 스레드를 스케줄링 한다.
  - 그 중, user thread는 thread 라이브러리를 통해 관리되어 운영체제가 관여할 필요 없고, 커널스레드만 관리해주면 된다.