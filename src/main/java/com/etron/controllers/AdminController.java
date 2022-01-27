package com.etron.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etron.dto.AdminDto;
import com.etron.dto.AdminDtoWithoutPassword;
import com.etron.dto.UserPasswordDto;
import com.etron.models.Admin;
import com.etron.services.AdminService;

@RestController
@RequestMapping(path = "/api/v1/admins")

public class AdminController {

	@Autowired
	private AdminService adminService;

	private ModelMapper modelmapper;

	private AdminDto convertToDto(Admin admin) {
		return modelmapper.map(admin, AdminDto.class);
	}

	private Admin convertToEntity(AdminDto adminDto) {
		return modelmapper.map(adminDto, Admin.class);
	}

	private Admin convertToEntity(AdminDtoWithoutPassword adminDto) {
		return modelmapper.map(adminDto, Admin.class);
	}

	@GetMapping
	public List<AdminDto> getAllAdmins() {
		List<Admin> adminList = adminService.getAllAdmins().getBody();
		return adminList.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping(path = "{adminId}")
	public AdminDto getAdminById(@PathVariable("adminId") Long adminId) {
		var admin = adminService.getAdminById(adminId).getBody();
		return convertToDto(admin);
	}

	@PostMapping
	public AdminDto createAdmin(@Valid @RequestBody AdminDto newAdminDto) {
		var admin = convertToEntity(newAdminDto);
		var newAdmin = adminService.createAdmin(admin).getBody();
		return convertToDto(newAdmin);
	}

	@PutMapping(path = "{adminId}")
	public AdminDto updateAdmin(@PathVariable("adminId") Long adminId,
			@Valid @RequestBody AdminDtoWithoutPassword adminDto) {
		var admin = convertToEntity(adminDto);
		var newAdmin = adminService.updateAdmin(adminId, admin).getBody();
		return convertToDto(newAdmin);
	}

	@PutMapping(path = "{adminId}/password")
	public void updatePassword(@PathVariable("adminId") Long adminId,
			@Valid @RequestBody UserPasswordDto userPasswordDto) {
		adminService.updatePassword(adminId, userPasswordDto.getPassword());
	}

	@DeleteMapping(path = "{adminId}")
	public void deleteAdmin(@PathVariable("adminId") Long adminId) {
		adminService.deleteAdmin(adminId);
	}

	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteAllAdmins() {
		return adminService.deleteAllAdmins();
	}

}
