package net.daum;

import lombok.extern.java.Log;
import net.daum.dao.MemberRepository;
import net.daum.dao.ProfileRepository;
import net.daum.vo.MemberVO;
import net.daum.vo.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log //Lombok 라이브러리로 로그 기록을 사용할 때 이용한다.
@Commit // DB에 커밋하는 용도로
public class ProfileTests {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProfileRepository profileRepository;

    //20명 회원자료추가
    @Test
    public void testInsertMember() {
        IntStream.range(1, 21).forEach(
                i -> {
                    MemberVO member = new MemberVO();
                    member.setUid2("user"+i); member.setUpw("pw"+i); member.setUname("사용자"+i);
                    memberRepository.save(member);
                    System.out.println("----");
                }
        );
    }
    
    //특정 회원에 프로필 사진추가
    @Test
    public void testInsertProfile(){
        MemberVO member = new MemberVO();
        member.setUid2("user1");

        for(int i=1; i<5; i++){
            Profile profile01 = new Profile();
            profile01.setFname("face" + i + ".jpg");

            if(i==1){
                profile01.setCurrent2(true);
            }
            profile01.setMember(member);
            profileRepository.save(profile01);
            System.out.println("----");
        }
    }

    //user1 아이디정보와 프로필개수
    @Test
    public void testSelectProfile(){
        memberRepository.getMemberVoWithProfileCount("user1")
                .forEach(objArr -> System.out.println(Arrays.toString(objArr)));
    }

    @Test
    public void testSelectActivatedProfile(){
        memberRepository.getMemberVOWithActivatedProfile("user1")
                .forEach(objArr -> System.out.println(Arrays.toString(objArr)));
    }

}