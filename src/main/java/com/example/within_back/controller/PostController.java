package com.example.within_back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
}

@Controller
@RequestMapping("/post/*")
public class BoardController {
    @Inject
    BoardService boardService;
    UserService userService;

    @RequestMapping(value="/${userId}/boards", method=RequestMethod.GET)
    public void getMyBoard(Model model) throws Exception {
        List<BoardV0> boardList=null;
        boardList=userService.list();
        model.addAttribute("boardList", boardList);
    }  //내 게시판 조회

    @RequestMapping(value="/boards/${category}", method=RequestMethod.GET)
    public void getWrite() throws Exception{

    } //게시판 게시물 조회 (GET 방식)

    @RequestMapping(value="/boards/${category}", method=RequestMethod.POST)
    public String postWrite(BoardV0 vo) throws Exception{
        service.write(vo);
        return "redirect:/boards/${category}/${postId}
    } //게시물 작성 (POST 방식)

    @RequestMapping(value="/boards/${category}/${postId}", method=RequestMethod.GET)
    public void getWrite



}


