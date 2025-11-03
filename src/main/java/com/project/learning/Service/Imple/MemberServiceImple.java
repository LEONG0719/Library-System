package com.project.learning.Service.Imple;

import com.project.learning.Domain.Member;
import com.project.learning.Repo.MemberRepo;
import com.project.learning.Service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MemberServiceImple implements IMemberService {

    @Autowired
    private MemberRepo memberRepo;

    @Override
    public Member addNewMember(Member member){
        if (member == null){
            throw new IllegalArgumentException("hi");
        }
        if (member.getMember_name().isBlank()){
            throw new IllegalArgumentException("Member Name cannot be blank");
        }
        if (member.getMember_phone().isBlank()){
            throw new IllegalArgumentException("Member phone cannot be blank");
        }
        return memberRepo.save(member);
    }

    @Override
    public List<Member>getAllMember(){
        return memberRepo.findAll();
    }

    @Override
    public Member updateMember(Long member_id, @RequestBody Member updatedMember) {
        Member existingMember = memberRepo.findById(member_id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + member_id));

        if (updatedMember.getMember_name() != null && !updatedMember.getMember_name().isBlank()) {
            existingMember.setMember_name(updatedMember.getMember_name());
        }

        if (updatedMember.getMember_email() != null && !updatedMember.getMember_email().isBlank()) {
            existingMember.setMember_email(updatedMember.getMember_email());
        }

        if (updatedMember.getMember_phone() != null && !updatedMember.getMember_phone().isBlank()) {
            existingMember.setMember_phone(updatedMember.getMember_phone());
        }

        if (updatedMember.getJoinDate() != null) {
            existingMember.setJoinDate(updatedMember.getJoinDate());
        }

        Member savedMember = memberRepo.save(existingMember);

        System.out.println("Member with ID " + member_id + " updated successfully.");
        return savedMember;
    }


    @Override
    public void deleteMember(Long member_id){
        memberRepo.deleteMember(member_id);
    }

    @Override
    public Member getMemberById(Long member_id){
        return memberRepo.findById(member_id)
                .orElseThrow(()->new RuntimeException("Member not found with id: " + member_id));

    }


}
