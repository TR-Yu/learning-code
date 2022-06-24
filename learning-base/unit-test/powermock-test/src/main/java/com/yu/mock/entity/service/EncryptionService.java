package com.yu.mock.entity.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * @author YU
 * @date 2022-06-18 10:04
 */
public class EncryptionService {
    private final static Logger logger = Logger.getLogger("EncryptionService");

    public String sha256(String strText) {
        if (strText == null) {
            return null;
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            return new BigInteger(1, md.digest(strText.getBytes())).toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.warning("异常抛出" + e);
        }
        return null;
    }
}
