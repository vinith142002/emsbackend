package com.excelr.Repo;


import com.excelr.entity.User;
import com.excelr.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole userRole);

	Optional<User> findByEmail(String email);

	Optional<User> findByResetToken(String token);
}
