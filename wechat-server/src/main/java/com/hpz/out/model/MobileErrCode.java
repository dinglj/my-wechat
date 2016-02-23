package com.hpz.out.model;

/** 移动端接口错误码
 * Created by dinglj
 */
public enum MobileErrCode {

    RESULT_SUCCESS(20,"返回成功"),

    ERR_PARAMS_WRONG(4000,"入参数据错误"),
    ERR_PARAMS_PAGE_NULL(4001,"分页查询page对象为空"),
    ERR_PARAMS_KEY_NULL(4002,"搜索的key值为空"),
    ERR_PARAMS_DEVICEID_NULL(4003,"设备唯一识别码为空"),
    ERR_PARAMS_TOKENID_NULL(4004,"tokenId为空"),
    ERR_PARAMS_ID_NULL(4005,"id为空"),
    ERR_PARAMS_OBJECT_NULL(4006,"入参对象为空"),
    ERR_PARAMS_USERID_NULL(4007,"用户id为空"),
    ERR_PARAMS_CELLPOHNE_PWD_NULL(4008,"手机号码和用户密码为空"),
    ERR_PARAMS_USER_NOT_EXIST(4009,"不存在该用户"),
    ERR_PARAMS_PWD_WRONG(4010,"用户密码错误"),
    ERR_PARAMS_CELLPHONE_NULL(4011,"手机号码为空"),
    ERR_PARAMS_CELLPHONE_INVALID(4012,"手机号码应为11位"),
    ERR_PARAMS_PWD_NULL(4013,"密码为空"),
    ERR_PARAMS_CATEGORYID_NULL(4014,"类目id为空"),
    ERR_PARAMS_ACTIVITYTYPE_NULL(4015,"活动商品类型为空"),
    ERR_PARAMS_COLLECTIONTYPE_NULL(4016,"收藏类型为空"),
    ERR_PARAMS_ITEM_OR_BRAND_NULL(4017,"收藏的商品或品牌专场id为空"),
    ERR_PARAMS_STR_FORMAT_WRONG(4018,"字符串格式错误无法解析"),
    ERR_PARAMS_COLLECTIONTYPE_WRONG(4019,"收藏类型错误"),


    RESULT_QUERY_NULL(3001,"查询无记录"),
    RESULT_QUERY_TOKEN_NULL(3002,"找不到token信息"),
    RESULT_QUERY_IDENTIFYCODE_NULL(3003,"找不到验证"),
    RESULT_CONFIGURE_QUERY_NULL(3004,"查询不到配置信息"),

    RESULT_HANDLE_DEVICEID_NOTMATCH(5001,"设备唯一码不匹配"),
    RESULT_HANDLE_TOKEN_EXPIRE(5002,"token过期"),
    RESULT_HANDLE_ALREADY_SIGNIN(5003,"您今天已经签到过了"),
    RESULT_HANDLE_IDENTIFYCODE_NOTMATCH(5004,"验证码错误"),
    RESULT_HANDLE_SEND_FAIL(5005,"发送失败"),
    RESULT_HANDLE_CELLPHONE_EXIST(5006,"该手机号已注册"),
    RESULT_HANDLE_COLLECTION_EXIST(5007,"该商品或品牌专场已收藏"),
    RESULT_HANDLE_FORMAT_ERR(5008,"格式转化错误"),

    ERR_SQL_UPDATE(6001,"更新操作失败"),
    ERR_SQL_INSERT(6002,"添加操作失败"),
    ERR_SQL_DEL(6003,"删除操作失败"),


    RESULT_WRONG(999,"系统错误");


    private Integer code;

    private String desc;

    private MobileErrCode(Integer code, String desc){
        this.code  = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static MobileErrCode fromValue(Integer  code){
        for(MobileErrCode result:values()){
            if(result != null && result.getCode() == code){
                return result;
            }
        }
        return  null;
    }
}
