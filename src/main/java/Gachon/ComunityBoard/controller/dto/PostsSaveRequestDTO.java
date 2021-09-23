package Gachon.ComunityBoard.controller.dto;

import Gachon.ComunityBoard.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDTO { // 저장요청할때 쓰는 DTO
    private String title;
    private String writer;
    private String content;
    private String event;
    private int needPeopleNum;
    private int location;
    private LocalDateTime EventTime;

    @Builder
    public PostsSaveRequestDTO(String title, String writer,String content, String event, int needPeopleNumber, int location,LocalDateTime EventTime){
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.event = event;
        this.needPeopleNum = needPeopleNumber;
        this.location = location;
        this.EventTime = EventTime;
    }

    public Posts toEntity(){ // Posts객체를 이 DTO안의 내용을 바탕으로 만들어서 반환
        return Posts.builder()
                .title(title).writer(writer).content(content).event(event)
                .needPeopleNumber(needPeopleNum).location(location).eventTime(EventTime)
                .build();
    }

}
