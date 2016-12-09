package ua.nure.easygo.model;

public class Point implements Entity {

	public long pointId;
	public float x, y;
	public String name;
	public long mapId;
	public String attributeValues;

	public Point() {
	}

	public Point(long pointId, float x, float y, String name, long mapId, String attributeValues) {
		this.pointId = pointId;
		this.x = x;
		this.y = y;
		this.name = name;
		this.mapId = mapId;
		this.attributeValues = attributeValues;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Point)) {
			return false;
		}
		return this.pointId == ((Point) obj).pointId;
	}

	@Override
	public int hashCode() {
		return (int) ((x * 25.7) + (y / 8.34) + name.length() + pointId);
	}
}
