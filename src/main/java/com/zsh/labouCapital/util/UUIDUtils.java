package com.zsh.labouCapital.util;

import java.util.UUID;

/**
 * 
 * <p>
 * Title: UUIDUtils
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: openlo.cn Copyright (C) 2016
 * </p>
 *
 * @author chenz
 * @version
 * @since 2016年10月9日
 */
public class UUIDUtils {
    public static String get() {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        return id;
    }

}
