# Text 객체
 ### 제공 API
  - appendData(value) : 해당 문자열 value 해당하는 문자열을 append해준다.
  - deleteData(start, end) : (0번부터 채번)start 부터 end 길이까지 삭제
  - insertData(start, value) : (0번부터 채번)start 부터 해당 value 삽입
  - replaceData(start, end, value) 
		: (0번부터 채번)start부터 end 길이까지에 value 교체
  - substringData(start, end) 
		: (0번부터 채번)start 부터 end 길이까지 문자열을 return

# 이벤트
 ### 이벤트란?
  - event는 어떤 사건을 의미한다. 브라우저에서의 사건이란 사용자가 클릭을 했을 '때', 스크롤을 했을 '때', 필드의 내용을 바꾸었을 '때'와 같은 것을 의미한다.

 ### onclick 이벤트
  - 사용자가 이 버튼을 클릭 했을 '때' 실행된다.

 ### inline
  - 인라인(inline) 방식으로 이벤트를 등록하는 방식은 이벤트를 이벤트 대상의 태그 속성으로 지정하는 것이다.

 ### 프로퍼티 리스너
  - 프로퍼티 리스너 방식은 이벤트 대상에 해당하는 객체의 프로퍼티로 이벤트를 등록하는 방식이다. 인라인 방식에 비해서 HTML과 Javascript를 분리할 수 있다는 점에서 선호되는 방식이다.

 ### 이벤트 객체
  - 이벤트가 실행된 맥락의 정보가 필요할 때는 이벤트 객체를 사용한다.
  
 ### addEventListener
  - addEventListener방식은 이벤트를 등록하는 가장 권장되는 방식이다. 이 방식을 이용하면 여러개의 이벤트 핸들러를 등록할 수 있다.
