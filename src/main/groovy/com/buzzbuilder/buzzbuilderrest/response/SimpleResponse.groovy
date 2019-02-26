package com.buzzbuilder.buzzbuilderrest.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class SimpleResponse extends BaseResponse {

    public Object item;

    protected SimpleResponse() {
    }

    protected SimpleResponse(int status, String msg, Object item) {
        super(status, msg);
        this.item = item;
    }


}
