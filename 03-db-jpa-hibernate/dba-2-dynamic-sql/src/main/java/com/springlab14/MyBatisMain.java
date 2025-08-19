package com.springlab14;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.InputStream;
import java.util.*;

public class MyBatisMain {
    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            ProductMapper mapper = session.getMapper(ProductMapper.class);

            // INSERT
            Product newProduct = new Product();
            newProduct.setName("상품3");
            newProduct.setPrice(10000);
            mapper.insert(newProduct);

            // SELECT ALL
            List<Product> list = mapper.findAll();
            for (Product p : list) {
                System.out.println(p);
            }

            // SELECT BY ID
            Scanner scanner = new Scanner(System.in);
            System.out.print("조회할 상품 ID를 입력하세요: ");
            int searchedId = scanner.nextInt();
            scanner.nextLine();

            Product found = mapper.findById(searchedId);
            System.out.println("[조회 결과]");
            System.out.println("ID " + searchedId + ": " + found);

            // SELECT BY CONDITION
            System.out.print("상품명을 입력하세요: ");
            String searchedName = scanner.nextLine().trim();
            System.out.print("최소 가격을 입력하세요: ");
            String searchedPrice = searchedPrice = scanner.nextLine();

            Map<String, Object> param = new HashMap<>();
            if (!searchedName.isEmpty()) param.put("name", searchedName);

            try {
                if (!searchedPrice.isEmpty()) param.put("price", Integer.parseInt(searchedPrice));
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력 가능합니다.");
                return;
            }
            List<Product> searchedList = mapper.findByCondition(param);
            for (Product p : searchedList) {
                System.out.println(p);
            }
        }
    }
}