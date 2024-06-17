package cn.iocoder.yudao.module.infra.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * bookstore 错误码枚举类
 *
 * bookstore
 */
public interface BookStoreErrorCodeConstants {
    ErrorCode SOURCE_INFO_NOT_EXISTS = new ErrorCode(1_001_202_001, "资源信息不存在");

    ErrorCode BOOK_CHAPTER_NOT_EXISTS = new ErrorCode(1_001_202_002, "图书章节不存在");

    ErrorCode BOOK_INFO_NOT_EXISTS = new ErrorCode(1_001_202_003, "图书信息不存在");

    ErrorCode BOOK_QTCODE_INFO_NOT_EXISTS = new ErrorCode(1_001_202_004, "图书二维码信息不存在");

    ErrorCode BOOK_QTCODE_ITEM_NOT_EXISTS = new ErrorCode(1_001_202_005, "二维码项目不存在");

    ErrorCode BOOK_QTCODE_SOURCE_NOT_EXISTS = new ErrorCode(1_001_202_006, "二维码资源不存在");
    ErrorCode BOOK_SOURCE_SIGN_FAIL = new ErrorCode(1_001_203_001, "签名生成失败");
    ErrorCode OSS_STS_SIGN_FAIL = new ErrorCode(1_001_203_002, "生成STS签名生成失败");

    ErrorCode SOURCEAUDIT_HISTORY_NOT_EXISTS = new ErrorCode(1_001_204_001, "资源审核记录不存在");
}
