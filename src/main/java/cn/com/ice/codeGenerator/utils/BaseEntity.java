package cn.com.ice.codeGenerator.utils;

import lombok.Data;

@Data
public class BaseEntity {

    /**
     * 是否删除(0:删除 1:可用)
     */
    private Integer isDelete;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改人
     */
    private String modifyUser;

    /**
     * 修改时间
     */
    private String modifyTime;
}
