package cn.com.carit.market.dao.impl.app;

import java.sql.Connection;
import java.sql.Date;
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

import cn.com.carit.market.bean.app.AppDownloadLog;
import cn.com.carit.market.common.utils.DataGridModel;
import cn.com.carit.market.common.utils.JsonPage;
import cn.com.carit.market.common.utils.StringUtil;
import cn.com.carit.market.dao.app.AppDownloadLogDao;
import cn.com.carit.market.dao.impl.BaseDaoImpl;

/**
 * AppDownloadLogDaoImpl
 * Auto generated Code
 */
@Repository
public class AppDownloadLogDaoImpl extends BaseDaoImpl  implements
		AppDownloadLogDao {
		private final RowMapper<AppDownloadLog> rowMapper=new RowMapper<AppDownloadLog>() {
			
			@Override
			public AppDownloadLog mapRow(ResultSet rs, int rowNum) throws SQLException {
				AppDownloadLog appDownloadLog=new AppDownloadLog();
				appDownloadLog.setAccountId(rs.getInt("account_id"));
				appDownloadLog.setAppId(rs.getInt("app_id"));
				appDownloadLog.setDownloadTime(rs.getTimestamp("download_time"));
				return appDownloadLog;
			}
		};

		@Override
		public int add(final AppDownloadLog appDownloadLog) {
			// TODO change values field name to ? and deal with date field
			final String sql = "insert into t_app_download_log ("
					+", account_id"
					+", app_id"
					+", download_time"
					+") values ("
					+", account_id"
					+", app_id"
					+", download_time"
					+")";
			log.debug(String.format("\n%1$s\n", sql));
			KeyHolder gkHolder = new GeneratedKeyHolder(); 
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					 PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					 ps.setInt(1, appDownloadLog.getAccountId());
					 ps.setInt(2, appDownloadLog.getAppId());
					 ps.setDate(3, new Date(appDownloadLog.getDownloadTime().getTime()));
					return ps;
				}
			},  gkHolder);
			return gkHolder.getKey().intValue();
		}

		@Override
		public int delete(int id) {
			String sql="delete from t_app_download_log where id=?";
			log.debug(String.format("\n%1$s\n", sql));
			return jdbcTemplate.update(sql, id);
		}

		@Override
		public int update(AppDownloadLog appDownloadLog) {
			StringBuilder sql=new StringBuilder("update t_app_download_log set update_time=now()");
			List<Object> args=new ArrayList<Object>();
			sql.append(" where id=?");
			if (appDownloadLog.getAccountId()!=null) {
				sql.append(", account_id=?");
				args.add(appDownloadLog.getAccountId());
			}
			if (appDownloadLog.getAppId()!=null) {
				sql.append(", app_id=?");
				args.add(appDownloadLog.getAppId());
			}
			if (appDownloadLog.getDownloadTime()!=null) {
				sql.append(", download_time=?");
				args.add(appDownloadLog.getDownloadTime());
			}
			args.add(appDownloadLog.getId());
			log.debug(String.format("\n%1$s\n", sql));
			return jdbcTemplate.update(sql.toString(), args.toArray());
		}

		@Override
		public AppDownloadLog queryById(int id) {
			String sql="select * from t_app_download_log where id=?";
			log.debug(String.format("\n%1$s\n", sql));
			return query(sql, id, rowMapper);
		}

		@Override
		public List<AppDownloadLog> query() {
			return this.jdbcTemplate.query("select * from t_app_download_log", rowMapper);
		}

		@Override
		public List<AppDownloadLog> queryByExemple(AppDownloadLog appDownloadLog) {
			StringBuilder sql=new StringBuilder("select * from t_app_download_log where 1=1");
			List<Object> args=new ArrayList<Object>();
			List<Integer> argTypes=new ArrayList<Integer>();
			buildWhere(sql, args, argTypes, appDownloadLog);
			log.debug(String.format("\n%1$s\n", sql));
			return query(sql.toString(), args, argTypes, rowMapper);
		}

		@Override
		public JsonPage queryByExemple(AppDownloadLog appDownloadLog, DataGridModel dgm) {
			JsonPage jsonPage=new JsonPage(dgm.getPage(), dgm.getRows());
			StringBuilder sql=new StringBuilder("select * from t_app_download_log where 1=1");
			List<Object> args=new ArrayList<Object>();
			List<Integer> argTypes=new ArrayList<Integer>();
			buildWhere(sql, args, argTypes, appDownloadLog);
			// 排序
			if (StringUtils.hasText(dgm.getOrder()) && StringUtils.hasText(dgm.getSort())) {
				sql.append(" order by ").append(StringUtil.splitFieldWords(
						dgm.getSort())).append(" ").append(dgm.getOrder());
			}
			sql.append(" limit ?, ?");
			args.add(jsonPage.getStartRow());
			args.add(jsonPage.getEndRow());
			argTypes.add(Types.INTEGER);
			argTypes.add(Types.INTEGER);
			int totalRow=getCount(appDownloadLog);
			// 更新
			jsonPage.setTotal(totalRow);
			log.debug(String.format("\n%1$s\n", sql));
			jsonPage.setRows(query(sql.toString(), args, argTypes, rowMapper));
			return jsonPage;
		}

		@Override
		public int getCount(AppDownloadLog appDownloadLog) {
			StringBuilder sql=new StringBuilder("select count(1) from t_app_download_log where 1=1");
			List<Object> args=new ArrayList<Object>();
			List<Integer> argTypes=new ArrayList<Integer>();
			buildWhere(sql, args, argTypes, appDownloadLog);
			log.debug(String.format("\n%1$s\n", sql));
			return queryForInt(sql.toString(), args, argTypes);
		}
		
		private void buildWhere(StringBuilder sql, List<Object> args, 
				List<Integer> argTypes, AppDownloadLog appDownloadLog){
			if (appDownloadLog.getAccountId()!=null) {
				sql.append(" and account_id=?");
				args.add(appDownloadLog.getAccountId());
				argTypes.add(4);//java.sql.Types type
			}
			if (appDownloadLog.getAppId()!=null) {
				sql.append(" and app_id=?");
				args.add(appDownloadLog.getAppId());
				argTypes.add(4);//java.sql.Types type
			}
			if (appDownloadLog.getDownloadTime()!=null) {
				sql.append(" and download_time=?");
				args.add(appDownloadLog.getDownloadTime());
				argTypes.add(93);//java.sql.Types type
			}
		}
}