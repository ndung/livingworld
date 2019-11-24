package id.co.icg.lw.services.member;

import id.co.icg.lw.domain.user.Member;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.repositories.MemberRepository;
import id.co.icg.lw.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceBean implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserService userService;

    @Override
    public boolean createMember(String userId, CreateMemberRequest request) throws Exception {
        User user = userService.findOne(userId);
        Member member = memberRepository.findByUser(user);
        if (member != null) {
            throw new Exception("User has been registered");
        }
        member = new Member();
        member.setIdentityName(request.getFullName());
        member.setAddress(request.getAddress());
        member.setHomePhone(request.getHomePhone());
        member.setGender(request.getGender());
        member.setCity(request.getCity());
        member.setMartialStatus(request.getMartialStatus());
        member.setReligion(request.getReligion());
        member.setDateOfBirth(request.getDateOfBirth());
        member.setZipcode(request.getZipCode());
        member.setNationalitly(request.getNationality());
        member.setMemberType("0");
        member.setUser(user);

        memberRepository.save(member);

        return true;
    }

    @Override
    public Member findByUser(String userId) {
        User user = userService.findOne(userId);
        return memberRepository.findByUser(user);
    }

    @Override
    public boolean updateMember(String userId, CreateMemberRequest request) {
        Member member = findByUser(userId);
        if (member == null) {
            return false;
        }

        member.setIdentityName(request.getFullName());
        member.setAddress(request.getAddress());
        member.setHomePhone(request.getHomePhone());
        member.setGender(request.getGender());
        member.setCity(request.getCity());
        member.setMartialStatus(request.getMartialStatus());
        member.setReligion(request.getReligion());
        member.setDateOfBirth(request.getDateOfBirth());
        member.setZipcode(request.getZipCode());
        member.setNationalitly(request.getNationality());

        memberRepository.save(member);

        return true;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
