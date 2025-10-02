package com.example.todolist.dto;

import com.example.todolist.entity.Todo;

public record TodoRequestRecord(String content) {
    public static TodoRequestRecord from(Todo todo){
        return new TodoRequestRecord(todo.getContent());
    }
}
