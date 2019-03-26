package com.zillionfortune.cif.biz.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.facade.user.EnterpriseQualificationFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseQualificationUpdateRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-basic.xml")
@ActiveProfiles("dev")
public class EnterpriseQualificationFacadeImplTest {

	@Autowired
	private EnterpriseQualificationFacade facade;
	@Test
	public void update() {
		EnterpriseQualificationUpdateRequest req = new EnterpriseQualificationUpdateRequest();
		req.setMemberId("EM201612201129335535205108");
		req.setAccountOpeningLicenseUrl("test.jpg");
		req.setBusinessLicenceUrl("businessLicenceUrl");
		req.setLegalPersonCertificateBackUrl("legalPersonCertificateBackUrl");
		req.setLegalPersonCertificateFrontUrl("legalPersonCertificateFrontUrl");
		req.setOrganizationCodeCertificateUrl("organizationCodeCertificateUrl");
		req.setPowerOfAttorneyUrl("powerOfAttorneyUrl");
		req.setServiceApplicationUrl("serviceApplicationUrl");
		req.setTaxRegistrationCertificateLocalUrl("taxRegistrationCertificateLocalUrl");
		req.setTaxRegistrationCertificateUrl("taxRegistrationCertificateUrl");
		System.out.println(JSONObject.toJSONString(facade.qualificationUpdate(req)));
	}
}
