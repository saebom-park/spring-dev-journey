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
            for (Book book: list) {
                System.out.println(book);
            }

            // SELECT BY ID
            Book found = mapper.findById(1);
            System.out.println("ID 1:" + found);
        }

    }
}