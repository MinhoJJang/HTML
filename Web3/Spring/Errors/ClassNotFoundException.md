# java.lang.ClassNotFoundException: org.springframework.web.filter.CharacterEncodingFilter 

(2021/12/28)

## 해결법 

Project > properties > Deployment Assembly > add button > java Build Path Entries  선택 그리고 Next

Maven dependencies 선택 
Maven Dependencis -> WEB-INF/lib가 새로 생성

재실행 해보면 정상적으로 작동됨. 

## 전체 오류로그 & 정상적 실행 로그 

```r
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: 서버 버전 이름:    Apache Tomcat/8.5.72
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: Server 빌드 시각:  Oct 1 2021 15:15:33 UTC
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: Server 버전 번호:  8.5.72.0
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: 운영체제 이름:     Linux
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: 운영체제 버전:     5.11.0-43-generic
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: 아키텍처:          amd64
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: 자바 홈:           /usr/lib/jvm/java-11-openjdk-amd64
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: JVM 버전:          11.0.13+8-Ubuntu-0ubuntu1.20.04
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: JVM 벤더:          Ubuntu
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: CATALINA_BASE:     /mnt/18b3ea8d-ef9b-4057-be1e-87840846fb20/JMH_WEB3/Spring_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: CATALINA_HOME:     /mnt/18b3ea8d-ef9b-4057-be1e-87840846fb20/apache-tomcat-8.5.72
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: 명령 행 아규먼트:  -Dcatalina.base=/mnt/18b3ea8d-ef9b-4057-be1e-87840846fb20/JMH_WEB3/Spring_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: 명령 행 아규먼트:  -Dcatalina.home=/mnt/18b3ea8d-ef9b-4057-be1e-87840846fb20/apache-tomcat-8.5.72
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: 명령 행 아규먼트:  -Dwtp.deploy=/mnt/18b3ea8d-ef9b-4057-be1e-87840846fb20/JMH_WEB3/Spring_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps
12월 28, 2021 8:15:03 오후 org.apache.catalina.startup.VersionLoggerListener log
정보: 명령 행 아규먼트:  -Dfile.encoding=UTF-8
12월 28, 2021 8:15:03 오후 org.apache.catalina.core.AprLifecycleListener lifecycleEvent
정보: APR 기반의 Apache Tomcat Native 라이브러리의 예전 버전 [1.2.23](이)가 설치되어 있습니다. Tomcat은 최소 버전으로서 [1.2.30]을(를) 추천합니다.
12월 28, 2021 8:15:03 오후 org.apache.catalina.core.AprLifecycleListener lifecycleEvent
정보: APR 버전 [1.6.5]을(를) 사용한, APR 기반 Apache Tomcat Native 라이브러리 [1.2.23]을(를) 로드했습니다.
12월 28, 2021 8:15:03 오후 org.apache.catalina.core.AprLifecycleListener lifecycleEvent
정보: APR 용량정보들: IPv6 [true], sendfile [true], accept filters [false], random [true].
12월 28, 2021 8:15:03 오후 org.apache.catalina.core.AprLifecycleListener lifecycleEvent
정보: APR/OpenSSL 설정: useAprConnector [false], useOpenSSL [true]
12월 28, 2021 8:15:03 오후 org.apache.catalina.core.AprLifecycleListener initializeSSL
정보: OpenSSL이 성공적으로 초기화되었습니다: [OpenSSL 1.1.1f  31 Mar 2020]
12월 28, 2021 8:15:03 오후 org.apache.coyote.AbstractProtocol init
정보: 프로토콜 핸들러 ["http-nio-8022"]을(를) 초기화합니다.
12월 28, 2021 8:15:03 오후 org.apache.tomcat.util.net.NioSelectorPool getSharedSelector
정보: Using a shared selector for servlet write/read
12월 28, 2021 8:15:04 오후 org.apache.catalina.startup.Catalina load
정보: Initialization processed in 522 ms
12월 28, 2021 8:15:04 오후 org.apache.catalina.core.StandardService startInternal
정보: 서비스 [Catalina]을(를) 시작합니다.
12월 28, 2021 8:15:04 오후 org.apache.catalina.core.StandardEngine startInternal
정보: 서버 엔진을 시작합니다: [Apache Tomcat/8.5.72]
12월 28, 2021 8:15:04 오후 org.apache.jasper.servlet.TldScanner scanJars
정보: 적어도 하나의 JAR가 TLD들을 찾기 위해 스캔되었으나 아무 것도 찾지 못했습니다. 스캔했으나 TLD가 없는 JAR들의 전체 목록을 보시려면, 로그 레벨을 디버그 레벨로 설정하십시오. 스캔 과정에서 불필요한 JAR들을 건너뛰면, 시스템 시작 시간과 JSP 컴파일 시간을 단축시킬 수 있습니다.
12월 28, 2021 8:15:04 오후 org.apache.jasper.servlet.TldScanner scanJars
정보: 적어도 하나의 JAR가 TLD들을 찾기 위해 스캔되었으나 아무 것도 찾지 못했습니다. 스캔했으나 TLD가 없는 JAR들의 전체 목록을 보시려면, 로그 레벨을 디버그 레벨로 설정하십시오. 스캔 과정에서 불필요한 JAR들을 건너뛰면, 시스템 시작 시간과 JSP 컴파일 시간을 단축시킬 수 있습니다.
12월 28, 2021 8:15:04 오후 org.apache.jasper.servlet.TldScanner scanJars
정보: 적어도 하나의 JAR가 TLD들을 찾기 위해 스캔되었으나 아무 것도 찾지 못했습니다. 스캔했으나 TLD가 없는 JAR들의 전체 목록을 보시려면, 로그 레벨을 디버그 레벨로 설정하십시오. 스캔 과정에서 불필요한 JAR들을 건너뛰면, 시스템 시작 시간과 JSP 컴파일 시간을 단축시킬 수 있습니다.
12월 28, 2021 8:15:04 오후 org.apache.catalina.core.StandardContext filterStart
심각: 필터 [EncodingFilter]을(를) 시작하는 중 오류 발생
java.lang.ClassNotFoundException: org.springframework.web.filter.CharacterEncodingFilter
	at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1415)
	at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1223)
	at org.apache.catalina.core.DefaultInstanceManager.loadClass(DefaultInstanceManager.java:537)
	at org.apache.catalina.core.DefaultInstanceManager.loadClassMaybePrivileged(DefaultInstanceManager.java:518)
	at org.apache.catalina.core.DefaultInstanceManager.newInstance(DefaultInstanceManager.java:149)
	at org.apache.catalina.core.ApplicationFilterConfig.getFilter(ApplicationFilterConfig.java:260)
	at org.apache.catalina.core.ApplicationFilterConfig.<init>(ApplicationFilterConfig.java:105)
	at org.apache.catalina.core.StandardContext.filterStart(StandardContext.java:4607)
	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5258)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1427)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1417)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:829)

12월 28, 2021 8:15:04 오후 org.apache.catalina.core.StandardContext startInternal
심각: 하나 이상의 필터들이 시작하지 못했습니다. 모든 상세 사항은 적절한 컨테이너 로그 파일에서 찾을 수 있습니다.
12월 28, 2021 8:15:04 오후 org.apache.catalina.core.StandardContext startInternal
심각: 이전 오류들로 인해 컨텍스트 [/day15(1227)]의 시작이 실패했습니다.
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.apache.catalina.loader.WebappClassLoaderBase (file:/mnt/18b3ea8d-ef9b-4057-be1e-87840846fb20/apache-tomcat-8.5.72/lib/catalina.jar) to field java.io.ObjectStreamClass$Caches.localDescs
WARNING: Please consider reporting this to the maintainers of org.apache.catalina.loader.WebappClassLoaderBase
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
12월 28, 2021 8:15:04 오후 org.apache.jasper.servlet.TldScanner scanJars
정보: 적어도 하나의 JAR가 TLD들을 찾기 위해 스캔되었으나 아무 것도 찾지 못했습니다. 스캔했으나 TLD가 없는 JAR들의 전체 목록을 보시려면, 로그 레벨을 디버그 레벨로 설정하십시오. 스캔 과정에서 불필요한 JAR들을 건너뛰면, 시스템 시작 시간과 JSP 컴파일 시간을 단축시킬 수 있습니다.
12월 28, 2021 8:15:05 오후 org.apache.jasper.servlet.TldScanner scanJars
정보: 적어도 하나의 JAR가 TLD들을 찾기 위해 스캔되었으나 아무 것도 찾지 못했습니다. 스캔했으나 TLD가 없는 JAR들의 전체 목록을 보시려면, 로그 레벨을 디버그 레벨로 설정하십시오. 스캔 과정에서 불필요한 JAR들을 건너뛰면, 시스템 시작 시간과 JSP 컴파일 시간을 단축시킬 수 있습니다.
12월 28, 2021 8:15:05 오후 org.apache.catalina.core.ApplicationContext log
정보: No Spring WebApplicationInitializer types detected on classpath
12월 28, 2021 8:15:05 오후 org.apache.coyote.AbstractProtocol start
정보: 프로토콜 핸들러 ["http-nio-8022"]을(를) 시작합니다.
12월 28, 2021 8:15:05 오후 org.apache.catalina.startup.Catalina start
정보: Server startup in 1293 ms
12월 28, 2021 8:15:12 오후 org.apache.catalina.core.ApplicationContext log
정보: Initializing Spring FrameworkServlet 'DispatcherServlet'
INFO : org.springframework.web.servlet.DispatcherServlet - FrameworkServlet 'DispatcherServlet': initialization started
INFO : org.springframework.web.context.support.XmlWebApplicationContext - Refreshing WebApplicationContext for namespace 'DispatcherServlet-servlet': startup date [Tue Dec 28 20:15:12 KST 2021]; root of context hierarchy
INFO : org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loading XML bean definitions from ServletContext resource [/WEB-INF/DispatcherServlet-servlet.xml]
INFO : org.springframework.web.servlet.handler.SimpleUrlHandlerMapping - Mapped URL path [/updateBoard.do] onto handler 'update'
INFO : org.springframework.web.servlet.handler.SimpleUrlHandlerMapping - Mapped URL path [/insertBoard.do] onto handler 'insert'
INFO : org.springframework.web.servlet.handler.SimpleUrlHandlerMapping - Mapped URL path [/deleteBoard.do] onto handler 'delete'
INFO : org.springframework.web.servlet.handler.SimpleUrlHandlerMapping - Mapped URL path [/main.do] onto handler 'main'
INFO : org.springframework.web.servlet.handler.SimpleUrlHandlerMapping - Mapped URL path [/login.do] onto handler 'login'
INFO : org.springframework.web.servlet.handler.SimpleUrlHandlerMapping - Mapped URL path [/board.do] onto handler 'board'
INFO : org.springframework.web.servlet.handler.SimpleUrlHandlerMapping - Mapped URL path [/logout.do] onto handler 'logout'
INFO : org.springframework.web.servlet.DispatcherServlet - FrameworkServlet 'DispatcherServlet': initialization completed in 224 ms

```
