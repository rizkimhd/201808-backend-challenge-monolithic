package com.rizkirm.challenge.bank.validator;

import com.rizkirm.challenge.bank.exception.CustomBadRequestException;
import com.rizkirm.challenge.bank.exception.CustomUnauthorizedException;
import com.rizkirm.challenge.bank.util.AuthenticationUtil;
import com.rizkirm.challenge.bank.vo.TransactionRequestVO;
import com.rizkirm.challenge.bank.vo.TransferRequestVO;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class TransactionValidator extends AuthenticationUtil {

    public void checkRequest(String token, TransactionRequestVO transactionRequestVO) {
        if(isValidToken(token)) {
            if(transactionRequestVO.getAmount() == null) {
                throw new CustomBadRequestException("Transaction amount cannot be empty");
            }

            if(transactionRequestVO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new CustomBadRequestException("Transaction amount must be greater than zero");
            }
        } else {
            throw new CustomUnauthorizedException();
        }
    }

    public void checkRequest(String token, TransferRequestVO transferRequestVO) {
        if(isValidToken(token)) {
            if(StringUtils.isEmpty(transferRequestVO.getReceiverAccount())) {
                throw new CustomBadRequestException("Receiver account cannot be empty");
            }

            if(transferRequestVO.getAmount() == null) {
                throw new CustomBadRequestException("Transaction amount cannot be empty");
            }

            if(transferRequestVO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new CustomBadRequestException("Transaction amount must be greater than zero");
            }
        } else {
            throw new CustomUnauthorizedException();
        }
    }

}
