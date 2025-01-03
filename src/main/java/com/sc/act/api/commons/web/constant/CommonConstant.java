package com.sc.act.api.commons.web.constant;


import java.nio.charset.Charset;

/**
 * @ClassName Constant
 * @Description 系统通用常量定义
 */
public interface CommonConstant {
    String MDC_REQ_UID = "REQ_UID";
    String MDC_USER_CODE = "USER_CODE";
    String MDC_USER_NAME = "USER_NAME";
    String MDC_METHOD_PARAM = "METHOD_PARAM";

    String REQUEST_METHOD_POST = "POST";
    String REQUEST_METHOD_GET = "GET";
    String CONTENTTYPE_JSON = "application/json";
    String CONTENTTYPE_FORM = "application/x-www-form-urlencoded";
    String CONTENTTYPE_FORM_DATA = "multipart/form-data";
    String ENCODING_UTF8 = "UTF-8";
    Charset UTF_8 = Charset.forName("UTF-8");
    Charset US_ASCII = Charset.forName("US-ASCII");
    Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    String TOKEN_KEY = "Token";
    String SIGN_KEY = "Sign";
    String DEVICE_TYPE_KEY = "Device-Type";
    String DEVICETYPE_AND = "1";
    String DEVICETYPE_IOS = "2";
    String STRING_HYPHEN = "-";
    String STRING_UNDERLINE = "_";
    String STRING_EMPTY = "";
    String STRING_AND = "&";
    String STRING_AND_BLANK = " & ";
    String STRING_EQUAL = "=";
    String STRING_REGEXP = "[\"{}]";
    String STRING_COMMA = ",";
    String STRING_COLON = ":";
    String STRING_OK = "OK";
    String STRING_TOKEN_KEY = "key";
    String OBJECT_USERINFO = "UserInfo";
    String MD5 = "MD5";
    String STRING_LINE_BREAK = "\n";
    String STRING_SEMICOLON = ";";
    String STRING_PERCENT = "%";
    String EXCEL_XLS = "xls";
    String EXCEL_XLSX = "xlsx";
    String EXCEL_FILENAME_COMMA = ".";
    /**
     *
     */
    String X_REQUESTED_WITH = "X-Requested-With";
    String XMLHTTPREQUEST = "XMLHttpRequest";
    Integer EXCEL_ROW_NUM = 51;
    Integer PRODUCT_STATE_1 = 1;
    Integer PRODUCT_PLATFORM_SHOPXO_0 = 0;
    Integer PRODUCT_TICKET_0 = 0;
    Integer PRODUCT_TICKET_1 = 1;
    Integer PRODUCT_TICKET_2 = 2;

    /**
     * 0-处理中，1-成功，2-失败，3-未知失败
     */
    Integer ACC_SEP_RECORD_STATUS_0 = 0;
    Integer ACC_SEP_RECORD_STATUS_1 = 1;
    Integer ACC_SEP_RECORD_STATUS_2 = 2;
    Integer ACC_SEP_RECORD_STATUS_3 = 3;


}
