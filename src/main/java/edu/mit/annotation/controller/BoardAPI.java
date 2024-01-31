package edu.mit.annotation.controller;

import edu.mit.annotation.realdto.CommentDTO;
import edu.mit.annotation.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boardapi")
@RequiredArgsConstructor
public class BoardAPI {

    private final BoardService boardService;

    @GetMapping("/reg-comment")
    public void regComment(CommentDTO dto){
        boardService.registerComment(dto);
    }

    @GetMapping("/read-comment")
    public List<CommentDTO> readComment(Long bno){
        return boardService.readComment(bno);
    }
}
