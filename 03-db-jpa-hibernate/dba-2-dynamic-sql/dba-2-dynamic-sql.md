# [DBA-2단계] 동적 SQL & resultMap (dynamic-sql)

> ✨ "조건에 따라 SELECT 문이 동적으로 바뀌게 하려면 어떻게 해야 할까?"
> 
> 
> 👉 MyBatis는 `<if>`, `<where>` 태그로 SQL을 유연하게 구성할 수 있고,
> 
> `resultMap`으로 컬럼명과 필드명이 다른 경우도 정확히 매핑할 수 있어!
> 

---

## 💡 핵심 개념 요약

| 항목 | 설명 |
| --- | --- |
| 동적 SQL | `<if>`, `<where>`, `<choose>` 등을 사용해 조건에 따라 SQL 생성 |
| resultMap | DB 컬럼명과 자바 필드명이 다를 때 직접 매핑하는 도구 |
| 태그 | 여러 if문을 자동으로 WHERE 절로 묶어주고 AND 정리까지 해줌 |
| parameterType | 전달받는 파라미터의 자바 타입 (예: Product, int 등) |
| resultType / resultMap | 결과 매핑 방식 선택 (단순 타입 or 상세 매핑 ID) |

---

## 🧾 예시 코드

📁 예시 구조 기준: `com.springlab14`

📁 예시 도메인: `Product`

### 1. Product.java

```java
package com.springlab14;

public class Product {
    private int id;
    private String name;
    private int price;

    // getter
    public int getId() {return id;}
    public String getName() {return name;}
    public int getPrice() {return price;}

    // setter
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setPrice(int price) {this.price = price;}

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
}
```

### 2. ProductMapper.java

```java
package com.springlab14;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    List<Product> findAll();
    Product findById(int id);
    void insert(Product product);
    List<Product> findByCondition(Map<String, Object> param);
}
```

### 3. ProductMapper.xml (`📁 src/main/resources/mapper/ProductMapper.xml`)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.springlab14.ProductMapper">

    <resultMap id="productResultMap" type="com.springlab14.Product">
        <id property="id" column="product_id"/>
        <result property="name" column="name"/>
        <result property="price" column="price"/>
    </resultMap>

    <select id="findAll" resultMap="productResultMap">
        SELECT product_id, name, price FROM products
    </select>

    <select id="findById" resultMap="productResultMap" parameterType="int">
        SELECT product_id, name, price FROM products WHERE product_id = #{id}
    </select>

    <insert id="insert" parameterType="com.springlab14.Product">
        INSERT INTO products (name, price)
        VALUES (#{name}, #{price})
    </insert>

    <select id="findByCondition" resultMap="productResultMap">
        SELECT product_id, name, price FROM products
        <where>
            <if test="name != null">
                name = #{name}
            </if>
            <if test="price != null">
                AND price &gt;= #{price}
            </if>
        </where>
    </select>
    
</mapper>

```

### 4. mybatis-config.xml (`📁 src/main/resources/mybatis-config.xml`)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD 3.0 Config//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>

    <typeAliases>
        <typeAlias alias="Product" type="com.springlab14.Product"/>
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
        <mapper resource="mapper/ProductMapper.xml"/>
    </mappers>

</configuration>

```

### 5. MyBatisMain.java

```java
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
```

---

## 📌 포인트 요약

- 동적 SQL은 `<if>` + `<where>` 태그로 WHERE 절 조건을 유연하게 작성 가능
- `<where>`는 AND/OR 위치도 자동 정리해줌
- resultMap을 사용하면 컬럼명과 필드명이 달라도 정확히 매핑 가능
- Mapper 인터페이스와 XML의 네임스페이스/메서드명이 반드시 일치해야 함
- 실행 구조: SqlSessionFactory → SqlSession → getMapper → 실행

---

## 🧪 실습 미션

🎯 목표: 조건 검색 + resultMap을 활용한 실습 구조 만들기

📁 실습 구조 기준: `com.springlab14.practice`

📁 실습 도메인: `Order`

1. `Order.java` 클래스 생성 (필드: id, itemName, price)
2. `OrderMapper.java`에 `findByCondition(Map<String, Object>)` 메서드 정의
3. `OrderMapper.xml`에 `<if>`, `<where>`, `<resultMap>` 기반의 SQL 작성
4. `MyBatisPracticeMain.java`에서 Scanner로 itemName, price 입력 후 검색 실행
5. 모든 조건 생략 시 전체 결과 출력 확인

> 참고: 실습용 테이블은 orders(order_id INT, item_name VARCHAR, price INT) 구조를 사용함
>