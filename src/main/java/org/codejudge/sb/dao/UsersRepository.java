package org.codejudge.sb.dao;

import org.codejudge.sb.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value = "select * from users where email = :email and password = :password", nativeQuery = true)
    Users findByCredentials(@Param("email") String email, @Param("password") String password);

    @Query(value = "select * from users where email = :email", nativeQuery = true)
    Users findByEmail(@Param("email") String email);

    @Query(value = "select * from users where auth_token = :authToken", nativeQuery = true)
    Users findByAuthToken(@Param("authToken") String authToken);
}
