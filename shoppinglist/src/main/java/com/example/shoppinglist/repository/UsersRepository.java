package com.example.shoppinglist.repository;

import com.example.shoppinglist.entity.Items;
import com.example.shoppinglist.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    // users가 들고있는 item의 리스트 찾기
    @Query("SELECT i FROM Items i WHERE i.users.id = :userId")
    List<Items> findItemsByUserId(@Param("userId") Long id);
}
