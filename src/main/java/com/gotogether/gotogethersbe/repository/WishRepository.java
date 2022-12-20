package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {

    Optional<Wish> findByProduct_idAndMember_id(Long productId, Long memberId);

    List<Wish> findByMember_idOrderByIdDesc(Long id);

    @Modifying
    @Query("delete from Wish u where u.id in :list and u.member.id = :memberId")
    void deleteAllByIdInQuery(@Param("list") List<Long> list, @Param("memberId") Long id);

}
