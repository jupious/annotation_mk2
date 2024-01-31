package edu.mit.annotation.controller;


import edu.mit.annotation.realdto.BoardRequest;
import edu.mit.annotation.realdto.BoardResponse;
import edu.mit.annotation.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성 페이지
    @GetMapping("write")
    public String openPostWrite(){
        return "post/write";
    }

    @GetMapping("modify")
    public String modifyWrite(@RequestParam final int bno, Model model) {
        BoardResponse post = boardService.findPostById(bno);
        model.addAttribute("post", post);
        System.out.println(post);
        return "post/modify";
    }



    // 신규 게시글 생성
    @PostMapping("save")
    public String savePost(final BoardRequest params, String email) {
        params.setWriter(email);
        boardService.savePost(params);
        return "redirect:/post/list";
    }

    //게시글 수정
    @PostMapping("update")
    public String updatePost(BoardRequest params, String email){
        params.setWriter(email);
        boardService.updatePost(params);
        return "redirect:/post/list";
    }


    @PostMapping("count")
    public String addCount(BoardRequest params){
        boardService.addCount(params);
        return "redirect:/post/view?bno="+params.getBno();
    }



    //게시글 리스트 불러오기
    @GetMapping("list")
    public String openPostList(Model model){
        List<BoardResponse> posts = boardService.findAllPost();
        model.addAttribute("posts",posts);
        return "post/list";
    }

    // 게시글 상세 페이지
    @GetMapping("view")
    public String openPostView(@RequestParam int bno, Model model,BoardRequest params) {
        BoardResponse post = boardService.findPostById(bno);
        boardService.addCount(params);
        model.addAttribute("post", post);
        return "post/view";
    }

    @PostMapping("delete")
    public String deletePost(@RequestParam final int bno) {
        boardService.deletePost(bno);
        return "redirect:/post/list";
    }


}
