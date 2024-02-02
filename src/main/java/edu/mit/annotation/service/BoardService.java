package edu.mit.annotation.service;

import edu.mit.annotation.mapper.BoardMapper;
import edu.mit.annotation.realdto.BoardRequest;
import edu.mit.annotation.realdto.BoardResponse;
import edu.mit.annotation.realdto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public Integer savePost(final BoardRequest params) {
        boardMapper.save(params);
        return params.getBno();
    }

    /**
     * 게시글 상세정보 조회
     * @param bno - PK
     * @return 게시글 상세정보
     */
    public BoardResponse findPostById(final int bno) {
        return boardMapper.findById(bno);
    }


    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Integer updatePost(final BoardRequest params) {
        boardMapper.update(params);
        return params.getBno();
    }

    /**
     * 게시글 삭제
     * @param bno - PK
     * @return PK
     */
    public Integer deletePost(final int bno) {
        boardMapper.deleteComment(bno);
        boardMapper.deleteById(bno);
        return bno;
    }

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    public List<BoardResponse> findAllPost() {
        return boardMapper.findAll();
    }


    /**
     * 게시글 조회수 증감
     * @param params
     * @return
     */
    @Transactional
    public Integer addCount(BoardRequest params){
        boardMapper.addcount(params);
        return params.getBno();
    }


    //댓글저장
    public void registerComment(CommentDTO dto){
        boardMapper.regComment(dto);
    }

    //댓글읽기
    public List<CommentDTO> readComment(Long bno){
       return boardMapper.readComment(bno);
    }

}
