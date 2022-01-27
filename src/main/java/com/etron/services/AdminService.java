package com.etron.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.etron.exceptions.MessageResponse;
import com.etron.models.Admin;
import com.etron.models.Role;
import com.etron.models.enums.AppRole;
import com.etron.repositories.AdminRepository;
import com.etron.repositories.AppUserRepository;
import com.etron.repositories.RoleRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	public AdminService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public ResponseEntity<?> getAllAdmins() {

		try {
			List<Admin> admins = new ArrayList<Admin>();

			adminRepository.findAll().forEach(admins::add);

			if (admins.isEmpty()) {
				return new ResponseEntity<>(admins, HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(admins, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
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

	public ResponseEntity<?> createAdmin(Admin admin) {

		if (appUserRepository.existsByEmail(admin.getEmail().toLowerCase())) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new MessageResponse("Warn: cet email existe déja"));
		}

		var role = roleRepository.findByAppRole(AppRole.ADMIN)
				.orElseGet(() -> roleRepository.save(Role.builder().appRole(AppRole.ADMIN).build()));

		admin.setRole(role);
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		var a = adminRepository.save(admin);

		// var vToken = verificationTokenService.createVerificationToken(a);

		return new ResponseEntity<>(a, HttpStatus.OK);
	}

	public ResponseEntity<?> updateAdmin(Long id, Admin newAdmin) {

		var oldAdmin = getAdminById(id).getBody();

		if (appUserRepository.existsByEmail(newAdmin.getEmail().toLowerCase())
				&& !newAdmin.getEmail().equals(oldAdmin.getEmail().toLowerCase())) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new MessageResponse("Warn: cet email existe déja"));
		}

		oldAdmin.setEmail(newAdmin.getEmail());
		oldAdmin.setBirthDate(newAdmin.getBirthDate());
		oldAdmin.setFirstName(newAdmin.getFirstName());
		oldAdmin.setLastName(newAdmin.getLastName());
		var a = adminRepository.save(oldAdmin);
		// var vToken = verificationTokenService.createVerificationToken(a);
		return new ResponseEntity<>(a, HttpStatus.OK);
	}

	public void updatePassword(Long id, String password) {
		var encPassword = passwordEncoder.encode(password);
		adminRepository.updatePassword(id, encPassword);
	}

	public ResponseEntity<?> deleteAdmin(Long id) {

		try {
			adminRepository.deleteById(id);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> deleteAllAdmins() {
		try {
			adminRepository.deleteAll();
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new MessageResponse("Warn: Aucun admin dans la BDD"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

}
