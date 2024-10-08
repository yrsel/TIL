# IT 좀 아는 사람

### 닐메타, 아디티야아가쉐, 파스디트로자 지음 / 김고명 옮김

---

## 앱경제

- 3대 모바일 운영체제
    - 안드로이드, IOS , 카이OS
    - 카이OS
        - 인도에서 지오라는 통신사에서 저렴한 요금제와 무료핸드폰(보증금은 지불)으로 많은 인도인들에게 터치스크린을 지원하지않는 피처폰을 제공하는 지오폰의 운영체제 ( 피처폰을 지원하기 위한 신종 운영체제 )

          오픈소스인 파이어폭스OS 코드를 활용 ( 웹에서는 가볍지만 앱에서는 무겁다는 단점이 있지만 피처폰은 앱과 인터넷 사용을 지원하기에 적합했다. )

- 무료 앱이 지속될 수 있는 이유
    - 부분유료화 ( 인앱결제 ,유료구독 )
        - 유료구독 : 단순히 앱을 구매하는 개념보다 앱과 장기적 관계를 맺는다는 개념으로 고객의 생애 가치가 더 높다.
        - 파레토법칙 ( 고객 중 20%에서 80% 수익이, 고객 80%에게서 20% 수익이 발생 )
    - 광고
        - 광고 경매 : 노출당 광고료 과금(PPI,Pay-Per-Impression / 밀리(1,000회) 당 과금,Cost-Per-Mille ) , 클릭당 광고료 과금(CPC,Cost-Per-Click ,
          Pay-Per-Click)
        - 타깃광고 : 고객의 사용데이터를 활용해 알맞은 광고를 해준다. ( TV,라디오의 광고와의 차이점 )
            - 실리콘밸리에서의 명언 : `네 주머니에서 나가는 돈이 없다면 네가 곧 상품이다.`

        - 클릭률이 저조한 배너광고가 사라져가는 추세고 유명 사이트애서 자신들의 콘텐츠인것처럼 광고글을 작성해주는 협찬콘텐츠가 클릭률을 높일 수 있어 광고효과가 좋아 요즘 많이 사용된다.
            - 현실과 혼돈을 줄 수 있다는 단점이 존재하며 사용자가 네이티브 광고에 실망감이나 배신감을 느낀다는 반응이 발생한다. 하지만 배너광고에 비해서는 유익하다는 반응이 있기도해서 광고마다의 장단점이
              있다.
    - 수수료 ( 중개 수수료 )
        - 서비스 수수료 ( 에어비앤비 결제 수수료, 우버 결제 수수료, 아마존 판매수수료 , … )
    - ‘선 성장 후 수익화’ 전략
        - 무료로 서비스를 제공하고 사용자를 늘리고 몸집이 커지면 수익화 모델을 통해 수익 얻기
    - 다른 회사에게 인수되기

## 인터넷

- http://, https:// → 프로토콜
    - 브라우저가 어떤 방식으로 웹사이트에 접속해야 하는지 알려주는 역할
- `http://google.com` → 도메인
    - 고유한 이름
- 도메인 이름으로는 컴퓨터가 인식할수가 없다  
  → 도메인 네임 서비스(DNS)를 통해서 도메인과 매핑되어 있는 IP를 확인해서 컴퓨터가 인식  
  → DNS 에 매핑된 IP를 못찾는다면 인터넷 서비스 제공자(ISP)에게 IP 주소를 묻는다
- 획득한 주소를 이용해 url로 이동하면 url 요청을 받는 서버에서 화면에 대한 정보를 반환(response)해준다.
- TCP가 다수의 패킷으로 소분한 후 패킷에 이름을 붙인다.
    - DNS를 통해 IP주소를 찾는다.  
      각 패킷들은 짧은 연결로들을 경유(홉 hop)하며 여러 경유지를 거쳐 목적지 IP에 도달한다.   
      패킷이 사용자에게 도달하면 TCP가 원래 순서대로 재결합하고 빠진 패킷이 있다면 재전송 요청을 보낸다.
- IP를 통해 전달되는 정보는 장거리 케이블들을 타고 이동한다.
  많이 사용되는 케이블 :  광케이블  
  1과 0의 조합이 케이블을 타고 이동되며 1과 0의 조합들이 짧은 빛 신호로 전환하여 광섬유케이블을 통해 전송된다.

## 클라우드 컴퓨팅

과거에는 노트북에만 파일을 저장하는 방식으로 노트북 분실 혹은 하드드라이브 파손 시 데이터가 손실되었다.  
현재는 클라우드 서비스를 통해 인터넷에 연결된 서비스에서 해당 서비스에 로그인만 한다면 저장해놓은 파일을 확인할 수 있다.

> 이렇게 앱과 파일을 개인의 컴퓨터가 아닌 온라인에 저장하는 방식을 `클라우드 컴퓨팅`이라고 부른다.

- 예시) 지메일을 통해 웹에서 이메일을 읽고 쓰기
    - 스포티파이를 통해 음원을 다운받지 않고 인터넷에서 듣기
    - 아이폰은 문자메시지와 파일을 애플의 아이클라우드에 저장되기에 아이폰을 바꿔도 쉽게 복원할 수 있다. (저장한도 초과시 과금이 필요하기는 하다.)
    - 이러한 데이터들은 데이터센터에 저장되어 있다.

- 클라우드 컴퓨팅의 장점
    - 어디서든 인터넷만 있다면 확인 할 수 있다는 장점과 데이터가 손실될 위험이 적다는 장점
- 클라우드 컴퓨팅의 단점
    - 보안 : 데이터가 저장된 서버가 해킹되어 데이터가 유출될 수 있다.
        - 그렇기에 데이터센터의 보안은 엄청 삼엄하게 유지되고 있다. (보통은 개인 컴퓨터보다 클라우드에 저장된 정보가 더 안전하다고 볼 수 있다.)
    - 사생활 침해의 위험성 : 개발자가 서버관리하면서 데이터를 접근할 우려
    - 인터넷 접속 관련 : 인터넷 접속할 수 없는 경우 사용이 제한되는 경우가 다분하다.

### 어도비의 포토샵

- 과거에는 영구 라이센스를 판매하여 포토샵 기능을 판매하였다.
- 2013년에 어도비는 포토샵을 무료로 다운 받을 수 있지만 계속 사용하려면 구독 서비스에 가입해서 사용하게 하는 SaaS로 전환하였다.
    - SaaS : 소프트웨어를 대여해주는 서비스형 소프트웨어
    - 라이선스키를 발급해주고 매번 유효한 라이선스인지 확인한다.  
      과거에는 한번 판매할때마다 돈이 들어왔지만 현재는 매달 구독료가 들어오고 라이선스를 매번 확인하니 불법 복제와 같은 문제를 해결할 수도 있었다.  
      또한 인터넷 접속을 통해 포토샵을 사용하므로 수시로 업데이트를 제공하고 버그를 고칠 수 있어 고객만족도도 높이며 보안 문제가 한층 신속하게 해결되었다.
      ( 애자일 개발론을 적용할 수 있게 되었다. )
- 드롭박스(추가이용료로 서버 저장공간을 대여), 스포티파이(월이용료로 무제한 음악 제공) 등등 SaaS 앱들의 공통점은 웹 브라우저를 통해 이용할 수 있고 데이터가 기업 서버에 저장된다.

### 아마존 웹 서비스

- 클라우드 컴퓨팅 서비스 중 하나 , AWS에서 인기있는 것은 EC2와 S3  
  EC2 : 아마존 서버에서 앱의 코드를 실행할 수 있는 서비스  
  S3 : 아마존 서버에 앱의 데이터를 저장할 수 있는 서비스
  아마존닷컴 웹사이트도 S3와 EC2 기반
- AWS로 서버를 대여할 경우 업데이트,보안을 포함해 유지보수 문제를 책임져준다.
    - 세계 곳곳의 데이터센터에 앱과 데이터를 복사해두기 때문에 일부 데이터 센터가 파괴되어도 웹사이트나 앱 사용에 문제가 없다.
- AWS, 마이크로소프트 애저(Azure),구글클라우드 플랫폼 처럼 앱 구동용 서버를 빌려주는 서비스를 `IaaS` 라고 한다.
- `PaaS` : 데이터베이스, 고급 분석 도구, 운영체제 등 유용한 기능 제공
    - PaaS를 사용하면 클라우드에 웹사이트를 구축하기 더 쉬워진다.
    - 대표적인 예로는 Heroku(헤로쿠)라는 서비스 가 있다.

- 클라우드 컴퓨팅 서비스 예시
    - SaaS는 식당 , 종업원에게 원하는 요리를 주문하면 가져다준다.
    - IaaS는 임대형 주방, 공간만 대여해주기 때문에 재료 , 조리도구를 가져와서 직접 조리해야 한다.
    - PaaS는 SaaS와 IaaS 사이에 있으며, 재료와 레시피를 넘기면 대신 조리해준다.

- 넷플릭스가 자체 서버 운용하다가 AWS로 이주한 이유
    - 탄력성 : 최대사용량 맞게 서버를 갖추고 있어야했지만 AWS를 통해 시간대별로 컴퓨터자원 배정량을 조절해준다.
    - 확장성 : AWS가 자동으로 사용자 증가에 맞춰 서버를 증설
    - 중복성 : 클라우드는 동일한 정보나 코드를 여러 곳에 복사해놓기에 몇대 고장나도 대체할 컴퓨터가 존재한다.

- AWS 엔지니어의 오타 하나로 결제 시스템에 발생한 문제
    - 결제 시스템 문제를 해결하기 위해 AWS 서버 몇 대를 중지시키는 명령어를 입력하는 과정에서 실수로 한 글자를 잘못 쳐서 상당수의 서버가 중지되는 바람에 S3를 재시작해야만 하는 사태가 발생했다.

      클라우드가 안전하다고 할지라도 이러한 예기치 못한 상황이 발생할수도 있다.

      이럴 경우 클라우드 서비스의 다운 사태에 대비해 자체 서버를 앱을 구동하는 방법 (온프레미스)도 있지만 온프레미스 시스템과 클라우드 시스템의 다운율을 비교해보면 온프레미스 시스템이 3.5배에 달할만큼
      다운율이 높았다.

## 빅데이터

- 데이터의 중요성이 커진다.  
  → 많은 기업에서 고객의 모든 데이터를 활용해 추천 알고리즘을 구성

- 구글 : 맵 리듀스 알고리즘
    - 빅데이터 도구인 ‘하둡’도 맵 리듀스 사용
    - 슈퍼컴퓨터가 아닌 표준 크기의 여러 서버들에 데이터 분산해서 고속으로 처리
- 데이터 소유의 장단점
    - 소비자 → 단점 : 과도한 개인 정보 수집에 대한 불편함, 개인정보 유출에 대한 우려  
      → 장점 : 맞춤추천으로 시간과 돈을 절약할 수 있음

## 해킹과 보안

- 랜섬웨어 ( 예전, 워너크라이 악성코드가 랜섬웨어 일종 )
    - 컴퓨터에 침투해 파일을 암호화 설정 후, 사용자에게 돈을 갈취 ( 복호화 키에 대한 금액 )
- 보안을 위해 주기적인 소프트웨어 업데이트 필요하다.
- 구글 크롬OS에서는 각 탭이 샌드박스에서 돌아가기에 웹페이지의 내용이 컴퓨터의 다른 부분에 접근하지 못한다.

### 딥웹과 다크웹

- 딥웹 : 구글 검색으로 찾을 수 없는 인터넷상의 정보
- 다크웹 : 딥웹 중에서 통신 암호화, IP주소 익명화가 가능한 특수 SW를 이용해서만 접근 가능한 웹
    - .onion 으로 끝나고 긴 url을 갖고 있고 특수 소프트웨어를 이용하지 않으면 차단 당하는 특징
    - Tor 라고 하는 소프트웨어를 이용해 접근 ( The Onion Router ) : 여러 라우터를 거치며 중간 라우팅 과정에서는 계속 암호화된 상태로 데이터를 전달하며 최종 도착지에만 정상 링크로 전달된다.
  - 실크로드 : 중앙집중식 서버를 두고 암거래가 이루어졌던 다크웹  , 구매자와 판매자 사이에서 중재역할을 하였다.
  - 오픈바자 : 탈중앙화된 서버를 두고 판매자와 구매자가 직접 1:1로 거래를 진행 , 폐쇄하기 위해서 오픈바자 소프트웨어를 사용하는 모든 컴퓨터를 압수해야만 없앨 수 있기에 해체시키기가 너무 어렵다
  - 다크웹의 합법적 이용
      - 정부 규제로 인해 이용이 어려운 국가에 경우 다크웹을 이용하여 서비스 접근 가능
      - 사용자의 타깃광고를 위한 이용내역 추적하는 소프트웨어를 우회하는 용도로 사용
      - 내부고발자나 신원을 밝히기 어려운 사람과 안전하게 대화를 하는 용도로 사용
  - 비트코인 → 구매자와 판매자의 신원을 보호해주고 정부 소유가 아니지만 다크웹에 속하지는 않는다.
- 모든 데이터 전송시에는 암호화를 진행해서 정보유출을 방지를 위해 노력한다. → HTTPS 를 사용하는 이유
    - 하지만, 정보를 해독할 수 있다는 점
    - 그렇기에 왓츠앱에서는 종단간 암호화(end to end encryption ) 를 도입해서 송신자 수신자 외에는 정보를 해독할 수 없는 기술을 도입해 개인정보를 중시하는 사람들에게 환호를 받았다.
      - 종단간 암호화 방식 → 비대칭 암호화, 공개키 암호화 이라고 부른다.
    - 공개키와 개인키가 존재하며, 모든 메시지는 공개키로 암호화되고 수신자의 개인키를 갖고 있어야만 복호화를 할 수 있다.
    - 하지만 이러한 보안을 유지할 수 있는 장점을 범죄, 테러리스트도 사용할 수 있어 단점이 되기도 한다.
- 해커가 가짜 와이파이 네트워크로 정보 탈취하는 방법
    - 공개 와이파이와 비슷한 이름으로 설정하여 가짜 와이파이 네트워크로 접속을 유도하여 정보 탈취
    - 이전에 접속했던 네트워크에 자동으로 접속한다는 허점을 노려 컴퓨터의 네트워크 리스트를 읽어 이전에 접속했던 네트워크로 인식하게 하는 방법
    - HTTPS 인데도 정보 탈취되나 ? → SSL스트립 이라는 소프트웨어를 이용하면 HTTPS가 아닌 HTTP로 통신하게 변경시켜서  HTTPS를 사용하는 줄 알지만 HTTP를 사용 중  
      → 정보를 가로채 로그인 가능 ( 중간자 공격 )  
      → 이제는 웹브라우저가  SSL스트립 해킹 의심 시 , 사용자에게 경고 창 등으로 알려준다.
- 중간자 공격이 통하는 이유 : 와이파이가 보안성이 약하기 떄문에  
  → VPN(가상사설망)을 이용하여 종단간 암호화를 통해 보안을 강화 할 수 있다. ( 공개 네트워크르 비공개 네트워크로 전환 해준다 )

## 하드웨어와 로봇
크키 측정 단위 : bit → Byte → KB → MB → GB → TB → PB(페타) → EB(엑사)

- CPU ( 중앙처리장치 ) : 연산작업 처리
    - 연산을 수행하는 코어들의 집합체
    - 초당 수행 가능한 계산횟수 : 클럭 속도 (단위 : GHz , 1초에 10억회 계산 가능)
- 저장장치 ( 장기기억장치 )
    - 하드드라이브 : 자성물질로 코팅된 원형 철판과 arm으로 구성되어 있고 철판이 회전하면 암이 움직이며 코팅층에 정보를 기록하거나 기존 정보를 읽는다.
    - SSD(solid state drive) : ‘셀’ 이라는 작은 칸이 무수히 배열된 구조 ( 플래시 메모리 )
        - USB,SD카드도 플래시메모리
    - 하드드라이브 vs SSD
        - 하드드라이브 : 원판과 암이 움직이는 구조로 빨리 망가지고 소음 발생, 무겁고, 전력을 많이 소모한다
        - SSD :움직이는 부품이 없기에 튼튼하고 조용하고 가볍고 효율적이다 , 전기신호만 쏘면 되기에 속도도 빠르다
        - 과거에는 SSD가 하드드라이브에 비해 용량 대비 비쌌지만, 요즘에는 SSD가 보편화되면서 많은 물량으로 가격이 비슷해졌다.
        - 휴대 전화, 태블릿, 카메라에서는 플래시 메모리만 사용한다.
- 램 ( 단기기억장치 )
    - 휘발성이고 빠른 속도를 보여준다.
    - 램이 부족하면 컴퓨터가 하드드라이브나 SSD의 공간을 빌려(swap공간) 추가 램처럼 사용한다.
        - 하지만 램을 직접 사용하는 것보다 속도가 느리다.
    - 램을 많을수록 좋지만, 많다고 무조건 성능이 향상되는 것은 아니다 (CPU 문제 , 성능을 다 활용할 만한 작업이 없을 경우 등등 )

- 배터리 이슈
    - 휴대폰은 오래쓸수록 리튬이온 배터리의 성능이 떨어진다.
    - 휴대폰을 충전할때마다 ‘충전 사이클’이 증가하는데 일정 횟수 초과시 배터리가 현저히 빨리 소모된다.
    - 모바일 앱, 운영체제에서 요구하는 전력량은 갈수록 커지고 있고 사용할 수록 배터리 용량은 줄어들 수 밖에 없기에 배터리 지속시간은 나빠질수 밖에 없다.
        - 최대 전력 사용량을 낮추어 배터리 지속 시간을 개선할 수 있다.

- 휴대폰 지문인식
    - 광학식 스캐닝 : 초소형 카메라로 지문의 사진을 찍는 방식
        - 볼록한’마루’는 검은색, 오목한‘골’은 흰색으로 내부 데이터베이스와 비교하여 일치 여부 판단
        - 사진을 찍는 방식이여서 지문 사진으로 암호를 풀수 있는 등 보안이 약하다
    - 정전식 스캐닝 : 커패시터(초소형 배터리) 가 촘촘히 박힌 센서를 이용하는 방식
        - ‘마루’가 닿는 커패시터만 정전 용량이 증가하고 ‘골’이 닿는 커패시터는 정전 용량이 그대로다.
        - 정전 용량 패턴을 토대로 내부 데이터베이스에 저장된 지문과 일치여부를 판단한다.
        - 광학식 보다는 뛰어나지만, 지문사진을 플라스틱에 새겨 암호를 풀수 있는 등 완벽하지는 않다.
    - 홍채인식 , 생체인식
    - 모두 완벽할 수는 없다.

- 삼성페이의 작동원리
    - NFC 기술 (near field communication)
    - NFC칩이 장착된 기기끼리 접촉하면 소량의 정보가 교환된다.
    - NFC는 전력 소모가 거의 없고 ‘패시브’NFC 기기는 전원마저 필요 없다.
    - 패시브NFC(교통카드)와 NFC칩이 내장된 단말기에 대면 양 칩 통신을 통해 정보 교환
    - NFC를 통한 결제시, 데이터는 암호화되어 카드사에서만 토큰 정보를 확인하여 대금을 청구한다.
    - NFC 칩이 내장된 핸드폰만 있다면 주차요금 결제, 다양한 정보 제공 등 다양하게 사용할 수 있다.

- 포켓몬GO (증강현실)
    - 크라우드 소싱을 통해 위치정보가 축적되고, 휴대폰 내장 시계를 통한 시간 확인, 지리 데이터를 통해 ‘기후, 식생,지질,석질’ 을 기준으로 지형을 분류하여 다양한 공간에 적합한 포켓몬을 나오게 설정할 수 있었다.
    - 가속도계, 나침반, GPS를 통해 사용자 움직임이 감지되면 그에 맞춰 포켓몬도 움직인다.

- 아마존의 프라임나우 (다양한 상품을 1시간 이내에 배달하는 서비스)
    - 각 지역의 프라임 회원 데이터를 토대로 그곳의 물류창고에 어떤 상품의 재고가 필요한지 파악한다.
    - 주문처리센터 라고 불리는 물류창고에서 로봇들이 필요한 상품의 위치를 파악해서 상품의 선반을 가져오면 ‘피커(picker)’ 라고 하는 직원이 선반에서 상품을 꺼낸다.
    - 로봇은 알고리즘을 통해 최적의 동선으로 필요한 제품을 찾아온다.
- 아마존의 프라임에어 (드론을 통해 배달하는 서비스 , 고도 제한, 정부규제 등으로 아직은 활성화 되지 않았지만 나중이 기대된다.)

## 사업적 판단

- 무료 와이파이 제공
    - GPS 시스템에서 사용되는 삼각측량 기술을 활용하여 3개의 와이파이 라우터가 교차하는 지점에 있는 휴대폰의 MAC주소를 이용하여 사용자를 특정하고 사용자의 위치를 파악하여 사용자의 위치 데이터와 매장들을 조합하여 효율적인 판매전략을 세울 수 있도록 돕는다.
    - 와이파이 이용시, 가입을 요구해 이메일 등 정보를 받아 데이터를 쌓거나 판매를 위해 쿠폰등을 제공하는 등 서비스 전략으로 활용할 수 있다.
- 아마존의 아마존 프라임 사업 (수익은 최소여도, 매출을 최대로  / 같은 사업군 경쟁자는 알아서 몰락)
- 우버의 자율주행 (사업모델상 항상 적자, 기사 비용문제를 줄일 수 있는 방법 → 자율주행)
- 마이크로소프트의 링크드인 인수 (비즈니스 시장 접수, 경쟁사에서 먼저 인수하는 것을 대비 + 자사 서비스와의 시너지 효과, 많은 비즈니스용 데이터 획득)
- 페이스북의 인스타그램 인수 (모바일 사진 시장의 성장성을 보았음)
- 페이스북의 와츠앱 인수 (개발도상국 등 페이스북을 사용하지 않고 와츠앱을 주로 이용하는 국가들의 데이터 획득 및 페이스북의 광고를 와츠앱에도 적용해 수익성, 방대한 양의 사진 데이터로 시장 영향력 확보)

## 신흥국
- 서양 IT 기업들이 진출하려는 나라 5군데의 어려움
- 아프리카, 라틴아메리카, 동남아시아, 인도, 중국
- 중국
    - 만리방화벽(중국 정부가 인터넷상 정보가 자국 국경을 넘나드는 것을 제약하기 위해 만든 규제 집합체)
    - IT기업이 사용자 데이터를 정부에 제출하도록 법으로 강제되어 있다.
    - 위챗(병원 예약, 택시 호출, 대금 결제 등등 올인원 앱, 중국에서 9억명의 사용자)
        - 다양한 기능 제공(위챗페이 → 단톡방에 선착순 돈뿌리기, 중국문화인 빨간봉투에 돈 주는 것을 모바일로 재현, 송금을 받으려면 가입해야 되는 것, .. (카카오도 여기서 참고한듯))
        - 스마트폰 도입과 같이 성장
        - 정부와의 관계(데이터를 암호화하지 않고 정부에 넘겨줌) → 전자신분증시스템으로 확장 가능하게 된 계기
    - QR코드 결제의 활성화
    - 알리바바, 텐센트
- 인도
    - PC 세대를 뛰어넘고 모바일 시대로 바로 직행(10억대 이상의 스마트폰 보급)
    - 페이스북, 와츠앱, 구글 경량버전인 구글고, 모바일결제 앱 구글테즈 등 경량버전의 앱들과 아마존 프라임이 인도시장에서 성장하고 있다.
    - 백만명 이상이 사용하는 언어 종류가 29개나 되고, 데이터 사용량을 줄여야하고, 글을 읽어주는 기능(문맹률이 높아서) 이러한 기능이 중요하다.
- 동남아시아(인도네시아,태국,필리핀 등)
    - 인터넷 인구가 세계에서 세번째로 많다.
    - 인도에서 개발도상국 밑그림을 그리고 제품이 인기있으면 동남아시아에도 진출하는 분위기
    - 서양 IT 기업의 최대 승부처는 전자상거래, 전세계 IT 대기업들의 격전지가 될 것 이다.
- 라틴아메리카
    - 브라질은 인터넷 사용자 97%가 SNS 이용, 안드로이드와 와츠앱이 대세
    - 인터넷 인프라의 낙후가 최대 난점
- 아프리카
    - 인터넷 인프라가 열악해서 인터넷이 선진국에 비해 4배나 느리다.
    - 스마트폰 보다 피처폰이 인기다.
    - 페이스북에서 인터넷 접속성 개선을 위해 투자하고 무료 인터넷 제공에 힘쓰고 있지만 무료 접속 가능한 사이트가 극히 일부로 한정되어 있다.
    - 피처폰으로 수업료, 대출, 월세 지불 등등 모바일 결제는 활성화 되어 있다.
    - M페사 라고 케냐 통신사 사파리콤에서 제공하는 문자 기반 송금 서비스가 존재한다.