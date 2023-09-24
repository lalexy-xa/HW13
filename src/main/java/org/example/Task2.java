package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Task2 {
    public static void userComments(int userId, int postId) throws IOException, InterruptedException{
        File comments = new File(String.format("src/main/java/org/example/user-%d-post-%d-comments.json", userId, postId));
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("https://jsonplaceholder.typicode.com/posts/%d/comments", postId)))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        try (FileWriter writer = new FileWriter(comments))
        {
            writer.write(response.body());
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
