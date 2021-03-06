package cn.com.carit.market.dao.app;
import java.util.List;

import cn.com.carit.market.bean.app.AppVersionFile;
import cn.com.carit.market.bean.portal.PortalAppVersionFile;
import cn.com.carit.market.common.utils.DataGridModel;
import cn.com.carit.market.common.utils.JsonPage;

/**
 * AppVersionFileDao
 * Auto generated Code
 */
public interface AppVersionFileDao {
	
	/**
	 * 增加
	 * @param AppVersionFile
	 * @return
	 */
	int add(final AppVersionFile AppVersionFile);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(int id);
	
	/**
	 * 按应用Id删除
	 * @param appId
	 * @return
	 */
	int deleteByAppId(int appId);
	
	/**
	 * 更新
	 * @param PortalAppVersionFile
	 * @return
	 */
	int update(AppVersionFile appVersionFile);
	
	/**
	 * 加版本或启用版本时将非最新/当前版本记录更新为无效
	 * @param appId
	 * @param exceptedId
	 * @return
	 */
	int updateToInvalidByAppId(int appId, int exceptedId);
	
	/**
	 * 
	 * @param appId
	 * @param version
	 * @return
	 */
	int updateToValidByAppIdAndVersion(int appId, String version);
	/**
	 * 按Id查询
	 * @param id
	 * @return
	 */
	AppVersionFile queryById(int id);
	
	/**
	 * 根据AppId获取有效版本（有效版本只有一个）
	 * @param appId
	 * @return
	 */
	AppVersionFile queryValidVersionByAppId(int appId);
	
	/**
	 * 按应用Id查询
	 * @param appId
	 * @return
	 */
	List<AppVersionFile> queryByAppId(int appId);
	
	/**
	 * 按应用Id和排除Id查询，返回结果按Id倒序排序
	 * @param appId 应用Id
	 * @param exceptedId 排除之外的记录Id
	 * @return
	 */
	List<AppVersionFile> queryByAppIdAndExceptId(int appId, int exceptedId);
	
	/**
	 * 查询
	 * @return
	 */
	List<AppVersionFile> query();
	
	/**
	 * 条件查询
	 * @param appVersionFile
	 * @return
	 */
	List<AppVersionFile> queryByExemple(AppVersionFile appVersionFile);
	
	/**
	 * 带分页的条件查询
	 * @param appVersionFile
	 * @param dgm
	 * @return
	 */
	JsonPage<AppVersionFile> queryByExemple(AppVersionFile appVersionFile, DataGridModel dgm);
	
	/**
	 * 带分页的条件查询
	 * @param appVersionFile
	 * @param dgm
	 * @return
	 */
	JsonPage<PortalAppVersionFile> queryByExemple(PortalAppVersionFile appVersionFile, DataGridModel dgm);
	
	/**
	 * 按Id查询
	 * @param id
	 * @return
	 */
	PortalAppVersionFile query(int id, String local);
	
}
