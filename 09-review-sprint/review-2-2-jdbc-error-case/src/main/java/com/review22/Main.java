package com.review22;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // 피드백 2 수정
    // getConnection
    private static BookDao getBookDao (String url, String username, String password) throws SQLException{
        Connection connection = DriverManager.getConnection(url, username, password);
        return new BookDao(connection);
    }

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "spring1234";
        BookDao bookDao;

        Book b = new Book("탈환의 여운", "정해운", 15120);
        Book b1 = new Book("나는 멋지게 자라고 있어", "이충민", 25000);
        Book b2 = new Book("열반과 미륵의 도상학", "미야지 아키라", 54000);
        Book b3 = new Book("내부통제", "이병철", 50000);

        // insert
        bookDao = getBookDao(url, username, password);
        bookDao.insert(b);

        // insert multiple
        bookDao = getBookDao(url, username, password);
        List<Book> books = new ArrayList<>();
        books.add(b1);
        books.add(b2);
        books.add(b3);
        bookDao.insertMultiple(books);

        // select all
        bookDao = getBookDao(url, username, password);
        List<Book> list = new ArrayList<>();
        list = bookDao.findAll();
        System.out.println("[전체 도서 조회]");
        for (Book sb : list) {
            System.out.println(sb);
        }

        // select by id
        bookDao = getBookDao(url, username, password);
        Scanner scanner = new Scanner(System.in);
        System.out.print("조회할 책의 ID를 입력해주세요: ");
        int selectId = scanner.nextInt();
        scanner.nextLine();
        Book found = bookDao.findById(selectId);
        // 피드백 1 수정
        if (found == null) {
            System.out.println("조회 결과가 없습니다.");
        } else {
            System.out.println("[조회 결과]");
            System.out.println(found);
        }


        // update
        bookDao = getBookDao(url, username, password);
        System.out.print("변경할 책의 ID를 입력해주세요: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("변경할 정보의 유형 번호를 입력해주세요 (title: 1, author: 2, price: 3): ");
        int updateType = scanner.nextInt();
        scanner.nextLine();
        System.out.print("변경할 값을 입력해주세요: ");
        String updateValue = scanner.nextLine();
        bookDao.update(updateId, updateType, updateValue);

        // delete
        bookDao = getBookDao(url, username, password);
        System.out.print("삭제할 책의 ID를 입력해주세요: ");
        int deleteId = scanner.nextInt();
        scanner.nextLine();
        bookDao.delete(deleteId);

        // select all
        bookDao = getBookDao(url, username, password);
        list = bookDao.findAll();
        System.out.println("[최종 도서 조회]");
        for (Book book : list) {
            System.out.println(book);
        }
        
        // 피드백 3 수정
        scanner.close();
    }
}

/* 🔍 피드백 요약

1. findById()는 조회 결과가 없을 때 null을 반환하도록 리팩토링하면 더 명확해져.
   지금은 빈 Book 객체가 그대로 리턴되기 때문에,
   호출부에서는 null 체크 후 "존재하지 않는 책입니다" 같은 메시지를 출력하는 게 사용자에게 더 자연스러워.

2. DriverManager.getConnection(...)과 new BookDao(...) 흐름이 반복되므로
   getDao(url, user, pass) 메서드로 분리하면 중복 제거 + 코드 가독성이 좋아져!

3. 입력에 사용한 Scanner는 마지막에 꼭 scanner.close()로 닫아줘야 리소스 누수가 없어.
   작은 습관이지만, 실무에서는 이런 자원 정리가 신뢰성을 높여주는 포인트야!
*/