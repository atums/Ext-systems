package com.apys.learning.ext.register_offece.view;

import java.io.Serializable;
import java.time.LocalDate;

public class MarriageRequest implements Serializable {
    //Husband
    private String husbandSurname;
    private String husbandGivenname;
    private String husbandPatronymic;
    private LocalDate husbandDayBirth;
    private String husbandPassportSerial;
    private String husbandPassportNumber;
    private LocalDate husbandPassportIssueDate;
    //Wife
    private String wifeSurname;
    private String wifeGivenname;
    private String wifePatronymic;
    private LocalDate wifeDayBirth;
    private String wifePassportSerial;
    private String wifePassportNumber;
    private LocalDate wifePassportIssueDate;
    //
    private String marriageCertificateNumber;
    private LocalDate marriageCertificateDate;


}
