# [DBA-5단계] 트랜잭션과 영속성 전이 (transaction-cascade)

> ✨ "JPA에서는 연관된 객체를 함께 저장하거나, 롤백되게 만들 수 있을까?"
> 
> 
> 👉 `@Transactional`과 `CascadeType.PERSIST`를 사용하면
> 
> 트랜잭션 단위로 연관된 엔티티까지 함께 처리할 수 있어!
> 

---

### 💡 핵심 개념 요약

| 항목 | 설명 |
| --- | --- |
| 트랜잭션 (@Transactional) | JPA 작업을 하나의 논리적 작업 단위로 묶는 선언형 트랜잭션 처리 방식 |
| flush / commit | save() 호출로 DB에 바로 반영되지 않고, flush 또는 commit 시점에 SQL 실행됨 |
| CascadeType.PERSIST | 부모 엔티티 저장 시 자식 엔티티도 함께 저장되도록 영속성 전이를 설정하는 옵션 |
| 실무 기준 | cascade 설정은 신중하게! 대부분 명시적으로 save() 호출하는 방식 선호함 |

---

### 🧾 예시 코드 (실행 가능 기준)

📁 예시 구조 기준: `com.springlab18`

📁 예시 도메인: `Team` ↔ `Player`

### 1. build.gradle

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5'
    id 'io.spring.dependency-management' version '1.1.5'
}

group = 'com.springlab18'
version = '1.0.0'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'com.mysql:mysql-connector-j:8.0.33'
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

```

---

### 2. application.yml

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb
    username: root
    password: spring1234
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true
        enable_lazy_load_no_trans: true
    show-sql: true

logging:
  level:
    org.hibernate.SQL: debug
    org.hibernate.type: trace

```

---

### 3. JpaRelationCascadeApplication.java

```java
package com.springlab18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaRelationCascadeApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaRelationCascadeApplication.class, args);
    }
}

```

---

### 4. Team.java

```java
package com.springlab18;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    private List<Player> players = new ArrayList<>();

    public Team() {}
    public Team(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Player> getPlayers() { return players; }

    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }
}

```

---

### 5. Player.java

```java
package com.springlab18;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    @Column(name = "player_name")
    private String name;

    @Column(name = "position")
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Player() {}
    public Player(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public Team getTeam() { return team; }

    public void setTeam(Team team) { this.team = team; }
}

```

---

### 6. TeamRepository.java

```java
package com.springlab18;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}

```

---

### 7. PlayerRepository.java

```java
package com.springlab18;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}

```

---

### 8. InitPlayerDataRunner.java

```java
package com.springlab18;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitPlayerDataRunner implements CommandLineRunner {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public InitPlayerDataRunner(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Team team = new Team("드림FC");

        Player p1 = new Player("홍길동", "스트라이커");
        Player p2 = new Player("김영희", "골키퍼");

        team.addPlayer(p1);
        team.addPlayer(p2);

        teamRepository.save(team);

        teamRepository.findAll().forEach(t -> {
            System.out.println(t.getName() + " 선수 명단:");
            t.getPlayers().forEach(p -> System.out.println("- " + p.getName() + " / " + p.getPosition()));
        });
    }
}

```

---

### 📌 포인트 요약

- `@Transactional`이 없으면 Lazy 로딩 시 예외 발생 가능
- flush 시점에 영속성 컨텍스트의 변경사항이 DB에 반영됨
- `CascadeType.PERSIST`는 자식까지 자동 저장할 수 있지만, 실무에선 사용을 제한하는 편
- 명시적 save() 호출이 실무에서 더 예측 가능하고 안전한 방법

---

### 🧪 실습 미션

🎯 목표: `Academy` ↔ `Student` 연관관계 기반으로 트랜잭션 처리 흐름과 영속성 전이 여부 테스트

1. `Academy.java`
    - `@OneToMany(mappedBy = "academy") List<Student> students` 필드 추가
    - `addStudent(Student student)` 편의 메서드 구현
2. `Student.java`
    - `@ManyToOne(fetch = FetchType.LAZY)`
    - `@JoinColumn(name = "academy_id")`
3. `InitStudentDataRunner` 작성
    - `@Transactional`로 감싸고, `academy.addStudent(s)` 방식으로 설정
    - `academyRepository.save(academy)` 한 번만 호출
4. 콘솔 출력으로 각 학원별 학생 목록 확인
5. CascadeType.PERSIST 유무에 따라 결과가 어떻게 달라지는지 테스트

> 참고: 실무에선 cascade 대신 명시 저장 방식 선호 (예: studentRepository.save() 따로 호출)
>