package com.tecsup.petclinic.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.fail;

import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;

import com.tecsup.petclinic.exception.OwnerNotFoundException;

@SpringBootTest
public class OwnerServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
	private OwnerService ownerService;

	@Test
	public void testDeleteOwner() {

		String first_name = "Cordova";
		String last_name = "Bryan";
		String address = "Avenida San Carlos SJL";
		String city = "Lima";
		String telephone = "987654321";

		Owner own = new Owner(first_name, last_name, address, city, telephone);
		own = ownerService.create(own);
		logger.info("" + own);

		try {
			ownerService.delete(own.getId());
		} catch (OwnerNotFoundException e) {
			assertThat(e.getMessage(), false);
		}

		try {
			ownerService.findById(own.getId());
			assertThat(true, is(false));
		} catch (OwnerNotFoundException e) {
			assertThat(true, is(true));
		}

	}

	@Test
	public void testFindOwnerById() {

		long ID = 1;
		String NAME = "Beremiz";
		Owner owner = null;

		try {

			owner = ownerService.findById(ID);

		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		logger.info("" + owner);

		assertThat(owner.getFirst_name(), is(NAME));

	}

	@Test
	public void testCreateOwner() {
		String OWNER_FIRSTNAME = "Alarcon";
		String OWNER_LASTNAME = "Jhanina";
		String OWNER_ADDRESS = "Avenida Santa Anita";
		String OWNER_CITY = "Lima";
		String OWNER_TELEPHONE = "987321654";

		Owner owner = new Owner(OWNER_FIRSTNAME, OWNER_LASTNAME, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);

		Owner ownerCreated = ownerService.create(owner);

		logger.info("OWNER CREATED :" + ownerCreated);

		// ACTUAL , EXPECTED
		assertThat(ownerCreated.getId(), notNullValue());
		assertThat(ownerCreated.getFirst_name(), is(OWNER_FIRSTNAME));
		assertThat(ownerCreated.getLast_name(), is(OWNER_LASTNAME));
		assertThat(ownerCreated.getAddress(), is(OWNER_ADDRESS));
		assertThat(ownerCreated.getCity(), is(OWNER_CITY));
		assertThat(ownerCreated.getTelephone(), is(OWNER_TELEPHONE));

	}

	@Test
	public void testUpdateOwnerById() {

		String FIRST_NAME = "Lopez";
		String LAST_NAME = "Renzo";
		String ADDRESS = "Avenida El Ovalo";
		String CITY = "Lima";
		String TELEPHONE = "123789456";
		long created_id = -1;
		// UPDATE

		String UP_FIRST_NAME = "Ñahui";
		String UP_LAST_NAME = "Beremiz";
		String UP_ADDRESS = "San juan de lurigancho";
		String UP_CITY = "Lima";
		String UP_TELEPHONE = "123456789";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);

		logger.info(">" + owner);
		Owner ownerCreated = ownerService.create(owner);
		logger.info(">>" + ownerCreated);

		created_id = ownerCreated.getId();

		ownerCreated.setId(created_id);
		ownerCreated.setFirst_name(UP_FIRST_NAME);
		ownerCreated.setLast_name(UP_LAST_NAME);
		ownerCreated.setAddress(UP_ADDRESS);
		ownerCreated.setCity(UP_CITY);
		ownerCreated.setTelephone(UP_TELEPHONE);

		// Execute update
		Owner upgradeOwner = ownerService.update(ownerCreated);
		logger.info(">>>>" + upgradeOwner);

		// ACTUAL EXPECTED
		assertThat(created_id, notNullValue());
		assertThat(upgradeOwner.getId(), is(created_id));
		assertThat(upgradeOwner.getFirst_name(), is(UP_FIRST_NAME));
		assertThat(upgradeOwner.getLast_name(), is(UP_LAST_NAME));
		assertThat(upgradeOwner.getAddress(), is(UP_ADDRESS));
		assertThat(upgradeOwner.getCity(), is(UP_CITY));
		assertThat(upgradeOwner.getTelephone(), is(UP_TELEPHONE));

	}

}
