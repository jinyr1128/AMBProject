# AMBProject (익명 메시지 보드)
## 소개
AMBProject는 Spring 을 사용하여 개발된 간단한 익명게시판 웹입니다. 이 페이지는 포스트에 대한 기본 CRUD 작업을 제공합니다. 사용자는 게시물을 생성, 읽기, 업데이트 및 삭제할 수 있습니다. 포스트의 보안을 유지하기 위해 업데이트 및 삭제 작업에는 비밀번호가 필요합니다.만일 틀릴시는 오류 메세지를 송출합니다!

## 사용 기술
### 프로그래밍 언어: 	![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
### 웹 개발:  ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
### 데이터베이스: ![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
### 개발 도구: ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
### 의존성 관리: ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
## 기능
##### 모든 게시물 목록보기: 사용자는 시스템에 있는 모든 게시물을 볼 수 있습니다.
##### 단일 게시물 보기: 사용자는 하나의 게시물의 세부 정보를 선택하여 볼 수 있습니다.
##### 새 게시물 생성: 사용자는 제목, 작성자, 내용 및 비밀번호와 같은 필드를 포함하는 양식을 작성하여 새 게시물을 추가할 수 있습니다.
##### 게시물 업데이트: 사용자는 기존 게시물을 수정할 수 있습니다. 다만 변경 사항을 적용하려면 게시물과 연관된 올바른 비밀번호를 제공해야 합니다.
##### 게시물 삭제: 사용자는 시스템에서 게시물을 제거할 수 있습니다. 이 또한 게시물을 삭제하기 위해서는 비밀번호가 필요하여, 올바른 소유자 또는 관리자만 제거할 수 있습니다.
##### 오류 처리: 예상치 못한 오류가 발생하면 시스템은 사용자를 오류 메시지를 표시하는 오류 페이지로 리디렉션합니다.
## 애플리케이션 실행 방법
깃에서 클론으로 당겨온뒤 애플리케이션 시작 후 브라우저를 열고 http://localhost:8080/posts 로 이동하여 블로그 애플리케이션에 액세스합니다.
## 문서 및 다이어그램
- Use Case Diagram<br>
![스크린샷 2023-11-05 오전 9.27.54 복사본.png](img%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-11-05%20%EC%98%A4%EC%A0%84%209.27.54%20%EB%B3%B5%EC%82%AC%EB%B3%B8.png)<br><br>
- API 명세서<br>
![스크린샷 2023-11-05 오전 10.15.34 복사본.png](img%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-11-05%20%EC%98%A4%EC%A0%84%2010.15.34%20%EB%B3%B5%EC%82%AC%EB%B3%B8.png)<br>
<br>
- ERD<br>
![스크린샷 2023-11-05 오전 10.42.10.png](img%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-11-05%20%EC%98%A4%EC%A0%84%2010.42.10.png)
## 디렉토리 구조
- `model 패키지`: Post 엔터티 클래스를 포함하며, 이는 게시물을 나타냅니다. 이 클래스에는 `id`, `title`, `author`, `content`, `password`, `createdAt`과 같은 필드가 있습니다.
- `repository 패키지`: `JpaRepository`를 확장하는 `PostRepository` 인터페이스를 포함합니다. 이를 통해 `Post` 엔터티에 대한 CRUD 작업을 수행할 수 있습니다.
- `service 패키지`: 애플리케이션의 비즈니스 로직을 보유한 `PostService` 클래스가 있습니다. 데이터베이스와 상호 작용하기 위해 `PostRepository`와 연결합니다.
- `controller 패키지`: HTTP 요청 및 응답을 처리하는 `PostController` 클래스가 있습니다. CRUD 작업을 위한 엔드 포인트를 정의하고 이러한 요청을 처리하기 위해 `PostService`와 통신합니다.
## 결론
이 프로젝트를 통하여 최대한 많은 Spring의 활용을 해보려했지만 생각보다 잘 되지않는 점도 많았다...꾸미기도 아쉽긴하지만 근본적인 기능에서 에러가 나는 경우가 많이 있어서 아쉬움이 많이 남은 프로젝트이다. 좀 더 곰부를 하게된다면 더 나은 개발을 할수있지 않았을까?하는 미련이 남지만 지금의 배운내용을 정리하며 웹 개발의 기초를 하는 느낌이여서 좋은 복습이였다고 본다!!