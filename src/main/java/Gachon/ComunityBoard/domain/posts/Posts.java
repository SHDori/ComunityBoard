package Gachon.ComunityBoard.domain.posts;

import Gachon.ComunityBoard.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; //게시물의 id

    @Column(length = 500, nullable = false)
    private String title;   //게시물 제목


    private String writer;  //게시물 작성자

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; //모집 소개글


    private String event;   //운동종목


    private int NeedPeopleNumber;   //필요인원

    @Column(nullable = false)
    private boolean IsRecruiting;      //모집중 여부

    // 운동할 위치 좌표값
    private double locationX;
    private double locationY;
    // 운동위치 지역명
    private String locationName;

    // 프론트에서 날짜와 시간선택으로 LocalDateTime으로 받아옴
    private LocalDateTime EventTime;    //운동할 시간
    //삭제여부
    private boolean DeleteYn;

    @Builder
    public Posts(String title, String writer,String content, String event, int needPeopleNumber, double location_x,double location_y, String location_name, LocalDateTime eventTime){
        this.title=title;
        this.writer=writer;
        this.content = content;
        this.event=event;
        this.NeedPeopleNumber = needPeopleNumber;
        this.locationX = location_x;
        this.locationY = location_y;
        this.locationName = location_name;
        this.EventTime = eventTime;
        this.IsRecruiting=true;
        this.DeleteYn = false;
    }

    public void update(String title ,String content, String event, int needPeopleNumber, double location_x,double location_y, String location_name,LocalDateTime modifiedEventTime){
        // 초기화된값이랑 다른값이들어오면 update된 값이므로 수정을하지만
        //초기화된 값이랑 같은값이들어오면 update가 안된거기때문에 그대로 둔다.
        this.title=title;
        this.content = content;
        this.event=event;
        this.NeedPeopleNumber = needPeopleNumber;
        this.locationX = location_x;
        this.locationY = location_y;
        this.locationName = location_name;
        this.EventTime = modifiedEventTime;

    }

    public void delete(boolean deleteYn){
        DeleteYn = deleteYn;
    }



}
