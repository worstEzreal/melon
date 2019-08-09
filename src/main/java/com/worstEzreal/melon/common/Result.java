package com.worstEzreal.melon.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * restful 返回结果
 *
 * @author worstEzreal
 * @date 2019/8/1
 */
@Setter
@Getter
@AllArgsConstructor
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(0, "success", data);
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> error() {
        return new Result<>(-1, "error", null);
    }
}
