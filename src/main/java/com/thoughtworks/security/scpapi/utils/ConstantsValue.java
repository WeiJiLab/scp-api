package com.thoughtworks.security.scpapi.utils;

public class ConstantsValue {
    public static final String REPORT_PATH_TMP = "/tmp/compliance_report";
    public static final String USE_CASE_PATH_TMP = "/tmp/usecase";
    public static final String S3_BUCKET_NAME = "sss-inspec-report-bucket-ap-northeast-1";
    public static final String REPORT_NAME_PRE = "Compliance";
    // 当前没有与IAM集成，所以此处暂时写死用户id，后面集成后需要删除
    public static final Long USER_ID = (long)1;
}
