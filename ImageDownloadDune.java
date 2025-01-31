import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class ImageDownloadDune extends Application {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        List<String> links = new ArrayList<>();
        try {
            BufferedReader buffread = new BufferedReader(new FileReader("duneimperium.txt"));
            buffread.lines().forEach(e -> {
                lines.add(e.trim());
            });
        } catch (Exception e) {
            // TODO: handle exception
        }
        // launch(App.class);
        int counter = 0;
        for (String line : lines) {
            if (line.startsWith("src")) {
                System.out.println(line.substring(5, line.length() - 1));
                links.add(line.substring(5, line.length() - 1));
                System.out.println();
                counter++;
            }
        }
        System.out.println("cards count = " + counter);
        int CardCounter = 1;
        try {
            for (String link : links) {
                downloadImage(link, "Card" + CardCounter + ".avif");
                CardCounter++;

            }
            System.out.println("Image downloaded successfully: ");
        } catch (IOException e) {
            System.err.println("Failed to download image: " + e.getMessage());
        }
    }

    public static void downloadImage(String imageUrl, String savePath) throws IOException {
        URL url = new URL(imageUrl);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Path.of(savePath), StandardCopyOption.REPLACE_EXISTING);
        }
    }

}
