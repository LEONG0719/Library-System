package com.project.learning.Service;

import com.project.learning.Domain.Member;

import java.util.List;

public interface IMemberService {
    Member addNewMember(Member member);
    List<Member> getAllMember();
    Member updateMember(Long member_id, Member updateMember);
    void deleteMember(Long member_id);
    Member getMemberById(Long member_id);
}
