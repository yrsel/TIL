### [Medium Article - Myungpyo Shim](https://myungpyo.medium.com/%EC%BD%94%EB%A3%A8%ED%8B%B4-%EA%B3%B5%EC%8B%9D-%EA%B0%80%EC%9D%B4%EB%93%9C-%EC%9E%90%EC%84%B8%ED%9E%88-%EC%9D%BD%EA%B8%B0-part-5-dive-1-cc7ee8780c35)

### 시나리오

- 안드로이드 앱에서 사용자 설정을 동기화 하는 기능을 만들고자 한다.
- 사용자 설정은 로컬에만 저장, 필요한 순간마다 원격 설정을 가져와서 업데이트
- 로컬에는 원격에 없는 설정을 추가할 수 있지만, 원격과 동기화되는 설정은 로컬에서 변경할 수 없다.
    - 예제를 간단히 하기 위해서

- main : UI 역할
- ViewModel : 안드로이드 ViewModel을 상속하지 않고 간단하게 ViewModel 기능만 수행
- UserSettingRepository : DataSource 추상화 기능
- LocalUserSettingDataSource : 로컬 데이터 소스
- RemoteUserSettingDataSource : 원격 데이터 소스 