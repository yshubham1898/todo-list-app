package com.shubham.todolistapp.data;

import com.shubham.todolistapp.entity.Todo;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;


public class ResponseDto<T> {


    private T data ;
    private long totalRecords;
    private Error error;

    public ResponseDto(T data, long totalRecords, Error error) {
        this.data = data;
        this.totalRecords = totalRecords;
        this.error = error;
    }

    public ResponseDto() {

    }

    public T getData() {
        return data;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public Error getError() {
        return error;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
