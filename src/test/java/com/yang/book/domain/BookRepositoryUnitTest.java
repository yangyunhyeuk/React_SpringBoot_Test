package com.yang.book.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

// 단위 테스트 (DB 관련된 Bean이 IoC에 등록되면 된다.)


@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // 가짜 DB로 테스트 한다는 의미, Replace.ANY == 실제 DB로 테스트
@DataJdbcTest // Repository들을 다 IoC에 등록해주기에, @Mock 필요 X
public class BookRepositoryUnitTest {

    @Autowired
    private BookRepository bookRepository;

}
