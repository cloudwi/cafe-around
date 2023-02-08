package com.project.cafearound.domain.user.repository;

import com.project.cafearound.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
