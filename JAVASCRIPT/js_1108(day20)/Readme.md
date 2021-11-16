# Location 객체
 Location 객체는 문서의 주소와 관련된 객체로 Window 객체의 프로퍼티다. 이 객체를 이용해서 윈도우의 문서 URL을 변경할 수 있고, 문서의 위치와 관련해서 다양한 정보를 얻을 수 있다.

# DOM 제어 대상을 찾기
 문서(document)를 자바스크립트로 제어하려면 제어의 대상에 해당되는 객체를 찾는 것이 제일 먼저 할일이다. 문서 내에서 객체를 찾는 방법은 document 객체의 메소드를 이용한다.

 ### document.getElementsByTagName
  - 인자로 전달된 태그명에 해당하는 객체들을 찾아서 그 리스트를 NodeList라는 유사 배열에 담아서 반환한다. NodeList는 배열은 아니지만 length와 배열 접근연산자를 사용해서 엘리먼트를 조회할 수 있다.

 ### document.getElementsByClassName
  - class 속성의 값을 기준으로 객체를 조회할수도 있다.

 ### document.getElementsById
  - id값을 기준으로 객체를 조회한다. 성능면에서 가장 우수하다.



