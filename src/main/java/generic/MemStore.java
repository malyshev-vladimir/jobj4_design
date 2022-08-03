package generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        if (!storage.containsKey(model.getId())) {
            storage.put(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = storage.containsKey(id);
        if (result) {
            storage.put(id, model);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = storage.containsKey(id);
        if (result) {
            storage.remove(id);
        }
        return result;
    }

    @Override
    public T findById(String id) {
        return storage.containsKey(id) ? storage.get(id) : null;
    }
}