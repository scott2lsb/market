package cn.com.carit.market.dao.impl.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.com.carit.market.bean.app.AppVersionFile;
import cn.com.carit.market.bean.portal.PortalAppVersionFile;
import cn.com.carit.market.common.Constants;
import cn.com.carit.market.common.utils.DataGridModel;
import cn.com.carit.market.common.utils.JsonPage;
import cn.com.carit.market.common.utils.StringUtil;
import cn.com.carit.market.dao.app.AppVersionFileDao;
import cn.com.carit.market.dao.impl.BaseDaoImpl;

/**
 * AppVersionFileDaoImpl Auto generated Code
 */
@Repository
public class AppVersionFileDaoImpl extends BaseDaoImpl implements
		AppVersionFileDao {
	private final RowMapper<AppVersionFile> rowMapper = new RowMapper<AppVersionFile>() {

		@Override
		public AppVersionFile mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			AppVersionFile appVersionFile = new AppVersionFile();
			appVersionFile.setId(rs.getInt("id"));
			appVersionFile.setAppId(rs.getInt("app_id"));
			appVersionFile.setVersion(rs.getString("version"));
			appVersionFile.setSize(rs.getString("size"));
			appVersionFile.setFilePath(rs.getString("file_path"));
			appVersionFile.setNewFeatures(rs.getString("new_features"));
			appVersionFile.setEnNewFeatures(rs.getString("en_new_features"));
			appVersionFile.setStatus(rs.getInt("status"));
			appVersionFile.setCreateTime(rs.getTimestamp("create_time"));
			appVersionFile.setUpdateTime(rs.getTimestamp("update_time"));
			appVersionFile.setAppName(rs.getString("app_name"));
			appVersionFile.setEnName(rs.getString("en_name"));
			return appVersionFile;
		}
	};

	@Override
	public int add(final AppVersionFile appVersionFile) {
		final String sql = "insert into t_app_version_file (app_id"
				+ ", version, size, file_path, new_features, en_new_features, status"
				+ ", create_time" + ", update_time" + ") values (?"
				+ ", ?, ?, ?, ?,?, ?, now(), now())";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		KeyHolder gkHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				int i=1;
				ps.setInt(i++, appVersionFile.getAppId());
				ps.setString(i++, appVersionFile.getVersion());
				ps.setString(i++, appVersionFile.getSize());
				ps.setString(i++, appVersionFile.getFilePath());
				ps.setString(i++, appVersionFile.getNewFeatures());
				ps.setString(i++, appVersionFile.getEnNewFeatures());
				ps.setInt(i++, appVersionFile.getStatus());
				return ps;
			}
		}, gkHolder);
		return gkHolder.getKey().intValue();
	}

	@Override
	public int delete(int id) {
		String sql = "delete from t_app_version_file where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int deleteByAppId(int appId) {
		String sql = "delete from t_app_version_file where app_id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, appId);
	}

	@Override
	public int update(AppVersionFile appVersionFile) {
		StringBuilder sql = new StringBuilder(
				"update t_app_version_file set update_time=now()");
		List<Object> args = new ArrayList<Object>();
		if (appVersionFile.getAppId() != null) {
			sql.append(", app_id=?");
			args.add(appVersionFile.getAppId());
		}
		if (StringUtils.hasText(appVersionFile.getVersion())) {
			sql.append(", version=?");
			args.add(appVersionFile.getVersion());
		}
		if (StringUtils.hasText(appVersionFile.getSize())) {
			sql.append(", size=?");
			args.add(appVersionFile.getSize());
		}
		if (StringUtils.hasText(appVersionFile.getFilePath())) {
			sql.append(", file_path=?");
			args.add(appVersionFile.getFilePath());
		}
		if (StringUtils.hasText(appVersionFile.getNewFeatures())) {
			sql.append(", new_features=?");
			args.add(appVersionFile.getNewFeatures());
		}
		if (StringUtils.hasText(appVersionFile.getEnNewFeatures())) {
			sql.append(", en_new_features=?");
			args.add(appVersionFile.getEnNewFeatures());
		}
		if (appVersionFile.getStatus() != null) {
			sql.append(", status=?");
			args.add(appVersionFile.getStatus());
		}
		sql.append(" where id=?");
		args.add(appVersionFile.getId());
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql.toString(), args.toArray());
	}

	@Override
	public int updateToInvalidByAppId(int appId, int exceptedId) {
		StringBuilder sql = new StringBuilder(
				"update t_app_version_file set update_time=now(), status=? where app_id=?");
		List<Object> args = new ArrayList<Object>();
		args.add(Constants.STATUS_INVALID);
		args.add(appId);
		if (exceptedId>0) {
			sql.append(" and id!=?");
			args.add(exceptedId);
		}
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql.toString(), args.toArray());
	}
	@Override
	public int updateToValidByAppIdAndVersion(int appId, String version) {
		String sql="update t_app_version_file set update_time=now(), status=? where app_id=? and version=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, Constants.STATUS_VALID, appId, version);
	}

	@Override
	public AppVersionFile queryById(int id) {
		String sql = "select a.*,b.app_name, b.en_name from t_app_version_file a left join t_application b on a.app_id=b.id where a.id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, rowMapper);
	}


	@Override
	public AppVersionFile queryValidVersionByAppId(int appId) {
		String sql="select a.*,b.app_name, b.en_name from t_app_version_file a left join t_application b on a.app_id=b.id where a.app_id=? and a.status=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.queryForObject(sql, new Object[]{appId, Constants.STATUS_VALID}, rowMapper);
	}
	
	@Override
	public List<AppVersionFile> queryByAppId(int appId) {
		String sql="select a.*,b.app_name, b.en_name from t_app_version_file a left join t_application b on a.app_id=b.id where a.app_id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{appId}, rowMapper);
	}

	@Override
	public List<AppVersionFile> queryByAppIdAndExceptId(int appId, int exceptedId) {
		String sql="select a.*,b.app_name, b.en_name from t_app_version_file a " +
				"left join t_application b on a.app_id=b.id where a.app_id=? and a.id!=? order by a.id desc";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{appId, exceptedId}, rowMapper);
	}

	@Override
	public List<AppVersionFile> query() {
		String sql="select a.*,b.app_name, b.en_name from t_app_version_file a left join t_application b on a.app_id=b.id";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return this.jdbcTemplate.query(sql,rowMapper);
	}

	@Override
	public List<AppVersionFile> queryByExemple(AppVersionFile appVersionFile) {
		StringBuilder sql = new StringBuilder(
				"select a.*,b.app_name, b.en_name from t_app_version_file a left join t_application b on a.app_id=b.id where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, appVersionFile));
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public JsonPage<AppVersionFile> queryByExemple(AppVersionFile appVersionFile,
			DataGridModel dgm) {
		JsonPage<AppVersionFile> jsonPage = new JsonPage<AppVersionFile>(dgm.getPage(), dgm.getRows());
		StringBuilder sql = new StringBuilder(
				"select a.*,b.app_name, b.en_name from t_app_version_file a left join t_application b on a.app_id=b.id where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		String whereSql=buildWhere(args, argTypes, appVersionFile);
		sql.append(whereSql);
		String countSql="select count(1) from t_app_version_file a left join t_application b on a.app_id=b.id where 1=1"+whereSql;
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", countSql));
		}
		int totalRow = queryForInt(countSql, args, argTypes);
		// 更新
		jsonPage.setTotal(totalRow);
		// 排序
		if (StringUtils.hasText(dgm.getOrder())
				&& StringUtils.hasText(dgm.getSort())) {
			sql.append(" order by ")
					.append(StringUtil.splitFieldWords(dgm.getSort()))
					.append(" ").append(dgm.getOrder()).append(", id desc");
		} else {
			sql.append(" ordery by update_time, create_time desc");
		}
		sql.append(" limit ?, ?");
		args.add(jsonPage.getStartRow());
		args.add(jsonPage.getPageSize());
		argTypes.add(Types.INTEGER);
		argTypes.add(Types.INTEGER);
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		jsonPage.setRows(query(sql.toString(), args, argTypes, rowMapper));
		return jsonPage;
	}

	private String buildWhere(List<Object> args,
			List<Integer> argTypes, AppVersionFile appVersionFile) {
		StringBuilder sql=new StringBuilder();
		if (appVersionFile.getAppId() != null) {
			sql.append(" and a.app_id=?");
			args.add(appVersionFile.getAppId());
			argTypes.add(4);// java.sql.Types type
		}
		if (StringUtils.hasText(appVersionFile.getVersion())) {
			sql.append(" and a.version like CONCAT('%',?,'%')");
			args.add(appVersionFile.getVersion());
			argTypes.add(12);// java.sql.Types type
		}
		if (StringUtils.hasText(appVersionFile.getSize())) {
			sql.append(" and a.size like CONCAT('%',?,'%')");
			args.add(appVersionFile.getSize());
			argTypes.add(12);// java.sql.Types type
		}
		if (StringUtils.hasText(appVersionFile.getFilePath())) {
			sql.append(" and a.file_path like CONCAT('%',?,'%')");
			args.add(appVersionFile.getFilePath());
			argTypes.add(12);// java.sql.Types type
		}
		if(StringUtils.hasText(appVersionFile.getAppName())){
			sql.append(" and b.app_name like CONCAT('%',?,'%')");
			args.add(appVersionFile.getAppName());
			argTypes.add(12);// java.sql.Types type
		}
		if(StringUtils.hasText(appVersionFile.getEnName())){
			sql.append(" and  b.en_name like CONCAT('%',?,'%')");
			args.add(appVersionFile.getEnName());
			argTypes.add(12);// java.sql.Types type
		}
		
		if (StringUtils.hasText(appVersionFile.getNewFeatures())) {
			sql.append(" and a.new_features like CONCAT('%',?,'%')");
			args.add(appVersionFile.getNewFeatures());
			argTypes.add(12);// java.sql.Types type
		}
		if (appVersionFile.getStatus() != null) {
			sql.append(" and a.status=?");
			args.add(appVersionFile.getStatus());
			argTypes.add(4);// java.sql.Types type
		}
		if (appVersionFile.getCreateTime() != null) {
			sql.append(" and a.create_time=?");
			args.add(appVersionFile.getCreateTime());
			argTypes.add(93);// java.sql.Types type
		}
		if (appVersionFile.getUpdateTime() != null) {
			sql.append(" and a.update_time=?");
			args.add(appVersionFile.getUpdateTime());
			argTypes.add(93);// java.sql.Types type
		}
		return sql.toString();
	}

	private final RowMapper<PortalAppVersionFile> portalRowMapper = new RowMapper<PortalAppVersionFile>() {

		@Override
		public PortalAppVersionFile mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			PortalAppVersionFile appVersionFile = new PortalAppVersionFile();
			appVersionFile.setId(rs.getInt("id"));
			appVersionFile.setVersion(rs.getString("version"));
			appVersionFile.setSize(rs.getString("size"));
			appVersionFile.setFilePath(rs.getString("file_path"));
			appVersionFile.setNewFeatures(rs.getString("new_features"));
			return appVersionFile;
		}
	};

	@Override
	public JsonPage<PortalAppVersionFile> queryByExemple(PortalAppVersionFile appVersionFile,
			DataGridModel dgm) {
		JsonPage<PortalAppVersionFile> jsonPage = new JsonPage<PortalAppVersionFile>(dgm.getPage(), dgm.getRows());
		String viewName="v_app_version_file_cn";
		if (Constants.LOCAL_EN.equalsIgnoreCase(appVersionFile.getLocal())) {
			viewName="v_app_version_file_en";
		}
		StringBuilder sql=new StringBuilder("select * from ").append(viewName).append(" where status=1");
		StringBuilder countSql=new StringBuilder("select count(1) from ").append(viewName).append(" where status=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		if (appVersionFile.getAppId() != null) {
			sql.append(" and app_id=?");
			countSql.append(" and app_id=?");
			args.add(appVersionFile.getAppId());
			argTypes.add(4);// java.sql.Types type
		}
		if (StringUtils.hasText(appVersionFile.getVersion())) {
			sql.append(" and version like CONCAT('%',?,'%')");
			countSql.append(" and version like CONCAT('%',?,'%')");
			args.add(appVersionFile.getVersion());
			argTypes.add(12);// java.sql.Types type
		}
		if (StringUtils.hasText(appVersionFile.getSize())) {
			sql.append(" and size like CONCAT('%',?,'%')");
			countSql.append(" and size like CONCAT('%',?,'%')");
			args.add(appVersionFile.getSize());
			argTypes.add(12);// java.sql.Types type
		}
		if (StringUtils.hasText(appVersionFile.getFilePath())) {
			sql.append(" and file_path like CONCAT('%',?,'%')");
			countSql.append(" and file_path like CONCAT('%',?,'%')");
			args.add(appVersionFile.getFilePath());
			argTypes.add(12);// java.sql.Types type
		}
		if (StringUtils.hasText(appVersionFile.getNewFeatures())) {
			sql.append(" and new_features like CONCAT('%',?,'%')");
			countSql.append(" and new_features like CONCAT('%',?,'%')");
			args.add(appVersionFile.getNewFeatures());
			argTypes.add(12);// java.sql.Types type
		}
		log.debug(String.format("\n%1$s\n", countSql));
		int totalRow = queryForInt(countSql.toString(), args, argTypes);
		jsonPage.setTotal(totalRow);
		// 排序
		if (StringUtils.hasText(dgm.getOrder())
				&& StringUtils.hasText(dgm.getSort())) {
			sql.append(" order by ")
					.append(StringUtil.splitFieldWords(dgm.getSort()))
					.append(" ").append(dgm.getOrder());
		}
		sql.append(" limit ?, ?");
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		args.add(jsonPage.getStartRow());
		args.add(jsonPage.getPageSize());
		argTypes.add(Types.INTEGER);
		argTypes.add(Types.INTEGER);
		jsonPage.setRows(query(sql.toString(), args, argTypes, portalRowMapper));
		return jsonPage;
	}

	@Override
	public PortalAppVersionFile query(int id, String local) {
		String viewName="v_app_version_file_cn";
		if (Constants.LOCAL_EN.equalsIgnoreCase(local)) {
			viewName="v_app_version_file_en";
		}
		String sql="select * from "+viewName+" where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, portalRowMapper);
	}

}
