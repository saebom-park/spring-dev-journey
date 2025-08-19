package com.springlab13.practice;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.InputStream;
import java.util.List;

public class MyBatisPracticeMain {
    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            OrderMapper mapper = session.getMapper(OrderMapper.class);

            // INSERT
            Order newOrder = new Order();
            newOrder.setItemName("쉽게 배우는 소프트웨어 공학");
            newOrder.setPrice(15000);
            mapper.insert(newOrder);

            // SELECT ALL
            List<Order> list = mapper.findAll();
            for (Order order : list) {
                System.out.println(order);
            }

            // SELECT ONE
            Order found = mapper.findById(1);
            System.out.println("ID 1 = " + found);
        }
    }
}