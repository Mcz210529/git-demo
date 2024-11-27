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
@TableName("bidding")
@ApiModel(value="Bidding对象", description="")
public class Bidding implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uuid", type = IdType.AUTO)
    private String uuid;

    @ApiModelProperty(value = "招标编号")
    private String biddingId;

    @ApiModelProperty(value = "开标时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "物料编号")
    private String itemNo;

    @ApiModelProperty(value = "物料名称")
    private String itemName;

    @ApiModelProperty(value = "物料规格")
    private String specifications;

    @ApiModelProperty(value = "物料重量")
    private Integer weight;

    @ApiModelProperty(value = "物料单位")
    private String unit;

    @ApiModelProperty(value = "数量")
    private Double number;

    @ApiModelProperty(value = "技术附件")
    private String technicalAttachments;

    @ApiModelProperty(value = "物料技术要求描述")
    private String technicalRequirement;

    @ApiModelProperty(value = "竞标单位,单位用户编号用逗号隔开")
    private String biddingCompany;

    @ApiModelProperty(value = "交货日期")
    private LocalDateTime deliveryDate;

    @ApiModelProperty(value = "报价次数")
    private Integer quoteCount;

    @ApiModelProperty(value = "报价每轮时间（分钟）")
    private Integer duration;

    @ApiModelProperty(value = "0表示保存草稿，1表示发布")
    private Integer visible;

    @ApiModelProperty(value = "0未开始，1进行中，2结束")
    private Integer state;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "技术附件名称")
    private String technicalAttachmentsName;

    private Boolean deleted;

    private Boolean locked;

    private Boolean available;

    private Integer priority;


}
