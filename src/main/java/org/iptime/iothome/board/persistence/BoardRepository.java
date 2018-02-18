package org.iptime.iothome.board.persistence;

import org.iptime.iothome.board.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long>{

}
