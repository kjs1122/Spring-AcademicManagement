package com.academic.biz.student.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academic.biz.student.StudentService;
import com.academic.biz.student.StudentVO;


@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	public List<StudentVO> getStudentList() {
	
		return studentDAO.getStudentList();
	}

	@Override
	public StudentVO getStudent(int num) {
		return studentDAO.getStudentListByNum(num);
	}
	@Override
	public void insertStudent(StudentVO vo) {
		studentDAO.insertStudent(vo);		
	}

	@Override
	public void updateStudent(StudentVO vo) {
		studentDAO.updateStudent(vo);
		
	}

	@Override
	public void deleteStudent(StudentVO vo) {
		studentDAO.deleteStudent(vo);
		
	}

}
