package com.simplify.api.service;

import com.simplify.api.model.response.CommonResult;
import com.simplify.api.model.response.ListResult;
import com.simplify.api.model.response.SingleResult;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Getter
    public enum CommonResponse{

        SUCCESS(0, "성공하였습니다."),
        FAIL(-1, "실패하였습니다.");

        int code;
        String msg;

        CommonResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    public <T> SingleResult<T> getSingleResult(T data){
        SingleResult<T> singleResult = new SingleResult<>();
        singleResult.setData(data);
        setSuccessResult(singleResult);
        return singleResult;
    }

    public <T> ListResult<T> getListResult(List<T> list){
        ListResult<T> listResult = new ListResult<>();
        listResult.setList(list);
        setSuccessResult(listResult);
        return listResult;
    }

    public CommonResult getSuccessResult(){
        CommonResult commonResult = new CommonResult();
        setSuccessResult(commonResult);
        return commonResult;
    }

    public CommonResult getFailResult(int code, String msg){
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(false);
//        commonResult.setCode(CommonResponse.FAIL.getCode());
//        commonResult.setMsg(CommonResponse.FAIL.getMsg());
        commonResult.setCode(code);
        commonResult.setMsg(msg);
        return commonResult;
    }

    private void setSuccessResult(CommonResult commonResult){
        commonResult.setSuccess(true);
        commonResult.setCode(CommonResponse.SUCCESS.getCode());
        commonResult.setMsg(CommonResponse.SUCCESS.getMsg());
    }


}
