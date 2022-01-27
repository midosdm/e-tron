package com.etron.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.etron.models.Admin;
import com.etron.models.Role;
import com.etron.models.enums.AppRole;
import com.etron.repositories.AdminRepository;
import com.etron.repositories.RoleRepository;
import com.etron.repositories.AppUserRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private RoleRepository roleRepository;

	private PasswordEncoder passwordEncoder;

	public ResponseEntity<List<Admin>> getAllAdmins() {

		try {
			List<Admin> admins = new ArrayList<Admin>();

			adminRepository.findAll().forEach(admins::add);

			if (admins.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(admins, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Admin> getAdminById(Long id) {
		Optional<Admin> admin = adminRepository.findById(id);

		if (admin.isPresent()) {
			return new ResponseEntity<>(admin.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Admin> createAdmin(Admin admin) {

		if (appUserRepository.existsByEmail(admin.getEmail().toLowerCase())) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}

		var role = roleRepository.findByAppRole(AppRole.ADMIN)
				.orElseGet(() -> roleRepository.save(Role.builder().appRole(AppRole.ADMIN).build()));

		admin.setRole(role);
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		var a = adminRepository.save(admin);

		//var vToken = verificationTokenService.createVerificationToken(a);


		return new ResponseEntity<>(a, HttpStatus.OK);
	}

	public ResponseEntity<Admin> updateAdmin(Long id, Admin newAdmin) {

		var oldAdmin = getAdminById(id).getBody();

		if (appUserRepository.existsByEmail(newAdmin.getEmail().toLowerCase())
				&& !newAdmin.getEmail().equals(oldAdmin.getEmail().toLowerCase())) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}

		oldAdmin.setEmail(newAdmin.getEmail());
		oldAdmin.setBirthDate(newAdmin.getBirthDate());
		oldAdmin.setFirstName(newAdmin.getFirstName());
		oldAdmin.setLastName(newAdmin.getLastName());
		var a = adminRepository.save(oldAdmin);
		//var vToken = verificationTokenService.createVerificationToken(a);
		return new ResponseEntity<>(a, HttpStatus.OK);
	}


	public void updatePassword(Long id, String password) {
		var encPassword = passwordEncoder.encode(password);
		adminRepository.updatePassword(id, encPassword);
	}


	public ResponseEntity<HttpStatus> deleteAdmin(Long id) {

		try {
			adminRepository.deleteById(id);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<HttpStatus> deleteAllAdmins() {
		try {
			adminRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
