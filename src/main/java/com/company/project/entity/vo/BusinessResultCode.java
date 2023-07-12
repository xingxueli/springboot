package com.company.project.entity.vo;

/**
 * 枚举业务常用返回码
 *
 * @author mike
 * @version $Id: $Id
 */
public enum BusinessResultCode implements IResultCode {


    //通用成功返回码
    SUCCESS(2000, "success"),

    //Internal request error
    INTERNAL_ERROR(4000, "Internal Service Error"),

    //通用失败返回码
    FAILED(5000, "Network connection error!"),

    /**
     * 1开头 通用异常
     */
    //未知错误
    UNKNOWN_ERROR(10000, "An error occurred. Please contact us at {support} so we can help you fix it."),
    //参数不合法
    INVALID_PARAM(10001, "invalid param ,check the param again"),
    //检验失败
    VALIDATE_FAILED(10002, "validate failure"),
    //操作太频繁,并发控制
    OPERATION_TOO_FREQUENTLY(10003, "Operation too frequently"),


    /**
     * 2开头 login相关
     */
    //未验证权限
    UNAUTHORIZED(20000, "user didn't login or token is out date"),
    //账号未激活
    USER_NOT_EXIST(20001, "user doesn't exist!"),
    USER_EXISTS(20002, "user already existed!"),
    //账号未激活
    ACCOUNT_UNCONFIRMED(20003, "user account was unconfirmed!"),
    //账号被禁用
    ACCOUNT_BANNED(20004, "user account was banned!"),
    //firebase 相关异常
    FIREBASE_EXCEPTION(20005, "there are something wrong when connecting to firebase..."),
    //firebase token被撤销
    TOKEN_REVOKED(20006, "Login expired, please login again!"),
    //firebase refreshtoken被撤销
    REFRESHTOKEN_INVALID(20007, "Login expired, please login again!"),
    //验证码发送过于频繁
    FAIL_SEND_OTP(20008, "otp request are too frequent"),

    FAIL_SEND_OTP_BY_HEADER(20009, "bad request"),

    PASSWORD_OVER_TIME_FORCE_CHANGE(20010, "Your password expired.Please change it now."),
    PASSWORD_OVER_TIME_PROMPT_CHANGE(20011, "Your password will expire.Please change your password as soon as possible."),

    HCAPYCHAR_VALIDATION_ERROR(20012, "hCaptcha validation error!"),
    /**
     * 3开头 招聘者相关
     */
    RECRUITER_NOT_EXISTS(30001, "recruiter doesn't exist!"),
    RECRUITER_EXISTS(30002, "recruiter already existed!"),
    //账号被禁用
    RECRUITER_BANNED(30004, "recruiter account was banned!"),
    //投递工作数量已达上限
    LIMIT_CREATE_JOB(30001, "reach the limit count of creating jobs"),
    //投递工作数量已达上限
    RECRUITER_IS_EXIT(30002, "recruiter is exit,don't repeat to create"),
    WAITING_FOR_VERIFIED(30010, "already uploaded, please wait for verifying!"),
    ALREADY_VERIFIED(30011, "already verified, don't upload document again!"),
    UN_VERIFIED_COMPANY(30012, "the company is not verified, please verify the company first!"),
    FORBIDDEN_VERIFY_RECRUITER(30013, "YOU ARE NOT ALLOWED TO UPLOAD AGAIN!PLEASE CONTACT THE MANAGER!"),
    DOCUMENT_ALREADY_REVIEWED(30014, "document was already reviewed!"),
    LIMIT_CREATE_JOB_BEFORE_VERIFICATION(30015, "Hirect is dedicated to creating an environment if efficiency and authenticity . Get verified and unlock unlimited job posts"),
    LIMIT_SEARCH_CANDIDATE_BEFORE_VERIFICATION(30016, "Hirect is dedicated to creating an environment if efficiency and authenticity . Get verified and unlock unlimited searchs"),
    RECRUITER_REJECT_REVIEW(30019, "recruiter has been reject by review,need upload document again!"),
    RECRUITER_ACCOUNT_CANCELLATION(30020, "recruiter account has been cancelled."),
    RECRUITER_HIREY_OTP_SEND_BLOCK(30021, "recruiter block send otp."),

    /**
     * 招聘者邮箱黑名单返回码
     */
    IN_EMAIL_BLACK(30017, "The mailbox is unavailable！"),
    PART_MATCH_EMAIL_BLACK(30018, "The mailbox may be unavailable！"),


    /**
     * 4开头 求职者相关
     */

    CANDIDATE_NOT_EXISTS(40001, "candidate doesn't exist!"),
    CANDIDATE_EXISTS(40002, "candidate already existed!"),
    //账号被禁用
    CANDIDATE_BANNED(40004, "candidate account was banned."),
    PREFERENCE_EXISTS(40005, "You have added the same job preference."),
    FORBIDDEN_DELETE_PREFERENCE(40006, "You can't delete this job preference details. At least one job preference details is required."),
    FORBIDDEN_DELETE_EDUCATION(40007, "You can't delete this work education details. At least one work experience details is required."),
    FORBIDDEN_DELETE_EXPERIENCE(40008, "You can't delete this work experience details. At least one work experience details is required."),
    CANDIDATE_ACCOUNT_CANCELLATION(40009, "candidate account has been cancelled."),

    /**
     * 5开头 工作相关
     */


    /**
     * 6开头 公司相关
     */
    //公司的boss数量达到上限
    LIMIT_BOSS_COUNT(60001, "reach the limit count of company bosses"),
    WAITING_FOR_VERIFIED1(60010, "already uploaded, please wait for verifying!"),
    FORBIDDEN_VERIFY_COMPANY(60011, "YOU ARE NOT ALLOWED TO UPLOAD AGAIN!PLEASE CONTACT THE MANAGER!"),

    /**
     * 7开头 聊天相关
     */
    //发起聊天的数量达到上限
    LIMIT_INIT_CHAT_COUNT(70001, "reach the limit count of chat initiate"),

    /**
     * 8 文件处理相关
     */
    FAIL_UPLOAD_FILE(80001, "上传文件失败"),
    FORBID_UPLOADING_FILE(80002, "不允许上传文件"),
    FILE_NOT_FOUND(80003, "file not found!"),

    /**
     * 9 邮件处理相关
     */
    MAIL_NOT_BINDED(90033, "mail has not binded "),
    FAIL_SEND_MAIL(90001, "发送邮件失败！"),
    FORCE_LOGOUT_WITHOUT_MSG(90000, "");


    private final int code;
    private final String message;

    private BusinessResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getCode() {
        return this.code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    public static BusinessResultCode fromCode(int code) {
        for (BusinessResultCode resultCode : BusinessResultCode.values()) {
            if (resultCode.getCode() == code) {
                return resultCode;
            }
        }
        return null;
    }

}
