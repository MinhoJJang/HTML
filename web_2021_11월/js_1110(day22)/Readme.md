# submit
  - submit은 폼의 정보를 서버로 전송하는 명령어인 submit 시에 일어나고
  form 태그에 적용된다. 

# change
  - change는 컨트롤의 값이 변경 되었을 때 발생하는 이벤트이다.
  - input(text, radio, checkbox), textarea, select 태그에 적용

# 마우스

  - 웹브라우저는 마우스와 관련해서 다양한 이벤트 타입을 지원한다.
    + click	: 클릭했을때 발생하는 이벤트
    + dbclick 	: 더블클릭했을때 발생하는 이벤트
    + mousedown	: 마우스를 누를 때 발생하는 이벤트
    + mouseup 	: 마우스버튼을 땔 때 발생하는 이벤트
    + mousemove : 마우스를 움직일 때 발생하는 이벤트               
    + mouseover	: 마우스가 엘리먼트에 진입할 때
    + mouseout	: 마우스가 엘리먼트에서 빠져나갈 때
    + contextmenu	: 컨텍스트 메뉴가 실행될 때

  - 키보드 조합
	  - 마우스 이벤트가 호출될 때 특수키(alt, ctrl, shift)가 눌려진 상태를 감지해야 한다면 이벤트 객체의 프로퍼티를 사용한다.
        + event.shiftKey
        + event.altKey
        + event.ctrlKey

  - 마우스 포인터 위치
	  + 마우스 이벤트와 관련한 작업에서는 마우스 포인터의 위치를 알아내는 것이 중요할 때가 있는데 이런 경우 이벤트 객체의 clientX와 clientY를 사용한다.