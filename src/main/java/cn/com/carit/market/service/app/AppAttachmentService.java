package cn.com.carit.market.service.app;
import java.util.List;

import cn.com.carit.market.common.utils.DataGridModel;
import cn.com.carit.market.common.utils.JsonPage;
import  cn.com.carit.market.bean.app.AppAttachment;

/**
 * AppAttachmentService
 * Auto generated Code
 */
public interface AppAttachmentService {
	/**
	 * 增加
	 * @param appAttachment
	 * @return
	 */
	void saveOrUpdate(AppAttachment appAttachment);
	
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
	 * 按Id查询
	 * @param id
	 * @return
	 */
	AppAttachment queryById(int id);
	
	/**
	 * 查询
	 * @return
	 */
	List<AppAttachment> query();
	
	/**
	 * 条件查询
	 * @param appAttachment
	 * @return
	 */
	List<AppAttachment> queryByExemple(AppAttachment appAttachment);
	
	/**
	 * 带分页的条件查询
	 * @param appAttachment
	 * @param dgm
	 * @return
	 */
	JsonPage queryByExemple(AppAttachment appAttachment, DataGridModel dgm);
	
	/**
	 * 按样例获取总数
	 * @param appAttachment
	 * @return
	 */
	int getCount(AppAttachment appAttachment);
}
