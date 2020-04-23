package com.agh.surveys.repository;

import com.agh.surveys.model.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {

    Optional<Group> findByGroupName (String groupName);

    @Transactional
    void removeByGroupName(String groupName);

}
