package com.example.springblog.repository;

import com.example.springblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
}
