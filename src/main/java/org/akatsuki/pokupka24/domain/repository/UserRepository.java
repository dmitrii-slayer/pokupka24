package org.akatsuki.pokupka24.domain.repository;

import org.akatsuki.pokupka24.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u from User u where day(u.birthDate) = ?1 and month(u.birthDate) = ?2")
    List<User> findByBirthday(int day, int month);
}
