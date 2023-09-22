package net.daum.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString(exclude = "member") //member 필드 제외 호출
@Entity
@SequenceGenerator( //시퀀스 생성기
        name="bno_seq3_gename", //시퀀스 제네레이터의 이름
        sequenceName = "bno_seq3", //시퀀스의 이름
        initialValue = 1, //시작값
        allocationSize = 1 //증가값 (default = 50)
)
@Table(name="tbl_profile")
@EqualsAndHashCode(of="fname")
public class Profile {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bno_seq3_gename"
    )
    private int fno;

    private String fname; //회원 프로필 사진 파일명
    private boolean current2; //프로필 사진 사용여부

    @ManyToOne // N:1 연관관계
    private MemberVO member;
    // (외래키 -> 주종관계 : Fetch join문을 생성)
    // 주인테이블인 tbl_members의 PK(uid2)컬럼만 저장됨
}
