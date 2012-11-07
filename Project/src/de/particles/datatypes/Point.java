package de.particles.datatypes;

public class Point extends Particle {
	public float x, y, z;

	public Point(float px, float py, float pz) {
		this.x = px;
		this.y = py;
		this.z = pz;
	}

	@Override
	public void Update(double delta) {
	}

	@Override
	public void Draw() {
	}
}