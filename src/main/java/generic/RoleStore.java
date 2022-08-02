package generic;

public class RoleStore implements Store<Role> {

    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role role) {
        store.add(role);
    }

    @Override
    public boolean replace(String artistId, Role role) {
        return store.replace(artistId, role);
    }

    @Override
    public boolean delete(String artistId) {
        return store.delete(artistId);
    }

    @Override
    public Role findById(String artistId) {
        return store.findById(artistId);
    }
}