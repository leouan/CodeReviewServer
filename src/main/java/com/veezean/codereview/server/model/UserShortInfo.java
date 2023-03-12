package com.veezean.codereview.server.model;

import lombok.Data;

/**
 * <类功能简要描述>
 *
 * @author Veezean, 公众号 @架构悟道
 * @since 2023/3/12
 */
@Data
public class UserShortInfo {
    private String account;
    private String userName;
    private String department;
}