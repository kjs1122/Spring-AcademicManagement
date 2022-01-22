package com.academic.biz.student;

import java.util.List;

public interface StudentService {
	
	List<StudentVO> getStudentList();
	
	StudentVO getStudent(int num);
	
	void insertStudent(StudentVO vo);
	
	void updateStudent(StudentVO vo);
	
	void deleteStudent(StudentVO vo);
	
}
