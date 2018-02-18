package org.iptime.iothome.board.persistence;

import static org.junit.Assert.*;

import org.iptime.iothome.board.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    
    @Test
    public void inspectTest() {

        Class<?> clz = boardRepository.getClass();
        System.out.println("clz.getName() : " + clz.getName());
        
        Class<?>[] interfaces = clz.getInterfaces();
        for(Class cls : interfaces)
            System.out.println("interfaces : " + cls.getName());
        
        System.out.println("clz.getSuperclass() : " + clz.getSuperclass());    
    }
    
    //게시글 입력 테스트
    @Test
    public void insertTest() {
        /***given***/
        Board board = new Board();
        board.setTitle("게시물의 제목");
        board.setContent("게시물 내용 넣기...");
        board.setWriter("brown");
        
        /***when***/
        boardRepository.save(board);

        /***then***/
        assertTrue(true);
    }
    
    //게시글 조회 테스트
    @Test
    public void readTest() {
        /***given***/
        long id = 1L;
                
        /***when***/
        Board board = boardRepository.findOne(id);
        
        /***then***/
        assertEquals("게시물의 제목", board.getTitle());
        assertEquals("게시물 내용 넣기...", board.getContent());
        assertEquals("brown", board.getWriter());
    }
    
    //게시글 수정 테스트
    @Test
    public void updateTest() {
        /***given***/
        long id = 1L;
        Board board = boardRepository.findOne(id);
        
        /***when***/
        board.setTitle("게시물의 제목 수정");
        boardRepository.save(board);
        
        /***then***/
        Board updateBoard = boardRepository.findOne(id);
        assertEquals("게시물의 제목 수정", updateBoard.getTitle());
        assertEquals("게시물 내용 넣기...", updateBoard.getContent());
        assertEquals("brown", updateBoard.getWriter());
    }
    
    //게시글 삭제 테스트
    @Test
    public void deleteTest() {
        /***given***/
        long id = 1L;
        Board board = boardRepository.findOne(id);
        
        /***when***/
        boardRepository.delete(board);

        /***then***/
        Board deletedBoard = boardRepository.findOne(id);
        assertNull(deletedBoard);
    }
}
