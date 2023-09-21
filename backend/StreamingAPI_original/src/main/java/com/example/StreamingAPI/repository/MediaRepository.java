package com.example.StreamingAPI.repository;

import com.example.StreamingAPI.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByName(String name);
}
