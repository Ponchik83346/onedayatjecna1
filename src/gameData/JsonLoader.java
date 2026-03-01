package gameData;
import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
/**
 * Utility třída pro načítání JSON dat ze zdrojových souborů.
 * Používá knihovnu Gson pro deserializaci objektů.
 */
public final class JsonLoader {

    private static final Gson GSON = new Gson();

    private JsonLoader() {

    }
    /**
     * Načte JSON soubor z resources a převede jej na objekt daného typu.
     *
     * @param resourcePath cesta k resource souboru
     * @param c třída výsledného objektu
     * @return deserializovaný objekt z JSON
     * @param <T> typ objektu
     * @throws IllegalStateException pokud resource neexistuje
     * @throws RuntimeException pokud dojde k chybě při načítání JSON
     */
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