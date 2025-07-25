# [V-1단계] Vue 인스턴스와 템플릿 문법 (vue-instance-template)

> ✨ “HTML에 JavaScript 객체를 연결하는 순간,
> 
> 
> Vue는 단순한 마크업을 ‘반응형 UI’로 바꿔버려요.”
> 

---

## 💡 핵심 개념 요약

| 개념 | 설명 |
| --- | --- |
| Vue 인스턴스 | `new Vue({...})`로 생성하는 객체. 데이터/함수/UI 영역을 제어 |
| el 옵션 | DOM에서 Vue가 제어할 대상을 선택 (`el: "#app"`) |
| data | 뷰가 반응형으로 추적할 변수들 (`data: { count: 0 }`) |
| methods | UI와 연결할 메서드들 (`methods: { plus() { this.count++ } }`) |
| 템플릿 문법 | HTML에 `{{ count }}`, `v-on:click` 등 Vue 전용 지시문 사용 |
| this | methods 내부에서는 반드시 `this.변수명`으로 접근 |

---

## 🧾 예시 코드

> 📁 vue-instance.html — Vue의 기본 구조를 구성해보자
> 

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Vue Instance</title>
  <script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
</head>
<body>
  <div id="app">
    <h1>{{ message }}</h1>
    <p>현재 카운트: {{ count }}</p>
    <button v-on:click="increment">+1</button>
  </div>

  <script>
    const app = new Vue({
      el: '#app',
      data: {
        message: '안녕하세요! Vue입니다.',
        count: 0
      },
      methods: {
        increment() {
          this.count++;
        }
      }
    });
  </script>
</body>
</html>
```

---

## 📌 포인트 요약

- `el: '#app'` → Vue가 연결될 HTML의 루트 DOM 요소
- `data` 속성 안의 값들은 `{{ }}`로 자동 바인딩됨
- `v-on:click="increment"` → 클릭 이벤트에 메서드 연결
- `methods` 안에서는 `this.변수명`으로 접근해야 동작함

---

## 🧪 실습 미션

🎯 **목표:** Vue 인스턴스를 생성하고, 버튼 클릭 시 숫자가 증가하는 UI 만들기

1. `vue-practice.html` 파일 생성
2. `<div id="counter">`에 count 숫자 출력
3. `+1`, `-1`, `초기화` 버튼 추가
4. Vue 인스턴스에서 `count`, `plus`, `minus`, `reset` 구현

> 참고: this.count += 1 / this.count = 0 형식으로 작성
> 
> 
> HTML은 예시코드 그대로 사용해도 되고, 봄이 스타일로 바꿔도 좋아!
>