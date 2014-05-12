package com.coursefeedback.teacher.teachermanager;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.coursefeedback.course.Course;
import com.coursefeedback.teacher.Teacher;

@ManagedBean(name = "teacherManager")
public class BasicTeacherManager implements TeacherManager {
	private static final String TEACHER_PROFILE = "teacherProfile";

	@PersistenceContext(name = "CourseFeedback")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	@Override
	public String addCourseToTeacher(Course course, String teacherUserName) {
		System.out.println(teacherUserName);
		try {
			this.utx.begin();
			Teacher teacher = getTeacherByUserName(teacherUserName);
			teacher.addCourse(course);
			this.utx.commit();
		} catch (Exception e) {
			try {
				utx.rollback();
			} catch (Exception e1) {
			}
		}

		return TEACHER_PROFILE;
	}

	@Override
	public Teacher getTeacherByUserName(String userName) {
		return this.em.find(Teacher.class, userName);
	}
}
