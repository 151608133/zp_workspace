package com.zp.sys.entity;

import com.zp.core.plugin.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 用户实体类
 * @author chencheng  update
 * @since 2016-07-18
 */
public class User implements Serializable {
	private static final long serialVersionUID = -5268898209820709788L;
	
	private Long userId;
	private String userName;
	private String password;
	private String email;
	private String contactPhone;		///手机号码
	private String stateCd;				//用户状态     00A  正常    00X 为禁用
	private Date createDate;     //创建日期1
	private int userType;       //用户类型
	private Long custId;      //归属客户id
	private String realName;  //用户真实姓名
	private int  cityId;    //对应的城市信息
	private Integer isManageBy;  //是否归省公司管理     0 是   1不是
	private Page page;
	private String is_service_time;
	private List<Role> roles;
	private String listRoleName;
	private Date appLastLoginTime;	//上次登录时间
	private String appTypeName;
	private String appTypeId;
	private String token;			//app
	private int rgType;				//收发货类型
	private String LcCustId;//lc关联客户id
	private String LcType;//lc关联客户类型
	private String storeName; //客户关联本地库名称 供APP使用  诺基亚确认一个客户只可能关联一个本地库 
	private String roleId;
	private String roleName;
	private String shipToParty;
	private String customerId;//客户id,由创建人手动添加,2016-12-21日添加
	private String stateName;
	private int userType1;       //用户类型
	private String companyName;
	private String companyAddress;
	private String deliveryPoint_id;//交互点id
	private String custNames;//归属客户(以,分隔)

	private String address;
	private String province;
	private String storeIdStr;//存储前台仓库查询条件
	
	private int pwd_errer_number;
	private Date endEdit_pwd_time;
	private Date pwd_error_time;
	
	private int collect; //收藏数
	private int loading;  //下载数
	private int uploadsum; //上传数
	private int noticesum; //消息数
	private int readsum; //阅读总数
	
	private int noticeId; //消息ID
	private String noticeTitle;//消息标题
	private String noticeContent;//消息内容
	private Date create_time;//创建时间
	private Date staus_time;//状态时间
	private String sendObject;//发送对象    P为个人   A为全体
	private String smallTitle;//简介
	private String noticeStatecd;//消息状态  只针对个人 00A为显示  00X为不显示
	private int buserId;//接收消息人ID
	private String bName;
	private String fileName;//文档名
	
	private int timechoose;//公司公告时间筛选
	
	private int noRead_number;//公司公告个人没有阅读的消息个数
	private int docid;
	private String filePath;
	private String swfPath;
	private int pageNumber;
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getReadsum() {
		return readsum;
	}
	public void setReadsum(int readsum) {
		this.readsum = readsum;
	}
	public int getDocid() {
		return docid;
	}
	public void setDocid(int docid) {
		this.docid = docid;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getSwfPath() {
		return swfPath;
	}
	public void setSwfPath(String swfPath) {
		this.swfPath = swfPath;
	}
	public int getBuserId() {
		return buserId;
	}
	public void setBuserId(int buserId) {
		this.buserId = buserId;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public int getNoRead_number() {
		return noRead_number;
	}
	public void setNoRead_number(int noReadNumber) {
		noRead_number = noReadNumber;
	}
	public int getTimechoose() {
		return timechoose;
	}
	public void setTimechoose(int timechoose) {
		this.timechoose = timechoose;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}
	public Date getStaus_time() {
		return staus_time;
	}
	public void setStaus_time(Date stausTime) {
		staus_time = stausTime;
	}
	public String getSendObject() {
		return sendObject;
	}
	public void setSendObject(String sendObject) {
		this.sendObject = sendObject;
	}
	public String getSmallTitle() {
		return smallTitle;
	}
	public void setSmallTitle(String smallTitle) {
		this.smallTitle = smallTitle;
	}
	public String getNoticeStatecd() {
		return noticeStatecd;
	}
	public void setNoticeStatecd(String noticeStatecd) {
		this.noticeStatecd = noticeStatecd;
	}
	public int getCollect() {
		return collect;
	}
	public void setCollect(int collect) {
		this.collect = collect;
	}
	public int getLoading() {
		return loading;
	}
	public void setLoading(int loading) {
		this.loading = loading;
	}
	public int getUploadsum() {
		return uploadsum;
	}
	public void setUploadsum(int uploadsum) {
		this.uploadsum = uploadsum;
	}
	public int getNoticesum() {
		return noticesum;
	}
	public void setNoticesum(int noticesum) {
		this.noticesum = noticesum;
	}
	public String getDeliveryPoint_id() {
		return deliveryPoint_id;
	}
	public void setDeliveryPoint_id(String deliveryPointId) {
		deliveryPoint_id = deliveryPointId;
	}
	public int getPwd_errer_number() {
		return pwd_errer_number;
	}
	public void setPwd_errer_number(int pwdErrerNumber) {
		pwd_errer_number = pwdErrerNumber;
	}
	public Date getEndEdit_pwd_time() {
		return endEdit_pwd_time;
	}
	public void setEndEdit_pwd_time(Date endEditPwdTime) {
		endEdit_pwd_time = endEditPwdTime;
	}
	public Date getPwd_error_time() {
		return pwd_error_time;
	}
	public void setPwd_error_time(Date pwdErrorTime) {
		pwd_error_time = pwdErrorTime;
	}
	public String getCustNames() {
		return custNames;
	}
	public void setCustNames(String custNames) {
		this.custNames = custNames;
	}
	public String getIs_service_time() {
		return is_service_time;
	}
	public void setIs_service_time(String is_service_time) {
		this.is_service_time = is_service_time;
	}
	public String getShipToParty() {
		return shipToParty;
	}
	public void setShipToParty(String shipToParty) {
		this.shipToParty = shipToParty;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getLcCustId() {
		return LcCustId;
	}
	public void setLcCustId(String lcCustId) {
		LcCustId = lcCustId;
	}
	public String getLcType() {
		return LcType;
	}
	public void setLcType(String lcType) {
		LcType = lcType;
	}
	public String getListRoleName() {
		return listRoleName;
	}
	public void setListRoleName(String listRoleName) {
		this.listRoleName = listRoleName;
	}
	private String provinceName;
	private String  cityName;
	private String typeName;
	private String headImage;
	private String appKey;
	private String orderCount; //订单统计
	private String custName;//客户名称
	private String receiveCount;//收货数统计
	private String sendCount;//发货数统计
	private String custCode;//客户编号
	private String storeCode;//仓库编号
	private List<String> storeNameList;//用户选择的仓库
	private String store_code;
	
	public String getStore_code() {
		return store_code;
	}
	public void setStore_code(String store_code) {
		this.store_code = store_code;
	}
	public List<String> getStoreNameList() {
		return storeNameList;
	}
	public void setStoreNameList(List<String> storeNameList) {
		this.storeNameList = storeNameList;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Date getAppLastLoginTime() {
		return appLastLoginTime;
	}
	public void setAppLastLoginTime(Date appLastLoginTime) {
		this.appLastLoginTime = appLastLoginTime;
	}
	public String getAppTypeName() {
		return appTypeName;
	}
	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}
	public String getAppTypeId() {
		return appTypeId;
	}
	public void setAppTypeId(String appTypeId) {
		this.appTypeId = appTypeId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getIsManageBy() {
		return isManageBy;
	}
	public void setIsManageBy(Integer isManageBy) {
		this.isManageBy = isManageBy;
	}
	public int getRgType() {
		return rgType;
	}
	public void setRgType(int rgType) {
		this.rgType = rgType;
	}
	public String getReceiveCount() {
		return receiveCount;
	}
	public void setReceiveCount(String receiveCount) {
		this.receiveCount = receiveCount;
	}
	public String getSendCount() {
		return sendCount;
	}
	public void setSendCount(String sendCount) {
		this.sendCount = sendCount;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getStoreIdStr() {
		return storeIdStr;
	}
	public void setStoreIdStr(String storeIdStr) {
		this.storeIdStr = storeIdStr;
	}
	public int getUserType1() {
		return userType1;
	}
	public void setUserType1(int userType1) {
		this.userType1 = userType1;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
