# WiFi_WEB
이 다이나믹 웹 프로젝트는 현재 자신의 위치 좌표를 기준으로 자신과 가장 가까운 공용 와이파이들을 보여주고, 자주 사용하는 와이파이를 북마크로 저장할 수 있는 간단한 토이프로젝트이다.
이 프로젝트를 통해 공공 Api를 활용한 Http 통신, RDB에서의 CRUD, 프론트와 백(서버)의 데이터 통신에 대한 내용들을 학습해보고 구현해볼 수 있었음.


## 활용한 기술스택 
- 서울시 공공API 활용 https://data.seoul.go.kr/dataList/OA-20883/S/1/datasetView.do
- MYSQL
- JAVA Maven Dynamic Web Project - JSP 
- Intellij - IDE


## ERD
<img width="643" alt="Mission1_ERD" src="https://user-images.githubusercontent.com/100022877/235304675-c5c4d8b1-2872-45b9-9dc0-8d6e1742e21a.png">

- WIFI_INFO 테이블 : 서울시가 API로 제공해주는 공용WIFI 정보를 저장하기 위한 테이블
- HISTORY 테이블 : 자신의 위치를 조회한 시점을 저장하기 위한 테이블
- BOOKMARK 테이블 : 특정 와이파이를 즐겨찾기로 설정하기 위한 테이블 
- BOOKMARK_GROUP 테이블 : 즐겨찾기 그룹 정보를 저장하기 위한 테이블


## Trouble shooting
https://velog.io/@dev_hong/Project-WiFi-%EC%9B%B9%EC%84%9C%EB%B9%84%EC%8A%A4-TroubleShooting
