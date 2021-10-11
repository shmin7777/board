package com.example.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
// 컨트롤러를 테스트하는 문법
public class ControllerTest {

//    가짜 MVC
//    마치 브라우저에서 URL을 요청한 것처럼 환경을 만들어 준다.
    private MockMvc mockMvc;

//     요청을 처리해 주는 WebApplicationContext를 불러온다.
    @Autowired
    private WebApplicationContext webApplicationContext;

//    하위의 모든 테스트가 실행 전에 실행되도록 한다.
    @BeforeEach
    public void setUp(){
//        가짜 MVC에 WebApplicationContext를 전달한 후 환경을 생성한다.
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testList() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                .andReturn().getModelAndView().getModelMap().toString()); //get방식 doGet ..
    }


    @Test
    public void testRegister () throws Exception{
        String bno = mockMvc.perform(
                MockMvcRequestBuilders.post("/board/register")
                .param("title","테스트 새 글 제목")
                .param("content","테스트 새 글 내용")
                .param("writer","hds1234")
        ).andReturn().getFlashMap().toString();

        log.info(bno);
    }


    @Test
    public void testRead() throws Exception{
        String board = mockMvc.perform(
                MockMvcRequestBuilders.get("/board/read")
                .param("bno","5")
        ).andReturn().getModelAndView().getViewName();/*.getModelMap().toString();*/

        log.info(board);

    }

    @Test
    public void testModify() throws Exception{
        String result = mockMvc.perform(
                MockMvcRequestBuilders.post("/board/modify")
                        .param("bno","5")
                        .param("title","테스트 수정된 글 제목")
                        .param("content","테스트 수정된 글 내용")
                        .param("writer","hds1234")
        ).andReturn().getFlashMap().toString();

    }

    @Test
    public void testRemove() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("remove/"));
    }

    @Test
    public void testGoRegister() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/register"))
                .andReturn().getModelAndView().getViewName());
    }

    @Test
    public void testGoModify() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/board/modify").param("bno", "2"))
                .andReturn().getModelAndView().getModelMap().toString();
        log.info(result);
    }
}
