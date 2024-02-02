package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.BoardRequest;
import edu.mit.annotation.realdto.BoardResponse;
import edu.mit.annotation.realdto.CommentDTO;

import java.util.List;

public interface BoardMapper {

    void save(BoardRequest params);

    BoardResponse findById(int bno);

    void update(BoardRequest params);

    void deleteById(int bno);

    //댓글삭제
    void deleteComment(int bno);

    List<BoardResponse> findAll();

    void addcount(BoardRequest params);

    //댓글등록
    void regComment(CommentDTO dto);

    //댓글가져오기

    List<CommentDTO> readComment(Long bno);

}
