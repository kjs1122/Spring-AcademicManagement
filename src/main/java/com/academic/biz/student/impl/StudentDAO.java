package com.academic.biz.student.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.academic.biz.common.JDBCUtil;
import com.academic.biz.student.StudentVO;

@Repository
public class StudentDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	public static final String STUDENT_LIST_BY_NUM = "select * from student where num=?";
	public static final String STUDENT_LIST = "select * from student";
	public static final String STUDENT_INSERT = "insert into student "
			+ "values((select nvl(max(num),0) + 1 from student), ?, ?, ?, ?, ?, ?)";
	// nvl(?,0) null인경우 0을 출력하고 널이아니면 num값중 제일 큰값 max에서 +1하라
	public static final String STUDENT_UPDATE = "update student set "
			+ "department=?, name=?, grade=?, age=?, phone=?, address=?";
	public static final String STUDENT_DELETE = "delete from student where num=?";

	public StudentVO getStudentListByNum(int num) {
		StudentVO vo = new StudentVO();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(STUDENT_LIST_BY_NUM);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				vo.setNum(rs.getInt("num"));
				vo.setDepartment(rs.getString("department"));
				vo.setName(rs.getString("name"));
				vo.setGrade(rs.getString("grade"));
				vo.setAge(rs.getInt("age"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
			}
		} catch (Exception e) {
			System.out.println("번호 조회 실패");
		}
		
		return vo;
	}
	
	public List<StudentVO> getStudentList() {
		List<StudentVO> studentList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(STUDENT_LIST);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StudentVO vo = new StudentVO();
				vo.setNum(rs.getInt("num"));
				vo.setDepartment(rs.getString("department"));
				vo.setName(rs.getString("name"));
				vo.setGrade(rs.getString("grade"));
				vo.setAge(rs.getInt("age"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				studentList.add(vo);
			}
		} catch (Exception e) {
			System.out.println("조회 실패");
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return studentList;
	}// getStudentList

	public void insertStudent(StudentVO vo) {

		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(STUDENT_INSERT);
			pstmt.setString(1, vo.getDepartment());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getGrade());
			pstmt.setInt(4, vo.getAge());
			pstmt.setString(5, vo.getPhone());
			pstmt.setString(6, vo.getAddress());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삽입 실패");
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}// insertStudent

	public void updateStudent(StudentVO vo) {

		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(STUDENT_UPDATE);
			pstmt.setString(1, vo.getDepartment());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getGrade());
			pstmt.setInt(4, vo.getAge());
			pstmt.setString(5, vo.getPhone());
			pstmt.setString(6, vo.getAddress());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("업데이트 실패");
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}// updateStudent
	
	public void deleteStudent(StudentVO vo) {
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(STUDENT_DELETE);
			pstmt.setInt(1, vo.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제 실패");
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}// deleteStudent

}
