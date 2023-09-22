package net.daum.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@ToString
@Entity
@Table(name="tbl_members")
@EqualsAndHashCode(of="uid2")
// equals(), hashCode(), canEqual() 메서드 자동 생성
public class MemberVO { //회원 Entity빈 클래스

    @Id //엔티티 빈을 식별할 수 있도록 해주는 식별키(구분키) : PK
    private String uid2; //회원아이디
    private String upw; //비밀번호
    private String uname; //회원이름
}
