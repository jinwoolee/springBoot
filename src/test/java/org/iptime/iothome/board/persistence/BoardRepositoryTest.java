package org.iptime.iothome.board.persistence;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.iptime.iothome.board.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    
    //board make dummy data
    @Test
    public void insertBoard200DataTest() {
        for(int i = 0; i < 200; i++) {
            Board board = new Board();
            board.setTitle("제목.." + i);
            board.setContent("내용 ...." + i + " 채우기");
            board.setWriter("user0" + (i%10));
            boardRepository.save(board);
        }
    }
    
    //query method 테스트
    @Test
    public void queryMethodByTitleTest() {
        /***given***/
        
        /***when***/
        List<Board> boardList = boardRepository.findByTitle("제목..177");
        
        /***then***/
        assertEquals(1, boardList.size());
        assertEquals("내용 ....177 채우기", boardList.get(0).getContent());
    }
    
    //게시글 번호 정렬 페이징
    @Test
    public void findByBnoGreaterThanOrderByBnoDescTest() {
        /***given***/
        Pageable pageable = new PageRequest(1, 10);

        /***when***/
        Collection<Board> boardList = boardRepository.findByBnoGreaterThanOrderByBnoDesc(0L, pageable); 

        /***then***/
        assertEquals(10, boardList.size());
    }
    
    //게시글 번호 페이징 조회(Page<T>)
    @Test
    public void findByBnoGreaterTest() {
        /***given***/
        Pageable pageable = new PageRequest(0, 10, Sort.Direction.ASC, "bno");
        
        /***when***/
        Page<Board> result = boardRepository.findByBnoGreaterThan(0l, pageable);
        
        List<Board> boardList = result.getContent();
        
        /***then***/
        assertEquals(10, boardList.size());
        assertEquals(0, result.getNumber());
    }
    
    //게시글 제목으로 조회  Query annotationgit clone https://github.com/spring-guides/gs-rest-service-cors.git
    @Test
    public void findByTitleQueryATest() {
        /***given***/
        
        /***when***/
        List<Board> boardList = boardRepository.findByTitleQueryA("제목..177");
                
        /***then***/
        assertEquals(1, boardList.size());
    }
}
