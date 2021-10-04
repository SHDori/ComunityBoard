package Gachon.ComunityBoard.controller;


import Gachon.ComunityBoard.config.auth.dto.SessionUser;
import Gachon.ComunityBoard.controller.dto.UserResponseDTO;
import Gachon.ComunityBoard.controller.dto.UserUpdateRequestDTO;
import Gachon.ComunityBoard.domain.user.UserRepository;
import Gachon.ComunityBoard.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserInfoApiController {
    private final UserService userService;
    private final UserRepository userRepository;

    // 유저정보 조회
    @GetMapping("/api/user/userInfo/{email}")
    public UserResponseDTO findByEmail(@PathVariable String email, HttpSession session){
        // 조회하려는 사람
        SessionUser user = (SessionUser) session.getAttribute("user");
        // 조회대상 사람
        UserResponseDTO userResponseDTO = userService.findByEamil(email);

        // 조회하려는사람과 조회대상이 같다면(내 프로필을 보려한다면)
        if(user.getEmail().equals(userResponseDTO.getEmail())){
            userResponseDTO.setMine(true); // 수정버튼 보이게
        }else{
            userResponseDTO.setMine(false); // 수정버튼 안보이게
        }
        return userResponseDTO;
    }

    // 유저 정보 수정
    @PutMapping("/api/user/userInfo/{email}")
    public String update(@PathVariable String email, @RequestBody UserUpdateRequestDTO updateDTO){
        //중복된닉네임이면 예외발생
        if(userService.nicknameCheck(updateDTO.getNickname())){
            return userService.update(email, updateDTO);
        }else {
            return "닉네임이 중복된 값입니다.";
        }
    }

    // 닉네임수정 중복확인 (유저정보 수정시 사용)
    @GetMapping("/api/user/userInfo/DuplicateCheck/{nickname}")
    public boolean nicknameDuplicateCheck(@PathVariable String nickname){
        // false 리턴되면 중복된것. true가 리턴되면 중복안된것
        return userService.nicknameCheck(nickname);
    }



}
