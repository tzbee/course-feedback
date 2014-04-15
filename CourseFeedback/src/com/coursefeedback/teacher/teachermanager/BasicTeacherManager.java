package com.coursefeedback.teacher.teachermanager;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.coursefeedback.coursemanager.Course;
import com.coursefeedback.teacher.Teacher;

@ManagedBean
public class BasicTeacherManager implements TeacherManager {

	@PersistenceContext(name = "CourseFeedback")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	@Override
	public String addCourseToTeacher(Course course, Teacher teacher) {

		try {
			this.utx.begin();
			this.em.persist(course);
			teacher.addCourse(course);
			this.em.merge(teacher);
			this.utx.commit();
		} catch (Exception e) {
			try {
				utx.rollback();
			} catch (Exception e1) {
			}
		}

		return "teacher-profile";
	}
}
