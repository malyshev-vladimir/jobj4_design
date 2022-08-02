package generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsBuratino() {
        UserStore store = new UserStore();
        store.add(new User("2", "Buratino"));
        User result = store.findById("2");
        assertThat(result.getUsername()).isEqualTo("Buratino");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("3", "Buratino"));
        User result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleIsBuratino() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Buratino"));
        store.add(new Role("2", "Malvina"));
        Role result = store.findById("2");
        assertThat(result.getRole()).isEqualTo("Buratino");
    }

    @Test
    void whenReplaceThenBuratinoIsMalvina() {
        RoleStore store = new RoleStore();
        store.add(new Role("5", "Buratino"));
        store.replace("5", new Role("5", "Malvina"));
        Role result = store.findById("5");
        assertThat(result.getRole()).isEqualTo("Malvina");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("8", "Buratino"));
        store.replace("13", new Role("13", "Malvina"));
        Role result = store.findById("8");
        assertThat(result.getRole()).isEqualTo("Buratino");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("4", "Buratino"));
        store.delete("4");
        Role result = store.findById("4");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleIsBuratino() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Buratino"));
        store.delete("14");
        Role result = store.findById("2");
        assertThat(result.getRole()).isEqualTo("Buratino");
    }
}