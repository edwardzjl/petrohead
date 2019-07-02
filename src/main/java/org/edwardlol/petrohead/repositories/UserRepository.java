package org.edwardlol.petrohead.repositories;

import org.edwardlol.petrohead.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
