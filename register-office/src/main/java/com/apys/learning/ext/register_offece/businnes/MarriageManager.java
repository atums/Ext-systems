package com.apys.learning.ext.register_offece.businnes;

import com.apys.learning.ext.register_offece.dao.MarriageDao;
import com.apys.learning.ext.register_offece.domain.MarriageCertificate;
import com.apys.learning.ext.register_offece.rest.MarriageController;
import com.apys.learning.ext.register_offece.view.MarriageRequest;
import com.apys.learning.ext.register_offece.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarriageManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageManager.class);
    private MarriageDao marriageDao;

    public void setMarriageDao(MarriageDao marriageDao) {
        this.marriageDao = marriageDao;
    }

    public MarriageResponse findMarriageCertificate (MarriageRequest request) {
        LOGGER.info("findMarriageCertificate called");
        MarriageCertificate marriageCertificate = marriageDao.findMarriageCertificate(request);
        return new MarriageResponse();
    }
}
