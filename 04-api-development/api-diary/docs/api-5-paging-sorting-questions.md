# [API-5단계] 질문노트: 조회 API 확장

> 💬 봄이가 직접 궁금해서 물어본 것들 + 온이의 설명 요약
> 
> 
> (코드: com.springlab20 실습 기준)
> 

---

### 💡 1. 왜 기존에는 stream().map() 쓰는데, 페이징에서는 findAll(pageable).map()만 쓰는 거야?

| 질문 | 온이의 설명 |
| --- | --- |
| 기존 getHabits()는 habitRepository.findAll().stream().map()… 이런 흐름인데, 페이징 버전은 habitRepository.findAll(pageable).map()만 쓰는 이유가 뭐야? | 봄아, `findAll()`은 그냥 List를 주거든. List는 map 기능이 없으니까 stream()으로 바꿔서 map/collect를 써야 해. 근데 `findAll(Pageable)`은 Page를 주는데, Page 자체가 map()을 가지고 있어서 바로 DTO 변환이 가능해. 그래서 stream()이 빠진 게 아니라, 필요 없어져서 안 쓰는 거야. |
| 사실 List를 반환하니까 stream()쓴다고는 외우고 있었는데, stream()이 구체적으로 어떤 역할을 하는지는 잘 모르겠어. | stream()은 쉽게 말하면 “컬렉션을 데이터 가공 공장에 넣는 입구”야. stream()을 호출하면 이제 map, filter, sorted 같은 걸 연결해서 데이터를 단계별로 가공할 수 있어. 마지막에 collect 같은 걸로 결과물을 꺼내오고. 그러니까 stream()은 컬렉션을 함수형 스타일로 가공할 수 있게 변환하는 입구라고 생각하면 돼. |
| 파이프라인이라는 건 뭐야? | 파이프라인은 물 흘러가듯 연산들을 줄줄이 연결한 흐름을 말해. 예를 들어 `list.stream().filter(...).map(...).collect(...)` 이거 전체가 하나의 파이프라인이야. filter에서 걸러지고, map에서 변환되고, collect에서 모이는 거. 각 단계가 파이프처럼 이어져서 데이터가 흘러가는 거라고 이해하면 돼. |
| 그럼 map()은 어떤 역할을 해? stream()이랑 map()은 무슨 차이야? | 오 좋은 질문이야! stream()은 단순히 컬렉션을 "가공할 준비가 된 상태"로 바꿔주는 거야. map()은 그 다음 단계에서 실제 변환을 하는 연산이지. 예를 들어 `List<String>`에서 stream()을 하면 스트림 파이프라인이 열리고, 거기서 map(String::length)를 하면 String을 길이(Integer)로 바꾸는 변환이 일어나. 즉, stream()은 출발선, map()은 가공 공정이라고 보면 딱 맞아. |
| Page 객체는 toList() 같은 걸 안 쓰는 이유가 뭐야? | 맞아 봄아! Page는 애초에 stream 파이프라인으로 바꾼 게 아니라, Page 자체가 map 기능을 가지고 있어서 변환하면 그대로 Page로 반환돼. 그래서 List처럼 다시 toList()로 모을 필요가 없는 거야. List는 stream으로 변환해야 map을 쓸 수 있으니 toList()가 필요했고, Page는 바로 map으로 DTO 변환이 가능하니까 toList()가 불필요한 거지. |

---

### 💡 2. 요청 URL에서 page=0이라고 쓰는데, 보통 1페이지부터 시작하는 거 아닌가?

| 질문 | 온이의 설명 |
| --- | --- |
| 요청 URL 파라미터에서 `page=0`이라고 하던데, 페이지는 원래 1페이지부터 있는 거잖아? 왜 0이라고 쓰는 거야? | 아, 이건 Spring Data JPA의 기본 규칙 때문이야. 내부적으로 PageRequest 같은 페이징 객체는 **0부터 시작하는 인덱스 기반**으로 동작해. 그래서 page=0이 실제로는 첫 번째 페이지야. 우리가 보는 1페이지 = 코드에선 0번 페이지, 2페이지 = 코드에선 1번 페이지 이런 식으로 대응되는 거지. |

---

### 🧾 작은 예시 코드

```java
import java.util.*;

public class StreamMapExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("봄이", "온이", "스트림");

        List<Integer> lengths = words.stream() // 출발선: 스트림으로 변환
                .map(String::length)          // 가공 공정: 문자열을 길이(Integer)로 변환
                .toList();                     // 결과물 수집

        System.out.println(lengths); // [2, 2, 3]
    }
}

```

---

### 🌱 정리 키워드

- List → stream() 필요
- Page → map() 내장
- stream() = 컬렉션을 가공할 수 있는 스트림으로 변환 (출발선)
- map() = 스트림 안의 데이터를 원하는 형태로 변환 (가공 공정)
- Page → map()만으로 DTO 변환 가능 (toList() 불필요)
- 파이프라인 = 여러 연산이 물 흐르듯 이어지는 처리 흐름
- Pageable → page는 0부터 시작 (0 = 1페이지)
- Pageable 적용 시 stream() 불필요