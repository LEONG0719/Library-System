package com.project.learning.Controller;

import com.project.learning.Domain.Member;
import com.project.learning.Service.IBookService;
import com.project.learning.Service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/Member")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @PostMapping("/addNewMember")
    public Member addNewMember(@RequestBody Member member){
        System.out.println("New Member added successfully");
        return memberService.addNewMember(member);

    }

    @GetMapping("/getAllMember")
    public List<Member> getAllMember(){
        return memberService.getAllMember();

    }

    @PutMapping("/updateMember/{member_id}")
    public Member updateMember(@PathVariable Long member_id,@RequestBody Member updateMember){
        System.out.println("Information of Member updated successfully");
        return memberService.updateMember(member_id,updateMember);
    }

    @DeleteMapping("/deleteMember/{member_id}")
    public void deleteMember(@PathVariable Long member_id){
        memberService.deleteMember(member_id);
        System.out.println("Member already deleted");
    }

    @GetMapping("/getMemberById/{member_id}")
    public Member getMemberById(@PathVariable Long member_id){
        System.out.println("Below is the information for member_id : "+member_id);
        return memberService.getMemberById(member_id);

    }



}
