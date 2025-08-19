# [DBA-1단계] MyBatis 기본 (mybatis-basic)

> ✨ "JDBC보다 효율적으로 SQL을 관리하려면 어떻게 해야 할까?"
> 
> 
> 👉 MyBatis는 SQL을 XML에 분리하고, 자바 Mapper 인터페이스를 통해 깔끔하게 연결할 수 있어!
> 

---

## 💡 핵심 개념 요약

| 항목 | 설명 |
| --- | --- |
| MyBatis | SQL 매퍼 프레임워크. JDBC 코드의 반복 제거 및 SQL 분리 가능 |
| Mapper Interface | SQL 매핑을 위한 자바 인터페이스 (BookMapper.java 등) |
| Mapper XML | SQL 정의 파일 (`BookMapper.xml`) |
| SqlSessionFactory | MyBatis의 핵심 객체, DB 세션 생성용 |
| mybatis-config.xml | 환경설정 파일 (DB 연결, 매퍼 등록 등) |
| resources/mapper/ | SQL XML 파일이 위치하는 경로 (지정 필요) |

---

## 🧾 예시 코드

📁 예시 구조 기준: `com.springlab13`

📁 예시 도메인: `Book`

### 1. Book.java

```java
package com.springlab13;

public class Book {
    private int id;
    private String title;
    private String author;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author + "'}";
    }
}

```

### 2. BookMapper.java

```java
package com.springlab13;

import java.util.List;

public interface BookMapper {
    List<Book> findAll();
    Book findById(int id);
    void insert(Book book);
}

```

### 3. BookMapper.xml

`📁 src/main/resources/mapper/BookMapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "<http://mybatis.org/dtd/mybatis-3-mapper.dtd>">

<mapper namespace="com.springlab13.BookMapper">
    <select id="findAll" resultType="com.springlab13.Book">
        SELECT * FROM books
    </select>

    <select id="findById" resultType="com.springlab13.Book" parameterType="int">
        SELECT * FROM books WHERE id = #{id}
    </select>

    <insert id="insert" parameterType="com.springlab13.Book">
        INSERT INTO books (title, author)
        VALUES (#{title}, #{author})
    </insert>
</mapper>

```

### 4. mybatis-config.xml

`📁 src/main/resources/mybatis-config.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
    PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>

    <typeAliases>
        <typeAlias alias="Book" type="com.springlab13.Book"/>
    </typeAliases>

    <environments default="dev">
        <environment id="dev">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/testdb"/>
                <property name="username" value="root"/>
                <property name="password" value="spring1234"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="mapper/BookMapper.xml"/>
    </mappers>

</configuration>

```

### 5. MyBatisMain.java

```java
package com.springlab13;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.InputStream;
import java.util.List;

public class MyBatisMain {
    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory =
            new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);

            // INSERT 예시
            Book newBook = new Book();
            newBook.setTitle("Spring in Action");
            newBook.setAuthor("Craig Walls");
            mapper.insert(newBook);

            // SELECT ALL
            List<Book> list = mapper.findAll();
            for (Book book : list) {
                System.out.println(book);
            }

            // SELECT BY ID
            Book found = mapper.findById(1);
            System.out.println("ID 1: " + found);
        }
    }
}

```

---

## 📌 포인트 요약

- MyBatis는 **SQL을 XML에 분리**하고, 자바 인터페이스로 연결함
- `mapper.xml`과 `mapper interface`의 메서드 이름은 반드시 일치해야 함
- `SqlSessionFactory` → `SqlSession` → `getMapper()` 흐름 기억
- `mybatis-config.xml`은 resource 기준 경로로 작성되어야 함
- 전체 폴더 구조는 `java` + `resources` 기준으로 맞춰야 정상 작동

---

## 🧪 실습 미션

🎯 목표: MyBatis 설정을 직접 구성하고, `Order` 도메인으로 실습 진행해보기

1. `com.springlab13.practice` 패키지 생성
2. `Order.java` 클래스 생성 (필드: id, itemName, price)
3. `OrderMapper.java` + `OrderMapper.xml` 생성
4. `OrderMain.java`에서 insert, findAll, findById 테스트
5. `mybatis-config.xml`에 `OrderMapper.xml` 등록

> 참고: mapper/OrderMapper.xml의 경로와 네임스페이스 설정 주의
> 
> 
> 💾 DB에 `orders(id INT, item_name VARCHAR, price INT)` 테이블 직접 생성 필요
>