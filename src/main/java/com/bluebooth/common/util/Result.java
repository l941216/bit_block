package com.bluebooth.common.util;
import java.util.HashMap;

/**
 * * 接口返回结果实体类 使用案例：Result r=new Result();
 * r.set(OrderStatus.BE_PAYMENT).setData("测试数据"); 1、
 * 实体set方法现已支持添加其他业务枚举，但其他枚举必需和SystemEnum一样包含
 * getIndex和getDescr这两个方法，以便该实体可以通过反射获取对应code和msg 2、实体中重载的set方法可以动态传入对应的方法
 * 
 * @author
 * @version [1.0, 2016年6月8日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Result extends HashMap<String, Object> {

	private static final long serialVersionUID = 2546281331124443628L;
	public static final String CODE = "code";
	public static final String MSG = "msg";
	public static final String DATA_AREA = "data";
	public static final String GET_INDEX = "getIndex";
	public static final String GET_DESCR = "getDescr";
	public static final String SUCCESS = "success";
	public static final String TOKEN = "token";

	public Result() {
		this.setData(null);
		this.setSuccess(true);
		this.set(SystemEnum.SUCCESS);
	}

	/**
	 * 传人枚举对象，通过反射默认获取getIndex和getDescr方法设置对应参数
	 * 如果使用的枚举方法名不是这个两个，可以使用重载的set的方法将对应方法名传入
	 * 
	 * @author chuzhisheng
	 * @version 1.0
	 * @date 2016年3月31日 下午2:17:24
	 * @param enumObj
	 */
	public Result set(Object enumObj) {
		try {
			this.set(enumObj, GET_INDEX, GET_DESCR);
		} catch (Exception e) {
			this.put(CODE, SystemEnum.SYSTEM_ERROR.getIndex());
			this.put(MSG, SystemEnum.SYSTEM_ERROR.getDescr());
		}
		return this;
	}

	/**
	 * 添加set重载方法，兼容枚举结构和SystemEnum不同的枚举， 传入的getIndex为获取索引的方法名， getDescr为获取描述的方法名
	 * 
	 * @author chuzhisheng
	 * @version 1.0
	 * @date 2016年3月31日 下午2:13:05
	 * @param enumObj
	 * @param getIndex
	 * @param getDescr
	 */
	public Result set(Object enumObj, String getIndex, String getDescr) {
		try {
			this.put(CODE, enumObj.getClass().getMethod(getIndex).invoke(enumObj));
			this.put(MSG, enumObj.getClass().getMethod(getDescr).invoke(enumObj));
			this.setSuccess(SystemEnum.SUCCESS.getIndex().equals(this.getCode()));
		} catch (Exception e) {
			this.put(CODE, SystemEnum.SYSTEM_ERROR.getIndex());
			this.put(MSG, SystemEnum.SYSTEM_ERROR.getDescr());
			this.setSuccess(false);
		}
		return this;
	}

	public Result setCode(String code) {
		this.put(CODE, code);
		return this;
	}

	public Result setMsg(String msg) {
		this.put(MSG, msg);
		return this;
	}

	public String getCode() {
		return this.get(CODE).toString();
	}

	public String getMsg() {
		return this.get(MSG).toString();
	}

	public Object getData() {
		return this.get(DATA_AREA);
	}

	public Result setData(Object data) {
		this.put(DATA_AREA, data);
		return this;
	}

	public Result setSuccess(Boolean b) {
		this.put(SUCCESS, b);
		return this;
	}

	public boolean isSuccess() {
		return SystemEnum.SUCCESS.getIndex().equals(this.getCode());
	}
	
	public Result setToken(String token) {
		this.put(TOKEN, token);
		return this;
	}

}
