package com.rizkirm.challenge.bank.controller;

import com.rizkirm.challenge.bank.exception.CustomBadRequestException;
import com.rizkirm.challenge.bank.exception.CustomNotFoundException;
import com.rizkirm.challenge.bank.exception.CustomUnauthorizedException;
import com.rizkirm.challenge.bank.util.ConstantUtil;
import com.rizkirm.challenge.bank.util.JsonUtil;
import com.rizkirm.challenge.bank.vo.ResponsePageVO;
import com.rizkirm.challenge.bank.vo.ResponseVO;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Map;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public abstract class AbstractRequestHandler {

    public static ResponseEntity<ResponsePageVO> getPageResult(Map<String, Object> resultMap) {
        ResponsePageVO responsePageVO = new ResponsePageVO();
        responsePageVO.setTimestamp(new Date());
        try {
            if (resultMap == null) {
                responsePageVO.setPages("0");
                responsePageVO.setElements("0");
                responsePageVO.setMessage(ConstantUtil.ResponseMessage.DATA_NOT_FOUND);
            } else {
                responsePageVO.setResult(resultMap.get(ConstantUtil.PageParameter.LIST_DATA));
                responsePageVO.setPages(String.valueOf(resultMap.get(ConstantUtil.PageParameter.TOTAL_PAGES)));
                responsePageVO.setElements(String.valueOf(resultMap.get(ConstantUtil.PageParameter.TOTAL_ELEMENTS)));
                responsePageVO.setMessage(ConstantUtil.ResponseMessage.OK);
            }
        } catch (Exception e) {
            responsePageVO.setMessage(e.getMessage());
        }
        return JsonUtil.getJsonResponse(responsePageVO);
    }

    public ResponseEntity<ResponseVO> getResult() {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setTimestamp(new Date());
        try {
            Object obj = processRequest();
            if (obj instanceof ResponseVO) {
                responseVO = (ResponseVO) obj;
                return JsonUtil.getJsonResponse(responseVO);
            }

            if (obj != null) {
                responseVO.setMessage(ConstantUtil.ResponseMessage.OK);
                responseVO.setResult(obj);
            } else {
                responseVO.setMessage(ConstantUtil.ResponseMessage.INTERNAL_SERVER_ERROR);
                responseVO.setResult(null);
            }
        } catch (CustomBadRequestException e) {
            responseVO.setMessage(ConstantUtil.ResponseMessage.BAD_REQUEST);
            responseVO.setResult(e.getMessage());
        } catch (CustomNotFoundException e) {
            responseVO.setMessage(ConstantUtil.ResponseMessage.DATA_NOT_FOUND);
            responseVO.setResult(e.getMessage());
        } catch (CustomUnauthorizedException e) {
            responseVO.setMessage(ConstantUtil.ResponseMessage.DATA_NOT_FOUND);
            responseVO.setResult(e.getMessage());
        }

        return JsonUtil.getJsonResponse(responseVO);
    }

    public abstract Object processRequest();
}
