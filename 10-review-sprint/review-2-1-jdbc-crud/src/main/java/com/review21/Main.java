package com.review21;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, InvalidScopeException {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "spring1234";

        Connection conn = DriverManager.getConnection(url, username, password);
        BookDao bookDao = new BookDao(conn);

        // insert
//        Book b1 = new Book("강원국의 책쓰기 수업", "강원국", 18000);
//        Book b2 = new Book("야구선수 김원중", "김원중, 김하진", 18000);
//        Book b3 = new Book("가공범", "히가시노 게이고", 19800);
//
//        bookDao.insert(b1);
//        bookDao.insert(b2);
//        bookDao.insert(b3);

        // select all
        List<Book> list = bookDao.findAll();
        System.out.println("[전체 도서 목록]");
        for (Book b : list) {
            System.out.println(b);
        }

        // select by id
        Scanner scanner = new Scanner(System.in);
        System.out.print("조회할 책의 번호를 입력해주세요: ");
        int foundId = scanner.nextInt();
        scanner.nextLine();

        Book found = bookDao.findById(foundId);
        if (found == null) {
            System.out.println("해당 번호에 조회되는 결과가 없습니다.");
        } else {
            System.out.println("[도서 검색 결과]\n" + found);
        }

        // update
        int result = 0;
        System.out.print("변경할 책의 번호를 입력해주세요: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();

        // 피드백 2 수정
        try {
            System.out.print("변경할 유형 번호을 입력해주세요 (제목: 1, 저자: 2, 가격: 3): ");
            int updateType = scanner.nextInt();
            scanner.nextLine();

            if (updateType < 1 || updateType > 3) {
                throw new InvalidScopeException();
            }
            System.out.print("변경할 값을 입력해주세요: ");
            String value = scanner.nextLine();

            result = bookDao.update(updateId, updateType, value);

            if (result == 0) {
                System.out.println("변경된 정보가 없습니다.");
            } else {
                System.out.println("정보 변경이 완료되었습니다.");
            }
        } catch(InvalidScopeException e) {
            System.out.println(e.getMessage());
        }

        // delete
        System.out.print("삭제할 책의 번호를 입력해주세요: ");
        int deleteId = scanner.nextInt();
        scanner.nextLine();
        result = bookDao.delete(deleteId);
        if (result == 0) {
            System.out.println("삭제된 정보가 없습니다.");
        } else {
            System.out.println("삭제가 완료되었습니다.");
        }

        list = bookDao.findAll();
        System.out.println("[최종 도서 목록]");
        for (Book b : list) {
            System.out.println(b);
        }
    }
}

/* 🔍 피드백 요약
1. BookDao.update()에서 price 타입은 int이므로 setString() 대신 setInt()로 변경하자!
   입력은 문자열로 받아도, DB 컬럼 타입에 맞게 처리해주는 것이 안전하고 명확해.

2. InvalidScopeException은 RuntimeException도 좋지만,
   입력 유효성 검증용 예외는 Exception으로 정의해도 의미가 분명해져.
   (다만 실습 흐름상 Runtime으로 처리한 점은 충분히 타당한 선택이었어!)

3. BookDao.findById()에서 pstmt.setInt()와 executeQuery() 호출 순서는 반드시 setInt → query 순으로!
   try-with-resources에 함께 넣으면 순서 오류가 생길 수 있어.

4. BookDao 생성자에 public 누락은 접근 제한자에 대한 감각이 생겼다는 좋은 신호야.
   구조 확장 시 꼭 필요한 디테일이니 지금 알아챈 게 정말 훌륭해.
*/