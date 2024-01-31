package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.BoardRequest;
import edu.mit.annotation.realdto.BoardResponse;

import java.util.List;

public interface BoardMapper {

    void save(BoardRequest params);

    BoardResponse findById(int bno);

    void update(BoardRequest params);

    void deleteById(int bno);

    List<BoardResponse> findAll();

    void addcount(BoardRequest params);



}
