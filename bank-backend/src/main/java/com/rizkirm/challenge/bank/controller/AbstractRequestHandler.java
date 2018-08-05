package com.rizkirm.challenge.bank.controller;

import com.rizkirm.challenge.bank.exception.CustomBadRequestException;
import com.rizkirm.challenge.bank.exception.CustomNotFoundException;
import com.rizkirm.challenge.bank.exception.CustomUnauthorizedException;
import com.rizkirm.challenge.bank.util.ConstantUtil;
import com.rizkirm.challenge.bank.util.JsonUtil;
import com.rizkirm.challenge.bank.vo.ResponsePageVO;
import com.rizkirm.challenge.bank.vo.ResponseVO;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public abstract class AbstractRequestHandler {
    public static ResponseEntity<ResponsePageVO> constructListResult(Map<String, Object> pageMap) {
        ResponsePageVO result = new ResponsePageVO();
        result.setTimestamp(new Date());
        try {
            Collection list = constructPageResult(pageMap, result);
            result.setResult(list);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
        }
        return JsonUtil.getJsonResponse(result);
    }

    public static Collection constructPageResult(Map<String, Object> map, ResponsePageVO result) {
        if (map == null) {
            result.setPages("0");
            result.setElements("0");
            result.setMessage(ConstantUtil.ResponseMessage.DATA_NOT_FOUND);
            return null;
        } else {
            Collection vos = (Collection) map.get(ConstantUtil.PageParameter.LIST_DATA);
            result.setPages(String.valueOf(map.get(ConstantUtil.PageParameter.TOTAL_PAGES)));
            result.setElements(String.valueOf(map.get(ConstantUtil.PageParameter.TOTAL_ELEMENTS)));
            result.setMessage(ConstantUtil.ResponseMessage.OK);
            return vos;
        }
    }

    public ResponseEntity<ResponseVO> getResult() {
        ResponseVO result = new ResponseVO();
        result.setTimestamp(new Date());
        try {
            Object obj = processRequest();
            if (obj instanceof ResponseVO) {
                result = (ResponseVO) obj;
                return JsonUtil.getJsonResponse(result);
            }

            if (obj != null) {
                result.setMessage(ConstantUtil.ResponseMessage.OK);
                result.setResult(obj);
            } else {
                result.setMessage(ConstantUtil.ResponseMessage.INTERNAL_SERVER_ERROR);
                result.setResult(null);
            }
        } catch (CustomBadRequestException e) {
            result.setMessage(ConstantUtil.ResponseMessage.BAD_REQUEST);
            result.setResult(e.getMessage());
        } catch (CustomNotFoundException e) {
            result.setMessage(ConstantUtil.ResponseMessage.DATA_NOT_FOUND);
            result.setResult(e.getMessage());
        } catch (CustomUnauthorizedException e) {
            result.setMessage(ConstantUtil.ResponseMessage.DATA_NOT_FOUND);
            result.setResult(e.getMessage());
        }

        return JsonUtil.getJsonResponse(result);
    }

    public abstract Object processRequest();
}
