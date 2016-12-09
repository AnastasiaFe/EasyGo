package ua.nure.easygo.model;

public class GoMap implements Entity {

    public long mapId;
    public String ownerLogin;
    public String name;
    public String tags;
    public String mapAttributes;

    public boolean isPrivate;

    public GoMap() {
    }

    public GoMap(long mapId, String ownerLogin, String name, String tags, String mapAttributes, boolean isPrivate) {
        this.mapId = mapId;
        this.ownerLogin = ownerLogin;
        this.name = name;
        this.tags = tags;
        this.mapAttributes = mapAttributes;
        this.isPrivate = isPrivate;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GoMap)) {
            return false;
        }
        return this.mapId == ((GoMap) obj).mapId;
    }

    @Override
    public int hashCode() {
        return (int) (mapId * 7 + ownerLogin.hashCode() * 15);
    }


}
