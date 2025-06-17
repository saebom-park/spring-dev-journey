// * 실습 미션: 인터페이스 다중 구현 연습 + 기능 분리 이해하기
// 1. Camera 인터페이스: takePhoto() 선언
// 2. MusicPlayer 인터페이스: playMusic() 선언
// 3. SmartWatch 클래스에서 두 인터페이스 모두 구현
// 4. 각각의 메서드를 System.out.println()으로 출력
// 5. SmartWatchExample에서 객체 생성 후
// 6. takePhoto() & playMusic() 호출

package oop;

interface Camera {
    void takePhoto();
}