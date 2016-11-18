package ua.nure.easygo.model;

public class Mark implements Entity {
	
	public final int id;
	public final double x;
	public final double y;
	public final String name;
	public final int map_id;
	public final String description;

	public Mark(int id, double x, double y, String name, int map_id, String description) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.name = name;
		this.map_id = map_id;
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Mark)) {
			return false;
		}
		return this.id == ((Mark) obj).id;
	}

	@Override
	public int hashCode() {
		return (int) ((x*25.7) + (y / 8.34) + name.length() + id);
	}
}
