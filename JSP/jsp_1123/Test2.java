package jsp_1123;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// jsoup-1.14.3.jar 파일을 이클립스에서 따로 추가해줘야 함. 

public class Test2 {

	// 최근 개봉 영화
	// 인기 영화
	// 빌보드차트
	// 날씨
	// 커뮤니티 댓글
	// 영웅 픽순
	// 1. 웹 크롤링
	// 2. DB에 저장
	// 3. DB에 저장된 데이터 Java에서 console로 출력
	public static void main(String[] args) {
		String url="https://comic.naver.com/index";
		// 크롤링대상 url지정
		
		try {
			Document doc=Jsoup.connect(url).get();
			//System.out.println(doc);
			
			Elements ele=doc.select("h6.title");
			//System.out.println(ele);
		
			Iterator<Element> itr=ele.select("a > span").iterator();
			while(itr.hasNext()) {
				System.out.println(itr.next().text());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
