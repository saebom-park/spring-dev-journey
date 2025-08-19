package com.springlab13;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.InputStream;
import java.util.List;

public class MyBatisMain {
    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml"; // 어떤 설정 파일을 쓸 건지 지정하기
        // 위의 설정파일을 읽어서 메모리에 올리는 작업
        // Resource.getResourceAsStream()은 클래스패스 기준으로 xml 파일 찾는 MyBatis 전용 도구
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // ⭐ 가장 중요한 줄
        // 지금까지 읽은 xml을 바탕으로 DB와 대화할 수 있는 세션 공장을 만듦
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        // openSession(true)는 자동 커밋 모드로 세션을 여는 것
        // 이제부터 이 session을 이용해 DB 작업을 할 수 있음
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // mapper는 "SQL을 실행할 수 있는 비서"와 같은 역할
            // MyBatis가 자동으로 구현체를 만들어서 연결해줌
            // mapper.findAll() 하면 → mapper.xml에 적힌 SQL이 자동 실행 됨
            BookMapper mapper = session.getMapper(BookMapper.class);

            // INSERT 예시
            Book newBook = new Book();
            newBook.setTitle("Spring in Action");
            newBook.setAuthor("Craig Walls");
            mapper.insert(newBook);

            // SELECT ALL
            List<Book> list = mapper.findAll();
            for (Book book: list) {
                System.out.println(book);
            }

            // SELECT BY ID
            Book found = mapper.findById(1);
            System.out.println("ID 1:" + found);
        }

    }
}

// 📍 그림으로 요약
//[mybatis-config.xml]      ← 설정파일 읽음
//       ↓
//[SqlSessionFactory]       ← DB와 연결해줄 수 있는 세션 공장
//       ↓
//[SqlSession]              ← 진짜 DB랑 대화하는 객체
//       ↓
//[getMapper(BookMapper)]   ← mapper의 구현체를 자동으로 생성
//       ↓
//[mapper.insert(), findAll()] ← 실제 SQL 실행됨 (mapper.xml에 정의된 내용 기준)