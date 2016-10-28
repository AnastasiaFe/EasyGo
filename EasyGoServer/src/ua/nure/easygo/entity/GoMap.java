package ua.nure.easygo.entity;

public class GoMap implements Entity {

	public final int id;
	public final int creator_id;
	public final String name;
	public final String tags;
	public final String data;
	public final String access;

	public GoMap(int id, int creator_id, String name, String tags, String data, String access) {
		this.id = id;
		this.creator_id = creator_id;
		this.name = name;
		this.tags = tags;
		this.data = data;
		this.access = access;
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((GoMap)obj).id;
	}
	
	@Override
	public int hashCode() {
		return id * 7 + creator_id * 15 ;
	}
}
