package com.etron.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.etron.models.Admin;
import com.etron.models.Role;
import com.etron.models.enums.AppRole;
import com.etron.repositories.AdminRepository;
import com.etron.repositories.RoleRepository;
import com.etron.repositories.UserRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	// private final PasswordEncoder passwordEncoder;

	public ResponseEntity<List<Admin>> getAllAdmins(String prenom) {

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

		if (userRepository.existsByEmail(admin.getEmail().toLowerCase())) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}

		var role = roleRepository.findByAppRole(AppRole.ADMIN)
				.orElseGet(() -> roleRepository.save(Role.builder().appRole(AppRole.ADMIN).build()));

		admin.setRole(role);
		/*
		 * admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		 * admin.setBeginDate(LocalDate.now());
		 */
		var a = adminRepository.save(admin);
		// admin.setStatus(ACTIVE);
		// admin.setEnabled(true);

		// var vToken = verificationTokenService.createVerificationToken(a);

		// mailService.sendMail(vToken);
//jjjjjjjj
		// return a;
		return null;
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
