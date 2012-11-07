package de.particles.generators;

import de.particles.datatypes.Quad;
import de.particles.manager.ParticleSystem;
import de.particles.math.Vector3;

public class CircleEmitter extends BaseEmitter {

	double elapsedTime = 0;
	double currentAngle = 0;

	public CircleEmitter(ParticleSystem system, Vector3 location, int particlePerSecond) {
		super(system, location, particlePerSecond);
	}

	@Override
	public void Update(double delta) {
		elapsedTime += delta;

		if (elapsedTime > (1000 / particlePerSecond)) {
			system.AddParticle(new Quad(this.location, 1, 1, Vector3.Zero(), new Vector3(Math.sin(currentAngle) / 100, Math.cos(currentAngle) / 100, 0)));
			
			elapsedTime = 0;

			currentAngle += (360 / particlePerSecond);
			if (currentAngle > 360)
				currentAngle = 0;
		}
	}
}
