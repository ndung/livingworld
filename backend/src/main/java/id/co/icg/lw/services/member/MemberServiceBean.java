package id.co.icg.lw.services.member;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceBean implements MemberService{
    @Override
    public boolean createMember(CreateMemberRequest request) {
        return false;
    }
}
