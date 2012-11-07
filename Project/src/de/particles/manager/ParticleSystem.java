package de.particles.manager;

import java.util.ArrayList;

import de.particles.datatypes.Particle;
import de.particles.generators.BaseEmitter;
import de.particles.math.Vector3;

public class ParticleSystem {
	ArrayList<BaseEmitter> emitters = new ArrayList<>();
	ArrayList<Particle> particles = new ArrayList<>();
	int maxParticles = 100;
	Vector3 gravity = Vector3.Zero();

	public ParticleSystem(int pMaxSystemParticles) throws Exception {
		if (pMaxSystemParticles < 0)
			throw new Exception("pMaxParticles cannot be smaller than 0");

		maxParticles = pMaxSystemParticles;
	}

	public void AddEmitter(BaseEmitter emitter) {
		emitters.add(emitter);
	}

	public void Update(double delta) {
		// Wenn nicht max particles erreicht ist dann erstelle neue particles
		if (particles.size() < maxParticles) {
			for (BaseEmitter emitter : emitters) {
				emitter.Update(delta);
			}
		}

		// Lebende Partikle update
		for (int index = 0; index < particles.size(); index++) {
			particles.get(index).Update(delta);
		}

		// Tote Partikle entfernen
		for (int i = particles.size() - 1; i >= 0; i--) {
			if (!particles.get(i).IsAlive()) {
				particles.remove(i);
			}
		}
	}

	public void Draw() {
		for (int index = 0; index < particles.size(); index++) {
			if (particles.get(index).IsAlive())
				particles.get(index).Draw();
		}
	}

	public void AddParticle(Particle entity) {
		this.particles.add(entity);
	}
}
