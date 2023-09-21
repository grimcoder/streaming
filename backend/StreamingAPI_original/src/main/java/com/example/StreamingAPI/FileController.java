package com.example.StreamingAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.StreamingAPI.service.MediaService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "*")
@RestController
public class FileController {
    private static String UPLOADED_FOLDER = "/Users/t0k05ap/uploads/";
    @Autowired
    private MediaService service;
    private ObjectMapper mapper;

    String defaultDashName = "list.mpd";
    String cda_uri = "http://localhost:3030/";


    public FileController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("/list")
    public ResponseEntity<JsonNode> mediaList() {
            var list = service.getMedias();
            return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<JsonNode> singleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            ObjectNode response = mapper.createObjectNode();
            response.put("error", "file is empty");
            return ResponseEntity.ofNullable(response);
        }

        try {
            byte[] bytes = file.getBytes();
            String name = file.getOriginalFilename();

            var medias = service.getMediasByName(name);
            if (medias.size() > 0) {
                ObjectNode response = mapper.createObjectNode();
                response.put("error", "file already exists");
                return ResponseEntity.ofNullable(response);
            }

            Path path = Paths.get(UPLOADED_FOLDER + name);

            if (!Files.exists(path) || !Files.isDirectory(path)) {
                Files.createDirectories(path);
            }
            var newOriginalFilePath = Path.of(path.toString(), "original.mp4");
            Files.write(newOriginalFilePath, bytes);

            Media media = new Media();
            media.name = name;
            media.uri = cda_uri + name + "/list.mpd";

            service.saveMedia(media);
            segmentToDash(newOriginalFilePath.toString(), 
            Paths.get(path.toString() + "/list.mpd").toString());

        } catch (IOException e) {
            e.printStackTrace();
            ObjectNode response = mapper.createObjectNode();
            response.put("error", e.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ObjectNode response = mapper.createObjectNode();
        response.put("success", "Successfully uploaded!");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public void segmentToDash(String inputFilePath, String outputManifestPath) {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "ffmpeg",
                "-i", inputFilePath,
                "-vf", "scale=1280:720",
                "-c:v", "libx264",
                "-b:v", "1500k",
                "-keyint_min", "60",
                "-g", "60",
                "-hls_time", "10",
                "-hls_playlist_type", "vod",
                "-b:a", "128k",
                "-c:a", "aac",
                "-strict", "-2",
                "-f", "dash",
                outputManifestPath);
        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            // Handle exception
            e.printStackTrace();
        }
    }
}
