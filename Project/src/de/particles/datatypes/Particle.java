package de.particles.datatypes;

import de.particles.math.Vector3;

public class Particle {
	float life = 100;

	public Vector3 location;
	public Vector3 velocity;

	public Vector3 rotation;
	public Vector3 rotationVelocity;

	public void Update(double delta) {
		life -= (0.06 * delta);
	}

	public void Draw() {

	}

	public boolean IsAlive() {
		return life > 0;
	}
}
