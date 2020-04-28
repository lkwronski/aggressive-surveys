package com.agh.surveys.repository;

import com.agh.surveys.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUserNick(String nick);

    Optional<User> findByUserEmail(String email);

    @Transactional
    void removeByUserNick(String nick);

    Optional <List<User>> findByUserNickIn(List<String> userNicks);
}
