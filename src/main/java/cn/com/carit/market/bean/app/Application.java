package cn.com.carit.market.bean.app;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.com.carit.market.common.jackjson.CustomDateTimeSerializer;

/**
 * Application
 * Auto generated Code
 */
public class Application  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 314936188348558691L;
	/**
	 * id
	 */
	private int id;
	/**
	 * appName
	 */
	private String appName;
	/**
	 * enName
	 */
	private String enName;
	/**
	 * version
	 */
	private String version;
	/**
	 * icon
	 */
	private String icon;
	/**
	 * catalogId
	 */
	private Integer catalogId;
	/**
	 * size
	 */
	private String size;
	/**
	 * appFilePath
	 */
	private String appFilePath;
	/**
	 * platform
	 */
	private String platform;
	/**
	 * supportLanguages
	 */
	private Integer supportLanguages;
	/**
	 * price
	 */
	private Double price;
	/**
	 * downCount
	 */
	private Integer downCount;
	/**
	 * appLevel
	 */
	private Integer appLevel;
	/**
	 * description
	 */
	private String description;
	private String enDescription;
	/**
	 * permissionDesc
	 */
	private String permissionDesc;
	private String enPermissionDesc;
	
	/**
	 * 截图路径，以“;”分隔
	 */
	@JsonIgnore
	private String images;
	/**
	 * status
	 */
	private Integer status;
	/**
	 * createTime
	 */
	private Date createTime;
	/**
	 * updateTime
	 */
	private Date updateTime;
	
	/**
	 * 版本文件
	 */
	@JsonIgnore
	private AppVersionFile appVersionFile;
	

	public void setId(int value) {
		this.id = value;
	}
	public int getId() {
		return this.id;
	}
	public void setAppName(String value) {
		this.appName = value;
	}
	public String getAppName() {
		return this.appName;
	}
	
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public void setVersion(String value) {
		this.version = value;
	}
	public String getVersion() {
		return this.version;
	}
	public void setIcon(String value) {
		this.icon = value;
	}
	public String getIcon() {
		return this.icon;
	}
	public void setCatalogId(Integer value) {
		this.catalogId = value;
	}
	public Integer getCatalogId() {
		return this.catalogId;
	}
	public void setSize(String value) {
		this.size = value;
	}
	public String getSize() {
		return this.size;
	}
	public void setAppFilePath(String value) {
		this.appFilePath = value;
	}
	public String getAppFilePath() {
		return this.appFilePath;
	}
	public void setPlatform(String value) {
		this.platform = value;
	}
	public String getPlatform() {
		return this.platform;
	}
	public void setSupportLanguages(Integer value) {
		this.supportLanguages = value;
	}
	public Integer getSupportLanguages() {
		return this.supportLanguages;
	}
	public void setPrice(Double value) {
		this.price = value;
	}
	public Double getPrice() {
		return this.price;
	}
	public void setDownCount(Integer value) {
		this.downCount = value;
	}
	public Integer getDownCount() {
		return this.downCount;
	}
	public void setAppLevel(Integer value) {
		this.appLevel = value;
	}
	public Integer getAppLevel() {
		return this.appLevel;
	}
	public void setDescription(String value) {
		this.description = value;
	}
	public String getDescription() {
		return this.description;
	}
	public void setPermissionDesc(String value) {
		this.permissionDesc = value;
	}
	public String getPermissionDesc() {
		return this.permissionDesc;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	public Integer getStatus() {
		return this.status;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}

	public String [] getImageList(){
		if (this.images!=null && this.images.trim().length()>0) {
			return this.images.trim().split(";");
		}
		return null;
	}
	public String getEnDescription() {
		return enDescription;
	}
	public void setEnDescription(String enDescription) {
		this.enDescription = enDescription;
	}
	public String getEnPermissionDesc() {
		return enPermissionDesc;
	}
	public void setEnPermissionDesc(String enPermissionDesc) {
		this.enPermissionDesc = enPermissionDesc;
	}
	public AppVersionFile getAppVersionFile() {
		return appVersionFile;
	}
	public void setAppVersionFile(AppVersionFile appVersionFile) {
		this.appVersionFile = appVersionFile;
	}
	
}
