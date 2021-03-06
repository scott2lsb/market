package cn.com.carit.market.dao.impl.permission;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.com.carit.market.bean.BaseField;
import cn.com.carit.market.common.utils.DataGridModel;
import cn.com.carit.market.common.utils.JsonPage;
import cn.com.carit.market.common.utils.StringUtil;
import cn.com.carit.market.dao.impl.BaseDaoImpl;
import cn.com.carit.market.dao.permission.BaseFieldDao;
@Repository
public class BaseFieldDaoImpl extends BaseDaoImpl implements BaseFieldDao {
	
	private final RowMapper<BaseField> rowMapper=new RowMapper<BaseField>() {
		@Override
		public BaseField mapRow(ResultSet rs, int index) throws SQLException {
			BaseField field=new BaseField();
			field.setId(rs.getInt("id"));
			field.setField(rs.getString("field"));
			field.setFieldName(rs.getString("field_name"));
			field.setFieldValue(rs.getInt("field_value"));
			field.setDisplayValue(rs.getString("display_value"));
			field.setEnabled(rs.getByte("enabled"));
			field.setSort(rs.getInt("sort"));
			field.setCreateTime(rs.getTimestamp("create_time"));
			field.setUpdateTime(rs.getTimestamp("update_time"));
			return field;
		}
	};

	@Override
	public int add(BaseField field) {
		String sql = "insert into t_base_field(field, field_name, field_value," +
				" display_value, enabled, sort, create_time, update_time) " +
				"values(?, ?, ?, ?, ?, ?, now(), now())";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql
				, field.getField()
				, field.getFieldName()
				, field.getFieldValue()
				, field.getDisplayValue()
				, field.getEnabled()
				, field.getSort()
			);
	}

	@Override
	public int delete(int id) {
		String sql="delete from t_base_field where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int batchDelete(String ids) {
		String sql="delete from t_base_field where id in("+ids+")";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql);
	}

	@Override
	public int update(BaseField field) {
		StringBuffer sql=new StringBuffer("update t_base_field set update_time=now()");
		List<Object> val=new ArrayList<Object>();
		/*if (StringUtils.hasText(field.getField())) {
			sql.append(", fi")
		}*/
		if (StringUtils.hasText(field.getFieldName())) {
			sql.append(", field_name=?");
			val.add(field.getFieldName());
		}
		if (field.getFieldValue()!=null) {
			sql.append(", field_value=?");
			val.add(field.getFieldValue());
		}
		if (StringUtils.hasText(field.getDisplayValue())) {
			sql.append(", display_value=?");
			val.add(field.getDisplayValue());
		}
		if (field.getEnabled()!=null) {
			sql.append(", enabled=?");
			val.add(field.getEnabled());
		}
		if (field.getSort()!=null) {
			sql.append(", sort=?");
			val.add(field.getSort());
		}
		sql.append(" where id=?");
		val.add(field.getId());
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql.toString(), val.toArray());
	}

	@Override
	public BaseField queryById(int id) {
		String sql="select * from t_base_field where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, rowMapper);
	}

	@Override
	public List<BaseField> queryByField(String filed) {
		String sql="select * from t_base_field where field=? order by field_value desc";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{filed}, new int[]{Types.VARCHAR}, rowMapper);
	}

	@Override
	public List<Map<String,Object>> queryGroupByField() {
		String sql="select * from t_base_field group by field";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<BaseField> queryByExemple(BaseField field) {
		StringBuilder sql=new StringBuilder("select * from t_admin_user where 1=1");
		List<Object> args=new ArrayList<Object>();
		List<Integer> argTypes=new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, field));
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public JsonPage<BaseField> queryByExemple(BaseField field, DataGridModel dgm) {
		JsonPage<BaseField> jsonPage=new JsonPage<BaseField>(dgm.getPage(), dgm.getRows());
		StringBuilder sql=new StringBuilder("select * from t_base_field where 1=1");
		StringBuilder countSql=new StringBuilder("select count(1) from t_base_field where 1=1");
		List<Object> args=new ArrayList<Object>();
		List<Integer> argTypes=new ArrayList<Integer>();
		String where=buildWhere(args, argTypes, field);
		sql.append(where);
		countSql.append(where);
		log.debug(String.format("\n%1$s\n", countSql));
		int totalRow=queryForInt(countSql.toString(), args, argTypes);
		jsonPage.setTotal(totalRow);
		// 排序
		if (StringUtils.hasText(dgm.getOrder()) && StringUtils.hasText(dgm.getSort())) {
			sql.append(" order by ").append(StringUtil.splitFieldWords(
					dgm.getSort())).append(" ").append(dgm.getOrder());
			
		} else {
			sql.append(" order by update_time desc");
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
			List<Integer> argTypes, BaseField field) {
		StringBuilder sql=new StringBuilder();
		if (StringUtils.hasText(field.getField())) {
			sql.append(" and field=?");
			args.add(field.getField());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(field.getFieldName())) {
			sql.append(" and field_name like CONCAT('%',?,'%')");
			args.add(field.getFieldName());
			argTypes.add(Types.VARCHAR);
		}
		if (field.getFieldValue()!=null) {
			sql.append(" and field_value=?");
			args.add(field.getFieldValue());
			argTypes.add(Types.INTEGER);
		}
		if (StringUtils.hasText(field.getDisplayValue())) {
			sql.append(" and display_value like CONCAT('%',?,'%')");
			args.add(field.getDisplayValue());
			argTypes.add(Types.VARCHAR);
		}
		if (field.getEnabled()!=null) {
			sql.append(" and enabled=?");
			args.add(field.getEnabled());
			argTypes.add(Types.TINYINT);
		}
		if (field.getSort()!=null) {
			sql.append(" and sort<=?");
			args.add(field.getSort());
			argTypes.add(Types.INTEGER);
		}
		return sql.toString();
	}

	@Override
	public int checkField(String field) {
		String sql="select 1 from t_base_field where field=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		try {
			return jdbcTemplate.queryForInt(sql, field);
		} catch (Exception e) {
			log.warn("not exist record of this field["+field+"]");
			log.warn(e.getMessage());
		}
		return 0;
	}

	@Override
	public List<BaseField> queryByField(String field, int limit) {
		String sql="select field, field_name from t_base_field where field like CONCAT('%',?,'%') limit ?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{field, limit}, rowMapper);
	}
	
	
}
