# [DBA-1단계] 추가 설명 - 예시 코드 분석

## 🧾 예시 코드 전체 구조

이번 실습에는 총 5개 파일이 있어:

1. `mybatis-config.xml` – **MyBatis의 전역 설정 파일**
2. `BookMapper.xml` – **SQL 쿼리 정의 파일 (매퍼 XML)**
3. `Book.java` – **도메인 객체 (VO / Entity 역할)**
4. `BookMapper.java` – **MyBatis 매퍼 인터페이스**
5. `MyBatisMain.java` – **MyBatis 실행 진입점 (JDBC 실행 코드)**

---

## 🔍 mybatis-config.xml — **MyBatis 설정 핵심 파일**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
    PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
    "<http://mybatis.org/dtd/mybatis-3-config.dtd>">

```

- XML 선언문과 MyBatis 설정을 위한 DTD 연결
- MyBatis가 이 파일을 XML 설정으로 인식하게 해줌

---

```xml
<configuration>

```

- 설정의 최상위 루트 태그

---

```xml
    <typeAliases>
        <typeAlias alias="Book" type="com.springlab13.Book"/>
    </typeAliases>

```

- Book이라는 짧은 이름(alias)을 com.springlab13.Book 클래스에 부여
- 이후 Mapper XML에서 resultType="Book"처럼 간결하게 사용 가능

---

```xml
    <environments default="dev">
        <environment id="dev">
            <transactionManager type="JDBC"/>

```

- 여러 DB 설정 중 기본으로 사용할 환경을 "dev"로 지정
- 트랜잭션은 JDBC 방식으로 관리 (자동 커밋 or 수동 커밋 가능)

---

```xml
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/testdb"/>
                <property name="username" value="root"/>
                <property name="password" value="spring1234"/>
            </dataSource>
        </environment>
    </environments>

```

- DB 연결 정보 입력 구간 (POOLED는 커넥션 풀 방식)
- driver: MySQL JDBC 드라이버
- url: 접속할 DB 주소
- username/password: DB 접속 계정 정보

---

```xml
    <mappers>
        <mapper resource="mapper/BookMapper.xml"/>
    </mappers>
</configuration>

```

- 사용할 SQL 매퍼 XML 파일 등록
- resource="..." 경로는 resources/ 기준 상대경로

---

## 🔍 BookMapper.xml — **SQL 쿼리 정의 XML 파일**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "<http://mybatis.org/dtd/mybatis-3-mapper.dtd>">

```

- 이 XML은 SQL 매핑용 문서이며, MyBatis가 이 문법을 이해할 수 있도록 DTD를 선언함

---

```xml
<mapper namespace="com.springlab13.BookMapper">

```

- 이 매퍼가 연결될 Java 인터페이스를 지정 (BookMapper.java)

---

```xml
    <select id="findAll" resultType="com.springlab13.Book">
        SELECT * FROM books
    </select>

```

- 전체 책 목록을 조회하는 SQL
- resultType은 반환값 타입 (Book 객체 리스트)

---

```xml
    <select id="findById" resultType="com.springlab13.Book" parameterType="int">
        SELECT * FROM books WHERE id = #{id}
    </select>

```

- ID를 조건으로 책을 조회하는 SQL
- #{id}는 파라미터 바인딩

---

```xml
    <insert id="insert" parameterType="com.springlab13.Book">
        INSERT INTO books (title, author)
        VALUES (#{title}, #{author})
    </insert>
</mapper>

```

- 새 책 정보를 DB에 추가하는 SQL
- #{title}, #{author}는 객체의 getter에서 값을 가져와 바인딩됨

---

## 🔍 Book.java — **책 정보를 담는 데이터 클래스 (도메인 객체)**

```java
package com.springlab13;

```

- 클래스의 패키지 경로

---

```java
public class Book {
    private int id;
    private String title;
    private String author;

```

- 책의 ID, 제목, 저자 정보를 필드로 정의

---

```java
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

```

- 각 필드에 대한 getter/setter 메서드

---

```java
    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author + "'}";
    }
}

```

- 객체 출력 시 보기 좋게 포맷을 바꿔주는 toString 오버라이드

---

## 🔍 BookMapper.java — **매퍼 인터페이스 (SQL 연결용 자바 인터페이스)**

```java
package com.springlab13;
import java.util.List;

```

- 매퍼 인터페이스도 동일한 패키지에서 관리
- 리스트 반환용 List import

---

```java
public interface BookMapper {
    List<Book> findAll();
    Book findById(int id);
    void insert(Book book);
}

```

- SQL 매퍼 XML과 1:1로 연결되는 메서드 시그니처 정의
- XML의 id와 메서드명이 일치해야 매핑됨

---

## 🔍 MyBatisMain.java — **MyBatis 실행 테스트용 클래스**

```java
package com.springlab13;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import java.io.InputStream;
import java.util.List;

```

- MyBatis 관련 클래스와 유틸 import

---

```java
public class MyBatisMain {
    public static void main(String[] args) throws Exception {

```

- 애플리케이션의 진입점

---

```java
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

```

- 설정 파일을 classpath에서 읽어오기

---

```java
        SqlSessionFactory sqlSessionFactory =
            new SqlSessionFactoryBuilder().build(inputStream);

```

- SqlSessionFactory 객체 생성

---

```java
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);

```

- 자동 커밋 설정(true)
- SQL 매퍼 인터페이스 구현체 가져오기

---

```java
            Book newBook = new Book();
            newBook.setTitle("Spring in Action");
            newBook.setAuthor("Craig Walls");
            mapper.insert(newBook);

```

- 새 책 객체를 만들고 DB에 저장

---

```java
            List<Book> list = mapper.findAll();
            for (Book book : list) {
                System.out.println(book);
            }

```

- 전체 책 목록 조회 + 출력

---

```java
            Book found = mapper.findById(1);
            System.out.println("ID 1:" + found);
        }
    }
}

```

- ID로 책 조회 후 출력
- 세션은 try-with-resources로 안전하게 닫힘

---

## 🔁 전체 흐름 요약

```
1. mybatis-config.xml을 기준으로 DB 연결 설정, typeAlias, mapper 등록 수행
2. BookMapper.java 인터페이스와 BookMapper.xml이 1:1로 매핑됨
3. MyBatisMain.java에서 SqlSessionFactory를 통해 DB 세션 열고 Mapper 실행
4. insert → findAll → findById 순서로 SQL 실행됨
5. 결과는 Book.java 객체 형태로 받아서 출력됨
```

---

## 🌱 봄이 맞춤 포인트

| 개념 | 설명 |
| --- | --- |
| SqlSessionFactory | MyBatis의 핵심 객체, DB 세션 생성 역할 |
| Mapper interface | Java 메서드와 SQL을 연결해주는 연결고리 |
| #{변수} 문법 | 자바 객체의 getter 값을 SQL에 바인딩하는 방식 |
| resultType / parameterType | SQL 결과값과 파라미터 타입 지정 → 매핑 정확도 향상 |
| toString() 오버라이드 | 객체 출력 시 원하는 포맷으로 보기 좋게 만들기 |