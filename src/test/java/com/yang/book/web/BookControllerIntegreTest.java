package com.yang.book.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.book.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// 통합 테스트 : 컨트롤러로 전체 스프링을 테스트
// == 모든 Bean들을 똑같이 IoC에 올리고 테스트 하는 것
/*
 * WebEnvironment.MOCK = 실제 톰캣을 올리는 게 아니라 다른 톰캣으로 테스트
 * WebEnvironment.RANDOM_PORT = 실제 톰캣으로 테스트
 * @AutoConfigureMockMvc = MockMvc를 IoC에 등록해준다.
 * @Transactional = 각각의 테스트 함수가 종료될 때마다 트랜잭션을 rollback해주는 어노테이션
 * */
@Slf4j
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class BookControllerIntegreTest {

    @Autowired
    private MockMvc mockMvc;

    // BDD Mockito 패턴 : 모키토를 확장한 라이브러리 given, when, then
    @Test
    public void save_테스트() throws Exception {
        log.info("save_테스트 시작===========================================");
        // given (테스트를 하기 위한 준비)

        Book book = new Book(null, "스프링따라하기", "yang");

        String content = new ObjectMapper().writeValueAsString(new Book(null, "스프링따라하기", "yang"));

        // when (테스트 실행)
        ResultActions resultActions =
                mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(content)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        // then (검증)
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("스프링따라하기"))
                .andDo(MockMvcResultHandlers.print());


    }

}
