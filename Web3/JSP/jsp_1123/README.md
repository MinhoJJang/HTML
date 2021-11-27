# Crawling

Crawling 이란, 사전적으로 기어다니는 동작을 의미하는데, 웹 프로그래밍적 의미로는 WEB 상에 존재하는 CONTENTS를 수집하는 작업이다. 

일반적으로 크롤링은 HTML 페이지를 가져와서, HTML 혹은 CSS 부분을 [PARSING](https://na27.tistory.com/230) 하고,  필요한 데이터만 추출하는 기법이다. 

크롤링을 위해서는 웹의 데이터를 긁어오는 도구, 즉 라이브러리가 필요한데, Python의 경우 BeautifulSoup가 있고 selenium이라는 라이브러리도 있으며, 자바의 경우 Jsoup라는 HTML 파싱 라이브러리가 존재한다. 

그렇다면 어떻게 크롤링을 하는 것인지 직접 Jsoup을 통해 해보도록 하자. 

# Settings 

앞서 말했듯이, 크롤링에는 Jsoup 이라는 이름의 라이브러리가 필요하다. 따라서 그 라이브러리를 가져와서 인식시켜주어야 한다. 