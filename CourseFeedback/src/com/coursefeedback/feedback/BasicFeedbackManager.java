package com.coursefeedback.feedback;

import java.util.Collection;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.coursefeedback.courseitem.CourseItem;

/**
 * Basic implementation of FeedbackManager
 * 
 * @author touzbi
 */
@ManagedBean(name = "feedbackManager")
public class BasicFeedbackManager implements FeedbackManager {
	private static final String COURSE_ITEM_VIEW = "course-item-page";

	@PersistenceContext(name = "CourseFeedback")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	@Override
	public String addFeedback(Feedback feedback) {
		try {
			this.utx.begin();
			this.em.persist(feedback);
			this.utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return COURSE_ITEM_VIEW;
	}

	@Override
	public String addFeedbackToCourseItem(Feedback feedback, int courseItemId) {
		try {
			this.utx.begin();
			CourseItem courseItem = this.em
					.find(CourseItem.class, courseItemId);
			courseItem.addFeedback(feedback);
			this.utx.commit();
		} catch (Exception e) {
			// XXX To do better
			e.printStackTrace();
		}

		return COURSE_ITEM_VIEW;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Feedback> getFeedbacksByCourseItemId(int courseItemId) {
		return this.em
				.createQuery(
						"SELECT f FROM Feedback f WHERE f.courseItem.courseItemId = :courseItemId")
				.setParameter("courseItemId", courseItemId).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Feedback> getAllFeedbacks() {
		return this.em.createQuery("SELECT f FROM Feedback f").getResultList();
	}
}