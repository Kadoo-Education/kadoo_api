package com.kadoo_academy.kadoo.repositories;

import com.kadoo_academy.kadoo.models.Edict;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.models.UserEdict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEdictRepository extends JpaRepository<UserEdict, Long> {

    boolean existsByUserSubscribeAndEdict(User user, Edict edict);

    List<UserEdict> findByUserSubscribe(User user);

}
