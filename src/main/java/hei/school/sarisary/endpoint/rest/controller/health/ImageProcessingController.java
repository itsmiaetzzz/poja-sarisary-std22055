package hei.school.sarisary.endpoint.rest.controller.health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ImageProcessingController {

    // Pour stocker les résultats de traitement d'images
    private Map<String, String> processedImages = new HashMap<>();

    @PutMapping("/black-and-white/{id}")
    public ResponseEntity<Void> processImage(@RequestBody String imageBase64, @RequestParam String id) {
        processedImages.put(id, "Processed");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/black-and-white/{id}")
    public ResponseEntity<Map<String, String>> getImageUrls(@PathVariable String id) {
        if (processedImages.containsKey(id)) {
            String originalUrl = "https://original.url/" + id;
            String transformedUrl = "https://transformed.url/" + id;
            Map<String, String> response = new HashMap<>();
            response.put("original_url", originalUrl);
            response.put("transformed_url", transformedUrl);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

