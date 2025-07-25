# [V-2단계] 디렉티브: v-bind, v-if, v-for, v-on (vue-directives)

> ✨ “Vue에서 v-로 시작하는 지시문은,
> 
> 
> HTML과 JavaScript의 연결 고리를 만들어주는 마법의 문법이에요.”
> 

---

## 💡 핵심 개념 요약

| 디렉티브 | 설명 | 예시 |
| --- | --- | --- |
| `v-bind` | 속성 바인딩 | `:href="url"` ← `v-bind` 생략 가능 |
| `v-if` | 조건부 렌더링 | `<p v-if="isVisible">보임</p>` |
| `v-for` | 리스트 렌더링 | `<li v-for="item in items">{{ item }}</li>` |
| `v-on` | 이벤트 바인딩 | `@click="함수"` ← `v-on:click` 축약형 |

---

## 🧾 예시 코드

📁 `vue-directives.html`

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Vue Directives</title>
  <script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
</head>
<body>
  <div id="app">
    <h2 v-if="titleVisible">{{ title }}</h2>

    <ul>
      <li v-for="fruit in fruits">{{ fruit }}</li>
    </ul>

    <a v-bind:href="link" target="_blank">Vue 공식 사이트</a>

    <br /><br />
    <button @click="toggleTitle">제목 보이기/숨기기</button>
  </div>

  <script>
    new Vue({
      el: '#app',
      data: {
        title: '🍎 나의 과일 리스트 🍌',
        titleVisible: true,
        fruits: ['사과', '바나나', '포도', '복숭아'],
        link: 'https://vuejs.org/'
      },
      methods: {
        toggleTitle() {
          this.titleVisible = !this.titleVisible;
        }
      }
    });
  </script>
</body>
</html>
```

---

## 📌 포인트 요약

- `v-bind:속성`은 `:`로 축약 가능 → `:href`
- `v-if`, `v-for`는 **DOM을 조건/반복으로 조작**
- `v-on:click`도 `@click`으로 축약 가능
- `v-for="item in items"` 구조는 꼭 기억! (`in` 키워드 사용)

---

## 🧪 실습 미션

🎯 **목표:** 좋아하는 영화 목록을 출력하고, 버튼 클릭 시 제목을 숨기거나 보이게 하자

1. `vue-directives-practice.html` 파일 생성
2. data에 `title`, `titleVisible`, `movies (배열)`, `link` 정의
3. `v-if`, `v-for`, `v-bind`, `v-on` 각각 1번 이상 사용
4. 버튼을 눌러 제목 토글 (보이기/숨기기)