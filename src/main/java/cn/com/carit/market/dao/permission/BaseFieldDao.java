package cn.com.carit.market.dao.permission;

import java.util.List;
import java.util.Map;

import cn.com.carit.market.bean.BaseField;
import cn.com.carit.market.common.utils.DataGridModel;
import cn.com.carit.market.common.utils.JsonPage;

public interface BaseFieldDao {
	/**
	 * 增加
	 * @param field
	 * @return
	 */
	int add(BaseField field);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(int id);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int batchDelete(String ids);
	
	/**
	 * 更新
	 * @param field
	 * @return
	 */
	int update(BaseField field);
	
	/**
	 * 按Id查询
	 * @param id
	 * @return
	 */
	BaseField queryById(int id);
	
	/**
	 * 按字段名字查询
	 * @param filed
	 * @return
	 */
	List<BaseField> queryByField(String filed);
	
	/**
	 * 查询已有字典名字
	 * @return
	 */
	List<Map<String,Object>> queryGroupByField();
	
	/**
	 * 条件查询
	 * @param field
	 * @return
	 */
	List<BaseField> queryByExemple(BaseField field);
	
	/**
	 * 带分页的条件查询
	 * @param field
	 * @param dgm
	 * @return
	 */
	JsonPage<BaseField> queryByExemple(BaseField field, DataGridModel dgm);
	
	/**
	 * 检测字典名称是否存在
	 * @param field
	 * @return
	 */
	int checkField(String field);
	
	/**
	 * 按字段名返回limit条记录
	 * @param field
	 * @param limit
	 * @return
	 */
	List<BaseField> queryByField(String field, int limit);
}
