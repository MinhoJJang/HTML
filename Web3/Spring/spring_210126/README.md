# Dynamic SQL 

Mybatis에서 지원하는 Dynamic SQL은 SQL코드의 가독성을 올려준다. 

변경 전과 변경 후를 비교해보자. 

>변경 전 
```xml
   <select id="selectW" parameterType="board" resultMap="boardResult">
   	<![CDATA[
   		  SELECT * FROM BOARD WHERE WRITER LIKE #{searchContent} ORDER BY BID DESC
      ]]>
      <!-- select * from board where writer like #{searchContent} order by bid desc -->
   </select>
   
    <select id="selectT" parameterType="board" resultMap="boardResult">
   	<![CDATA[
   		  SELECT * FROM BOARD WHERE TITLE LIKE #{searchContent} ORDER BY BID DESC
      ]]>
      <!-- select * from board where writer like #{searchContent} order by bid desc -->
   </select>
```

>변경 후
```xml
<!-- Dynamic SQL -->
	 <select id="selectAll" parameterType="board" resultMap="boardResult">
   		SELECT * FROM newboard WHERE 1=1

   		<!-- 항상 참인 조건식을 달아서 where 까지를 해석할 수 있도록 1=1 같은 식을 해줌
   		그리고 AND를 통해서 식이 연결되도록 한다. 인터프리터 언어인 SQL은 한줄한줄에서 에러가 발생하면 실행되지 않기 때문이다. -->
   		<if test="searchCondition == 'title'">
   			AND TITLE LIKE CONCAT('%',#{searchContent},'%') 
   		</if>  		
   		<if test="searchCondition == 'writer'">
   			AND	WRITER LIKE CONCAT('%',#{searchContent},'%') 
   		</if> 
   		ORDER BY BID DESC
   </select>
```

BoardDAO도 변경된 SQL같이 바꿔준다. 
```java
package com.test.app.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.app.board.BoardVO;

//1. 상속
//2. @

@Repository("boardDAO")
public class BoardDAO5 {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.insertBoard", vo);
	}
	public void updateBoard(BoardVO vo) {
		mybatis.update("BoardDAO.updateBoard", vo);
	}
	public void deleteBoard(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteBoard", vo);
	}
	public List<BoardVO> selectAll(BoardVO vo){
		return mybatis.selectList("BoardDAO.selectAll",vo);
	}
	public BoardVO selectOne(BoardVO vo) {
		return (BoardVO)mybatis.selectOne("BoardDAO.selectOne", vo);
	}
}
```

## 오류 

> `AND TITLE LIKE #{searchContent}` 이 부분을 MySQL이 읽을 수 있도록 하는 법을 모르겠다

해결1: [DBMS 에서의 LIKE문](https://dongram.tistory.com/12)
해결2: 테이블 이름은 대소문자를 구별한다!!!!!!!!!!!!!!!!!!