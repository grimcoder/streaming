package com.example.StreamingAPI.service;

import com.example.StreamingAPI.Media;
import com.example.StreamingAPI.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {
    @Autowired
    private MediaRepository repository;

    public Media saveMedia(Media media) {
        return repository.save(media);
    }

    // 1. Get list of Media by the name.
    public List<Media> getMediasByName(String name) {
        return repository.findByName(name);
    }

        // 1. Get list of Media by the name.
    public List<Media> getMedias() {
        return repository.findAll();
    }


    // 2. Get list of Media by id.
    // (Usually, ID should be unique, so we return a single Optional<Media>)
    public Optional<Media> getMediaById(Long id) {
        return repository.findById(id);
    }

    // 3. Delete media by id.
    public void deleteMediaById(Long id) {
        repository.deleteById(id);
    }

    // 4. Delete medias by name.
    public void deleteMediasByName(String name) {
        List<Media> mediasToDelete = repository.findByName(name);
        repository.deleteAll(mediasToDelete);
    }
}
