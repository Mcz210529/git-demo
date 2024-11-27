package com.mcz.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mcz
 * @since 2024-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("_sys_user")
@ApiModel(value="SysUser对象", description="")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键，唯一标识")
    @TableId(value = "uuid", type = IdType.AUTO)
    private String uuid;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "记录创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "记录更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "记录删除时间")
    private LocalDateTime deleteTime;

    @ApiModelProperty(value = "是否已删除")
    private Boolean deleted;

    @ApiModelProperty(value = "是否锁定")
    private Boolean locked;

    @ApiModelProperty(value = "是否可用")
    private Boolean available;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "创建该用户的管理员ID")
    private String createUser;

    @ApiModelProperty(value = "最后修改该用户的管理员ID")
    private String updateUser;

    @ApiModelProperty(value = "删除该用户的管理员ID")
    private String deleteUser;

    @ApiModelProperty(value = "用户代码")
    private String userCode;

    @ApiModelProperty(value = "登录用户名")
    private String username;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "状态码（如启用、禁用等）")
    private Integer status;

    @ApiModelProperty(value = "用户类型（如管理员、普通用户）")
    private String userType;

    @ApiModelProperty(value = "是否接收通知")
    private Boolean receiver;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "关联的角色ID")
    private String roleId;

    @ApiModelProperty(value = "用户所属组")
    private String userGroup;

    @ApiModelProperty(value = "所属公司")
    private String company;

    @ApiModelProperty(value = "厂商代码")
    private String vendorCode;

    @ApiModelProperty(value = "联系方式")
    private String tel;


}
