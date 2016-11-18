package ua.nure.easygo.model;

public class GoMap implements Entity {
	
	    public static final String TABLE_NAME = "gomaps";
	    public static final String ID_COLUMN = "id";
	    public static final String CREATOR_ID_COLUMN = "creator_id";
	    public static final String NAME_COLUMN = "name";
	    public static final String TAGS_COLUMN = "tags";
	    public static final String DATA_COLUMN = "data";
	    public static final String ACCESS_COLUMN = "access";
	
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
		if (obj == null || !(obj instanceof GoMap)) {
			return false;
		}
		return this.id == ((GoMap)obj).id;
	}
	
	@Override
	public int hashCode() {
		return id * 7 + creator_id * 15 ;
	}
}
