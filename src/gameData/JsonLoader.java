package gameData;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class JsonLoader {

    private static final Gson GSON = new Gson();

    private JsonLoader() {

    }

    public static <T> T load(String resourcePath, Class<T> c) {
        try (InputStream is = JsonLoader.class.getResourceAsStream(resourcePath)) {

            if (is == null) {
                throw new IllegalStateException("Nenalezen resource: " + resourcePath);
            }

            return GSON.fromJson(
                    new InputStreamReader(is, StandardCharsets.UTF_8),
                    c
            );

        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + resourcePath, e);
        }
    }
}