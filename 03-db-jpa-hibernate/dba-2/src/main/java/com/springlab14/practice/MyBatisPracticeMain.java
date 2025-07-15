package com.springlab14.practice;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.InputStream;
import java.util.*;

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
            newOrder.setItemName("상품1");
            newOrder.setPrice(10000);
            mapper.insert(newOrder);

            // SELECT ALL
            System.out.println("[전체 리스트 조회]");
            List<Order> list = mapper.findAll();
            for (Order o : list) {
                System.out.println(o);
            }

            // SELECT BY ID
            Scanner scanner = new Scanner(System.in);
            System.out.print("조회하실 ID를 입력해주세요: ");
            String searchedId = scanner.nextLine();

            try {
                Order found = mapper.findById(Integer.parseInt(searchedId));
                System.out.println("ID " + searchedId + ": " + found);
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력 가능합니다.");
            }
            
            // SELECT BY CONDITION
            System.out.print("상품명을 입력하세요: ");
            String searchedItem = scanner.nextLine();
            System.out.print("최소 가격을 입력하세요: ");
            String minPrice = scanner.nextLine();

            Map<String, Object> param = new HashMap<>();
            if (!searchedItem.isBlank()) param.put("item_name", searchedItem);

            try {
                if (!minPrice.isBlank()) param.put("price", Integer.parseInt(minPrice));
                System.out.println("[검색 결과]");
                List<Order> searchedList = mapper.findByCondition(param);
                for (Order o : searchedList) {
                    System.out.println(o);
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력 가능합니다.");
            }
        }
    }
}