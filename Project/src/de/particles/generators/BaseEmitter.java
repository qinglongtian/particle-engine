package de.particles.generators;

import de.particles.manager.ParticleSystem;
import de.particles.math.Vector3;

public class BaseEmitter {
	boolean isEmitting = false;
	ParticleSystem system;
	int particlePerSecond = 10;
	Vector3 location = Vector3.Zero();

	public BaseEmitter(ParticleSystem system, Vector3 location, int particlePerSecond) {
		this.system = system;
		this.location = location;
		this.particlePerSecond = particlePerSecond;
	}

	public void Update(double delta) {
	}

	public void SetEmitting(boolean value) {
		isEmitting = value;
	}

	public boolean SetEmitting() {
		return isEmitting;
	}
}
