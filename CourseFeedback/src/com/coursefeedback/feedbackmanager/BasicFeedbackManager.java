package com.coursefeedback.feedbackmanager;

import java.util.Collection;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import com.coursefeedback.courseitemmanager.CourseItem;
import com.coursefeedback.feedback.Feedback;

@ManagedBean(name = "feedbackManager")
public class BasicFeedbackManager implements FeedbackManager {
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

		return "course-item-page";
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
			e.printStackTrace();
		}

		return "course-item-page";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Feedback> getFeedbacksByCourseItemId(int courseItemId) {
		Collection<Feedback> feedbacks = this.em
				.createQuery(
						"SELECT f FROM Feedback f WHERE f.courseItem.courseItemId = :courseItemId")
				.setParameter("courseItemId", courseItemId).getResultList();

		return feedbacks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Feedback> getAllFeedbacks() {
		Query query = this.em.createQuery("SELECT f FROM Feedback f");
		return (Collection<Feedback>) query.getResultList();
	}
}