package de.particles;

import java.io.IOException;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import de.particles.generators.CircleEmitter;
import de.particles.manager.ParticleSystem;
import de.particles.math.Vector3;

import static org.lwjgl.opengl.GL11.*;

public class Engine {

	private int windowWidth = 800;
	private int windowHeight = 800;

	Random random = new Random();
	Texture texture;

	double camZ = 0;
	float increment = 0;
	ParticleSystem particleSystem = null;

	// Under the class definition
	private static long lastFrame;

	public void Run() throws Exception {
		try {
			Display.setDisplayMode(new DisplayMode(windowWidth, windowHeight));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		particleSystem = new ParticleSystem(10000);
		particleSystem.AddEmitter(new CircleEmitter(particleSystem, new Vector3(-10, -30, -60), 300));
		particleSystem.AddEmitter(new CircleEmitter(particleSystem, new Vector3(10, 30, -60), 5));

		// In initialization code
		lastFrame = getTime();
		LoadContent();
		InitOpenGL();
		while (!Display.isCloseRequested()) {
			Update(getDelta());
			Render();
			Display.update();
			Display.sync(30);
		}

		Display.destroy();
	}

	private static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	private static double getDelta() {
		long currentTime = getTime();
		double delta = (double) currentTime - (double) lastFrame;
		lastFrame = getTime();
		return delta;
	}

	private void LoadContent() {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("content/textures/star.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private void InitOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);

		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		glClearDepth(1);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		GLU.gluPerspective(45.0f, windowWidth / windowHeight, 0.1f, 200.0f);
		glMatrixMode(GL_MODELVIEW);

		glEnable(GL_TEXTURE_2D);
		glEnable(GL_RGBA8);
		glLoadIdentity();
	}

	private void Render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		texture.bind();

		glLoadIdentity();
		glColor3f(1f, 1f, 1f);

		particleSystem.Draw();
	}

	private void Update(double delta) {
		particleSystem.Update(delta);
	}

}
