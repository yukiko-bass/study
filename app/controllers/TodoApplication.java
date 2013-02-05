package controllers;

import models.Task;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.task;

public class TodoApplication extends Controller {

	static Form<Task> taskForm = form(Task.class);

	public static Result tasks() {
		return ok(task.render(Task.all(), taskForm));
	}

	public static Result newTask() {
		Form<Task> filledForm = taskForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(task.render(Task.all(), filledForm));
		} else {
			Task.create(filledForm.get());
			return redirect(routes.TodoApplication.tasks());
		}
	}

	public static Result deleteTask(String id) {
		Task.delete(id);
	    return redirect(routes.TodoApplication.tasks());
	}

}
