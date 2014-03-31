package com.coursefeedback.feedbackmanager;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.coursefeedback.feedback.Feedback;

@ManagedBean
public class BasicFeedbackManager implements FeedbackManager {
	@Override
	public void saveFeedback(Feedback feedback) {
		EntityManager em = Persistence.createEntityManagerFactory(
				"CourseFeedback").createEntityManager();

		em.getTransaction().begin();
		em.persist(feedback);
		em.getTransaction().commit();
	}
}