package ua.nure.easygo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import ua.nure.easygo.model.GoMap;

public class GoMapDAO implements IGoMapDAO {
	Map<String, GoMap> profsMap = new HashMap<String, GoMap>();
	DataSource datasource;
	private SimpleJdbcInsert insertMap;
	private JdbcTemplate templMap;

	public void setDataSource(DataSource dataSource) {
		this.templMap = new JdbcTemplate(dataSource);
		this.insertMap = new SimpleJdbcInsert(dataSource).withTableName("gomap");
	}

	private IGoMapDAO goMapDAO;

	public IGoMapDAO getGoMapDAO() {
		return goMapDAO;
	}

	public void setmapsDAO(IGoMapDAO mapsDAO) {
		this.goMapDAO = mapsDAO;
	}

	public GoMap getMap(int id) {
		if ((templMap.queryForInt("Select count(1) FROM maps WHERE id = '" + id + "'")) > 0) {
			GoMap map = (GoMap) templMap.queryForObject("SELECT * FROM maps WHERE id = '" + id + "'",
					new RowMapper<GoMap>() {
						public GoMap mapRow(ResultSet rs, int rowNum) throws SQLException {
							GoMap map = new GoMap(Integer.parseInt(rs.getString("id")),
									Integer.parseInt(rs.getString("map_id")), rs.getString("name"),
									rs.getString("tags"), rs.getString("data"), rs.getString("access"));
							return map;
						}
					});

			return map;
		} else {
			return null;
		}
	}

	public GoMap createMap(GoMap map) {
		if (map != null) {
			Map<String, Object> parameters = new HashMap<String, Object>(6);
			/*
			 * changed by Anexander Shylin on 18.11.2016 to avoid adding data
			 * with the same id
			 * 
			 * int uuid = Integer.parseInt(UUID.randomUUID().toString());
			 * map.id = uuid;
			 */

			List<GoMap> maxId = null;
			try {
				maxId = ua.nure.easygo.DBconnect.MySqlConnector.selectGoMap("select * from map having MAX(id)");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (maxId != null && maxId.size() > 0) {
				// if not first 
				parameters.put("id", maxId.get(0).id + 1);
			} else {
				// if first map in the table 
				parameters.put("id", 1);
			}
			if (map.name != null)
				parameters.put("name", map.name);
			if (map.creator_id != 0)
				parameters.put("creator_id", map.creator_id);
			if (map.tags != null)
				parameters.put("tags", map.tags);
			if (map.data != null)
				parameters.put("data", map.data);
			if (map.access != null)
				parameters.put("access", map.access);

			insertMap.execute(parameters);

			return map;
		} else {
			return null;
		}
	}

	@Override
	public GoMap updateMap(GoMap map) {
		if (map != null && map.id != 0) {
			GoMap oldmap = getMap(map.id);
			String sqlUpdate = String.format(
					"UPDATE maps SET creator_id=%s, " + "name = %s, data = %s, tags = %s, access = %s WHERE id = %s",
					map.creator_id, map.name, map.data, map.tags, map.access, map.id);
			System.out.println(sqlUpdate);
			templMap.update(sqlUpdate);
			return oldmap;
		} else {
			return null;
		}
	}

	@Override
	public boolean removeMap(int id) {
		if (templMap.update("DELETE FROM maps WHERE id = '" + id + "'") > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<GoMap> getGoMapList(String parameters) {
		List<GoMap> mapList = (List<GoMap>) templMap.query("SELECT * FROM maps;", new RowMapper<GoMap>() {
			public GoMap mapRow(ResultSet rs, int rowNum) throws SQLException {
				GoMap map = new GoMap(Integer.parseInt(rs.getString("id")),
						Integer.parseInt(rs.getString("creator_id")), rs.getString("name"), rs.getString("tags"),
						rs.getString("data"), rs.getString("access"));

				return map;
			}
		});

		return mapList;
	}

	/*
	 * public final int id; public final int creator_id; public final String
	 * name; public final String tags; public final String data; public final
	 * String access;
	 */

}
