package org.akatsuki.pokupka24.domain.repository;

import org.akatsuki.pokupka24.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
