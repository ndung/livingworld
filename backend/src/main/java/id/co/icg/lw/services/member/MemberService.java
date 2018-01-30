package id.co.icg.lw.services.member;

import id.co.icg.lw.domain.user.Member;

import java.util.List;

public interface MemberService {
    boolean createMember(String userId, CreateMemberRequest request) throws Exception;
    Member findByUser(String userId);

    boolean updateMember(String userId, CreateMemberRequest createMemberRequest);

    List<Member> findAll();
}
