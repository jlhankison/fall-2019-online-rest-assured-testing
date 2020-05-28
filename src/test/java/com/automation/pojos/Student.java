package com.automation.pojos;


/**
 * {
 *   "admissionNo": "1234",
 *   "batch": 15,
 *   "birthDate": "01/01/1890",
 *   "company": {
 *     "address": {
 *       "city": "McLean",
 *       "state": "Virginia",
 *       "street": "7925 Jones Branch Dr",
 *       "zipCode": 22102
 *     },
 *     "companyName": "Cybertek",
 *     "startDate": "02/02/2020",
 *     "title": "SDET"
 *   },
 *   "contact": {
 *     "emailAddress": "james_bond@gmail.com",
 *     "phone": "240-123-1231",
 *     "premanentAddress": "7925 Jones Branch Dr"
 *   },
 *   "firstName": "James",
 *   "gender": "Males",
 *   "joinDate": "01/01/3321",
 *   "lastName": "Bond",
 *   "major": "CS",
 *   "password": "1234",
 *   "section": "101010",
 *   "subject": "Math"
 * }
 */
public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private int batch;
    private String joinDate;
    private String birthDate;
    private String password;
    private String subject;
    private String gender;
    private String admissionNo;
    private String major;
    private String section;
}
