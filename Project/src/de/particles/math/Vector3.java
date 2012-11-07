package de.particles.math;

public class Vector3 {
	public float x, y, z;

	public Vector3(float px, float py, float pz) {
		x = px;
		y = py;
		z = pz;
	}

	public Vector3(double px, double py, double pz) {
		x = (float) px;
		y = (float) py;
		z = (float) pz;
	}

	public static Vector3 Zero() {
		return new Vector3(0, 0, 0);
	}

	public void Add(Vector3 value) {
		x += value.x;
		y += value.y;
		z += value.z;
	}

	public void Multiply(float value) throws Exception {
		throw new Exception();
	}
}
