package com.project.cafearound.domain.cafe.repository;

import com.project.cafearound.domain.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
}
