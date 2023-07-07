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
    //Certificate
    private String marriageCertificateNumber;
    private LocalDate marriageCertificateDate;

    public String getHusbandSurname() {
        return husbandSurname;
    }

    public void setHusbandSurname(String husbandSurname) {
        this.husbandSurname = husbandSurname;
    }

    public String getHusbandGivenname() {
        return husbandGivenname;
    }

    public void setHusbandGivenname(String husbandGivenname) {
        this.husbandGivenname = husbandGivenname;
    }

    public String getHusbandPatronymic() {
        return husbandPatronymic;
    }

    public void setHusbandPatronymic(String husbandPatronymic) {
        this.husbandPatronymic = husbandPatronymic;
    }

    public LocalDate getHusbandDayBirth() {
        return husbandDayBirth;
    }

    public void setHusbandDayBirth(LocalDate husbandDayBirth) {
        this.husbandDayBirth = husbandDayBirth;
    }

    public String getHusbandPassportSerial() {
        return husbandPassportSerial;
    }

    public void setHusbandPassportSerial(String husbandPassportSerial) {
        this.husbandPassportSerial = husbandPassportSerial;
    }

    public String getHusbandPassportNumber() {
        return husbandPassportNumber;
    }

    public void setHusbandPassportNumber(String husbandPassportNumber) {
        this.husbandPassportNumber = husbandPassportNumber;
    }

    public LocalDate getHusbandPassportIssueDate() {
        return husbandPassportIssueDate;
    }

    public void setHusbandPassportIssueDate(LocalDate husbandPassportIssueDate) {
        this.husbandPassportIssueDate = husbandPassportIssueDate;
    }

    public String getWifeSurname() {
        return wifeSurname;
    }

    public void setWifeSurname(String wifeSurname) {
        this.wifeSurname = wifeSurname;
    }

    public String getWifeGivenname() {
        return wifeGivenname;
    }

    public void setWifeGivenname(String wifeGivenname) {
        this.wifeGivenname = wifeGivenname;
    }

    public String getWifePatronymic() {
        return wifePatronymic;
    }

    public void setWifePatronymic(String wifePatronymic) {
        this.wifePatronymic = wifePatronymic;
    }

    public LocalDate getWifeDayBirth() {
        return wifeDayBirth;
    }

    public void setWifeDayBirth(LocalDate wifeDayBirth) {
        this.wifeDayBirth = wifeDayBirth;
    }

    public String getWifePassportSerial() {
        return wifePassportSerial;
    }

    public void setWifePassportSerial(String wifePassportSerial) {
        this.wifePassportSerial = wifePassportSerial;
    }

    public String getWifePassportNumber() {
        return wifePassportNumber;
    }

    public void setWifePassportNumber(String wifePassportNumber) {
        this.wifePassportNumber = wifePassportNumber;
    }

    public LocalDate getWifePassportIssueDate() {
        return wifePassportIssueDate;
    }

    public void setWifePassportIssueDate(LocalDate wifePassportIssueDate) {
        this.wifePassportIssueDate = wifePassportIssueDate;
    }

    public String getMarriageCertificateNumber() {
        return marriageCertificateNumber;
    }

    public void setMarriageCertificateNumber(String marriageCertificateNumber) {
        this.marriageCertificateNumber = marriageCertificateNumber;
    }

    public LocalDate getMarriageCertificateDate() {
        return marriageCertificateDate;
    }

    public void setMarriageCertificateDate(LocalDate marriageCertificateDate) {
        this.marriageCertificateDate = marriageCertificateDate;
    }
}
