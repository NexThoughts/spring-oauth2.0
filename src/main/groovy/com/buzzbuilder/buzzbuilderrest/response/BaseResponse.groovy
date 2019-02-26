package com.buzzbuilder.buzzbuilderrest.response;

import lombok.Data;


@Data
public  class BaseResponse  {

    public int status;
    public String msg;

    public BaseResponse() {
    }

    public BaseResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
