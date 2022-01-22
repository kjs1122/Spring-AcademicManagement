package com.academic.biz.sutdent;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.academic.biz.config.RootConfig;
import com.academic.biz.student.StudentService;
import com.academic.biz.student.StudentVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class StudentServiceClient {
	
	@Test
	public void test() {
	
		AbstractApplicationContext contatiner = new AnnotationConfigApplicationContext(RootConfig.class);
		
		StudentService studentService = (StudentService)contatiner.getBean("studentService");
		
		StudentVO vo = new StudentVO();
		
//		vo.setName("김지수");
//		vo.setDepartment("식품영양과");
//		vo.setAge(31);
//		vo.setAddress("서울시 구로구");
//		vo.setPhone("010-4953-3653");
//		vo.setGrade("졸업");
//		studentService.insertStudent(vo);
		
		//조회
		List<StudentVO> sutdentList = studentService.getStudentList();
		
		Iterator<StudentVO> iter = sutdentList.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		vo = studentService.getStudent(1);
		System.out.println(vo);
		
		
		vo.setName("김대영");
		
		studentService.updateStudent(vo);
		
		vo = studentService.getStudent(1);
		System.out.println(vo);
		
		studentService.deleteStudent(vo);
		
		vo = studentService.getStudent(1);
		System.out.println(vo);
	
		contatiner.close();
	}


}
