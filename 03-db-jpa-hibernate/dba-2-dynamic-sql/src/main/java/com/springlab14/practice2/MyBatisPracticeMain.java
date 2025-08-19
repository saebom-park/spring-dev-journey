package com.springlab14.practice2;

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
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);

            // INSERT
            Customer newCustomer = new Customer();
            newCustomer.setName("oni2");
            newCustomer.setEmail("robot2@dev.com");
            mapper.insert(newCustomer);

            // SELECT ALL
            System.out.println("[전체 조회]");
            List<Customer> list = mapper.findAll();
            for (Customer c : list) {
                System.out.println(c);
            }

            // SELECT BY ID
            Scanner scanner = new Scanner(System.in);
            System.out.print("ID를 입력해주세요: ");
            String searchedId = scanner.nextLine();

            try {
                Customer found = mapper.findById(Integer.parseInt(searchedId));
                System.out.println("ID " + searchedId + ": " + found);
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력 가능합니다.");
            }

            // SELECT BY CONDITION
            System.out.print("이름을 입력해주세요: ");
            String searchedName = scanner.nextLine();
            System.out.print("이메일을 입력해주세요(부분 입력 가능): ");
            String searchedEmail = scanner.nextLine();

            Map<String, Object> param = new HashMap<>();
            if (!searchedName.isBlank()) param.put("name", searchedName);
            if (!searchedEmail.isBlank()) param.put("email", searchedEmail);

            System.out.println("[검색 결과]");
            List<Customer> searchedList = mapper.findByCondition(param);
            for (Customer c : searchedList) {
                System.out.println(c);
            }
        }
    }
}