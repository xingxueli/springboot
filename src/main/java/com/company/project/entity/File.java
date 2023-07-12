package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author pzblog
 * @since 2023-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_file")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文件名称
     */
    @TableField("name")
    private String name;

    /**
     * 文件
     */
    @TableField("bin_data")
    private byte[] binData;

    /**
     * Original name
     */
    @TableField("original_name")
    private String originalName;

    /**
     * File storage path
     */
    @TableField("path")
    private String path;

    /**
     * file format
     */
    @TableField("format")
    private String format;

    /**
     * File size (byte)
     */
    @TableField("size")
    private Long size;

    /**
     * md5
     */
    @TableField("md5")
    private String md5;

    /**
     * key name
     */
    @TableField("key_name")
    private String keyName;

    /**
     * Data status (logical deletion)
     */
    @TableField("is_deleted")
    private Boolean isDeleted;

    /**
     * Creation time
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * Update time
     */
    @TableField("update_time")
    private Date updateTime;


}
