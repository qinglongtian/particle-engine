package de.particles.datatypes;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex3f;
import de.particles.math.Vector3;

public class Quad extends Particle {

	public float w, h;

	public Quad(Vector3 location, float pw, float ph) {
		this.location = location;
		this.rotation = Vector3.Zero();
		this.w = pw;
		this.h = ph;
	}

	public Quad(Vector3 location, float pw, float ph, Vector3 rotation) {
		this.location = location;
		this.rotation = rotation;
		this.w = pw;
		this.h = ph;
	}

	public Quad(Vector3 location, float pw, float ph, Vector3 rotation, Vector3 velocity) {
		this.location = location;
		this.rotation = rotation;
		this.velocity = velocity;
		this.w = pw;
		this.h = ph;
	}

	public void Draw() {
		glPushMatrix();
		glLoadIdentity();

		glTranslated(this.location.x, this.location.y, this.location.z);
		glRotatef(this.rotation.x, 1, 0, 0);
		glRotatef(this.rotation.y, 0, 1, 0);
		glRotatef(this.rotation.z, 0, 0, 1);

		glBegin(GL_QUADS);
		glVertex3f(-w, -h, this.location.z);
		glTexCoord2f(0, 0);

		glVertex3f(w, -h, this.location.z);
		glTexCoord2f(1, 0);

		glVertex3f(w, h, this.location.z);
		glTexCoord2f(1, 1);

		glVertex3f(-w, h, this.location.z);
		glTexCoord2f(0, 1);
		glEnd();

		glPopMatrix();
	}

	public void Update(double delta) {
		this.rotation.z += (0.1f * delta);
		this.location = new Vector3(location.x + (velocity.x * delta), location.y + (velocity.y * delta), location.z + (velocity.z * delta));

		super.Update(delta);

	}

}