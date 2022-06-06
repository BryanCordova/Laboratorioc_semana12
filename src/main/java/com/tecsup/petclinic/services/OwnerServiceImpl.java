package com.tecsup.petclinic.services;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Owner;

import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;

@Service
public class OwnerServiceImpl implements OwnerService {

	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

	@Autowired
	OwnerRepository ownerRepository;
	
	@Override
	public void delete(Long id) throws OwnerNotFoundException {

		Owner own = findById(id);
		ownerRepository.delete(own);
	}
	
	@Override
	public Owner findById(long id) throws OwnerNotFoundException {
		Optional<Owner> own = ownerRepository.findById(id);

		if (!own.isPresent())
			throw new OwnerNotFoundException("Record not found...!");

		return own.get();
	}

	public Owner create(Owner owner) {
		return ownerRepository.save(owner);
	}

	public Owner update(Owner own) {
		return ownerRepository.save(own);
	}
	
	@Override
	public Iterable<Owner> findAll() {
		
		// TODO Auto-generated 
		return ownerRepository.findAll();
	}
}