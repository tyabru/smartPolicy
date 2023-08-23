package com.jingyu.common.exception;

public class ExcelRowErrorInsertException extends RuntimeException{

    private int index;
    private String message;

    public ExcelRowErrorInsertException(int index, String message)
    {
        this.index = index;
        this.message = message;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getErrorMessage(int sheetIndex) {
        return String.format("sheet【%s】第【%s】行导入出现异常：%s", sheetIndex, index, message);
    }
}
