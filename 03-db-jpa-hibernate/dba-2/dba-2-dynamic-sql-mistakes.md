# [DBA-2단계] 실수노트: 동적 SQL & resultMap

> 💻 실습 코드: OrderMapper.xml, MyBatisMain.java
> 

---

### 😅 실수 1 — resultMap 이름을 resultType에 잘못 사용함

```xml
<select id="findByCondition" resultType="orderResultMap"> <!-- ❌ 잘못된 부분 -->

```

✅ 정답:

```xml
<select id="findByCondition" resultMap="orderResultMap"> <!-- ✅ resultMap은 resultMap 속성으로! -->

```

📌 **설명**:

- `orderResultMap`은 `<resultMap>`의 ID이지, 클래스명이 아니기 때문에 `resultType`으로 쓰면 MyBatis가 클래스로 착각함
- `resultMap="..."` 속성으로 지정해야 XML 내부 매핑 구성을 참조할 수 있음

---

### 😅 실수 2 — SELECT문에서 PK 컬럼을 누락함 (id가 0으로 나옴)

```xml
SELECT item_name, price FROM orders

```

✅ 정답:

```xml
SELECT order_id, item_name, price FROM orders

```

📌 **설명**:

- `Order` 객체의 `id` 필드에 값을 넣으려면 반드시 `SELECT` 대상 컬럼에 `order_id`도 포함돼야 함
- 누락 시 해당 필드는 초기값(0)으로 유지되며 toString 출력에 반영되지 않음

---

### 📌 요약 포인트

- `resultType`과 `resultMap`은 혼용하지 않도록 주의할 것
- resultMap을 지정할 때는 반드시 `resultMap="..."` 속성을 사용해야 함
- PK 필드도 SELECT 대상에 반드시 포함시켜야 올바르게 객체에 매핑됨