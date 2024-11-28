package com.tus.coupon.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tus.coupon
.model.Coupon;
import com.tus.coupon.repo.CouponRepo;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

	@Autowired
	CouponRepo repo;

	@PostMapping(value = "/coupons")
	public ResponseEntity<Coupon> create(@RequestBody Coupon coupon) {
		return new ResponseEntity<>(repo.save(coupon), HttpStatus.OK);
	}

	@GetMapping("/coupons/{code}")
	Coupon getCouponByCouponCode(@PathVariable String code) {
		System.out.println(code);
			return repo.findByCode(code);
	}

	@GetMapping(value = "/coupons")
	public List<Coupon> getAllCoupons() {
		return repo.findAll();
	}

	@GetMapping(value = "/generateload")
	public ResponseEntity<Coupon> generateLoad() {

		ObjectMapper objectMapper = new ObjectMapper();

		Coupon dummyData = new Coupon();

		System.out.println("Generating load..");

        for (int i = 0; i < 5000; i++) {
            try {
				ClassPathResource resource = new ClassPathResource("data.json");
				InputStream inputStream = resource.getInputStream();
        		dummyData = objectMapper.readValue(inputStream, Coupon.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
		}

		System.out.println("Done");

			return new ResponseEntity<>(dummyData, HttpStatus.OK);
	}

}
