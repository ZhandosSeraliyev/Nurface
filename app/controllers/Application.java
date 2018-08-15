package controllers;

import models.Doc;
import models.User;
import org.apache.commons.io.FileUtils;
import play.Logger;
import play.Play;
import play.mvc.*;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Application extends Controller {

	public static void index() {
		render();
	}

	public static void login() {
		String error = flash.get("error");
		render(error);
	}

	public static void authorize(String first, String password) {
		User user = User.find("first = ?1 and password = ?2", first, password).first();
		if (user != null) {
			session.put("id", user.id);
			myacc();
		} else {
			login();
		}
	}

	public static void myacc() {
		String error = flash.get("error");
		render(error);
	}
	public static void Calendar() {
		String error = flash.get("error");
		render(error);
	}
	public static void NPA() {
		String error = flash.get("error");
		render(error);
	}

	public static void makeRegistration(String first, String second, String nomer, String pass) {
		User user = new User();
		user.first = first;
		user.second = second;
		user.password = pass;
		user.save();

		login();
	}

	public static void NPA(String errorMessage) {
		List<Doc> docs = Doc.findAll();
		render(errorMessage, docs);
	}

	public static void upload(File file) {
		User user = User.findById(Integer.parseInt(session.get("id")));
		Doc doc = Doc.find("name = ?1", file.getName()).first();
		if (doc != null) {
			NPA("Используйте другое название");
		} else {
			doc = new Doc();
			doc.name = file.getName();

			String destFolder = Play.configuration.getProperty("file.upload.path");
			String destFile = destFolder + File.separator + file.getName();
			try {
				FileUtils.moveFile(file, new File(destFile));
			} catch (IOException e) {
				Logger.info(e.getMessage() + " ERROR");
			}


			doc.save();
		}

		NPA(null);
	}

	public static void download(Long docId) {
		Doc doc = Doc.findById(docId);
		String destFolder = Play.configuration.getProperty("file.upload.path");
		String destFile = destFolder + File.separator + doc.name;
		File file = new File(destFile);

		if (file.exists()) {
			renderBinary(new File(destFile));
		}
		renderText("error");
	}
}