package com.project.learning.Repo;

import com.project.learning.Domain.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepo extends JpaRepository<Member, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM member WHERE member_id= :member_id")
    void deleteMember(@Param("member_id")Long member_id);
}
