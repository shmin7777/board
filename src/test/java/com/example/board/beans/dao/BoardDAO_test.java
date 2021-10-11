package com.example.board.beans.dao;

import com.example.board.beans.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardDAO_test {

    @Autowired
    private BoardDAO boardDAO;

    @Test
    public void testRegister() {
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글 3");
        board.setContent("새로 작성하는 내용 3");
        board.setWriter("hds1234");

        boardDAO.register(board);

    }


    @Test
    public void testGet() {
        log.info(boardDAO.get(3L).toString());
    }

    @Test
    public void testModify() {
        if (boardDAO.get(3L) == null) {
            log.info("***********NO SUCH BOARD***********");
        } else {
            BoardVO board = new BoardVO();
            board.setBno(3L);
            board.setTitle("수정된 글 제목");
            board.setContent("수정된 글 내용");
            log.info("UPDATE  : " + boardDAO.modify(board));
        }
    }

    @Test
    public void testRemove() {
        if (boardDAO.get(3L) == null) {
            log.info("***********NO SUCH BOARD***********");
        } else {
            log.info("REMOVE  : " + boardDAO.remove(3L));
        }
    }


    @Test
    public void testGetList(){
        boardDAO.getList().forEach(board->log.info(board.toString()));
    }

}