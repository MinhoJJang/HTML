# Transaction

> 관련 블로그 
[What is Transaction?](https://mommoo.tistory.com/62)

- DB에서의 작업의 단위 or 관련된 작업들을 하나로 묶는 것 
- commit, rollback등을 활용하여 처리하는 것이 일반적이다. 
- commit은 트랜잭션을 완료시키는 작업이고, rollback은 트랜잭션을 취소하는 과정이다. 
- JDBC 등 다양한 DB 연동환경에서 자동 commit 모드가 지원된다. 그래서 따로 트랜잭션을 사용하지 않는다면 Auto commit 을 이용하게 된다. 

트랜잭션을 이용하기 위해서는 아래와 같은 과정을 거쳐야 된다. 
1. 자동 commit 모드 해제
2. 액션(비즈니스 메서드)들을 수행
3. 일련의 액션들이 모두 수행되면 commit()호출
4. 자동 commit모드로 다시 연결 

***

# Controller Part2 