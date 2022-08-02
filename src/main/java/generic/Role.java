package generic;

public class Role extends Base {

    private final String role;

    public Role(String artistId, String name) {
        super(artistId);
        this.role = name;
    }

    public String getRole() {
        return role;
    }
}