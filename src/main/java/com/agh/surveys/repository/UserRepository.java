package com.agh.surveys.repository;

import com.agh.surveys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByNick(String nick);

    @Transactional
    void removeByNick(String nick);
}
