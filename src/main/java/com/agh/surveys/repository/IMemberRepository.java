package com.agh.surveys.repository;


import com.agh.surveys.model.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface IMemberRepository extends JpaRepository<GroupMember, String> {

    Optional<GroupMember> findByNick (String nick);

    @Transactional
    void removeByNick (String nick);

    @Transactional
    void removeByGroupID (Integer id);
}
