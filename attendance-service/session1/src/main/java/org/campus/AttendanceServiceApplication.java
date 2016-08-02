package org.campus;

import org.campus.domain.Attendance;
import org.campus.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class AttendanceServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(AttendanceServiceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(AttendanceServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AttendanceRepository attendanceRepository, StudentRepository studentRepository) {
		return (args) -> {
			// save a couple of Attendances
			Student indrajeet = new Student();
			indrajeet.setName("indrajeet");
			studentRepository.save(indrajeet);
			Attendance attendance = new Attendance("20160726", "COMP-BE");
			attendanceRepository.save(attendance);

			// fetch all Attendances
			log.info("Attendances found with findAll():");
			log.info("-------------------------------");
			for (Attendance Attendance : attendanceRepository.findAll()) {
				log.info(Attendance.toString());
			}
			log.info("");

			// fetch an individual Attendance by ID
			Attendance Attendance = attendanceRepository.findOne(1L);
			log.info("Attendance found with findOne(1L):");
			log.info("--------------------------------");
			log.info(Attendance.toString());
			log.info("");
		};
	}
}
