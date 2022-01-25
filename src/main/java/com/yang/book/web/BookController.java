package com.yang.book.web;

import com.yang.book.domain.Book;
import com.yang.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // final이 붙은 변수에 생성자를 만들어서 DI해준다는 의미
@RestController
public class BookController {

    private final BookService bookService;


    @PostMapping("/book")
    public ResponseEntity<?> save(@RequestBody Book book) {// RequestBody : 제이슨 데이터를 받기 위함
        return new ResponseEntity<>(bookService.저장하기(book), HttpStatus.CREATED); // 200 응답
        // httpstatus 코드를 같이 보내기 위해 ResponseEntity 사용
    }

    @GetMapping("/book")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(bookService.모두가져오기(), HttpStatus.OK); // 200 응답
        // httpstatus 코드를 같이 보내기 위해 ResponseEntity 사용
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.한건가져오기(id), HttpStatus.OK); // 200 응답
        // httpstatus 코드를 같이 보내기 위해 ResponseEntity 사용
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.수정하기(id, book), HttpStatus.OK); // 200 응답
        // httpstatus 코드를 같이 보내기 위해 ResponseEntity 사용
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.삭제하기(id), HttpStatus.OK); // 200 응답
        // httpstatus 코드를 같이 보내기 위해 ResponseEntity 사용
    }
}
