package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.config.auth.dto.SessionUser;
import Gachon.ComunityBoard.controller.dto.*;
import Gachon.ComunityBoard.domain.posts.Posts;
import Gachon.ComunityBoard.domain.user.User;
import Gachon.ComunityBoard.domain.user.UserRepository;
import Gachon.ComunityBoard.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final HttpSession httpSession;
    private final UserRepository userRepository;
    private final PostsService postsService;

    // 게시글 등록
    @PostMapping("/api/board/posts")
    public Long save(@RequestBody PostsSaveRequestDTO saveDTO){
        // 세션에서 지금 로그인한 유저의 정보를 가져옴
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // 그 유저의 이메일을 가저옴
        String userEmail = user.getEmail();
        // 그 이메일로 유저의 DB정보를가져옴
        User userDB = userRepository.findByUserEmail(userEmail);
        // 가저온 DB정보안에있는 해당유저의 닉네임을 가져와서 DTO에 추가해줌
        saveDTO.setWriter(userDB.getNickname());
        return postsService.save(saveDTO);
    }


    // 게시글 조회
    @GetMapping("/api/board/posts/{idx}")
    public PostsResponseDTO findById(@PathVariable Long idx){
        return postsService.findById(idx);
    }


    // 게시글 수정
    @PutMapping("/api/board/posts/{idx}")
    public Long update(@PathVariable Long idx, @RequestBody PostsUpdateRequestDTO updateDTO){
        return postsService.update(idx,updateDTO);
    }


    //게시글 삭제
//    @PutMapping("/api/board/posts/{idx}/isDelete")
//    public Long delete(@PathVariable Long idx, @RequestBody PostsDeleteRequestDTO deleteDTO){
//        postsService.delete(idx, deleteDTO);
//        return idx;
//    }
    @PutMapping("/api/board/posts/{idx}/isDelete")
    public Long delete(@PathVariable Long idx){
        PostsDeleteRequestDTO deleteDTO = new PostsDeleteRequestDTO();
        postsService.delete(idx, deleteDTO);
        return idx;
    }


    // 임시로 해보는것들

//    @GetMapping("/api/board/posts/{keyword}")
//    public PostsListResponseDTO

//    @GetMapping("/api/board")
//    public Page<PostsListResponseDTO> paging(@PageableDefault(size = 5, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageRequest){
//        Page<PostsListResponseDTO> pagingList =postsService.paging()
//        return pagingList;
//    }










}
