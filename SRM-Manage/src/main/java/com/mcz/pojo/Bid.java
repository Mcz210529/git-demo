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
@TableName("bid")
@ApiModel(value="Bid对象", description="")
public class Bid implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "投标编号")
    @TableId(value = "uuid", type = IdType.AUTO)
    private String uuid;

    @ApiModelProperty(value = "招标编号,外键")
    private String biddingId;

    @ApiModelProperty(value = "用户编号,外键")
    private String userId;

    @ApiModelProperty(value = "报价金额")
    private Double quotedPrice;

    @ApiModelProperty(value = "货期天数")
    private Integer deliveryDays;

    @ApiModelProperty(value = "预计交货期")
    private LocalDateTime deliveryDate;

    @ApiModelProperty(value = "技术偏差说明")
    private String technicalDeviationDescription;

    @ApiModelProperty(value = "投标第次")
    private Integer bidCount;

    @ApiModelProperty(value = "价格排名")
    private Integer priceRanking;

    @ApiModelProperty(value = "质量排名")
    private Integer qualityRanking;

    @ApiModelProperty(value = "服务排名")
    private Integer serviceRanking;

    @ApiModelProperty(value = "价格评分")
    private Double priceScore;

    @ApiModelProperty(value = "质量评分")
    private Double qualityScore;

    @ApiModelProperty(value = "服务评分")
    private Double serviceScore;

    @ApiModelProperty(value = "总分")
    private Double totalScore;

    @ApiModelProperty(value = "是否在招标结果显示；0不显示，1显示")
    private Integer visible;

    @ApiModelProperty(value = "是否中标,0未中标，1中标")
    private Integer winningTheBid;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean deleted;

    private Boolean locked;

    private Boolean available;

    private Integer priority;

    @ApiModelProperty(value = "是否已评标，0未评，1已评")
    private Integer isEvaluation;


}
