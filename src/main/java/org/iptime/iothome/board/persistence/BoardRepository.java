package org.iptime.iothome.board.persistence;

import java.util.List;

import org.iptime.iothome.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long>{
    
    //query method : 리턴 타입은 Page<T>, Slice<T>, List<T>
    /*
     * find <Entitly> By <Column> 조건 Or/And <Column> 조건 OrderBy<Column>Desc/Asc 
     * 
     * find..By..
     * read..By..
     * query..By..
     * get..By..
     * count..By
     */
    
    //제목 equal query method
    public List<Board> findByTitle(String title);
    
    //게시글 번호 정렬 페이징
    public List<Board> findByBnoGreaterThanOrderByBnoDesc(long bno, Pageable pageable);
    
    //게시글 페이징 조회(page<T>)
    public Page<Board> findByBnoGreaterThan(Long bno, Pageable pageable);
    
    //@Query annotation 
    // JPQL or native SQL 
    
    //제목 equals Query annotation
    //의도한대로 실행계획이 수립되지 않을 때
    @Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findByTitleQueryA(String title);
}
