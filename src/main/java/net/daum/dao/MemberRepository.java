package net.daum.dao;

import net.daum.vo.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberVO, String> {

    /**
     * JPQL문: tbl_members 테이블에는 레코드가 있으나 tbl_profile 테이블에는 레코드가 없는 경우
     *         left outer join을 한다. 스프링부트2.0 이상과 하이버네이트 5.2.x버전 이후부터는
     *         참조 관계가 없어도 on조건절을 통한 left out join이 가능하다.
     *         JPQL문은 실제 테이블명 대신 엔티티빈 클래스명을 사용하고, 실제 컬럼명대신 속성명(변수명)을 사용한다.
     *         -> JPA에서 Fetch JOIN문이라고 한다. (연관관계라고도 한다.)
     * /
    */
    @Query( "select m.uid2, count(p) " +
            "from MemberVO m left outer join Profile p " +
            "on m.uid2 = p.member " +
            "where m.uid2= ?1 " +
            "group by m")
    public List<Object[]> getMemberVoWithProfileCount(String uid2); //회원아이디와 프로필 사진 개수

    @Query( "select m, p " +
            "from MemberVO m left outer join Profile p " +
            "on m.uid2 = p.member " +
            "where m.uid2=?1 and p.current2=true")
    public List<Object[]> getMemberVOWithActivatedProfile(String uid2); //회원정보와 활성화된 Profile정보
}
