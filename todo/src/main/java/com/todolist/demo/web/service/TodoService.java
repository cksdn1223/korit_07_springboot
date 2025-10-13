package com.todolist.demo.web.service;

import com.todolist.demo.domain.Todo;
import com.todolist.demo.domain.TodoRepository;
import com.todolist.demo.domain.User;
import com.todolist.demo.domain.UserRepository;
import com.todolist.demo.dto.TodoRequestDto;
import com.todolist.demo.dto.TodoRequestRecord;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User를 찾을 수 없습니다."));
    }
    private void checkOwnership(Todo todo) throws AccessDeniedException{
         if(!todo.getUser().equals(getCurrentUser())) {
             throw new AccessDeniedException("해당 todo에 접근할 수 없습니다.");
         }
    }
    private Todo findTodo(Long id){
        return todoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 Id 를 가진 Todo를 찾을 수 없습니다. Id : " + id));
    }

    // 1. 모든 할일 목록 조회
    @Transactional
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    // 2. 현재 사용자의 모든 할 일 목록 조회
    @Transactional(readOnly = true)
    public List<Todo> getTodosForCurrentUser() {
        return todoRepository.findAllByUserId(getCurrentUser().getId());
    }

    // 3. 새로운 to.do 추가
    public Todo createTodo(TodoRequestRecord todoRequestRecord) {
        User currentUser = getCurrentUser();
        return todoRepository.save(new Todo(todoRequestRecord.content(), currentUser));
    }

    // 4. 할 일 내용 수정
    @Transactional
    public Todo updateTodoContent(Long id, TodoRequestDto updateDto) throws AccessDeniedException {
        Todo todo = findTodo(id);
        checkOwnership(todo);
        todo.setContent(updateDto.getContent());
        return todoRepository.save(todo);
    }

    // 5. 할 일 삭제
    @Transactional
    public void deleteTodo(Long id) throws AccessDeniedException {
        Todo todo = findTodo(id);
        checkOwnership(todo);
        todoRepository.delete(todo);
    }

    // 6. 할 일 완료 상태 토글
    @Transactional
    public Todo toggleTodoStatus(Long id) throws AccessDeniedException{
        Todo todo = findTodo(id);
        checkOwnership(todo);
        todo.setCompleted(!todo.isCompleted());
        return todoRepository.save(todo);
    }

    // 7. 완료된 할 일 전체 삭제하는 로직
    @Transactional
    public void clearCompletedTodos() {
        todoRepository.deleteByUserAndIsCompleted(getCurrentUser(), true);
    }
}
