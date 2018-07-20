package com.zhonglai.service;

import com.zhonglai.dao.mapper.BaseMapper;
import com.zhonglai.dto.Message;
import com.zhonglai.dto.MessageCode;
import com.zhonglai.dto.PageBean;
import com.zhonglai.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 公共业务类
 * @author zhonglai
 *实例对象生成规则：传入的对象实例名称必须是表名按照驼峰规则生成
 */
@Service("baseService")
public class BaseService {
	@Autowired
    BaseMapper baseMapper;
	
	/**
	 * 添加对象
	 * @param object 实例对象
	 * @return
	 */
	public Message insertObject(Object object)
	{
		if(null == object)
		{
			return new Message(MessageCode.DEFAULT_FAIL_CODE,"添加信息为空");
		}
		baseMapper.insert(object);
		return new Message(MessageCode.DEFAULT_SUCCESS_CODE,"添加成功",object);
	}
	
	/**
	 * 指定表名添加
	 * @param object 实例对象
	 * @param tableName 指定的表名称（表的字段必须要和实例对象的字段按照驼峰规则一致）
	 * @return
	 */
	public Message insertObject(Object object, String tableName)
	{
		if(null == object)
		{
			return new Message(MessageCode.DEFAULT_FAIL_CODE,"添加信息为空");
		}
		try {
			object.getClass().getSuperclass().getMethod("setTableName",String.class).invoke(object,tableName);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}


		baseMapper.insertToTable(object);
		return new Message(MessageCode.DEFAULT_SUCCESS_CODE,"添加成功",object);
	}

	/**
	 * 指定表名添加
	 * @param objectList 实例对象集合
	 * @param tableName 指定的表名称（表的字段必须要和实例对象的字段按照驼峰规则一致）
	 * @return
	 */
	public Message insertListObject(List<?> objectList, String tableName)
	{
		if(null == objectList)
		{
			return new Message(MessageCode.DEFAULT_FAIL_CODE,"添加信息为空");
		}
		for(Object object:objectList)
		{
			try {
				object.getClass().getSuperclass().getMethod("setTableName",String.class).invoke(object,tableName);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			baseMapper.insertToTable(object);
		}
		return new Message(MessageCode.DEFAULT_SUCCESS_CODE,"添加成功",objectList);
	}
	
	/**
	 * 通过条件查询
	 * @param clas 实例对象的class
	 * @param idName 查询的条件字段名称，多个用英文逗号,分割 （不是实例的属性名称）
	 * @param values 查询的条件字段对应的值，多个用英文逗号,分割
	 * @return 查询的结果记录条数必须小于等于1,不然会报异常
	 */
	public Message getObject(Class<?> clas, String idName, String values)
	{
		Map<String, Object> map = baseMapper.getObject(clas, idName, values);
		return new Message(MessageCode.DEFAULT_SUCCESS_CODE, map);
	}
	
	/**
	 *  获得对象
	 * @param clas 实例对象的class
	 * @param select 需要查询的字段名称，多个用英文逗号,分割
	 * @param idName 查询的条件字段名称，多个用英文逗号,分割 （不是实例的属性名称）
	 * @param values 查询的字段对应的值，多个用英文逗号,分割
	 * @return 查询的结果记录条数必须小于等于1,不然会报异常
	 */
	public Message getObject(Class<?> clas, String select, String idName, String values)
	{
		Map<String, Object> map = baseMapper.getObjectSelectTableName(clas,select, idName, values,null);
		return new Message(MessageCode.DEFAULT_SUCCESS_CODE, map);
	}

	/**
	 *  获得对象
	 * @param clas 实例对象的class
	 * @param select 需要查询的字段名称，多个用英文逗号,分割
	 * @param idName 查询的条件字段名称，多个用英文逗号,分割 （不是实例的属性名称）
	 * @param values 查询的字段对应的值，多个用英文逗号,分割
	 * @return 查询的结果记录条数必须小于等于1,不然会报异常
	 */
	public Message getObject(Class<?> clas, String select, String idName, String values,String tableName)
	{
		Map<String, Object> map = baseMapper.getObjectSelectTableName(clas,select, idName, values,tableName);
		return new Message(MessageCode.DEFAULT_SUCCESS_CODE, map);
	}
	
	/**
	 * 
	 * @param clas 实例对象的class
	 * @param idName 查询的条件字段名称，多个用英文逗号,分割 （不是实例的属性名称）
	 * @param values 查询的条件字段对应的值，多个用英文逗号,分割
	 * @return 查询的结果记录条数必须小于等于1,不然会报异常
	 */
	public <T> Object get(Class<T> clas,String idName,String values)
	{
		Map<String, Object> map = baseMapper.getObject(clas, idName, values);
		if(null != map)
		{
			return CommonUtil.transMap2Bean(map, clas);
		}
		return null;
	}
	
	/**
	 * 
	 * @param clas 实例对象的class
	 * @param select 需要查询的表字段名称，多个用英文逗号,分割
	 * @param idName 查询的条件字段名称，多个用英文逗号,分割 （不是实例的属性名称）
	 * @param values 查询的条件字段对应的值，多个用英文逗号,分割
	 * @return 查询的结果记录条数必须小于等于1,不然会报异常
	 */
	public <T> Object get(Class<T> clas,String select,String idName,String values,String tableName)
	{
		Map<String, Object> map = baseMapper.getObjectSelectTableName(clas,select, idName, values,tableName);
		if(null != map)
		{
			return CommonUtil.transMap2Bean(map, clas);
		}
		return null;
	}
	
	/**
	 * 
	 * @param object 实例对象
	 * @param whereFieldNames 条件字段名称，多个用英文逗号,分割 （实例的属性名称）
	 * @return
	 */
	public Message updateObject(Object object, String whereFieldNames)
	{
		if(null == object)
		{
			return new Message(MessageCode.DEFAULT_FAIL_CODE,"更新信息为空");
		}
		baseMapper.updateObject(object, whereFieldNames);
		return new Message(MessageCode.DEFAULT_SUCCESS_CODE,"更新成功");
	}
	
	/**
	 * 
	 * @param oClass 实例对象的class
	 * @param wheremap 条件字段名称和参数
	 * @return
	 */
	public Message deleteObjectByContent(Class<?> oClass, Map<String,String> wheremap)
	{
		baseMapper.deleteObjectByContent(oClass, wheremap);
		return new Message(MessageCode.DEFAULT_SUCCESS_CODE,"更新成功");
	}
	
	
	/**
	 * 查询list
	 * @param object 实例对象
	 * @param whereMap 条件字段名称和条件类型，如果是时间，whereMap里面对应的字段比较符为time,同时添加一个end_字段名的值 表示是结束时间
	 *                  map的value种类：<,>,<>,like,=,time
	 * @param order 排列
	 * @param pageBean 分页对象
	 * @return
	 */
	public Message getObjectList(Object object, String selectStr, Map<String,String> whereMap, String order, PageBean pageBean)
	{
		if(null == object)
		{
			return new Message(MessageCode.DEFAULT_FAIL_CODE,"参数异常");
		}
		if(null == pageBean)
		{
			pageBean = new PageBean();
			pageBean.setPageNo(0);
			pageBean.setPageSize(0);
		}
		List<Map<String,Object>> list = baseMapper.getObjectList(object,selectStr, whereMap, order, pageBean.getPageSize(), pageBean.getPageNo());
		pageBean.setTotal(Integer.parseInt(baseMapper.getObjectListTotle(object, whereMap)+""));
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("list", list);
		returnMap.put("pageBean", pageBean);
		return new Message(MessageCode.DEFAULT_SUCCESS_CODE,returnMap);
	} 
	
	/**
	 * 自定义sql语句查询list 
	 * @param sql 自定义sql
	 * @return
	 */
	public List<Map<String,Object>> getObjectListBySQL(String sql)
	{
		return baseMapper.getObjectListBySQL(sql);
	}
	
	/**
	 * 查询数量
	 * @param object 实例对象
	 * @param whereMap 条件字段名称和条件类型，如果是时间，whereMap里面对应的字段比较符为time,同时添加一个end_字段名的值 表示是结束时间
	 *                 map的value种类：<,>,<>,like,=,time
	 * @return
	 */
	public Long getObjectListTotle(Object object,Map<String,String> whereMap)
	{
		return baseMapper.getObjectListTotle(object,whereMap);
	}
	
	/**
	 * 自定义sql 更新
	 * @param sql 自定义sql
	 * @return
	 */
	public void update(String sql)
	{
		baseMapper.updateBySql(sql);
	}

	public BaseMapper getBaseMapper()
	{
		return baseMapper;
	}

	/**
	 * 通过id删除数据
	 * @param id 主键id的值
	 * @return
	 */
	public void deleteObjectById(Class<?> oClass,String id)
	{
		baseMapper.deleteObjectById(oClass,id);
	}
}
