package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.controller.dto.PostsDeleteRequestDTO;
import Gachon.ComunityBoard.controller.dto.PostsResponseDTO;
import Gachon.ComunityBoard.controller.dto.PostsSaveRequestDTO;
import Gachon.ComunityBoard.controller.dto.PostsUpdateRequestDTO;
import Gachon.ComunityBoard.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    // 게시글 등록
    @PostMapping("/api/board/posts")
    public Long save(@RequestBody PostsSaveRequestDTO saveDTO){
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
    @PutMapping("/api/board/posts/{idx}/isDelete")
    public Long delete(@PathVariable Long idx, @RequestBody PostsDeleteRequestDTO deleteDTO){
        postsService.delete(idx, deleteDTO);
        return idx;
    }






}
