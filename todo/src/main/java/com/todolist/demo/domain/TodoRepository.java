package com.todolist.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUserId(Long id);

    void deleteByUserAndIsCompleted(User User, boolean isCompleted);
}
