package com.agh.surveys.repository;

import com.agh.surveys.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface IGroupRepository extends JpaRepository<Group,String> {

    Optional<Group> findByGroupName (String groupName);

    @Transactional
    void removeByGroupName(String groupName);

}
