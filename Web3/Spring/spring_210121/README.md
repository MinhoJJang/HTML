# 다국어 처리 or 국제화 처리 + 템플릿 

언어별 페이지를 각각 만들면 유지보수가 불리해진다. 이때, 우리는 언어정보가 저장된 파일을 불러와서 다국어 처리를 해주는데, 그 파일을 메세지 파일이라고 하며 확장자는 .properties 이다. 

## 1. src/main/resource 하위에 message 폴더 생성 및 파일 생성 

이때 파일명은 ###_en.properties 같은 형식으로 해준다. 

## 2. messageSource_**.properties 채우기 

```r
# main.jsp
message.header.title=MAIN PAGE
message.header.hello=, HELLO! :D
message.header.logout=LOGOUT

message.body.search=SEARCH
message.body.table.num=NUM
message.body.table.title=TITLE
message.body.table.writer=WRITER
message.body.table.reg=REG
message.body.table.cnt=CNT

message.footer.board=INSERT BOARD
message.footer.member=MYPAGE

# board.jsp
```

참고로 한글의 경우 유니코드로 자동변환되어 작성된다. 

```r
# main.jsp
message.header.title=\uAC8C\uC2DC\uAE00 \uBAA9\uB85D
message.header.hello=\uB2D8, \uC548\uB155\uD558\uC138\uC694! :D
message.header.logout=\uB85C\uADF8\uC544\uC6C3

message.body.search=\uAC80\uC0C9\uD558\uAE30
message.body.table.num=\uAE00 \uBC88\uD638
message.body.table.title=\uAE00 \uC81C\uBAA9
message.body.table.writer=\uC791\uC131\uC790
message.body.table.reg=\uC791\uC131\uC77C
message.body.table.cnt=\uC870\uD68C\uC218

message.footer.board=\uAE00 \uC791\uC131\uD558\uAE30
message.footer.member=\uB9C8\uC774\uD398\uC774\uC9C0

# board.jsp
```

## 3. DispatcherServlet에 bean 등록

```xml
<bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
    <property name="basenames">
        <list>
            <value>message.messageSource</value>
        </list>
    </property>
</bean>
```

value 값을 저렇게 적어주면, message폴더 내의 표준 양식을 따르는 모든 다국어 파일에 대해서 자동으로 인식할 수 있다. 

또한, 템플릿도 추가하였다. 

## +오류 

login.do에서 영문모를 한글깨짐 현상이 발생한다. 추후 원인을 알아볼 예정. 

또한 delete board 기능이 제대로 동작하지 않는다. 

// TODO 
지금까지 해왔던 프로젝트를 반응형 웹 템플릿으로 옮기자 
