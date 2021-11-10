# 반복문
  > while
```js
	while(조건){
		반복해서 실행할 코드
	}
```
  > for 문
```js
	for(초기화; 반복조건; 증감식){
		반복해서 실행될 코드
	}

	for(var i = 0; i<10; i++){
		document.write('HelloWorld<br>');
	}
```
  > break
   - 반복작업을 중단시킬때 사용

  > continue
   - 반복을 지속되게 한다.

# 함수
  > 함수란?
   - 함수(function)란 하나의 로직을 재실행 할 수 있도록 하는 것으로 코드의 재 사용성을 높여준다.
```js
	function 함수명(인자){
		코드;
		return 반환값;
	}
```
  > return
   - 함수 내에서 사용한 return은 return뒤에 따라오는 값을 함수의 결과로 반환한다.

  > 인자란?
   - 인자는 함수로 유입되는 입력 값을 의미하는데, 어떤 값을 인자로 전달하느냐에 따라서 함수가 반환하는 값이나 동작방법을 다르게 할 수 있다.

# 변수의 유효성 범위(scope)
 - 함수 밖에서 변수를 선언하면 그 변수는 전역변수가 된다. 
 - 전역변수는 애플리케이션 전역에서 접근이 가능한 변수다. 다시 말해서 어떤 함수 안에서도 그 변수에 접근 할 수 있다. 

  > var 변수 선언의 문제점
   - 변수를 한 번 더 선언했음에도 불구하고, 에러가 나오지 않고 각기 다른 값이 출력되는 것을 볼 수 있다. 이를 보완하기 위해 추가 된 변수 선언 방식이 let, const이다

  > let, const의 차이점
   - let : 선언, 재할당 가능하다.
```js
    let name = 'html';
    console.log(name);
    
    let name = 'javascript';
    console.log(name);
    
    name = 'javascript';
    console.log(name);

--> Uncaught SyntaxError: Identifier 'name' has already been declared
```
   - const : 선언, 재할당이 불가능. 최초의 할당만 가능. 재할당이 필요가 없는 상수에 사용한다.
```js
	const name = 'html';
    console.log(name);
    
    const name = 'javascript';
    console.log(name);

    name = 'javascript';
    console.log(name);

--> Uncaught SyntaxError: Identifier 'name' has already been declared
```
```js
    const name = 'html';
    console.log(name);
    
    name = 'javascript';
    console.log(name);
    
--> Uncaught TypeError: Assignment to constant variable at val2.html:15
```

# Object Model
 - 웹 브라우저의 구성요소들은 하나하나가 객체화되어 있다. 자바스크립트로 이 객체를 제어해서 웹브라우저를 제어할 수 있게 된다. 이 객체들은 서로 계층적인 관계로 구조화되어 있다. BOM, DOM은 이 구조를 구성하고 있는 가장 큰 틀의 분류하고 할 수 있다.

 - DOM : Document Object Model
 - BOM : Browser Object Model
 - javascript core