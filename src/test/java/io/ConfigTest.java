package io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/properties_without_expressions.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("birthday")).isEqualTo("19.08.1992");
    }
    @Test
    void whenPairWithTwoEqualSigns() {
        String path = "./data/properties_without_expressions.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("city")).isEqualTo("Frankfurt=FrankfurtAmMain");
        assertThat(config.value("lastName")).isEqualTo("Malyshev=");
        assertThat(config.value("comment")).isEqualTo("=HelloJava");
    }

    @Test
    void whenPairHasEmptyKeyThenException() {
        String path = "./data/properties_without_key.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, () -> config.load());
    }

    @Test
    void whenPairHasEmptyValueThenException() {
        String path = "./data/properties_without_value.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, () -> config.load());
    }


    @Test
    void whenPairHasNotEqualSignThenException() {
        String path = "./data/properties_without_equal_sign.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, () -> config.load());
    }
}