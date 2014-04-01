package com.coursefeedback.feedbackmanager;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.coursefeedback.feedback.Feedback;

@ManagedBean
public class BasicFeedbackManager implements FeedbackManager {
	private EntityManager em = Persistence.createEntityManagerFactory(
			"CourseFeedback").createEntityManager();

	@Override
	public String saveFeedback(Feedback feedback) {
		this.em.getTransaction().begin();
		this.em.persist(feedback);
		this.em.getTransaction().commit();

		return "feedbackBar";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Feedback> getAllFeedbacks() {
		Query query = this.em.createQuery("SELECT f FROM Feedback f");
		return (Collection<Feedback>) query.getResultList();
	}
}