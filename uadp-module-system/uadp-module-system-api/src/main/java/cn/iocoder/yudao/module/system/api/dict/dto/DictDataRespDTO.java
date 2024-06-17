package cn.iocoder.yudao.module.system.api.dict.dto;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import lombok.Data;

/**
 * 字典数据 Response DTO
 *
 * @author 晟云鑫通
 */
@Data
public class DictDataRespDTO {

    /**
     * 字典标签
     */
    private String label;
    /**
     * 字典值
     */
    private String value;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}
